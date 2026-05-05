import java.util.*;
public class Stack2Que{
    Queue <Integer> q1 = new LinkedList <>();
    Queue <Integer> q2 = new LinkedList <>();

    public boolean isEmpty(){
        return q1.isEmpty();
    }

    //O(1) //add
    public void push(int data){
        q1.add(data);
    }

    //0(n) //remove
    public int pop(){
        if(q1.isEmpty()){
            return -1;
        }

        while(!q1.isEmpty()){
            q2.add(q1.remove());
        }
        int top = q2.remove();
        while(!q2.isEmpty()){
            q1.add(q2.remove());
        }
        return top;
    }

    //0(n) //peek
    public int peek(){
        if(q1.isEmpty()){
            return -1;
        }

        while(!q1.isEmpty()){
            q2.add(q1.remove());
        }
        int top = q2.peek();
        while(!q2.isEmpty()){
            q1.add(q2.remove());
        }
        return top;
    }

    public static void main(String[] args){
        Stack2Q stack = new Stack2Q();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        stack.pop();
        stack.pop();
        while(!stack.isEmpty()){
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}
