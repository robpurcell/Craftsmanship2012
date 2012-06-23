package com.teamdojo.formatter

import java.text.Format;


import groovy.text.Template
import groovy.text.SimpleTemplateEngine

class KudosFormatter extends AbstractFormatter {	
	
	def format(kudosList) {
		def formatter = setupFormatter()
		def programmers = kudosList.collect{
			[Programmer:it.key, Kudos:it.value]
		}
		def binding = ['rows': programmers]
		def result = new SimpleTemplateEngine().createTemplate(formatter.template).make(binding).toString()
		
		return result
	}
	
	def setupFormatter() {
		def formatter = new TableTemplateFormatter()
		formatter
				.addColumn("Programmer", 25)
				.addColumn("Kudos", 8)
				.underlineColumnNames()
		return formatter
	}
}
