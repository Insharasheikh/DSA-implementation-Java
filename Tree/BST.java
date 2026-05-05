//implementation of Binary Search Tree
package Tree;
import java.util.*;
public class BST {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static class BinaryST{

        //insert(build)
        public static Node insert(Node root, int val){
            if(root == null){
                root= new Node(val);
                return root;
            }

            if(val < root.data){
                root.left = insert(root.left, val);
            }else{ //(val > root.data)
                root.right = insert(root.right, val);
            }
            return root;
        }

        //Inorder
        public static void Inorder(Node root){
            if(root == null){
                return;
            }

            Inorder(root.left);
            System.out.print(root.data+" ");
            Inorder(root.right);
        }

        //Search
        public static boolean search(Node root, int val){
            if(root == null){
                return false;
            }

            if(val < root.data){
                return search(root.left, val);
            }else if(val == root.data){
                return true;
            }else{ //(val > root.data)
                return search(root.right, val);
            }
        }

        //delete
        public static Node delete(Node root,int val){
            if(root == null){
                return root;
            }

            if(val < root.data){
                root.left = delete(root.left, val);
            }else if(val > root.data){
                root.right = delete(root.right, val);
            }else{ //(val == root.data)
                if(root.left==null && root.right ==null){//0 child
                    return null;
                }else if (root.left == null){//1 child
                    return root.right;
                }else if(root.right == null){//1 child
                    return root.left;
                }else{//2 child
                    Node successor = inorderSuccessor(root.right);
                    root.data = successor.data; 
                    root.right = delete(root.right, successor.data);
                }
            }
            return root;
        }

        public static Node inorderSuccessor(Node root){
            while(root.left != null){
                root = root.left;
            }
            return root;
        }


        //print in range
        public static void printInRange(Node root, int x, int y){
            if(root == null){
                return;
            }

            if(x<= root.data && root.data <= y){
                printInRange(root.left, x, y);
                System.out.print(root.data+" ");
                printInRange(root.right, x, y);
            }else if(root.data > y){
                printInRange(root.left, x, y);
            }else{//root.data <x
                printInRange(root.right, x, y);
            }   
        }

        //root to leaf path
        public static void RootToLeaf(Node root, ArrayList <Integer> arr){
            if(root == null){
                return;
            }
            arr.add(root.data);
            if(root.left == null && root.right == null){
                printPath(arr);
            }else{
                RootToLeaf(root.left, arr);
                RootToLeaf(root.right, arr);
            }
            arr.remove(arr.size()-1);
        }
        public static void printPath(ArrayList <Integer> arr){
            for(int i =0; i<arr.size(); i++){
                System.out.print(arr.get(i)+" ");
            }
            System.out.println();
        }

    }
    
    public static void main(String[] args){
        int[] values = {8,5,3,1,4,6,10,11,14};
        Node root = null;
        
        for(int i =0; i<values.length; i++){
            root = BinaryST.insert(root,values[i]);
        }

        BinaryST.Inorder(root);
        System.out.println();

        System.out.println(BinaryST.search(root, 11));

        root = BinaryST.delete(root, 4);
        BinaryST.Inorder(root);
        System.out.println();

        root = BinaryST.insert(root,4);
        root = BinaryST.delete(root, 10);
        BinaryST.Inorder(root);
        System.out.println();
        
        root = BinaryST.insert(root, 10);
        root = BinaryST.delete(root, 5);
        BinaryST.Inorder(root);
        System.out.println();
        
        root = BinaryST.insert(root, 5);

        BinaryST.printInRange(root, 5, 11);
        System.out.println();

        BinaryST.RootToLeaf(root, new ArrayList<>());

    }
}
