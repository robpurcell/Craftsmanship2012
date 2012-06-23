package com.teamdojo

import com.teamdojo.formatter.KudosFormatter
import com.teamdojo.formatter.ProNetworkDisplayFormatter;
import com.teamdojo.kudos.ProgrammerKudos

class ProNetworkController {
	def programmers = []
	
	def ProNetworkController(File xml) {
		XmlProNetworkParser reader = new XmlProNetworkParser()
		programmers = reader.processInput(xml.text)
	}
	
	def getFormattedProNetwork() {
		def formatter = new ProNetworkDisplayFormatter()
		return formatter.format(programmers)
	}
	
	def getFormattedKudos() {
		def formatter = new KudosFormatter()
		def programmerKudos = new ProgrammerKudos()
		return formatter.format(programmerKudos.getKudos(programmers))
	}

}
