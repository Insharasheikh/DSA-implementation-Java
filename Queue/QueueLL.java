// Queue implementation using Linked List
public class QueueLL {
    public static class Node{
            int data; 
            Node next;

            public Node(int data) {
                this.data = data;
                next= null;
            }        
    }

    public static class Queue{
        static Node head = null;
        static Node tail = null;

        public static boolean isEmpty(){
            return head ==null && tail == null;
        }

        public static void add(int data){
            Node newnode = new Node(data);
            if(isEmpty()){
                head = tail = newnode;
                return;
            }
            tail.next = newnode;
            tail = newnode;
        }

        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            int top = head.data;
            //if only one node
            if(tail == head){
                head = tail =null;
                return top;
            }
            head = head.next ;
            return top;
        }

        public static int peek(){
            return head.data;
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
