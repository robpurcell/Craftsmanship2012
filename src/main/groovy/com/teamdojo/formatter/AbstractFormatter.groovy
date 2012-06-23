package com.teamdojo.formatter

import java.text.Format;


import groovy.text.Template
import groovy.text.SimpleTemplateEngine

abstract class AbstractFormatter {	
	
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
