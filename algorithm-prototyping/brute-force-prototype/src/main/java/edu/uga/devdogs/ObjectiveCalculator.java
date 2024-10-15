package edu.uga.devdogs;

public class ObjectiveCalculator {

    private static float computeAverageProfessorQuality(Schedule schedule) {
        float averageQuality;
        int numberOfSections = schedule.sections().length();

        // Used for caluclation of the average professor quality.
        for (int i = 0; i < numberOfSections; i++) {
            averageQuality += schedule.sections[i].professor.quality;

        }
        averageQuality /= numberOfSections;

        return averageQuality;
    }

    private static float computeMaxDistance(Schedule schedule) {

    }

    private static float computeAverageIdleTime(Schedule schedule) {

    }

    public static float computeOverallObjective(Schedule schedule, Float[] weights) {

    }

}
