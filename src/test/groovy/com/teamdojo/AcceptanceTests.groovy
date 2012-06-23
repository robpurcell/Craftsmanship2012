package com.teamdojo;

import static org.junit.Assert.*

import org.junit.*

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

class AcceptanceTests {
	def proNet;
	
	@Before
	public void setUp() {
		File xml = new File("src/main/resources/ProNet.xml")
		proNet = new ProNetworkController(xml)
	}	

	@Test
	//Acceptance Test One
	public void theDisplayOfTheProgrammerNetwork() {
 		def formattedOutput  = proNet.displayNetwork()
		assert formattedOutput != null
		def lines = formattedOutput.readLines()
		assert lines.size() == 14
		
		println formattedOutput 				
	}
}
