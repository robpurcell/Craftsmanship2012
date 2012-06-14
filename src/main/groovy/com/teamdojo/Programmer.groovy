package com.teamdojo

class Programmer {
	
	def name
	def skills = []
	def recommendations = []
	
	String toString()  {
		println name + "\t" + skills.join(",") + "\t\t\t" + recommendations.join(",")
	}

}
