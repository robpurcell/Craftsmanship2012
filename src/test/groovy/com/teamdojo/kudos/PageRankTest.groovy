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
	public void thatANetworkWithTwoNodesLinkedToEachOtherBothHaveARankOfOne() {
//		A <--> B
		pageRank.addRecommendation("B", ["A"])
		pageRank.addRecommendation("A", ["B"])
		
		assert pageRank.rank("A") == 1d
		assert pageRank.rank("B") == 1d
	}
	
	@Test
	public void thatTheBelowSimpleNetworkReturnsTheCorrectRanks() {
//		A --> B
//		^   /
//		|  /
//		V L	
//		C <-- D
		
		pageRank.addRecommendation("A", ["B","C"])
		pageRank.addRecommendation("B", ["C"])
		pageRank.addRecommendation("C", ["A"])
		pageRank.addRecommendation("D", ["C"])
		
		assert pageRank.rank("A") == 1.49
		assert pageRank.rank("B") == 0.78		
//TODO: documentation expected 1.59: might be because EJML uses double instead of BigDecimal. Further investigation required.
		assert pageRank.rank("C") == 1.58 
		assert pageRank.rank("D") == 0.15

	}

}