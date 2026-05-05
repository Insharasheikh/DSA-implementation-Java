//Linked List scratch code
public class LL {
    private Node head;
    private int size;

    LL(){
        size=0;
    }
    
    //node class
    public class Node{
        String data;
        Node next;
    
        Node(String data){
            this.data = data;
            this.next= null;
        }
    }

    //addfirst
    public void addFirst(String data){
        size++;
        Node newnode= new Node(data);
        newnode.next= head;
        head= newnode;
    }

    //addLast
    public void addLast(String data){
        size++;
        Node newnode= new Node (data);
        if(head== null){
            head= newnode;
            return;
        }

        Node lastnode = head;
        while(lastnode.next!= null){
            lastnode= lastnode.next;
        }
        lastnode.next= newnode;
    }

    //add in middle
    public void addMiddle(int idx, String data){
        if(idx<0 || idx> size){
            System.out.println("Invalid index");
            return;
        }

        size++;
        Node newnode= new Node(data);
        if(idx==0 || head == null){
            newnode.next= head;
            head= newnode;
            return;
        }
        Node currnode =head;
        for(int i =1; i<idx; i++){
            currnode= currnode.next;
        }
        
        newnode.next = currnode.next;
        currnode.next= newnode;
    }    
    //removeFirst
    public void removeFirst(){
        if(head== null){
            System.out.println("the list is empty");
            return;
        }

        size--;
        head= head.next;
        
    }

    //removelast
    public void removeLast(){
        if(head== null){
            System.out.println("the list is empty");
            return;
        }

        size--;
        if (head.next == null) {
            head = null;
            return;
        }

        Node currnode= head;
        while(currnode.next.next != null){
            currnode= currnode.next;
        }
        currnode.next= null;
    }

    //removeinMiddle
    public void removeMiddle(int idx){
        if(idx<0 || idx>= size){
            System.out.println("Invalid index");
            return;
        }
        size--;
        if(idx== 0){
            head= head.next;
            return;
        }
        
        Node currnode= head; 
        for(int i =1; i<idx;  i++){
            currnode =currnode.next;
        }
        
        currnode.next= currnode.next.next;
    }

    //print list
    public void print(){
        if(head == null){
            System.out.println("list is empty");
            return;
        }
        Node currnode = head;
        while(currnode != null){
            System.out.print(currnode.data +"->");
            currnode = currnode.next;
        }
        System.out.print("null");
        System.out.println();
    }

    //size of list
    public int size(){
        return size;
    }   

    //reverseIterate
    public void reverseIterate(){
        if(head == null || head.next == null){
            return;
        }

        Node prevNode = head;
        Node currNode = head.next;
        while (currNode != null) { 
            Node nextNode = currNode.next;
            currNode.next = prevNode;

            //update 
            prevNode= currNode;
            currNode= nextNode;
        }

        head.next= null;
        head= prevNode;
    }

    //reverse recursive
    public Node reverseRecursive(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node newHead= reverseRecursive(head.next);
        head.next.next = head;
        head.next= null;

        return newHead;
    }

    public static void main(String[] args){
        LL list= new LL();
        list.addFirst("1");
        list.addFirst("2");
        list.addLast("3");
        list.addMiddle(2,"4");
        list.print();
        System.out.println(list.size());

        list.removeFirst();
        list.removeLast();
        list.removeMiddle(0);
        list.print();
        System.out.println(list.size());
        list.addFirst("3");
        list.addFirst("2");
        list.addFirst("1");
        list.print();

        list.reverseIterate();
        list.print();

        list.head = list.reverseRecursive(list.head);
        list.print();

    }
}
