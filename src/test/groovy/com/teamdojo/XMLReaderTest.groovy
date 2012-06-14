package com.teamdojo;

import static org.junit.Assert.*;
import groovy.util.XmlSlurper;

import org.junit.Test;

/**
 * @author rob
 *
 */
public class XMLReaderTest {

	private static String PROGRAMMER = '''<Network>
			<Programmer name='Rick'>
		    <Recommendations>
    <Recommendation>Ed</Recommendation>
  </Recommendations>
  <Skills>
    <Skill>Java</Skill>
    <Skill>PHP</Skill>
  </Skills>
</Programmer> 
</Network>
	'''
	
	
	
	@Test
	public void testSlurpXML() {
		def records = new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER)
		assert records.size() == 1
	}
	
	@Test
	public void testCreateProgrammers() {
		ProgrammerParser parser = new ProgrammerParser()
		def result = parser.createProgrammers(new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER))
		assert result.size() == 1
		//assert result[0].hasProperty(name)
	}	

}
