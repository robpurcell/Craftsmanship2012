package com.teamdojo

class ProgrammerParser {

	def processInput(def xml) {
		def records = new XmlSlurper().parseText(xml)

		createProgrammers(records)
	}

	def createProgrammers(def records) {
		def programmers = []
		for (programmer in records.Programmer) {
			programmers.add(new Programmer(name: programmer.@name.text(),
					skills: createSkills(programmer.Skills),
					recommendations: createRecommendations(programmer.Recommendations)))
		}
		return programmers
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
