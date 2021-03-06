package com.teamdojo.formatter;

import static org.junit.Assert.*
import org.junit.*

import com.teamdojo.Programmer;
import com.teamdojo.formatter.ProNetworkDisplayFormatter;

class ProNetworkDisplayFormatterTest {
	def programmer, programmer2
	def displayFormatter
	
	def EXPECTED_FORMAT =
'''	 Programmer                 Skills                          Recommends                               
	 __________                 ______                          __________                               
	 Bill                       Ruby, Perl, PHP                 Jason, Jill, Nick, Stu                   
	 Ben                                                        Bill                                     
'''
	
	@Before
	public void setUp() {
		programmer = new Programmer()
		programmer.name = "Bill"
		programmer.skills = ["Ruby", "Perl", "PHP"]
		programmer.recommendations = ["Jason", "Jill", "Nick", "Stu"]
		
		programmer2 = new Programmer()
		programmer2.name = "Ben"
		programmer2.recommendations = ["Bill"]
		
		displayFormatter = new ProNetworkDisplayFormatter()
	}
	
	@Test
	public void thatSkillsAreTransformedIntoTheExpectedFormat() {
		assert displayFormatter.formatSkills(programmer.skills) == "Ruby, Perl, PHP" 
	}
	
	@Test
	public void thatRecommendationAreTransformedIntoTheExpectedFormat() {
		assert displayFormatter.formatRecommendations(programmer.recommendations) == "Jason, Jill, Nick, Stu"
	}
	
	@Test
	public void thatDataIsFormattedCorrectly() {
		assert displayFormatter.format([programmer,programmer2]).contains(EXPECTED_FORMAT)
	}

}
