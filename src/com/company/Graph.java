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
 */
public class Graph {

    private HashTable graphHashTable;
    private WeightHeap weightHeap;
    private int numEdges, numNodes;


    /**
     * Initializes the graph on a given set of nodes. The created graph is empty, i.e. it has no edges.
     * You may assume that the ids of distinct nodes are distinct.
     *
     * @param nodes - an array of node objects
     */
    public Graph(Node[] nodes) {
        numNodes = nodes.length;
        this.graphHashTable = new HashTable(nodes);
        weightHeap = new WeightHeap(parseInputNodesForHeap(nodes));
    }

    /**
     * Create Array of Nodes for heap
     *
     * @pre nodes.length != 0
     */
    public Node[] parseInputNodesForHeap(Node[] nodes) {
        Node[] newNodeArr = new Node[nodes.length + 1];
        newNodeArr[0] = null;
        for (int i = 0; i < nodes.length; i++) {
            newNodeArr[i + 1] = nodes[i];
            newNodeArr[i + 1].setHeapPointer(i + 1);
        }
        return newNodeArr;
    }

    /**
     * This method returns the node in the graph with the maximum neighborhood weight.
     * Note: nodes that have been removed from the graph using deleteNode are no longer in the graph.
     *
     * @return a Node object representing the correct node. If there is no node in the graph, returns 'null'.
     */
    public Node maxNeighborhoodWeight() {
        if (graphHashTable.isEmpty()) return null;
        return this.weightHeap.getMax();
    }

