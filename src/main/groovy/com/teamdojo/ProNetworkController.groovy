package com.teamdojo

import com.teamdojo.formatter.ProNetworkDisplayFormatter;

class ProNetworkController {
	def displayNetwork(File xml) {
		XmlProNetworkParser reader = new XmlProNetworkParser()
		def programmers = reader.processInput(xml.text)
		def ProNetworkDisplayFormatter formatter = new ProNetworkDisplayFormatter()
		return formatter.format(programmers)
	}	

}
