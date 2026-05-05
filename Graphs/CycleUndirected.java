// Cycle detection in a undirected graph using DFS
import java.util.*;
public class CycleUndirected{
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 2));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 0));
        graph[3].add(new Edge(3, 4));
        graph[4].add(new Edge(4, 3));
        }

    public static  boolean isCyclic(ArrayList<Edge> graph[],int curr,boolean[] vis,int parent){
        vis[curr]=true;
        for(int i =0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(e.dest!=parent && vis[e.dest]){
                return true;
            }else if(!vis[e.dest]){
                if(isCyclic(graph, e.dest, vis, curr)){
                    return true;
                }
            }
        }
        vis[curr]=false;
        return false;
    }

    public static boolean cycleDetection( ArrayList<Edge> graph[],int V){
        boolean [] vis= new boolean[V];
        for(int i =0; i<V; i++){
        if(!vis[i]){
            if(isCyclic(graph,i,vis,-1)){
                return true;
                }
            }
        }
        return false;
    }
    
    public static void main(String args[]) {
    /*
           0 ------- 3
          /|         |
         / |         |
        1  |         4
         \ |
          \|
           2
    */
    int V = 5;
    ArrayList<Edge> graph[] = new ArrayList[V];
    createGraph(graph);
    System.out.println(cycleDetection(graph,V));
    }
}