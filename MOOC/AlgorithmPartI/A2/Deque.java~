import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   private Node first, last;
   int size;
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
     size++;
     Node newNode = new Node();
     newNode.item = item;
     if (size == 1){
       first = newNode;
       last = newNode;
     }else{
       first.prev = newNode;
       newNode.prev = null;
       newNode.next = first;
       first = newNode;
     } 
   }
   public void addLast(Item item)           // insert the item at the end
   {
     if (item == null) throw new NullPointerException();
     size++;
     Node newNode = new Node();
     newNode.item = item;
     if (size == 1){
       first = newNode;
       last = newNode;
     }else{
       last.next = newNode;
       newNode.prev = last;
       newNode.next = null;
       last = newNode;
     } 
   }
   public Item removeFirst()                // delete and return the item at the front
   {
     if (isEmpty()) throw new java.util.NoSuchElementException();
     Node oldFirst = first;
     first = first.next;
     first.prev = null;
     size --;
     return oldFirst.item;
   }
   public Item removeLast()                 // delete and return the item at the end
   {
     if (isEmpty()) throw new java.util.NoSuchElementException();
     Node oldLast = last;
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
     test.addLast("Test3");
     test.addLast("Test4");
     test.removeLast();
     for (String s : test)
       StdOut.println(s);
   }
}