    /**
     * given a node id of a node in the graph, this method returns the neighborhood weight of that node.
     *
     * @param node_id - an id of a node.
     * @return the neighborhood weight of the node of id 'node_id' if such a node exists in the graph.
     * Otherwise, the function returns -1.
     */
    public int getNeighborhoodWeight(int node_id) {
        Node node = graphHashTable.get(node_id);
        if (node != null) {
            return node.neighborhood_weight;
        }
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
    public boolean addEdge(int node1_id, int node2_id) {
        Node node_1 = graphHashTable.get(node1_id);
        Node node_2 = graphHashTable.get(node2_id);

        if (node_1 == null || node_2 == null) {
            return false;
        }

        //Adjacency update:
        node_1.addToAdjacency(node_2);
        node_2.addToAdjacency(node_1);

        //Heap update:
        this.weightHeap.additionKey(node_1.heapPointer, node_2.getWeight());
        this.weightHeap.additionKey(node_2.heapPointer, node_1.getWeight());

        this.numEdges++;

        return true;
    }

    /**
     * Given the id of a node in the graph, deletes the node of that id from the graph, if it exists.
     *
     * @param node_id - the id of the node to delete.
     * @return returns 'true' if the function deleted a node, otherwise returns 'false'
     */
    public boolean deleteNode(int node_id) {
        Node nodeToDelete = graphHashTable.get(node_id);
        if (nodeToDelete != null) {
            this.numNodes--;
            int removedWeight = nodeToDelete.weight;
            adjacencyNode adjacency = nodeToDelete.nodeAdjacency;
            while (adjacency != null) {
                int neighbor_id = adjacency.connection_id;
                Node neighbour = graphHashTable.get(neighbor_id);
                weightHeap.decreaseKey(neighbour.heapPointer, removedWeight);
                neighbour.removeAdj(node_id);
                this.numEdges--;
                adjacency = adjacency.next;
            }
            this.weightHeap.deleteNodeByPosition(nodeToDelete.heapPointer);

            return true;
        }
        return false;
    }

    /**
     * Returns the number of nodes currently in the graph.
     *
     * @return the number of nodes in the graph.
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Returns the number of edges currently in the graph.
     *
     * @return the number of edges currently in the graph.
     */
    public int getNumEdges() {
        return numEdges;
    }

    /**
     * This class represents a node in the graph.
     */
    public static class Node {
        /**
         * Creates a new node object, given its id and its weight.
         *
         * @param id - the id of the node.
         * @param weight - the weight of the node.
         */
        int id;
        int weight;
        int neighborhood_weight;
        int heapPointer;
        public adjacencyNode nodeAdjacency;

        // Constructors
        public Node(int id, int weight, int neighborhood_weight, int heapPointer, adjacencyNode nodeAdjacency) {
            this.id = id;
            this.weight = weight;
            this.neighborhood_weight = neighborhood_weight;
            this.heapPointer = heapPointer;
            this.nodeAdjacency = nodeAdjacency;
        }

        public Node(int id, int weight, int neighborhood_weight, int heapPointer) {
            this(id, weight, neighborhood_weight, heapPointer, new adjacencyNode());

        }

        public Node(int id, int weight, int neighborhood_weight) {
            this(id, weight, neighborhood_weight, 0, new adjacencyNode());
        }

        public Node(int id, int weight) {
            this(id, weight, weight, 0, new adjacencyNode());
        }

        // Add Node to this node Adjacency
        public void addToAdjacency(Node other) {
        	adjacencyNode otherToAdjNode = new adjacencyNode(other.id);
        	this.nodeAdjacency.addToStart(otherToAdjNode);
        }

        public boolean hasAdjacency(){
            return this.nodeAdjacency != null;
        }

        public int getHeapPointer() {
            return this.heapPointer;
        }

        public void setHeapPointer(int heapPointer) {
            this.heapPointer = heapPointer;
        }
        public int getNeighborhood_weight() {
			return neighborhood_weight;
		}

		public void setNeighborhood_weight(int neighborhood_weight) {
			this.neighborhood_weight = neighborhood_weight;
		}
        

        public void addToNeighborhoodWeight(int weightToAdd) {
            this.neighborhood_weight += weightToAdd;
        }

        public void reduceFromNeighborhoodWeight(int weightToReduce) {
            this.neighborhood_weight -= weightToReduce;
        }

        /**
         * Returns the id of the node.
         *
         * @return the id of the node.
         */
        public int getId() {
            return this.id;
        }

        /**
         * Returns the weight of the node.
         *
         * @return the weight of the node.
         */
        public int getWeight() {
            return this.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", weight=" + weight +
                    ", neighborhood_weight=" + neighborhood_weight +
                    '}';
        }



        public void removeAdj(int adjToRemove) {
            adjacencyNode adjacency = this.nodeAdjacency;
            if (adjacency != null && adjacency.connection_id == adjToRemove) {
                this.nodeAdjacency = adjacency.next;
                if(hasAdjacency()){adjacency.prev = null;}
            }
            adjacency = adjacency.next;
            while (nodeAdjacency != null) {
                if (adjacency.connection_id == adjToRemove) {
                    adjacency.prev.next = adjacency.next;
                    if(adjacency.next != null){adjacency.next.prev = adjacency.prev;}
                }
            }
        }

    }

    public class HashTable {
        private HashCell[] hashTable;
        private int p = (int) Math.pow(10, 9) + 9;
        private int n;
        private int a;
        private int b;
        private int fieldCells;

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
            return Math.floorMod(Math.floorMod((a * node_id + b) ,p) , n);
        }

        public boolean isEmpty() {
            return (this.fieldCells == 0);
            
            
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
            this.fieldCells++;
        }


        public Node get(int node_id) {
            int location = hash(node_id);
            if (hashTable[location] == null) {
                return null;
            } else {
                HashCell candidate = hashTable[location];
                while (candidate != null) {
                    if (candidate.getNode_id() == node_id) {
                        return candidate.node;
                    }
                    candidate = candidate.getNext();
                }
            }
            return null;
        }

        public void remove(int node_id) {
            int location = hash(node_id);
            HashCell candidate = hashTable[location];
            if (candidate == null) {
                return;
            }
            if (candidate.getNode_id() == node_id) {
                hashTable[location] = candidate.getNext();
                return;
            }
            while (candidate.getNext() != null) {
                if (candidate.getNext().getNode_id() == node_id) {
                    candidate.setNext(candidate.getNext().getNext());
                }
            }
            this.fieldCells--;
        }
    }


    private class HashCell {
        public Node node;
        public adjacencyNode nodeAdjacency;
        public HashCell next;

