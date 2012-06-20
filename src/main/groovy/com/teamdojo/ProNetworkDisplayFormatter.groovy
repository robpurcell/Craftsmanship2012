package com.teamdojo

import java.text.Format;
import groovy.text.Template
import groovy.text.SimpleTemplateEngine

class ProNetworkDisplayFormatter {
	
	
	def format(programmerList) {
		def formatter = setupFormatter()
		def programmers = programmerList.collect{
			[Programmer:it.name,
				Skills:formatSkills(it.skills),
				Recommends:formatRecommendations(it.recommendations)]
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
