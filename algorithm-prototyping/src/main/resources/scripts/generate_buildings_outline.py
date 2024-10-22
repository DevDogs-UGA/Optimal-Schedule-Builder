import json

courses = []

with open("courses.json", "r") as courses_json:
    courses = json.load(courses_json)

buildings = []

for course in courses:
    for section in course["sections"]:
        for _class in section["classes"]:
            building_name = _class["building_name"]
            if not any(building["name"] == building_name for building in buildings):
                building_dict = {
                    "name": building_name,
                    "x": 0,
                    "y": 0
                }
                buildings.append(building_dict)

with open("buildings.json", "w") as buildings_json:
    json.dump(buildings, buildings_json, indent=2)