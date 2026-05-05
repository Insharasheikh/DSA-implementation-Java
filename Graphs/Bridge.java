// Bridge in a graph
import java.util.ArrayList;

public class Bridge {
    static class Edge{
        int src;
        int dest;
        Edge(int s,int d){
            this.src = s;
            this.dest = d;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]){//Adjacent list
        for(int i =0; i<graph.length; i++){
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

    static int time =0;
    public static void dfs(ArrayList<Edge> graph[],int parent, int[] dt, int[]low, boolean[]vis, int curr){
        vis[curr]= true;
        dt[curr] = low[curr] = ++time;

        for(int i =0; i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(e.dest == parent){
                continue;
            }else if(!vis[e.dest]){
                dfs(graph, curr, dt, low, vis, e.dest);
                low[curr]= Math.min(low[curr],low[e.dest]);
                if(dt[curr] < low[e.dest]){
                    System.out.println("Bridge: "+curr+"--->"+e.dest);
                }
            }else{//(vis[e.dest]){
                low[curr]= Math.min(low[curr],dt[e.dest]);
            }
        }
    }

    public static void getBridge(ArrayList<Edge> graph[],int V){
        time =0;
        int[] dt= new int[V]; //discovery time
        int[] low= new int[V];  //lowest discovery time;
        boolean[] vis = new boolean[V];

        for(int i =0; i<V; i++){
            if(!vis[i]){
                dfs(graph, -1, dt, low, vis, i);
            }
        }
    }

    public static void main(String[] args) {
        int V = 5;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        getBridge(graph, V);
        
    }
}
