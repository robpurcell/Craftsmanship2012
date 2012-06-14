package com.teamdojo;

import static org.junit.Assert.*;
import groovy.util.XmlSlurper;

import org.junit.Test;

/**
 * @author rob
 *
 */
public class XMLReaderTest {

	private static String PROGRAMMER = '''
			<Programmer name='Rick'>
		    <Recommendations>
    <Recommendation>Ed</Recommendation>
  </Recommendations>
  <Skills>
    <Skill>Java</Skill>
    <Skill>PHP</Skill>
  </Skills>
</Programmer> 
	'''
	
	
	
	@Test
	public void test() {
		def records = new XmlSlurper().parseText(XMLReaderTest.PROGRAMMER)
		assert records.size() == 1
	}
	
	

}
