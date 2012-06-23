package com.teamdojo.kudos

import com.teamdojo.XmlProNetworkParser

class ProgrammerKudos {
	def getKudos(def programmers) {
		PageRank pageRank = new PageRank()
		
		for (def programmer: programmers) {
			pageRank.addRecommendation(programmer.name, programmer.recommendations)
		}
		
		def rankings = [:]
		for (def programmer: programmers) {
			def name = programmer.name
			rankings.put(name, pageRank.rank(name))
		}
		
		return rankings
	}
}
