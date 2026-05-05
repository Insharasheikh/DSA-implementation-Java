// Queue implementation using 2 stacks
import java.util.*;
public class Queue2Stack {
    public static class Queue{

        Stack <Integer> s1= new Stack<>();
        Stack <Integer> s2= new Stack<>();
        
        public boolean isEmpty(){
            return s1.isEmpty();
        }

        //0(1)
        public void add(int data){
            s1.push(data);
        }

        //0(n)
        public int remove(){
            if(isEmpty()){
                System.out.println("The queue is empty");
                return -1;
            }
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            int top = s2.pop();
            while(!s2.isEmpty())
                s1.push(s2.pop());
            return top;
        }

        //O(n)
        public int peek(){
            if(isEmpty()){
                System.out.println("The queue is empty");
                return -1;
            }
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            int top = s2.peek();
            while(!s2.isEmpty())
                s1.push(s2.pop());
            return top;
        }
    }
        public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);

        q.remove();

        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        } 
    }
}
