// Kosaraju's Algorithm 
import java.util.*;
public class Kosaraju {
    static class Edge {
        int src;
        int dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<Edge>();
        }
        graph[0].add(new Edge(0, 2));
        graph[0].add(new Edge(0, 3));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 1));
        graph[3].add(new Edge(3, 4));
    }


    public static void topologicSort(ArrayList<Edge> graph[],int curr,boolean[] vis, Stack<Integer> s){
        vis[curr]=true;

        for(int i =0; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                topologicSort(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }

    public static void DFS(ArrayList<Edge> graph[],int curr, boolean vis[]){
        if(vis[curr]){
            return;
        }
        System.out.print(curr+" ");
        vis[curr]= true;
        for(int i =0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                DFS(graph, e.dest, vis);
            }
        }
    }

    public static void KosarajuAlgo(ArrayList<Edge> graph[],int V){
        //step1
        Stack <Integer> s= new Stack<>();
        boolean[]vis = new boolean[V];
        for(int i=0; i<V; i++) {
            if(!vis[i]) {
                topologicSort(graph, i, vis,s);
            }
        }

        //step2
        ArrayList<Edge> transpose[]= new ArrayList[V];
        for(int i =0; i<V; i++){
                transpose[i]= new ArrayList<>();
        }
        for(int i =0; i<V; i++){
            vis[i]= false;
            for(int j =0; j<graph[i].size(); j++){
                Edge e = graph[i].get(j);
                transpose[e.dest].add(new Edge(e.dest,e.src));
            }
        }

        //step3
        while(!s.isEmpty()){
            int curr= s.pop();
            if(!vis[curr]){
                DFS(transpose, curr, vis);
                System.out.println();
            }
        }
    }

    public static void main(String args[]) {
        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        KosarajuAlgo(graph, V);
    }
}