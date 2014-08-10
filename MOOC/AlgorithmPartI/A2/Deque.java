import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private Node first, last;
   private int size;
   private class Node{
     Item item;
     Node prev, next;
   }
   public Deque()                           // construct an empty deque
   {
     first = null;
     last = null;
     size = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   {
     return (first == null);
   }
   public int size()                        // return the number of items on the deque
   {
     return size;
   }
   public void addFirst(Item item)          // insert the item at the front
   {
     if (item == null) throw new NullPointerException();
     Node newNode = new Node();
     newNode.item = item;
     newNode.prev = null;
     if (size == 0){
       newNode.next = null;
       first = newNode;
       last = newNode;
     }else{
       Node oldFirst = first;
       oldFirst.prev = newNode;
       newNode.next = oldFirst;
       first = newNode;
     } 
     size++;
   }
   public void addLast(Item item)           // insert the item at the end
   {
     if (item == null) throw new NullPointerException();
     Node newNode = new Node();
     newNode.item = item;
     newNode.next = null;
     if (size == 0){
       newNode.prev = null;
       first = newNode;
       last = newNode;
     }else{
       Node oldLast = last;
       oldLast.next = newNode;
       newNode.prev = oldLast;
       last = newNode;
     } 
     size++;
   }
   public Item removeFirst()                // delete and return the item at the front
   {
     if (isEmpty()) throw new java.util.NoSuchElementException();
     Node oldFirst = first;
     if (size == 1){
       first = null;
       last = null;
       size --;
       return oldFirst.item;
     }
     first = first.next;
     first.prev = null;
     size --;
     return oldFirst.item;
   }
   public Item removeLast()                 // delete and return the item at the end
   {
     if (isEmpty()) throw new java.util.NoSuchElementException();   
     Node oldLast = last;
     if (size == 1){
       first = null;
       last = null;
       size --;
       return oldLast.item;
     }
     last = last.prev;
     last.next = null;
     size --;
     return oldLast.item;
   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
     return new DequeIterator();
   }
   private class DequeIterator implements Iterator<Item>
   {
     Node current;
     public DequeIterator(){
       current = first;
     }
     public boolean hasNext(){ 
       return current != null; 
     }
     public void remove() { throw new UnsupportedOperationException(); }
     public Item next()
     {
       if (current == null) throw new java.util.NoSuchElementException();
       Item item = current.item;
       current = current.next;
       return item;
     }
   }
   public static void main(String[] args)   // unit testing
   {
     Deque<String> test = new Deque<String>();
//     while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            test.addFirst(item);
//     }
     test.addFirst("Test1");
     test.addFirst("Test2");
     test.addFirst("Test3");
     test.addLast("Test4");
     test.addLast("Test5");
     for (String s : test)
       StdOut.println(s);
     StdOut.println("-----------------------");
     test.removeLast();
     test.removeLast();
     test.removeLast();
     test.removeLast();
     test.removeLast();
     for (String s : test)
       StdOut.println(s);
     StdOut.println("-----------------------");
     test.addFirst("Test2");
     test.addFirst("Test3");
     test.addLast("Test4");
     test.addLast("Test5");
     for (String s : test)
       StdOut.println(s);
     StdOut.println("-----------------------");
     test.removeFirst();
     test.removeFirst();
     test.removeFirst();
     test.removeFirst();
     for (String s : test)
       StdOut.println(s);
     StdOut.println("-----------------------");
   }
}