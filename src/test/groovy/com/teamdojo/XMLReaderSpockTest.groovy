package com.teamdojo
import spock.lang.Specification;

class XMLReaderSpockTest
 extends Specification {
	
	def "read file and list it on the screen"() {
		expect:
		Checkout.price(items) == price

		where:
		items         | price
		["A"]         | 50
		["A", "A"]    | 100
		["B"]         | 30
	}

}




