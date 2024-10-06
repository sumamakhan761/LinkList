public class dublylinklist {

  public class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  public static Node head;
  public static Node tail;
  public static int size;

  public void addFirst(int data) {
    // step 1 = createt new node
    Node newnode = new Node(data);
    size++;
    if (head == null) {
      head = tail = newnode;
      return;
    }
    // step 2 = newNode = head
    newnode.next = head;// link
    head.prev = newnode;
    // step 3 = head = newnode
    head = newnode;
  }

  public void addLast(int data) {
    // step 1 = createt new node
    Node newnode = new Node(data);
    size++;
    if (head == null) {
      head = tail = newnode;
      return;
    }
    // step 2 = newNode = head
    tail.next = newnode;// link
    newnode.prev = tail;
    // step 3 = head = newnode
    tail = newnode;
  }

  public void print() {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "<->");
      temp = temp.next;
    }
    System.out.println("null");
  }

  public int removeFirst() {
    if (size == 0) {
      System.out.println("DLL is empty");
      return Integer.MAX_VALUE;
    } else if (size == 1) {
      int val = head.data;
      head = tail = null;
      size--;
      return val;
    }
    int val = head.data;
    head = head.next;
    head.prev = null;
    size--;
    return val;
  }

  public int removelast() {
    if (size == 0) {
      System.out.println("LL is empty");
      return Integer.MAX_VALUE;
    } else if (size == 1) {
      int val = head.data;
      head = tail = null;
      size = 0;
      return val;
    }
    // prev -> i == size -2 ;
    Node prev = head;
    for (int i = 0; i < size - 2; i++) {
      prev = prev.next;
    }

    int val = prev.next.data; // tail.next;
    prev.next = null;
    tail = prev;
    size--;
    return val;
  }

  public void reverse() {// O(n)
    Node prev = null;
    Node curr = tail = head;
    Node next;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      curr.prev = next;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  public static void main(String[] args) {
    dublylinklist dll = new dublylinklist();
    // dll.addFirst(3);
    // dll.addFirst(2);
    // dll.addFirst(1);
    dll.addLast(1);
    dll.addLast(2);
    dll.addLast(3);
    dll.addLast(4);
    dll.addLast(5);
    System.out.println(dll.size);
    dll.print();
    // dll.removeFirst();
    // dll.removelast();
    dll.reverse();
    dll.print();
  }
}
