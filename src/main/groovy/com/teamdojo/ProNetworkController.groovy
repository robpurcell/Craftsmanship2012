package com.teamdojo

import com.teamdojo.formatter.ProNetworkDisplayFormatter;

class ProNetworkController {
	def programmers = []
	
	def ProNetworkController(File xml) {
		XmlProNetworkParser reader = new XmlProNetworkParser()
		programmers = reader.processInput(xml.text)
	}
	
	def displayNetwork() {
		def ProNetworkDisplayFormatter formatter = new ProNetworkDisplayFormatter()
		return formatter.format(programmers)
	}

}
