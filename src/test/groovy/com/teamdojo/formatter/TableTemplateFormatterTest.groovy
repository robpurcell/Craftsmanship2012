package com.teamdojo.formatter;

import static org.junit.Assert.*
import org.junit.*

import com.teamdojo.formatter.TableTemplateFormatter;

import groovy.text.Template
import groovy.text.SimpleTemplateEngine

class TableTemplateFormatterTest {
	private def formatter
	
	private def EXPECTED_HEADER = 
'''	 Programmer                 Skills                          Recommends                               
	 __________                 ______                          __________                               
'''

	 private def PROGRAMMER_BILL = "Bill                       Ruby, Perl, PHP                 Jason, Jill, Nick, Stu"
	
	
	@Before
	public void setUp() {
		formatter = new TableTemplateFormatter()
		formatter
		.addColumn("Programmer", 25)
		.addColumn("Skills", 30)
		.addColumn("Recommends", 40)
		.underlineColumnNames()
	}
	
	@Test
	public void thatHeaderCanBeDisplayedAsRequired() {
		def programmers =[]
		def binding = ['rows': programmers]
		def result = new SimpleTemplateEngine().createTemplate(formatter.template).make(binding).toString()
		
		assert result.contains(EXPECTED_HEADER)
	}
	
	@Test
	public void thatDetailsCanBeDisplayedAsRequired() {
		def programmers =[] << [Programmer:"Bill", Skills:"Ruby, Perl, PHP", Recommends:"Jason, Jill, Nick, Stu"]
		def binding = ['rows': programmers]
		def result = new SimpleTemplateEngine().createTemplate(formatter.template).make(binding).toString()
				
		assert result.contains(PROGRAMMER_BILL)
	}
}
