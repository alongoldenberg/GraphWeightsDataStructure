package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphTester {

	public static void main(String[] args) {
		Graph.Node [] nodes = new Graph.Node[20];
		Random rand = new Random();
		for (int i = 0; i < 20; i ++) {
			Graph.Node newNode = new Graph.Node(i, (i+1)*10);
			nodes[i] = newNode;
		}
		Graph graph1 = new Graph(nodes);
		
		graph1.addEdge(0,1);
		graph1.addEdge(1,2);
		
		Graph.Node maxNode;
		//maxNode = graph1.maxNeighborhoodWeight();
		//if (maxNode.getId() != 19) System.out.println("Error in test1 - maxNeighborhoodWeight");
		
		graph1.addEdge(5, 6);
		graph1.addEdge(7,6);
		graph1.addEdge(10,11);
		graph1.addEdge(12,11);

		
		//maxNode = graph1.maxNeighborhoodWeight();
		//if (maxNode.getId() != 11) System.out.println("Error in test2- maxNeighborhoodWeight");
		
		if (graph1.getNumNodes() != 20) System.out.println("Error in test3- getNumNodes");
		if (graph1.getNumEdges() != 6) System.out.println("Error in test4- getNumEdges");
		
		if(graph1.getNeighborhoodWeight(0) != 30) System.out.println("Error in test5- getNeighborhoodWeight");
		if (graph1.getNeighborhoodWeight(11) != (110 + 120 + 130)) System.out.println("Error in test6- getNeighborhoodWeight");
		if (graph1.getNeighborhoodWeight(19) != 200) System.out.println("Error in test7- getNeighborhoodWeight");
		if (graph1.getNeighborhoodWeight(30) != -1) System.out.println("Error in test8- getNeighborhoodWeight");
		
		graph1.deleteNode(19);
		if(graph1.getNumNodes() != 19) System.out.println("Error in test9- deleteNode");
		
		graph1.deleteNode(1);
		if(graph1.getNumEdges() != 4) System.out.println("Error in test10- deleteNode");
		if(graph1.getNeighborhoodWeight(2) != 30) System.out.println("Error in test11- deleteNode");
		
		graph1.deleteNode(0);
		for (int i = 2; i < 19; i++) {
			if (!graph1.deleteNode(i)) {
				System.out.println("Error in test11.Something- deleteNode");
			}
		}
		
		if (graph1.maxNeighborhoodWeight() != null) System.out.println("Error in test12- deleteNode");
		if (graph1.getNumEdges() != 0) System.out.println("Error in test13- deleteNode/getNumEdges");
		
		System.out.println("Success!");
		
	}

}
