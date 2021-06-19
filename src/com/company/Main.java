package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
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

        Graph bigGraph = new Graph((Graph.Node[]) l.toArray(new Graph.Node[0]));
        for(int i = 0;i<997;i+=2){
            bigGraph.addEdge(i, i+2);
        }
        print("Max node is: " + bigGraph.maxNeighborhoodWeight());
        print("Delete 11-999:");
        for(int i = 999;i>10;i--){
            print(i);
            bigGraph.deleteNode(i);
        }
        print("Max node is: " + bigGraph.maxNeighborhoodWeight());
    }
    public static void print(String text){
        System.out.println(text);
    }
    public static void print(int text){
        System.out.println(text);
    }
    
}
