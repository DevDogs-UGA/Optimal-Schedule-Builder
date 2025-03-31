package edu.uga.devdogs.course_information.webscraping;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pdf {
    /**
     * returns a list of all course parsed from pdf
     * @param semester fall, spring, summer
     * @return
     */
    public static List<Course2> parsePdf(String semester, String downloadDirectory) {
        String pdfLocation = downloadDirectory + semester + ".pdf";
        try {
            Files.createFile(Paths.get(pdfLocation)); //creates the file if doesnt already exist
        } catch (Exception e)  {
            //empty catch to ignore when the file already exists. will overwrite
        }

        try {
            downloadPDF("https://apps.reg.uga.edu/soc/SOC" + semester + ".pdf", pdfLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Course2> courses = new ArrayList<>();
        try {
            // Load the PDF document
            PDDocument doc = Loader.loadPDF(new File(pdfLocation)); // loads the pdf

            // Create PDFTextStripper object
            PDFTextStripper pdfStripper = new PDFTextStripper() ;
            pdfStripper.setSortByPosition(true); //makes sure the parser doesnt jumble the pdf


            String text = "";
            int numPages = doc.getNumberOfPages();
            int topOffset = 0;
            int bottomOffset = 0;
            for (int i = 1; i <= numPages; i++) {
                pdfStripper.setStartPage(i);
                pdfStripper.setEndPage(i); // set start and stop to same page so only one page is precessed per loop

                text = pdfStripper.getText(doc);

                topOffset = 2; //strip non data lines off top and bottom
                bottomOffset = (int) text.lines().count() - 2;
                if (i == 1) { //page one has extra useless lines
                    topOffset = 4;
                }
                if (i == numPages) { //last page has extra useless lines
                    bottomOffset = (int) text.lines().count() - 4;
                }

                List<Course2> c = parsePage(text, topOffset, bottomOffset);

                courses.addAll(c);
                //System.out.println(text);
            }
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
        Files.deleteIfExists(Paths.get(pdfLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    } //parsePdf

    /**
     * takes in a pdf page in string format and returns a list of course object parsed from the page
     * @param page a pdf page as a string as parsed by pdfstripper
     * @param topOffset the amount of lines to take off the top of the page
     * @param bottomOffset the amount of lines to take off the bottom of the page
     * @return List of Course objects
    */
    public static List<Course2> parsePage(String page, int topOffset, int bottomOffset) {
        List<String> pageList = new ArrayList<String>(Arrays.asList(page.split("\n")));
        pageList = pageList.subList(topOffset, bottomOffset); //strips unnecessary data

        String header = ""; // subject, course number, course title, department
        String prevContent = "";
        String currContent = ""; // crn, sec, stat, credit hours, days, time, building, room, campus, instructor, part of term, class size, seats available
        List<Course2> courseList = new ArrayList<>();

        for (var elem : pageList) {
            prevContent = currContent; //some cases the current line is missing data so keep memory of last line
            if (isAllLetters(elem.substring(0, 4))) { //header is start with subject like csci. content will always start with a number, either crn or sec
                header = elem;
                continue; //set a header then continue to set its children
            }

            currContent = elem;
            if (currContent.substring(0, currContent.indexOf(" ")).length() < 5) { //in some cases the parser misses the sec column so take it from the previous entry
                currContent = prevContent.substring(0, prevContent.indexOf(".")) + currContent.substring(currContent.indexOf("."));
            }

            Course2 c = parseCourseString(header, currContent);
            courseList.add(c);
        }
        return courseList;
    } //parsePage


    /**
     * returns a course object from a given course header and course content parsed by pdftextstripper
     * @param header a string containing subject, course number, course title, department
     * @param content a string containing crn, sec, stat, credit hours, days, time, building, room, campus, instructor, part of term, class size, seats available
     * @return a course object
     */
    public static Course2 parseCourseString(String header, String content) {
        List<String> headerList = new ArrayList<String>(Arrays.asList(header.split(" ")));
        List<String> contentList = new ArrayList<String>(Arrays.asList(content.split(" ")));


        //variables from header
        String subject = headerList.get(0);
        String number = headerList.get(1);
        String title = String.join(" ", headerList.subList(2, headerList.size()));
        String department = title; //dont have a way to tell differnce right now


        if (contentList.get(1).equalsIgnoreCase("A") || contentList.get(1).equalsIgnoreCase("X")) {
            //SEC (Section):  This is an optional field used by departments to uniquely identify individual sections of the same course. <-- from https://reg.uga.edu/enrollment-and-registration/schedule-of-classes/
            //found in testing that when sec is missing it should mean that the course is TBA
            return new Course2(subject,
                    number,
                    title,
                    department,
                    Integer.parseInt(contentList.get(0)),
                    "",
                    contentList.get(1),
                    String.join("", contentList.subList(2, 5)),
                    "TBA",
                    "TBA",
                    "TBA",
                    "TBA",
                    contentList.get(contentList.size() - 4),
                    "",
                    "",
                    0,
                    0);
        }

        //variables from content
        int crn = Integer.parseInt(contentList.get(0));
        String sec = contentList.get(1);
        String stat = contentList.get(2);
        String creditHours = String.join("", contentList.subList(3, 6));
        String meetingDays = ""; // the following values arent always in the same location so it is set below
        String meetingTime = "";
        String building = "";
        String roomNumber = "";
        String campus = "";
        String professor = "";
        String partOfTerm = contentList.get(contentList.size() - 3); // sometimes can be replaced by a professors name if it overlaps in the pdf
        int classSize = Integer.parseInt(contentList.get(contentList.size() - 2));
        int seatsAvailable = Integer.parseInt(contentList.get(contentList.size() - 1).trim());


        //should now only contain meeting days, meeting time, building room number, campus and professor.
        contentList = contentList.subList(6, contentList.size() - 3);

        boolean stateMeetingDays = true; //true if parsing for meeting days, there could be no meeting days if stat is X
        int ampmCount = 0;
        boolean stateMeetingTimes = false; //true if parsing for meeting times
        int count = 0;
        boolean stateBuildingRoomCampus = false; //true if parsing for building, meeting room, and campus
        for(var elem : contentList) {
            if (stateMeetingDays) {
                if (isAllLetters(elem)) { //meeting days can only be M, T, W, R, F
                    meetingDays = meetingDays + elem;
                } else {
                    stateMeetingDays = false;
                    stateMeetingTimes = true;
                }
            } //if

            if (stateMeetingTimes) {
                //we know we have gotten the full meeting time one we have seen the second am/pm
                //if class is tba then times will be empty
                if (ampmCount >=2 ) {
                    stateMeetingTimes = false;
                    stateBuildingRoomCampus = true;
                } else {
                    meetingTime = meetingTime + elem;
                    if (elem.contains("am") || elem.contains("pm")) {
                        ampmCount++;
                    }
                }
            } //if

            if (stateBuildingRoomCampus) { //set the values for building, room, and campus
                if (count >= 3) {
                    stateBuildingRoomCampus = false;
                } else {
                    if (count == 0) {
                        building = elem;
                    } else if(count == 1) {
                        roomNumber = elem;
                    } else {
                        campus = elem;
                    }
                    count++;
                }
            } //if

            if (!(stateMeetingDays || stateMeetingTimes || stateBuildingRoomCampus)) { //the remaining of the string is professor name. sometimes the full name is given instead of only the last name
                professor = professor + elem  + " ";
            }

        } //for


        return new Course2(subject,
                number,
                title,
                department,
                crn,
                sec,
                stat,
                creditHours,
                meetingDays,
                meetingTime,
                building,
                roomNumber,
                campus,
                professor,
                partOfTerm,
                classSize,
                seatsAvailable);
    } //parseCourseString

    /**
     * returns true if every char in s is a letter in the alphabet
     * @param s string to be checked
     * @return true if all letters, false if at least 1 non letter
     */
    public static boolean isAllLetters(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    } //isAllLetters


    //-------------------------------------------------------------------------------------------------------
    /**
     * <a href="https://asyncq.com/how-to-build-httpclient-in-java-to-download-file">...</a>
     * downloads and saves a pdf file to the computer
     * @param uri uri where the pdf is located
     * @param fileLocation where to save the file
     * @throws IOException stuff with io
     * @throws InterruptedException if http times out
     */
    public static void downloadPDF(String uri, String fileLocation) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<InputStream> response =  HttpClient
                .newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofInputStream());

        //System.out.println("status code: " +response.statusCode());
        InputStream body = response.body();

        FileOutputStream fos = new FileOutputStream(fileLocation);
        fos.write(body.readAllBytes());
        fos.close();
    }
}
