import java.*;

public class Linklist {
  public static class Node {
    int data;
    Node next; // object caliing

    public Node(int data) {
      this.data = data;
      this.next = null;
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
    // step 3 = head = newnode
    tail = newnode;

  }

  public void print() {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.data + "->");
      temp = temp.next;
    }
    System.out.println("null");
  }

  public void add(int idx, int data) {
    if (idx == 0) {
      addFirst(data);// first one is head so head have important data
      return;
    }

    Node newnode = new Node(data);
    size++;
    Node temp = head;
    int i = 0;
    while (i < idx - 1) {
      temp = temp.next;
      i++;
    }

    // i == dix -1 ; temp -> prev
    newnode.next = temp.next;
    temp.next = newnode;
  }

  public int removeFirst() {
    if (size == 0) {
      System.out.println("LL is empty");
      return Integer.MAX_VALUE;
    } else if (size == 1) {
      int val = head.data;
      head = tail = null;
      size = 0;
      return val;
    }
    int val = head.data;
    head = head.next;
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

  public int itrSearch(int key) { // O(n)
    Node temp = head;
    int i = 0;
    while (temp != null) {
      if (temp.data == key) {
        return i;
      }
      temp = temp.next;
      i++;
    }
    return -1;
  }

  public int helper(Node head, int key) {
    if (head == null) {
      return -1;
    }
    if (head.data == key) {
      return 0;
    }
    int idx = helper(head.next, key);
    if (idx == -1) {
      return -1;
    }

    return idx + 1;
  }

  public int recSearch(int key) {
    return helper(head, key);
  }

  public void reverse() {// O(n)
    Node prev = null;
    Node curr = tail = head;
    Node next;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  public void deletenthfromend(int n) {
    // calculate size
    int sz = 0;
    Node temp = head;
    while (temp != null) {
      temp = temp.next;
      sz++;
    }

    if (n == sz) {
      head = head.next;// removo first
      return;
    }
    // sz - n
    int i = 1;
    int itofind = sz - n;
    Node prev = head;
    while (i < itofind) {
      prev = prev.next;
      i++;
    }
    prev.next = prev.next.next;
    return;
  }

  public Node findMid(Node head) { // helper
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next; // +1
      fast = fast.next.next; // +2
    }
    return slow; // slow is my midNode
  }

  public boolean checkPlindrome() {
    if (head == null || head.next == null) {
      return true;
    }
    // step -1 find mid
    Node midNode = findMid(head);
    // step -2 reverse 2nd half
    Node prev = null;
    Node curr = midNode;
    Node next;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    Node Right = prev;// right half head
    Node Left = head;
    // check left half and right half
    while (Right != null) {
      if (Left.data != Right.data) {
        return false;
      }
      Left = Left.next;
      Right = Right.next;
    }
    return true;
  }

  public static boolean Iscycle() {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;// +1
      fast = fast.next.next;// +2

      if (slow == fast) {
        return true;// cycle
      }
    }
    return false;// cycle doesn't exist
  }

  public static void removeCycle() {
    // detect cycle
    Node slow = head;
    Node fast = head;
    boolean cycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;// +1
      fast = fast.next.next;// +2

      if (slow == fast) {
        cycle = true;
        break;
      }
    }

    if (cycle == false)
      return;

    // find meeting point
    slow = head;
    Node prev = null; // last node
    while (slow != fast) {
      prev = fast;
      slow = slow.next;
      fast = fast.next;
    }
    // remove cycle -> last.next = null
    prev.next = null;
  }

  private Node gitMid(Node head) {
    Node slow = head;
    Node fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;// +1
      fast = fast.next.next;// +2
    }
    return slow;// mid node
  }

  private Node merge(Node head1, Node head2) {
    Node mergedLL = new Node(-1);
    Node temp = mergedLL;

    while (head1 != null && head2 != null) {
      if (head1.data <= head2.data) {
        temp.next = head1;
        head1 = head1.next;
        temp = temp.next;
      } else {
        temp.next = head2;
        head2 = head2.next;
        temp = temp.next;
      }
    }

      temp.next = head1;
      head1 = head1.next;
      temp = temp.next;
    }

  while(head2!=null)

  {
    temp.next = head2;
    head2 = head2.next;
    temp = temp.next;
  }return mergedLL.next;

  public Node mergeSort(Node head) {
    if (head == null || head.next == null) {
      return head;
    }
    // find mid
    Node mid = gitMid(head);
    // left & right MS
    Node rightHead = mid.next;
    mid.next = null;

    Node newLeft = mergeSort(head);
    Node newRight = mergeSort(rightHead);

    // merge
    return merge(newLeft, newRight);
  }

  public void zigZag() {
    // find mid
    Node slow = head;
    Node fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;// +1
      fast = fast.next.next;// +2
    }
    Node mid = slow;

    // reverse 2nd half
    Node curr = mid.next;
    mid.next = null;
    Node prev = null;
    Node next;

    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    Node left = head;
    Node right = prev;
    Node nextL, nextR;

    // alternate merge - zig- zag merge
    while (left != null && right != null) {
      nextL = left.next;
      left.next = right;
      nextR = right.next;
      right.next = nextL;

      left = nextL;
      right = nextR;
    }

  }

  public static void main(String[] args) {
    Linklist ll = new Linklist();
    // ll.addFirst(2);
    // ll.addFirst(1);
    // ll.addLast(4);
    // ll.addLast(5);
    // ll.addLast(6);

    // ll.add(2, 3);
    // ll.removeFirst();
    // ll.print();
    // ll.removelast();

    // System.out.println(ll.itrSearch(4));
    // System.out.println(ll.itrSearch(10));
    // System.out.println(ll.recSearch(4));
    // System.out.println(ll.recSearch(10));
    // System.out.println(ll.size);

    // ll.reverse();
    // ll.print();

    // ll.deletenthfromend(3);
    // ll.print();

    // ll.addLast(1);
    // ll.addLast(2);
    // ll.addLast(2);
    // ll.addLast(1);
    // ll.print();

    // System.out.println(ll.checkPlindrome());

    // head = new Node(1);
    // Node temp = new Node(2);
    // head.next = temp;
    // head.next.next = new Node(3);
    // head.next.next.next = temp;
    // // 1->2->3->|
    // // *-----|
    // System.out.println(Iscycle());
    // removeCycle();
    // System.out.println(Iscycle());

    ll.addFirst(1);
    ll.addFirst(2);
    ll.addFirst(3);
    ll.addFirst(4);
    ll.addFirst(5);
    ll.addFirst(6);

    // ll.print();
    ll.head = ll.mergeSort(ll.head);
    ll.print();

    ll.zigZag();
    ll.print();

  }
}