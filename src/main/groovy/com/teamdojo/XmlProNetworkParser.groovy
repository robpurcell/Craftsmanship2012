package com.teamdojo

class XmlProNetworkParser {

	def processInput(def xml) {
		def records = new XmlSlurper().parseText(xml)
		createProgrammers(records).sort{it.name}
	}

	def createProgrammers(def records) {
		records.Programmer.collect() {
			new Programmer(name: it.@name.text(),
					skills: createSkills(it.Skills),
					recommendations: createRecommendations(it.Recommendations))
		}
	}

	def createSkills(def skills) {
		skills.Skill.collect()
	}

	def createRecommendations(def recommendations) {
		recommendations.Recommendation.collect()
	}
}
