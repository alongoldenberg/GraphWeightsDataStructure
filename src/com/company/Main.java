package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.company.Graph.Node;

public class Main {

    public static void main(String[] args) {
    	testGraph();
    	
    	print("Empty Graph:");
    	Graph empty_graph = new Graph(new Node[0]);
    	empty_graph.addEdge(1, 2);
    	print("Max Weight Node: " + empty_graph.maxNeighborhoodWeight());
        print("Num of edges: " + empty_graph.getNumEdges());
        print("deletion");
    	empty_graph.deleteNode(1);
    	print("Num of edges: " + empty_graph.getNumEdges());
        print("Max Weight Node: " + empty_graph.maxNeighborhoodWeight());
    	empty_graph.getNeighborhoodWeight(0);
    	empty_graph.maxNeighborhoodWeight();
    	empty_graph.getNumEdges();
    	empty_graph.getNumNodes();
    	
    	print("\n");
    	print("1 node Graph:");
    	Node[] nodes1 = new Node[1];
    	nodes1[0] = new Node(1,3);
    	Graph one_graph = new Graph(nodes1);
    	
    	one_graph.addEdge(1, 2);
    	print("Max Weight Node: " + one_graph.maxNeighborhoodWeight() );
        print("Num of edges: " + one_graph.getNumEdges());
        print("Num of nodes: " + one_graph.getNumNodes());
        print("deletion");
        one_graph.deleteNode(1);
    	print("Num of edges: " + one_graph.getNumEdges());
    	print("Num of nodes: " + one_graph.getNumNodes());
        print("Max Weight Node: " + one_graph.maxNeighborhoodWeight());
        
    	print("\n");
    	
    	
        Graph.Node node1 = new Graph.Node(1, 3);
        Graph.Node node2 = new Graph.Node(2, 5);
        Graph.Node node3 = new Graph.Node(3, 2);
        Graph.Node node4 = new Graph.Node(4, 7);
        Graph.Node[] nodes = {node1, node2, node3, node4};
        Graph graph = new Graph(nodes);

        //Show Nodes:
        for(Graph.Node n: nodes){
            print(n.toString());
        }
        print("\n");

        // Test Adjacency
        print("Test Adjacenty");
        print("Adding Two nodes: 1->2, 1->3");
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        print("Max Weight: " + graph.maxNeighborhoodWeight().id + " and weight is " + graph.maxNeighborhoodWeight().getNeighborhood_weight());
        print("Num of edges: " + graph.getNumEdges());
        print("deleted node 1:");
        graph.deleteNode(1);
        print("Num of edges: " + graph.getNumEdges() + " == 0?");
        print("Max Weight: " + graph.maxNeighborhoodWeight().id + " and weight is " + graph.maxNeighborhoodWeight().getWeight());
        print("\n");

        // Test Delete:
        print("Test Deletion:");
        print(graph.getNumNodes());
        graph.deleteNode(2);
        print(graph.getNumNodes());

        print("\n");
        print("Big number test:");
        List<Graph.Node> l = new ArrayList<Graph.Node>();
        for(int i=0;i<1000;i++){
            l.add(new Graph.Node(i, i));
        }
                
        Graph.Node [] lstNodes = new Graph.Node[l.size()];
        for (int j = 0 ; j< l.size(); j++) {
        	lstNodes[j] = l.get(j);
        }
        Graph bigGraph = new Graph (lstNodes);
        
        for(int i = 0;i<997;i+=2){
            bigGraph.addEdge(i, i+2);
        }
        print("Max node is: " + bigGraph.maxNeighborhoodWeight());
        print("Delete 11-999:");
        for(int i = 999;i>10;i--){
            //print(i);
        	bigGraph.deleteNode(i);
        }
        print("Max node is: " + bigGraph.maxNeighborhoodWeight());
        print("DONE");
    }
    public static void print(String text){
        System.out.println(text);
    }
    public static void print(int text){
        System.out.println(text);
    }
    
    private static void testGraph() {

        Graph graph = new Graph(new Graph.Node[0]);
        assert graph.getNumEdges() == 0;
        assert graph.getNumNodes() == 0;
        assert graph.maxNeighborhoodWeight() == null;


        graph = new Graph(new Graph.Node[] {
                new Graph.Node(1, 0)
        });
        assert graph.getNumEdges() == 0;
        assert graph.getNumNodes() == 1;
        assert graph.maxNeighborhoodWeight().getId() == 1;
        assert graph.maxNeighborhoodWeight().getWeight() == 0;


        Graph.Node[] nodes = new Graph.Node[5];
        for (int i = 0; i < 5; i++) {
            nodes[i] = new Graph.Node(i, i);
        }
        graph = new Graph(nodes);
        assert graph.getNumNodes() == 5;


        assert !graph.addEdge(0, 12);
        assert !graph.addEdge(0, 0);

        assert graph.addEdge(2, 3);
        assert graph.maxNeighborhoodWeight().getId() == 2 || graph.maxNeighborhoodWeight().getId() == 3;
        assert graph.getNumEdges() == 1;
        assert graph.getNeighborhoodWeight(0) == 0;
        assert graph.getNeighborhoodWeight(1) == 1;
        assert graph.getNeighborhoodWeight(2) == 5;
        assert graph.getNeighborhoodWeight(3) == 5;
        assert graph.getNeighborhoodWeight(4) == 4;

        assert graph.addEdge(1, 2);
        assert graph.maxNeighborhoodWeight().getId() == 2;
        assert graph.getNumEdges() == 2;
        assert graph.getNeighborhoodWeight(0) == 0;
        assert graph.getNeighborhoodWeight(1) == 3;
        assert graph.getNeighborhoodWeight(2) == 6;
        assert graph.getNeighborhoodWeight(3) == 5;
        assert graph.getNeighborhoodWeight(4) == 4;

        assert graph.addEdge(3, 4);
        assert graph.addEdge(1, 4);
        assert graph.getNumEdges() == 4;
        assert graph.maxNeighborhoodWeight().getId() == 3;
        assert graph.getNeighborhoodWeight(0) == 0;
        assert graph.getNeighborhoodWeight(1) == 7;
        assert graph.getNeighborhoodWeight(2) == 6;
        assert graph.getNeighborhoodWeight(3) == 9;
        assert graph.getNeighborhoodWeight(4) == 8;

        assert !graph.deleteNode(12);

        assert graph.deleteNode(2);
        assert !graph.deleteNode(2);
        assert graph.getNumEdges() == 2;
        assert graph.getNumNodes() == 4;
        assert graph.getNeighborhoodWeight(0) == 0;
        assert graph.getNeighborhoodWeight(1) == 5;
        assert graph.getNeighborhoodWeight(3) == 7;
        assert graph.getNeighborhoodWeight(4) == 8;
        assert graph.maxNeighborhoodWeight().getId() == 4;
    }
}
