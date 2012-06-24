package com.teamdojo.kudos

import org.junit.Before
import org.junit.Test

public class PageRankTest {
	def PageRank pageRank
	
	@Before
	public void setUp() {
		pageRank = new PageRank();
	}
	
	@Test
	public void thatANetworkWithOnlyOneNodeHasARankOfMinusTheDampingFactor() {
		pageRank.addRecommendation("A", [])		
		assert pageRank.rank("A") == 1 - pageRank.dampingFactor
	}

	@Test
	public void thatANetworkWithTwoNodesLinkedToEachOtherBothHaveARankOfOne() {
//		A <--> B
		pageRank.addRecommendation("B", ["A"])
		pageRank.addRecommendation("A", ["B"])
		
		assert pageRank.rank("A") == 1
		assert pageRank.rank("B") == 1
	}
	
	@Test
	public void thatTheBelowSimpleNetworkReturnsTheCorrectRanks() {
//		A --> B
//		^   /
//		|  /
//		v L	
//		C <-- D
		pageRank.addRecommendation("A", ["B","C"])
		pageRank.addRecommendation("B", ["C"])
		pageRank.addRecommendation("C", ["A"])
		pageRank.addRecommendation("D", ["C"])
		
		assert pageRank.rank("A") == 1.49
		assert pageRank.rank("B") == 0.78		
		assert pageRank.rank("C") == 1.58 
		assert pageRank.rank("D") == 0.15

	}
	
	@Test
	public void networkAccuracy() {
//		A --> B
//		^   /
//		|  /
//		vL
//		C
		pageRank.dampingFactor = 0.5
		pageRank.addRecommendation("A", ["B","C"])
		pageRank.addRecommendation("B", ["C"])
		pageRank.addRecommendation("C", ["A"])
		
		assert pageRank.rank("C") == 1.15
		assert pageRank.rank("B") == 0.77
		assert pageRank.rank("A") == 1.08 
	}
}
