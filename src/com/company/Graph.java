package com.company;

import java.util.Random;
/*
You must NOT change the signatures of classes/methods in this skeleton file.
You are required to implement the methods of this skeleton file according to the requirements.
You are allowed to add classes, methods, and members as required.
 */

/**
 * This class represents a graph that efficiently maintains the heaviest neighborhood over edge addition and
 * vertex deletion.
 *
 */
public class Graph {

    private HashTable graphHashTable;
    private WeightHeap weightHeap;
    private AdjacencyList adjacencyList;
    private int numEdges, numNodes;


    /**
     * Initializes the graph on a given set of nodes. The created graph is empty, i.e. it has no edges.
     * You may assume that the ids of distinct nodes are distinct.
     *
     * @param nodes - an array of node objects
     */
    public Graph(Node[] nodes){
        numNodes = nodes.length;
        this.graphHashTable = new HashTable(nodes);
//        weightHeap = new WeightHeap();
//        for(Node node: nodes){
//            HeapNode heapNode = new HeapNode(node.node_id, node.weight);
//            weightHeap.insert(heapNode);
//
//        }
    }

    /**
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight(){
        return heapNodes[1];
    }

    /**
     * given a node id of a node in the graph, this method returns the neighborhood weight of that node.
     *
     * @param node_id - an id of a node.
     * @return the neighborhood weight of the node of id 'node_id' if such a node exists in the graph.
     * Otherwise, the function returns -1.
     */
    public int getNeighborhoodWeight(int node_id){
        Node node = graphHashTable.get(node_id);
        if (node!=null){return node.neighborhood_weight;}
        return -1;
    }

    /**
     * This function adds an edge between the two nodes whose ids are specified.
     * If one of these nodes is not in the graph, the function does nothing.
     * The two nodes must be distinct; otherwise, the function does nothing.
     * You may assume that if the two nodes are in the graph, there exists no edge between them prior to the call.
     *
     * @param node1_id - the id of the first node.
     * @param node2_id - the id of the second node.
     * @return returns 'true' if the function added an edge, otherwise returns 'false'.
     */
    public boolean addEdge(int node1_id, int node2_id){
        //TODO: implement this method.
        return false;
    }

    /**
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id){
        //TODO: implement this method.
        return false;
    }
    
    /**
     * Returns the number of nodes currently in the graph.
     * @return the number of nodes in the graph.
     */
    public int getNumNodes(){
        return numNodes;
    }
    
    /**
     * Returns the number of edges currently in the graph.
     * @return the number of edges currently in the graph.
     */
    public int getNumEdges(){
        return numEdges;
    }

    /**
     * This class represents a node in the graph.
     */
    public static class Node{

        int id;
        int weight;

        /**
         * Creates a new node object, given its id and its weight.
         * @param id - the id of the node.
         * @param weight - the weight of the node.
         */
        public Node(int id, int weight){
            this.id = id;
            this.weight = weight;
        }

        /**
         * Returns the id of the node.
         * @return the id of the node.
         */
        public int getId(){
           return this.id;
        }

        /**
         * Returns the weight of the node.
         * @return the weight of the node.
         */
        public int getWeight(){
            return this.weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }


        public class HashTable {
            private HashCell[] hashTable;
            private int p = 10 ^ 9 + 9;
            private int n;
            private int a;
            private int b;

            public HashTable(Node[] graph) {
                this.n = graph.length;
                this.hashTable = new HashCell[n];
                Random r = new Random();
                this.a = (int) (r.nextDouble() * p + 1);
                this.b = (int) (r.nextDouble() * p);
                for (Node node : graph) {
                    insert(node);
                }
            }

            private int hash(int node_id) {
                return ((a * node_id + b) % p) % n;
            }

            public void insert(Node node) {
                HashCell hashcell = new HashCell(node, null);
                int location = hash(node.getId());
                if (hashTable[location] == null) {
                    hashTable[location] = hashcell;
                } else {
                    HashCell collision = hashTable[location];
                    while (collision.getNext() != null) {
                        collision = collision.getNext();
                    }
                    collision.setNext(hashcell);
                }
            }

            public Node get(int node_id){
                int location = hash(node_id);
                if (hashTable[location] == null){return null;}
                else{
                    HashCell candidate = hashTable[location];
                    while (candidate != null){
                        if (candidate.getNode_id() == node_id){return candidate.node;}
                        candidate = candidate.getNext();
                    }
                }
                return null;
            }
            public void remove(int node_id){
                int location = hash(node_id);
                HashCell candidate = hashTable[location];
                if (candidate == null){return;}
                if (candidate.getNode_id() == node_id) {
                    hashTable[location] = candidate.getNext();
                    return;
                }
                while(candidate.getNext() != null){
                    if(candidate.getNext().getNode_id() == node_id){
                        candidate.setNext(candidate.getNext().getNext());
                    }
                }
            }
    }


    
    private class HashCell{
        public Node node;
        public adjencyNode nodeAdjencies;
        public HashCell next;

        public HashCell(Node node, adjencyNode nodeAdjencies) {
            this.node = node;
            this.nodeAdjencies =  nodeAdjencies;
        }

        public HashCell getNext() {
            return next;
        }

        public void setNext(HashCell next) {
            this.next = next;
        }

        public int getNode_id() {
            return node.getId();
        }

    }
    



    public static class adjencyNode{
        private int connection_id;
        private adjencyNode next;
        private adjencyNode prev;
        private adjencyNode connection;

        public adjencyNode(int connection_id) {
            this.connection_id = connection_id;
        }

        public adjencyNode getNext() {
            return next;
        }

        public void setNext(adjencyNode next) {
            this.next = next;
        }

        public adjencyNode getPrev() {
            return prev;
        }

        public void setPrev(adjencyNode prev) {
            this.prev = prev;
        }
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