        public HashCell(Node node, adjacencyNode nodeAdjacency) {
            this.node = node;
            this.nodeAdjacency = nodeAdjacency;
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


    /**
     * @author alongold
     * This Class represents node adjency in a linked list format.
     */

    public static class adjacencyNode {
        private int connection_id;
        private adjacencyNode next;
        private adjacencyNode prev;
        private adjacencyNode connection;

        public adjacencyNode(int connection_id) {
            this.connection_id = connection_id;
        }	
        
        public adjacencyNode() {
        	this.next = null;
        	this.prev = null;
        	this.connection = null;
        }
        
        public adjacencyNode(Node node) {
            this.connection_id = node.id;
        }

        public adjacencyNode getNext() {
            return next;
        }

        public void setNext(adjacencyNode next) {
        	if (next == null) {
            	this.next = null;
            	return;
            }
        	this.next = next;
        	this.next.connection = next;
            this.next.connection_id = next.connection_id;
        }

        public adjacencyNode getPrev() {
            return prev;
        }

        public void setPrev(adjacencyNode prev) {
            if (prev == null) {
            	this.prev = null;
            	return;
            }
        	this.prev = prev;
        	this.prev.connection = prev;
            this.prev.connection_id = prev.connection_id;
        }

        public void addToStart(adjacencyNode adjNode) {
            if (this.connection == null) {
            	this.connection = adjNode;
            	this.connection_id = adjNode.connection_id; 
            	return;
            }
            this.setNext(adjNode);
            adjNode.setPrev(this); 
        }
    }

    /**
     * @author ofirn
     * nodeArray[0] is null. heap start from index 1.
     */

    public static class WeightHeap {
        private Node[] heapArray;
        private int size;

        public WeightHeap(Node[] heapNodes) { // node array in index 0 is not relevant. 
            this.size = heapNodes.length - 1; // because of comment above.
            // Build heap:
            this.heapArray = heapNodes;
            for (int i = heapArray.length - 1; i > 0; i--) {  // this loop go threw all vertexes layer by layer.
                if ((2 * i) > this.size) {
                    continue;
                }
                HeapifyDown(i);
            }
        }

        public int parentIndex(int i) { // i is an index of heapArray 
            return Math.floorDiv(i, 2);
        }

        public int rightSonIndex(int i) { // i is an index of heapArray 
            return (2 * i) + 1;
        }

        public int leftSonIndex(int i) { // i is an index of heapArray 
            return (2 * i);
        }

        public void swap(int i, int j) {
            Node tmp = this.heapArray[i];
            this.heapArray[i] = this.heapArray[j];
            this.heapArray[j] = tmp;

            this.heapArray[i].setHeapPointer(i);
            this.heapArray[j].setHeapPointer(j);

        }

        public void HeapifyDown(int i) {
            int left = leftSonIndex(i);
            int right = rightSonIndex(i);
            int largest = i;
            if (left <= size && heapArray[left].neighborhood_weight > heapArray[largest].neighborhood_weight) {
                largest = left;
            }
            if (right <= size && heapArray[right].neighborhood_weight > heapArray[largest].neighborhood_weight) {
                largest = right;
            }
            if (largest > i) {
                swap(i, largest);
                HeapifyDown(largest);
            }
        }

        public void HeapifyUp(int i) {
            int parent = parentIndex(i);

            if (parent > 0 && heapArray[i].neighborhood_weight > heapArray[parent].neighborhood_weight) {
                swap(i, parent);
                HeapifyUp(parent);
            }
        }

        public Node getMax() {
            return this.heapArray[1];
        }

        public void additionKey(int i, int weightToAdd) {
            this.heapArray[i].addToNeighborhoodWeight(weightToAdd);
            HeapifyUp(i);
        }

        public void decreaseKey(int i, int weightToReduce) {
            this.heapArray[i].reduceFromNeighborhoodWeight(weightToReduce);
            HeapifyDown(i);
        }

        // @ pre -  size >= i > 0  &  size > 0
        public void deleteNodeByPosition(int i) {
            // #TODO: Look closely at the following line: pay attention that it caused doubles
            this.heapArray[i] = this.heapArray[size];
            this.size -= 1;
            if (parentIndex(i) > 0 && heapArray[i].neighborhood_weight > heapArray[parentIndex(i)].neighborhood_weight) {
                HeapifyUp(i);
            } else if (heapArray[leftSonIndex(i)].neighborhood_weight > heapArray[i].neighborhood_weight) {
                HeapifyDown(i);
            } else if (heapArray[rightSonIndex(i)].neighborhood_weight > heapArray[i].neighborhood_weight) {
                HeapifyDown(i);
            }
        }
    }

}