package com.teamdojo.separation

import com.teamdojo.Helper

class Distance {
	def neighbourhood = [:]
	def distanceMap = [:]
	
	def addNode(node, neighbours) {
		neighbourhood.put(node,neighbours)
		for (def neighbour : neighbours) {
			def aNeighbours = getNeighbours(neighbour)
			if (aNeighbours.isEmpty()) {
				neighbourhood.put(neighbour, [node])
			}
			else if (!aNeighbours.contains(node)) {
				aNeighbours << node
				neighbourhood.put(neighbour, aNeighbours)
			}
		}
		addDistance(node, neighbours)
	}
	
	def getNeighbours(node) {
		Helper.ensureNullSafeList(neighbourhood.get(node))		
	}
	
	def addDistance(node, neighbours) {
		for (def neighbour: neighbours) {
			def key = [node, neighbour].sort()
			distanceMap.put(key, 1)
		}
	}
	
	def getDistance(nodeA, nodeB) {
		return distanceMap.get([nodeA, nodeB].sort())
	}
}
