package com.teamdojo;

import static org.junit.Assert.*;
import groovy.util.XmlSlurper;

import org.junit.Before;
import org.junit.Test;

/**
 * @author rob
 *
 */
public class XMLReaderTest {

	private ProgrammerParser parser

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

	private static String SKILLS = '''
<Skills>
    <Skill>Java</Skill>
    <Skill>PHP</Skill>
  </Skills>
'''

	@Before
	public void setup() {
		parser = new ProgrammerParser()
	}


	@Test
	public void testSlurpXML() {
		def records = new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER)
		assert records.size() == 1
	}

	@Test
	public void testCreateProgrammers() {
		def result = parser.createProgrammers(new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER))
		assert result.size() == 1
		assert result[0].hasProperty("name")
		assert result[0].getName() == 'Rick'
		assert result[0].getSkills() == ['Java', 'PHP']
		assert result[0].getRecommendations() == ['Ed']
	}

	@Test
	public void testCreateSkills() {
		def result = new XmlSlurper().parseText(XMLReaderTest.SKILLS).Skill
		assert result.size() == 2
	}

	@Test
	public void testCreateSkillsImplementation() {
		def result = parser.createSkills(new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER).Programmer[0].Skills)
		assert result.size() == 2
		assert result[0].text() == 'Java'
		assert result[1].text() == 'PHP'
	}

	@Test
	public void testProcessInput() {
		File xml = new File("src/main/resources/ProNet.xml")
		def result = parser.processInput(xml.text)
		assert result.size() == 10
		println "Name\tSkills\t\t\tRecommendations"
		println result
		
	}
	
	
}
