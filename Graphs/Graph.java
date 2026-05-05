// Graph implementation 
import java.util.*;
public class Graph {
    static class Edge{
        int src;
        int dest;
        int wt;
        Edge(int s,int d,int w){
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    static void createGraph(ArrayList<Edge> graph[]){//Adjacent list
        for(int i =0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));
        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));
        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));
        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));
        graph[6].add(new Edge(6, 5, 1));
    }

    public static void BFS(ArrayList<Edge> graph[],int V, boolean vis[], int start){
        Queue<Integer> q= new LinkedList<>();
        q.add(start);
        vis[start]=true;
        while(!q.isEmpty()){
            int curr = q.remove();
            System.out.print(curr+" ");
            vis[curr]= true;
            for(int i =0; i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                if(!vis[e.dest]){
                    q.add(e.dest);
                    vis[e.dest]=true;
                }
            }
        }
    }     
    public static void DFS(ArrayList<Edge> graph[],int curr, boolean vis[]){
         if(vis[curr]){
            return;
        }
        System.out.print(curr+" ");
        vis[curr]= true;
        for(int i =0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            DFS(graph, e.dest, vis);
        }
    }

    public static void allPaths(ArrayList<Edge> graph[],int curr,int target,String path ,boolean vis[]){
        if(curr == target){
            System.out.print(path+"  ");
            return;
        }
        vis[curr]=true;
        for(int i =0; i<graph[curr].size(); i++){
            Edge e = graph[curr].get(i);
            if(!vis[e.dest]){
                allPaths(graph, e.dest,5,path+e.dest, vis);
            }
        }
        vis[curr]= false;
    }

    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        /*
              1 ---- 3
             /       | \
            0        |  5 -- 6
             \       | /
              2 ---- 4
        */
        createGraph(graph);
        for(int i =0; i<graph[2].size();i++){
            Edge e = graph[2].get(i);
            System.out.println(e.dest+" "+e.wt);
        }

        boolean[] vis = new boolean[V];
        for(int i =0; i<V; i++){
            if(!vis[i]){
                BFS(graph,V,vis,i);
            }
        }
        System.out.println();
        boolean[] vis2 = new boolean[V];
        for(int i =0; i<V; i++){
            if(!vis2[i]){
                DFS(graph,i,vis2);
            }
        }

        boolean[] vis3 = new boolean[V];
        allPaths(graph, 0, 5, "0", vis3);

    }
}

