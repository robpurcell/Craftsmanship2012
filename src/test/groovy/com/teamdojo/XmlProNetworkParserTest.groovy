package com.teamdojo;

import static org.junit.Assert.*;
import groovy.util.XmlSlurper;

import org.junit.Before;
import org.junit.Test;

/**
 * @author rob
 *
 */
public class XmlProNetworkParserTest {

	private XmlProNetworkParser parser

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
		parser = new XmlProNetworkParser()
	}


	@Test
	public void testSlurpXML() {
		def records = new XmlSlurper().parseText(XmlProNetworkParserTest.PROGRAMMER)
		assert records.size() == 1
	}

	@Test
	public void testCreateProgrammers() {
		def result = parser.createProgrammers(new XmlSlurper().parseText(XmlProNetworkParserTest.PROGRAMMER))
		assert result.size() == 1
		assert result[0].hasProperty("name")
		assert result[0].getName() == 'Rick'
		assert result[0].getSkills() == ['Java', 'PHP']
		assert result[0].getRecommendations() == ['Ed']
	}

	@Test
	public void testCreateSkills() {
		def result = new XmlSlurper().parseText(XmlProNetworkParserTest.SKILLS).Skill
		assert result.size() == 2
	}

	@Test
	public void testCreateSkillsImplementation() {
		def result = parser.createSkills(new XmlSlurper().parseText(XmlProNetworkParserTest.PROGRAMMER).Programmer[0].Skills)
		assert result.size() == 2
		assert result[0].text() == 'Java'
		assert result[1].text() == 'PHP'
	}

	@Test
	public void testProcessInput() {
		File xml = new File("src/main/resources/ProNet.xml")
		def programmers = parser.processInput(xml.text)
		assert programmers.size() == 10
		assert programmers.name[0..9] == ['Bill','Dave','Ed','Frank','Jason','Jill','Liz','Nick', 'Rick','Stu']		
	}
	
	
}
