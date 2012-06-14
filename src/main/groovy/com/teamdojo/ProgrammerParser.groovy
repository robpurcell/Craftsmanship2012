package com.teamdojo

class ProgrammerParser {
	
	def processInput(def xml) {
		def records = new XmlSlurper().parseText(xml)
		
		createProgrammers(records)
	}
	
	def createProgrammers(def records) {
		//records.Programmer[0]
		def programmer = new Programmer(name: records.Programmer[0].@name.text(),
			skills: createSkills(records.Programmer[0].Skills),
			recommendations: createRecommendations(records.Programmer[0].Recommendations))
		return [programmer]
	}
	
	def createSkills(def skills) {
		def result = []
		for (def skill in skills.Skill) {
			result.add(skill)	
		}
		return result
	}
	
	def createRecommendations(def recommendations) {
		def result = []
		for (def recommendation in recommendations.Recommendation) {
			result.add(recommendation)
		}
		return result
	}

}
