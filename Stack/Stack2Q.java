import java.util.*;
public class Stack2Q{
    Queue <Integer> q1 = new LinkedList <>();
    Queue <Integer> q2 = new LinkedList <>();

    public boolean isEmpty(){
        return q1.isEmpty();
    }

    //O(n) //add
    public void push(int data){
        q2.add(data);
        while(!q1.isEmpty()){
            q2.add(q1.poll());
        }

        Queue <Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    //0(1) //remove
    public int pop(){
        if(q1.isEmpty()){
            return -1;
        }

        return q1.poll();
    }

    //0(1) //peek
    public int peek(){
        if(q1.isEmpty()){
            return -1;
        }

        return q1.peek();
    }

    public static void main(String[] args){
        Stack2Q stack = new Stack2Q();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.pop();
        while(!stack.isEmpty()){
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}