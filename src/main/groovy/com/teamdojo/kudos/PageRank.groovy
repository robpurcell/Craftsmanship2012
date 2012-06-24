package com.teamdojo.kudos

import org.ejml.simple.SimpleMatrix
import com.teamdojo.Helper

/*
 * Based on code by Nima Goodarzi
 * Website: http://www.javadev.org
 */
class PageRank {

	def dampingFactor = 0.85
	
	def outboundMap = [:]
	def inboundMap = [:]

	/*
	 * Solve the equation of ax=b, which : a is the generated matrix based on
	 * the parameter constants. x is the page ranks matrix. b is a n*1 matrix
	 * which all the values are equal to the damping factor.
	 */
	def rank(node) {
		def params = []
		generateParamList(node, params)
		def a = new SimpleMatrix(generateMatrix(params))

		def arrayB = new double[params.size()][1]
		for (int i = 0; i < params.size(); i++) {
			arrayB[i][0] = 1 - dampingFactor
		}

		def b = new SimpleMatrix(arrayB)
		// Solve the equation and get the page ranks
		def x = a.solve(b)

		def ind, cnt = 0
		for (def currentNode : params) {
			if (currentNode.equals(node))
				ind = cnt
			cnt++
		}		
		return round(x.get(ind,0), 2)
	}

	/*
	 * This method generates the matrix of the linear equations. The generated
	 * matrix is a n*n matrix where n is number of the related pages.
	 */
	def generateMatrix(params) {
		def array = new double[params.size()][params.size()]

		for (int i = 0; i < params.size(); i++) {
			for (int j = 0; j < params.size(); j++) {
				array[i][j] = getMultiFactor(params.get(i), params.get(j))
			}
		}
		return array
	}

	/*
	 * This method returns the constant of the given variable in the linear
	 * equation.
	 */
	def getMultiFactor(sourceNode, linkNode) {
		if (sourceNode == linkNode) {
			return 1
		}
		else {
			def inboundNodes = getInboundNodes(sourceNode)
			for (def inboundNode: inboundNodes) {
				if (inboundNode.equals(linkNode)) {
					return -1 * (dampingFactor / getOutboundNodes(linkNode).size())
				}
			}
		}
		return 0
	}

	/*
	 * This method returns list of the related nodes. This list is also the
	 * parameters in the linear equation.
	 */
	def generateParamList(startingNode, params) {
		// Add the starting node.
		if (!params.contains(startingNode))
			params.add(startingNode)

		// Get list of the inbound node
		def inboundNodes = getInboundNodes(startingNode)

		// Recursive generate a list for each inboundNode found
		for (def inboundNode : inboundNodes) {
			if (!params.contains(inboundNode))
				generateParamList(inboundNode, params)
		}
	}

	def getInboundNodes(node) {
		Helper.ensureNullSafeList(inboundMap.get(node))
	}

	def getOutboundNodes(node) {
		Helper.ensureNullSafeList(outboundMap.get(node))
	}
	
	def addRecommendation(node, recommends) {
		outboundMap.put(node, recommends);
		for (String recommendedBy : recommends) {
			def recommenders = getInboundNodes(recommendedBy)
			if (recommenders.isEmpty()) {
				inboundMap.put(recommendedBy, [node])
			}
			else {
				recommenders << node
				inboundMap.put(recommendedBy, recommenders)
			}
		}
	}
	
	def round(d, decimalPlace) {
		// see the Javadoc about why we use a String in the constructor
		// http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html#BigDecimal(double)
		def bd = new BigDecimal(Double.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_DOWN);
		return bd.doubleValue();
	}
}