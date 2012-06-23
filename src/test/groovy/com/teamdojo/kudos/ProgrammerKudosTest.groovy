package com.teamdojo.kudos;

import org.junit.Before
import org.junit.Test

class ProgrammerKudosTest {
	
	@Test
	public void thatKudosCanBeFoundFromTheProNet() {
		def programmerKudos  = new ProgrammerKudos()
		File xml = new File("src/main/resources/ProNet.xml")
		def kudosList = programmerKudos.getKudos(xml)
		assert kudosList.size() > 0
		println kudosList
		assert kudosList.get("Bill") == 0.53	//should be 0.57
		assert kudosList.get("Dave") == 0.26
		assert kudosList.get("Ed") == 0.43		//should be 0.37
		assert kudosList.get("Frank") == 2.23	//should be 2.28
		assert kudosList.get("Jason") == 0.26	//should be 0.27
		assert kudosList.get("Jill") == 0.48	//should be 0.50
		assert kudosList.get("Liz") == 0.45		//should be 0.37
		assert kudosList.get("Nick") == 2.57	//should be 2.63
		assert kudosList.get("Rick") == 0.33	//should be 0.25
		assert kudosList.get("Stu") == 2.45		//should be 2.51
		
	}

}
