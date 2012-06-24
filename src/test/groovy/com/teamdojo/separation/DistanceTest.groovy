package com.teamdojo.separation

import org.junit.Before
import org.junit.Test

class DistanceTest {
	def distance
	
	@Before
	public void setUp() {
		distance = new Distance()
	}
	
	@Test
	public void thatANetworkWithOnlyOneNodeHasNoNeighbours() {
		distance.addNode("A", [])
		assert distance.getNeighbours("A").isEmpty()
	}
	
	@Test
	public void thatNeighboursCanBeRetrievedWhenDoubleLinked() {
//		A <-> B
		distance.addNode("A", ["B"])
		distance.addNode("B", ["A"])
		assert distance.getNeighbours("A") == ["B"]
		assert distance.getNeighbours("B") == ["A"]
		assert distance.getDistance("A","B") == 1
	}
	
	@Test
	public void thatNeighboursCanBeRetrievedWhenSingleLinked() {
//		A <- B
		distance.addNode("A", ["B"])
		assert distance.getNeighbours("A") == ["B"]
		assert distance.getNeighbours("B") == ["A"]
	}
	
	@Test
	public void thatNeighboursInATwoNodeNeighbourhoodHaveADistanceOfOne() {
//		A <- B
		distance.addNode("A", ["B"])
		assert distance.getDistance("B","A") == 1
		assert distance.getDistance("B","A") == 1
	}

}
