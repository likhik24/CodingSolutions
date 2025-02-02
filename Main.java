import Graph.*;
import Strings.*;
import Tree.*;
import Stack.*;
import Arrays.*;
import Queue.*;
import LinkedList.*;
import DynamicProgramming.*;
import HashMap.*;
import MergeIntervals.*;
import Matrix.*;
import Trie.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.http.client.utils.URIBuilder;

import java.util.*;

public class Main {
    public static void main(String[] args) {
       
        int[] arr = {111, 8, 3, 2, 9, 5, 18};
        QuickSort s = new QuickSort(arr);
        DFS D = new DFS();
        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Printing Graph.DFS");
        D.dfs(g.adj);
        System.out.println("Printing toposort");
        TopologicalSort topo = new TopologicalSort();
        topo.topologicalSort(g.adj);
        System.out.println("Find Discovery times for current graph ");
        ArticulationPoint point = new ArticulationPoint();
        point.findArticulationPoint(g.adj);
        System.out.println("Find Lowest Children");
        for (int i = 0; i < point.lowestchildren.length; i++) {
            System.out.println("lowest child of " + i + " is " + point.lowestchildren[i]);
        }


        g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
         g.addEdge(2, 0);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 6);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        System.out.println("Find Discovery times for new graph ");
        point = new ArticulationPoint();
        point.findArticulationPoint(g.adj);
        System.out.println("Find Lowest Children");
        for (int i = 0; i < point.lowestchildren.length; i++) {
            System.out.println("lowest child of " + i + " is " + point.lowestchildren[i]);
        }



        ArticulationEdges articulationEdges = new ArticulationEdges();
        List<List<Integer>> edges =  articulationEdges.criticalConnections(7, g.adj);
        for(List<Integer> edge : edges) {
            System.out.println("Articulation edge from " +  edge.get(0) + " to " + edge.get(1));
        }

        System.out.println("Finding if graph is bipartite");
        BiPartiteGraph graph = new BiPartiteGraph();
        System.out.println(graph.isBiPartite( g.adj));

        TarjansAlgorithm tarjansAlgorithm = new TarjansAlgorithm();
        tarjansAlgorithm.findStronglyConnectedComponents(g.adj);

        SkyLine skyLine = new SkyLine();
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        List<int[]> resultSkyLine = skyLine.getSkyline(buildings);
        for(int[] skyline : resultSkyLine) {
            System.out.println("x coordinate is " + skyline[0] + " with height " + skyline[1]);
        }
        MaximumOfMinimumSubArrays maximumOfMinimumSubArrays = new MaximumOfMinimumSubArrays();
        int[] result = maximumOfMinimumSubArrays.findMaximums(new int[]{2,8,1,10,4,10,8,9,3});
        System.out.println("Maximum of minimum subarrays ");
        for(int res:result) {
            System.out.println(res);
        }

        WildCardMatching stringMatch = new WildCardMatching();
        System.out.println("is pattern matching the string  ? " );

        boolean res = stringMatch.isMatch("catsandcat","cat**ca*");
        System.out.println("catsandcat "+res);


        res = stringMatch.isMatch("cat","c*t");
        System.out.println("cat c*t "+res);

        res = stringMatch.isMatch("cat","*t");
        System.out.println("cat *t " +res);

        res = stringMatch.isMatch("dog","c*t");
        System.out.println("dog c*t "+res);

        res = stringMatch.isMatch("cat","*t*a*c*");
        System.out.println("cat  *t*a*c* "+res);

        res = stringMatch.isMatch("catcatcat","cat*cat*cat***");
        System.out.println("catcatcat  cat*cat*cat*** "+res);

        res = stringMatch.isMatch("fdajhfjdsacatcatcatlsaflk","****cat*cat*cat***");
        System.out.println("fdajhfjdsacatcatcatlsaflk  ****cat*cat*cat*** "+res);


        res = stringMatch.isMatch("catdog","cat*cat*");
        System.out.println("catdog  cat*cat* "+res);


        res = stringMatch.isMatch("", "*");
        System.out.println(" *"+res);


        res = stringMatch.isMatch("cat", "cat*cat");
        System.out.println("cat cat*cat "+res);


        res = stringMatch.isMatch("catat", "ca*t");
        System.out.println("catat ca*t "+res);

        binaryTree bTree = new binaryTree();
        System.out.println("In order traversal is");
        bTree.constructNode(new int[]{3,9,1,2,20,15,7}, new int[]{1,9,2,3,15,20,7});

        System.out.println("Merge Sorting array");
        MergeSort merge = new MergeSort();
        int[] result1 = merge.mergeSort(new int[]{8,9,1,3,13,2,11}, 0, 6);
        for(int r: result1) {
            System.out.println(r);
        }

        System.out.println("Performing Binary Search");
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
        System.out.println(search.binarySearch(new int[]{4,5,6,7,0,1,2}, 2));
        System.out.println(search.binarySearch(new int[]{10, 11, 4, 5, 6 ,7 ,8, 9}, 8));

        //Find Maximum score of cards we can pick
        System.out.println("Maximum cards we can pick");
        MaximumScoreCardsWeCanPick cardsSum = new MaximumScoreCardsWeCanPick();
       System.out.println( cardsSum.maxScore(new int[]{1,2,3,4,5,6,1}, 3));

    }
}

