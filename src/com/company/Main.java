package com.company;

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
        print("Max Weight: " + graph.maxNeighborhoodWeight().id + " and weight is " + graph.maxNeighborhoodWeight().getWeight());
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


    }

    public static void print(String text){
        System.out.println(text);
    }
    public static void print(int text){
        System.out.println(text);
    }
}
