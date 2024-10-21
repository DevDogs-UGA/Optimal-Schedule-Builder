import json

# Read buildings.json and store data as a dictionary
with open('buildings.json', 'r') as file:
    data = json.load(file)

# Initialize output dictionary with buildings array and distance matrix
distanceData = {
    'buildings': [],
    'distance_matrix': []
}

#Calculate distance matrix and update output dictionary
for building1 in data['buildings']:
    distanceData['buildings'].append(building1['name'])
    distanceData['distance_matrix'].append([])

    for building2 in data['buildings']:
        # Calculate distance between two buildings using formula: distance = sqrt(a^2 + b^2)
        # Where 'a' is the difference in x-coordinates and 'b' is the difference in y-coordinates
        dist = ((building1['x']-building2['x']) ** 2 + (building1['y']-building2['y']) ** 2) ** 0.5
        
        distanceData['distance_matrix'][-1].append(dist)

# Serializing json
output = json.dumps(distanceData, indent=4)
 
# Writing to distance_matrix.json
with open('distance_matrix.json', 'w') as file:
    file.write(output)
