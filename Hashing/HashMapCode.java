// HashMap implementation 
import java.util.*;
public class HashMapCode{
    public static class HashMap<K,V>{//generics//parametrized types
        public class Node{
            K key;
            V value;
            public Node(K key, V value){
                this.key = key;
                this.value = value;
            }
        }

        private int n; //no. of nodes(key,value pairs)
        private int N;//size of array,no. of buckets
        private static double k = 2.0;
        private LinkedList<Node> buckets[];//array of linkedlist

        @SuppressWarnings("unchecked")
        public HashMap(){//constructor
            this.N = 4;
            this.buckets=  new LinkedList[4] ;
            for(int i =0; i<4; i++){
                this.buckets[i] = new LinkedList<>();//before each idx of array had null,ccreate empty linkedlist on each idx of array  
            }
        }

        private int hashFunction(K key){//gives bi
            int bi = key.hashCode();
            return Math.abs(bi)%N;   // %N to get value between 0 and N //absolute is used to make value positive
            //OR return (bi % N + N) % N;
        }

        private int searchInLL(K key, int bi){//eturns di
            LinkedList<Node> ll = buckets[bi];//get linked list
            for(int i =0; i<ll.size(); i++){
                if(key.equals(ll.get(i).key)){
                    return i;
                }
            }
            return -1;
        }

        //rehash function
        @SuppressWarnings("unchecked")
        private void rehash(){
            LinkedList<Node> oldBucket [] = buckets;
            N= N*2;
            buckets= new LinkedList[N];
            n=0;
            
            for(int i =0; i<N; i++){
                buckets[i]= new LinkedList<>();
            }

            for(int i =0; i<oldBucket.length; i++){//assign nofdes to new array
                LinkedList <Node> ll = oldBucket[i]; //bi loop
                for(int j =0; j<ll.size(); j++){
                    Node node = ll.get(j);//di loop
                    put(node.key, node.value);
                }
            }
        }

        //put // average:O(1) worst:O(n)
        public void put(K key, V value){
            int bi = hashFunction(key);//bucket index //hashfunction is blackbox
            int di = searchInLL(key,bi);//node index in linked list // data index

            if(di == -1){//doesnt exist already
                buckets[bi].add(new Node(key,value));//make new index
                n++;
            }else{//0<=bi<n //exist already
                Node node = buckets[bi].get(di);//get value
                node.value = value;//update value
            }

            double lambda = (double) n/N;
            if(lambda > k){
                rehash();
            }
        }

        public boolean containsKey(K key){
            int bi = hashFunction(key);//bucket index
            int di = searchInLL(key,bi);//node index in linked list

            if(di == -1){//doesnt exist already
                return false;
            }else{//0<=bi<n //exist already
                return true;
            }

            // OR return di != -1;
        }

        public V remove(K key){
            int bi = hashFunction(key);//bucket index
            int di = searchInLL(key,bi);//node index in linked list

            if(di == -1){//doesnt exist already
                return null;
            }else{//0<=bi<n //exist already
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            }
        }

        public V get(K key){
            int bi = hashFunction(key);//bucket index
            int di = searchInLL(key,bi);//node index in linked list

            if(di == -1){//doesnt exist already
                return null;
            }else{//0<=bi<n //exist already
                Node node = buckets[bi].get(di);
                return node.value;
            }
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();

            for(int i=0; i<buckets.length; i++){//bi
            LinkedList<Node> ll= buckets[i];
            for(int j= 0 ; j<ll.size(); j++){//di
                Node node = ll.get(j);
                keys.add(node.key);
            }
            }
            return keys;
        }

        public boolean isEmpty(){
            return n == 0;
        }

    }

    public static void main(String args[]) {
    HashMap<String, Integer> map = new HashMap<>();//using the class we made
    map.put("India", 190);
    map.put("China", 200);
    map.put("US", 50);

    ArrayList<String> keys = map.keySet();
    for(int i=0; i<keys.size(); i++) {
    System.out.println(keys.get(i)+" "+map.get(keys.get(i)));
    }

    map.remove("India");
    System.out.println(map.get("India"));
    System.out.println(map.containsKey("China"));
    }
}

