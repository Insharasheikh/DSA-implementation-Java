// Prim's Algorithm for Minimum Spanning Tree
import java.util.*;
public class PrimsAlgorithm{
    static class Edge {
        int src;
        int dest;
        int wt;
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));
    }

    public static class Pair implements Comparable<Pair>{
        int par;
        int node;
        int cost;
        public Pair(int parent,int node, int cost){
            this.par = parent;
            this.node = node;
            this.cost= cost;
        }

        @Override
        public int compareTo(Pair p2){
            return Integer.compare(this.cost,p2.cost);
        }
    }

    public static void primsAlgo(ArrayList<Edge> graph[],int V){
        PriorityQueue<Pair> pq= new PriorityQueue<>();
        boolean[] vis = new boolean[V];
        int mstCost=0;
        pq.add(new Pair(-1,0,0));
        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(!vis[curr.node]){
                vis[curr.node]= true;
                for(int i =0; i<graph[curr.node].size(); i++){
                    Edge e = graph[curr.node].get(i);
                    if(!vis[e.dest]){
                        pq.add(new Pair(curr.node,e.dest,e.wt));
                    }
                }

                if(curr.par != -1){
                    System.out.print(curr.par+"->"+curr.node+"="+curr.cost+ ", ");
                    mstCost+= curr.cost; 
                }
            }
        }
        System.out.println("total cost = "+ mstCost);
    }


    public static void main(String args[]) {
        int V = 4;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        primsAlgo(graph,V);
    }
}