import json

courses = []

with open("courses.json", "r") as courses_json:
    courses = json.load(courses_json)

professors = []

for course in courses:
    for section in course["sections"]:
        professor_name = section["professor_name"]
        if not any(professor["name"] == professor_name for professor in professors):
            professor_dict = {
                "name": professor_name,
                "quality": 0
            }
            professors.append(professor_dict)

with open("professors.json", "w") as professors_json:
    json.dump(professors, professors_json, indent=2)