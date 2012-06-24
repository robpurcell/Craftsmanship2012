package com.teamdojo

class Helper {
	def static ensureNullSafeList(nodes) {
		if (nodes == null) {
			nodes = []
		}
		return nodes
	}
}
