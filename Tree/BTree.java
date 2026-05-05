// implementation of Binary Tree
package Tree;
import java.util.*;

public class BTree{
    static class Node{
        int data;
        Node left;
        Node right;

        Node (int data){
            this.data = data;
            this.left= null;
            this.right= null;
        }
    }

    static class BinaryTree{
        static int idx= -1;

        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]== -1){
                return null;
            }
            Node newNode = new Node (nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        //Preorder O(n)
        public static void preOrder(Node root){
            if(root == null){
                return;
            }

            System.out.print(root.data+" ");
            preOrder(root.left);
            preOrder(root.right);
        }

        //InOrder O(n)
        public static void InOrder(Node root){
            if(root == null){
                return;
            }
            InOrder(root.left);
            System.out.print(root.data+ " ");
            InOrder(root.right);
        }

        //PostOrder O(n)
        public static void PostOrder(Node root){
            if(root == null){
                return;
            }
            PostOrder(root.left);
            PostOrder(root.right);
            System.out.print(root.data+ " ");
        }

        //Level Order O(n)
        public static void LevelOrder(Node root){
            if(root == null){
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while(!q.isEmpty()){
                Node curr = q.remove();
                if(curr == null){
                    System.out.println();
                    if(q.isEmpty()){
                        break;
                    }else{
                        q.add(null);
                    }
                }else{
                    System.out.print(curr.data+" ");
                    if(curr.left != null){
                        q.add(curr.left);
                    }
                    if(curr.right != null){
                        q.add(curr.right);
                    }
                }
            }
        }

        //Count of nodes O(n)
        public static int countOfNodes(Node root){
            if(root == null){
                return 0;
            }

            int leftsub = countOfNodes(root.left);
            int rightsub = countOfNodes(root.right);

            return leftsub+ rightsub + 1;
        }

        //sum of nodes O(n)
        public static int sumOfNodes(Node root){
            if(root == null){
                return 0;
            }
            int leftsum = sumOfNodes(root.left);
            int rightsum = sumOfNodes(root.right);

            return leftsum + rightsum + root.data;
        }

        // height (EDGE BASED) O(n)
        // empty tree = -1
        // leaf = 0
        public static int height(Node root){
            if(root == null){
                return -1;     // important change
            }

            int leftheight = height(root.left);
            int rightheight = height(root.right);

            return Math.max(leftheight, rightheight) + 1;
        }

        // depth of a node (EDGE BASED) O(n)
        // returns -1 if value not found
        public static int depth(Node root, int key, int depth){
            if(root == null){
                return -1;
            }
            if(root.data == key){
                return depth; // level = edges from root
            }

            int left = depth(root.left, key, depth + 1);
            if(left != -1) return left;

            return depth(root.right, key, depth + 1);
        }

        //Level O(n)
        public static int level(Node root, int key, int level){
            if(root == null){
                return -1;   // not found
            }

            if(root.data == key){
                return level;   // node-based level
            }

            int left = level(root.left, key, level + 1);
            if(left != -1) return left;

            return level(root.right, key, level + 1);
        }

        // diameter O(n^2) (EDGE BASED)
        public static int diameter(Node root){
            if(root == null){
                return 0;
            }
            int diam1 = diameter(root.left);
            int diam2 = diameter(root.right);

            // crossing diameter in edges
            int diam3 = height(root.left) + height(root.right) + 2;

            return Math.max(Math.max(diam1, diam2), diam3);
        }

        // diameter O(n) (EDGE BASED)
        static class treeInfo{
            int ht;
            int diam;

            treeInfo(int ht, int diam){
                this.ht = ht;
                this.diam = diam;
            }
        }

        public static treeInfo diameter2(Node root){
            if(root == null){
                return new treeInfo(-1, 0);   // height=-1 for edges
            }

            treeInfo leftTI = diameter2(root.left);
            treeInfo rightTI = diameter2(root.right);

            int myheight = Math.max(leftTI.ht, rightTI.ht) + 1;

            int diam1 = leftTI.diam;
            int diam2 = rightTI.diam;

            int diam3 = leftTI.ht + rightTI.ht + 2; // edges

            int mydiam = Math.max(diam3, Math.max(diam1, diam2));

            return new treeInfo(myheight, mydiam);
        }

        public static int sumOfKthLevelRec(Node root, int k){
            if(root == null){
                return 0;
            }
            if(k ==0){
                return root.data;
            }
            
            return sumOfKthLevelRec(root.left, k-1)+ sumOfKthLevelRec(root.right, k-1);
        }

        public static int sumOfKthLevelBFS(Node root, int k) {
            if(root == null) return 0;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            int level = 1;
            
            while(!q.isEmpty()) {
                int size = q.size();
                int sum = 0;
                for(int i=0; i<size; i++){
                    Node node = q.poll();
                    sum += node.data;
                    if(node.left != null) q.add(node.left);
                    if(node.right != null) q.add(node.right);
                }
                if(level == k) return sum;
                level++;
            }
            return 0; // If k is greater than height
        }

    }

    public static void main(String[] args){
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        BinaryTree tree = new BinaryTree();

        Node root = tree.buildTree(nodes);

        System.out.println(root.data);
        tree.preOrder(root);
        System.out.println();
        tree.InOrder(root);
        System.out.println();
        tree.PostOrder(root);
        System.out.println();
        tree.LevelOrder(root);

        System.out.println(tree.countOfNodes(root));
        System.out.println(tree.sumOfNodes(root));

        System.out.println(tree.height(root));            // edge-based height
        System.out.println(tree.diameter(root));          // O(n^2)
        System.out.println(tree.diameter2(root).diam);    // O(n)

        System.out.println(tree.depth(root, 6,0));          
        System.out.println(tree.level(root, 6,1)); 
        System.err.println(tree.sumOfKthLevelRec(root, 2));
        System.err.println(tree.sumOfKthLevelBFS(root, 2));
    }
}

