package com.teamdojo

class TableTemplateFormatter {
	
	def columns = []
	def underlineColumnNames = false
	
	def addColumn(name, size) {
		columns << [name:name, size:size]
		this
	}
	
	def underlineColumnNames() {
		underlineColumnNames = true
	}
	
	def getTemplate() {
		"""
	${columns.collect{ " <%print \"$it.name\".padRight($it.size)%> " }.join()}
	<% if ($underlineColumnNames) {%>${columns.collect{
			def titleSize = it.name.length()
			def columnRemainder = it.size - titleSize
			" <%print \"_\"*$titleSize%>"+"<%print \" \"*$columnRemainder %> " }.join()}<%}%>
	<% rows.each {%>${columns.collect{ " \${it.${it.name}.toString().padRight($it.size).substring(0,$it.size)} " }.join()}
	<% } %>"""
	  }

}
