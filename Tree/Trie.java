//  Trie implementation
package Tree;
public class Trie {
    public static class Node{
        Node[] children =  new Node[26];
        boolean eow;//end of word //false 

        Node(){
            for(int i =0; i<children.length; i++){
                children[i]=null;
            }
        }
    }
    public static Node root = new Node();

    public static void insert(String word){
        Node curr = root;
        for(int i =0; i<word.length(); i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]== null){
                curr.children[idx]=new Node();
            }
                curr = curr.children[idx];
        }
        
        curr.eow = true;
    }

    public static boolean search(String word){//O(L),l is length of word
        Node curr = root;
        for(int i =0; i<word.length(); i++){
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]== null){
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.eow;
    }

    public static boolean wordBreak(String word){//  O(n^2 *L)
        if(word.length()==0){
            return true;
        }
        Node curr = root;
        for(int i =1; i<=word.length(); i++){
            String first= word.substring(0,i);
            String second = word.substring(i);
            if(search(first) && wordBreak(second)){
                return true;
            }
        }
        return false;
    }

    public static boolean startsWith(String prefix){
        
        Node curr = root;
        for(int i =0; i<prefix.length(); i++){
            int idx = prefix.charAt(i)-'a';
            if(curr.children[idx]== null){
                return false;
            }
            curr= curr.children[idx];
        }
        return true;
    }

    public static int count(Node root){
        if(root ==null){
            return 0;
        }

        int count=0;
        for(int i =0; i<26; i++){
            if(root.children[i]!= null){
                count += count(root.children[i]);
            }
        }
        return count+1;
    }

    static String ans ="";
    public static String longestPrefix(Node root,StringBuilder temporary){//O(L),l is length of word
        if(root == null){
            return ans;
        }
        for(int i =0; i<26; i++){
            if(root.children[i] !=null && root.children[i].eow){
                temporary.append((char)((i)+'a'));
                if(temporary.length()>ans.length()){
                ans = temporary.toString();
                }
                longestPrefix(root.children[i], temporary);
                temporary.deleteCharAt(temporary.length()-1);
            }
            
        }
        return ans;
    }//Longest Word With All Prefixes Present.

    public static String longestCommonPrefix(Node root){
    StringBuilder sb = new StringBuilder();
    Node curr = root;

    while(true){
        int count = 0;
        int idx = -1;

        for(int i=0; i<26; i++){
            if(curr.children[i] != null){
                count++;
                idx = i;
            }
        }

        if(count != 1 || curr.eow)
            break;

        sb.append((char)(idx + 'a'));
        curr = curr.children[idx];
    }

    return sb.toString();
}

    public static void main(String[] args){
        //(for insert and search function checking)
        //String[] words= {"the","a","there","their","any","thee"};
        // for(String word: words){
        //     insert(word);
        //     // System.out.println("inserted " + word);
        // }
        // System.out.println("thee -> " + search("thee"));
        // System.out.println("thor -> " + search("thor"));


        //(for wordBreak function check)
        // String[] words= {"i","like","sam","samsung","mobile"};
        // for(String word: words){
        //     insert(word);
        //     // System.out.println("inserted " + word);
        // }
        // System.out.println(wordBreak("ilikesung"));


        //(for startsWith function check)
        // String[] words= {"apple","app","mango","man","woman"};
        // for(String word: words){
        //     insert(word);
        //     // System.out.println("inserted " + word);
        // }
        //System.out.println(startsWith("ap"));


        //(for unique substring(prefix) check)
        // String str = "ababa";
        // for(int i=0; i<str.length(); i++){
        //     String suffix = str.substring(i);
        //     System.out.print(suffix+" ");
        //     insert(suffix);
        // }System.out.println();
        // System.out.println(count(root));

        //(for prefix questions)
        // String[] words= {"a","banana","app","appl","ap","apply","apple"};
        // for(String word: words){
        //     insert(word);
        //     // System.out.println("inserted " + word);
        // }
        // StringBuilder temp =new StringBuilder("");
        // System.out.println(longestPrefix(root,temp));

        // String[] words= {"a","app","appl","ap","apply","apple"};
        // for(String word: words){
        //     insert(word);
        //     // System.out.println("inserted " + word);
        // }
        // System.out.println("longestCommonPrefix: "+ longestCommonPrefix(root));

    }
}
