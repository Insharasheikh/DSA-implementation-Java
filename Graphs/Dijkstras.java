// Dijkstra's Algorithm 
import java.util.*;
public class Dijkstras {
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
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    public static class Pair implements Comparable<Pair>{

        int n;
        int path;
        public Pair(int n, int path) {
            this.n = n;
            this.path =path;
        }

        @Override
        public int compareTo(Pair p2){
            return Integer.compare(this.path, p2.path);
        }
    }
    
    public static int[] Dij(ArrayList<Edge> [] graph, int src,int V){
        PriorityQueue <Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[V];
        int[] dist = new int [V];
        for(int i =0; i<V; i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        pq.add(new Pair(src,0));

        while(!pq.isEmpty()){
            Pair curr = pq.remove();
            if(! vis[curr.n]){
                vis[curr.n]= true;
                for(int i =0; i<graph[curr.n].size(); i++){
                    Edge e = graph[curr.n].get(i);
                    int v= e.dest;
                    int u= curr.n; //e.src; 
                    if(!vis[v] && dist[u]!= Integer.MAX_VALUE &&  dist[u]+e.wt< dist[v]){
                        dist[v]= dist[u]+ e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        return dist;
    }
    public static void main(String args[]) {
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        int dist[]= (Dij(graph,0, V));
        for(int i =0; i<V; i++){
            System.out.print(dist[i]+" ");
        }
    }
}
