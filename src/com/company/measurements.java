package com.company;
import java.util.*;

public class measurements {
    public static void main(String args[]) {
        Random rand = new Random();
        for (int i = 6; i < 22;i++) {
            int graphSize = (int) Math.pow(2, i);
            print("i: " + i + "graph size: " + graphSize);
            List<Graph.Node> l = new ArrayList<Graph.Node>();
            for (int j = 0; j < graphSize; j++) {
                l.add(new Graph.Node(i, 1));
            }
            Graph bigGraph = new Graph((Graph.Node[]) l.toArray(new Graph.Node[0]));
            HashSet<Integer[]> edgesChecker =  new HashSet<>();
            int edgeNum = 0;
            while (edgeNum < graphSize){
                int n1 = rand.nextInt(graphSize);
                int n2 = rand.nextInt(graphSize);
                Integer[] edge = new Integer[]{n1, n2};
                if(n1!=n2 & edgesChecker.contains(edge) == false){
                    edgesChecker.add(edge);
                    edgesChecker.add(new Integer[]{edge[1], edge[0]});
                    bigGraph.addEdge(n1, n2);
                    edgeNum++;
                }
            }
            print("Max node is: " + bigGraph.maxNeighborhoodWeight().id + " Weight is: "
                    + bigGraph.maxNeighborhoodWeight().getNeighborhood_weight());

        }
    }
    public static void print (String text){
        System.out.println(text);
    }
    public static void print ( int text){
        System.out.println(text);
    }
}
