package com.teamdojo.kudos

import com.teamdojo.XmlProNetworkParser

class ProgrammerKudos {
	def getKudos(def xml) {
		XmlProNetworkParser reader = new XmlProNetworkParser()
		def programmers = reader.processInput(xml.text)
		PageRank pageRank = new PageRank()
		
		def rank = 1
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
