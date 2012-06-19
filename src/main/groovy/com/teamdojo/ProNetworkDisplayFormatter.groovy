package com.teamdojo

import java.text.Format;
import groovy.text.Template
import groovy.text.SimpleTemplateEngine

class ProNetworkDisplayFormatter {
	
	
	def format(programmerList) {
		def formatter = setupFormatter()
		def programmers = []
		for (def programmer : programmerList) {
			programmers << [Programmer:programmer.name, 
				Skills:formatSkills(programmer.skills), 
				Recommends:formatRecommendations(programmer.recommendations)]			
		}
		def binding = ['rows': programmers]
		def result = new SimpleTemplateEngine().createTemplate(formatter.template).make(binding).toString()
		
		return result
	}
	
	def setupFormatter() {
		def formatter = new TableTemplateFormatter()
		formatter
				.addColumn("Programmer", 25)
				.addColumn("Skills", 30)
				.addColumn("Recommends", 40)
				.underlineColumnNames()
		return formatter
	}
	
	def formatSkills(skills) {
		formatListData(skills)
	}
	
	def formatRecommendations(recommendations) {
		formatListData(recommendations)
	}
	
	def formatListData(dataAsList) {
		StringBuilder builder = new StringBuilder()
		def it = dataAsList.iterator()
		if (it.hasNext()) {
			builder.append(it.next())
		}
		while (it.hasNext()) {
			builder
			.append(", ")
			.append(it.next())
		}
		
		builder.toString()
	}
}
