import json
import pandas
import os

usersCSVFN = 'app/seed_data/users.csv'
skillsCSVFN = 'app/seed_data/skills.csv'
hackersCSVFN = 'app/seed_data/hackers.csv'

fileNames = [usersCSVFN, skillsCSVFN, hackersCSVFN]

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

	jsonCsvMapping = [
		[users, usersCSVFN],
		[hackers, hackersCSVFN],
		[hackerSkills, skillsCSVFN],
	]

	for jsonToCsv in jsonCsvMapping:
		jsonStr = pandas.read_json(jsonToCsv[0])
		jsonStr.to_csv(jsonToCsv[1], index=False)

	print("CONVERTED JSON FILES TO CSV")

	print("SUCCESS")
			
		