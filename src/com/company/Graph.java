package com.company;

import java.util.Random;

public class Graph {
    private HashTable graphHashTable;
    private WeightHeap weightHeap;
    private AdjacencyList adjacencyList;

    /**
     * @param nodes
     *
     * Create a new Graph from list of nodes.
     * Generates a new HashTable, weightHeap and adjList.
     */
    public Graph(Node[] nodes) {
        weightHeap = new WeightHeap();
        for(Node node: nodes){
            HeapNode heapNode = new HeapNode(node.node_id, node.weight);
            weightHeap.insert(heapNode);
            
        }
    }

    public static class Node{
        private int node_id;
        private int weight;

        public Node(int id, int weight) {
            this.node_id = id;
            this.weight = weight;
        }

        public int getNodeID() {
            return node_id;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    public class HashTable{
        private Node[] hashTable;
        private long p = 10^9 + 9;
        private long a;
        private long b;

        public HashTable(Node[] graph) {
            Random r = new Random();
            this.a = (long) (r.nextDouble() * p + 1);
            this.b = (long) (r.nextDouble() * p);
        }
    }

    public class AdjacencyList{

    }

    public class WeightHeap{


    }

    public class HeapNode{
        private int node_id;
        private int weightSum;

        public HeapNode(int id, int weightSum) {
            this.node_id = id;
            this.weightSum = weightSum;
        }

        public int getNodeID() {
            return node_id;
        }

        public int getWeight() {
            return weightSum;
        }

        public void setWeightSum(int weight) {
            this.weightSum = weight;
        }
    }

}
