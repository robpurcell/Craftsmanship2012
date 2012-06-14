package com.teamdojo

class ProgrammerParser {
	
	def processInput(def xml) {
		def records = new XmlSlurper().parseText(xml)
		
		createProgrammers(records)
	}
	
	def createProgrammers(def records) {
		//records.Programmer[0]
		def programmer = new Programmer(name: records.Programmer[0].name())
		return [programmer]
		
	}

}
