import json
import pandas
import os

usersJsonFN = 'app/seed_data/users.json'
skillsJsonFN = 'app/seed_data/skills.json'
hackersJsonFN = 'app/seed_data/hackers.json'

usersCSVFN = 'app/seed_data/users.csv'
skillsCSVFN = 'app/seed_data/skills.csv'
hackersCSVFN = 'app/seed_data/hackers.csv'

fileNames = [usersJsonFN, skillsJsonFN, hackersJsonFN, usersCSVFN, skillsCSVFN, hackersCSVFN]

for fn in fileNames:
	os.makedirs(os.path.dirname(fn), exist_ok=True)

print("STARTING")

with open('app/HTN_2023_BE_Challenge_Data.json') as f:
	print("OPENED RAW JSON DATA")

	data = json.loads(f.read())
	
	users, hackerSkills, hackers = [], [], []
	userId, hackerId, skillId = 1, 1, 1
	
	for el in data:
		users.append({
			'id': userId,
			'name': el['name'],
			'email': el['email'],
			'phone': el['phone'],
		})
		
		hackers.append({
			'id': hackerId,
			'company': el['company'],
			'userId': userId,
		})
		
		for skill in el['skills']:
			hackerSkills.append({
				'id': skillId,
				'skill': skill['skill'],
				'rating': skill['rating'],
				'hackerId': hackerId,
			})
			skillId += 1

		userId += 1
		hackerId += 1
	
	print("PARSED RAW JSON DATA")

	jsonStrToJsonFileMapping = [
    	[users, usersJsonFN],
        [hackers, hackersJsonFN],
        [hackerSkills, skillsJsonFN],
    ]

	for jsonToJsonFile in jsonStrToJsonFileMapping:
		with open(file=jsonToJsonFile[1], mode='w') as fd:
			json.dump(jsonToJsonFile[0], fd)

    
	print("DUMPED PARSED JSON TO FILES")

	jsonCsvMapping = [
		[usersJsonFN, usersCSVFN],
		[hackersJsonFN, hackersCSVFN],
		[skillsJsonFN, skillsCSVFN],
	]

	for jsonToCsv in jsonCsvMapping:
		jsonStr = pandas.read_json(jsonToCsv[0])
		jsonStr.to_csv(jsonToCsv[1], index=False)

	print("CONVERTED JSON FILES TO CSV")

	print("SUCCESS")
			
		