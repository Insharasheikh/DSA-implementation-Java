// Bellman Ford Algorithm using edge list
import java.util.ArrayList;

public class BFA_EL{
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
    static void createGraph(ArrayList<Edge> graph) {
    
    graph.add(new Edge(0, 1, 2));
    graph.add(new Edge(0, 2, 4));
    graph.add(new Edge(1, 2, -4));
    graph.add(new Edge(2, 3, 2));
    graph.add(new Edge(3, 4, 4));
    graph.add(new Edge(4, 1, -1));
    }
    
    public static void bellmanFord(ArrayList<Edge> graph, int src,int V){
        int [] dist = new int[V];
        for(int i=0; i<V; i++){
                dist[i]= Integer.MAX_VALUE;
        }
        dist[src]= 0;

        for(int k=0; k<V-1; k++){
            for(int i =0; i<graph.size(); i++){
                    Edge e = graph.get(i);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u] != Integer.MAX_VALUE && dist[u]+e.wt < dist[v]){
                        dist[v]= dist[u]+e.wt;
                    }
            }
        }

        //negative cycle detection
        for(int i =0; i<graph.size(); i++){
            Edge e = graph.get(i);
            int u = e.src;
            int v = e.dest;
            if(dist[u] != Integer.MAX_VALUE && dist[u]+e.wt < dist[v]){
                System.out.println("Negative weight Cycle detected");
                return;
            }
        }
        

        for(int i =0; i<V; i++){
            System.out.print(dist[i]+" ");
        }
    }
    public static void main(String args[]) {
        int V = 5;
        ArrayList<Edge> graph = new ArrayList<>();
        createGraph(graph);
        int src = 0;
        bellmanFord(graph, src,5);
    }
}
