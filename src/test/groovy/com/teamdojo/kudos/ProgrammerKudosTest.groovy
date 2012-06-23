package com.teamdojo.kudos;

import com.teamdojo.ProNetworkController
import com.teamdojo.XmlProNetworkParser
import org.junit.Before
import org.junit.Test

class ProgrammerKudosTest {
	def programmers
	def programmerKudos
	
	@Before
	public void setUp() {
		File xml = new File("src/main/resources/ProNet.xml")
		def controller = new ProNetworkController(xml)
		programmers = controller.programmers
		programmerKudos = new ProgrammerKudos()
	}
	
	@Test
	public void thatKudosCanBeFoundFromTheProNet() {
		def kudosList = programmerKudos.getKudos(programmers)
		assert kudosList.size() > 0
		println kudosList
		assert kudosList.get("Bill") == 0.57
		assert kudosList.get("Dave") == 0.26
		assert kudosList.get("Ed") == 0.37
		assert kudosList.get("Frank") == 2.28
		assert kudosList.get("Jason") == 0.27
		assert kudosList.get("Jill") == 0.50
		assert kudosList.get("Liz") == 0.37
		assert kudosList.get("Nick") == 2.63
		assert kudosList.get("Rick") == 0.25
		assert kudosList.get("Stu") == 2.51	
	}

}
