// Circular Queue implementation using array
public class CircularQueueA {
    public static class Queue{
        static int rear =-1;
        static int front = -1;
        static int size;
        static int[] arr;
        public Queue(int n){        
        this.size =n;
        this.arr = new int[size];
        // this.rear =-1;
        // this.front=-1;
        }

        public static boolean isEmpty(){
            return rear==-1 && front == -1;
        }

        public static boolean isFull(){
            return (rear+1)%size == front;
        }

        //Enqueue
        public static void add(int data){
            if(isFull()){
                System.out.println("Queue is full");
                return;
            }

            //if only one element is there in queue
            if(front == -1){
                front = 0;
            }
            rear= (rear+1)%size;
            arr[rear]= data;
        }

        //Deque
        public static int remove(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            
            int top = arr[front];
            if(front == rear){
                rear = front = -1;
            }else{
                front = (front+1)%size;
            }
            return top;
        }

        //peek
        public static int peek(){
            if(isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }

            return arr[front];
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue(5);
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
