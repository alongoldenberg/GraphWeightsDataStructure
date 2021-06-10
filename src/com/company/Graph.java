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
        private HashCell[] hashTable;
        private int p = 10^9 + 9;
        private int n;
        private int a;
        private int b;

        public HashTable(Node[] graph) {
            this.n = graph.length;
            this.hashTable = new HashCell[n];
            Random r = new Random();
            this.a = (int) (r.nextDouble() * p + 1);
            this.b = (int) (r.nextDouble() * p);
            for (Node node: graph){
                insert(node);
            }
        }

        private int hash(int node_id){
            return ((a*node_id + b) % p) % n;
        }
        
        private void insert(Node node){
            
        }
    }
    
    private class HashCell{
        public int node_id;
        public HeapNode heapPointer;
        public AdjacencyList nodeAdjencies;
        public HashCell next;

        public HashCell(int node_id, HeapNode heapPointer, AdjacencyList nodeAdjencies) {
            this.node_id = node_id;
            this.heapPointer = heapPointer;
            this.nodeAdjencies =  nodeAdjencies;
        }

        public HashCell getNext() {
            return next;
        }

        public void setNext(HashCell next) {
            this.next = next;
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
