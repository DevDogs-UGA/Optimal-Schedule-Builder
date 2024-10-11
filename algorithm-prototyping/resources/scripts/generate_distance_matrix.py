import json

# Read buildings.json into a dictionary
with open('buildings.json', 'r') as file:
    data = json.load(file)

# Initialize output dictionary with buildings array and distance matrix
dict = {
    'buildings': [],
    'distance_matrix': []
}

#Calculate distance matrix and updatre optuput dictionary
for building1 in data['buildings']:
    dict['buildings'].append(building1['name'])
    dict['distance_matrix'].append([])

    for building2 in data['buildings']:
        dist = ((building1['x']-building2['x']) ** 2 + (building1['y']-building2['y']) ** 2) ** 0.5
        dict['distance_matrix'][-1].append(dist)

# Serializing json
output = json.dumps(dict, indent=4)
 
# Writing to distance_matrix.json
with open('distance_matrix.json', 'w') as file:
    file.write(output)
