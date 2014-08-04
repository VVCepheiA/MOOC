import java.util.Iterator;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
   private int N;
   private Item[] a;
   public RandomizedQueue()                 // construct an empty randomized queue
   {
     N = 0;
     a = (Item[]) new Object[2];
   }
   private void resize(int cap){
     Item[] temp = (Item[]) new Object[cap];
     for (int i = 0; i < N; i++) {
       temp[i] = a[i];
     }
     a = temp;
   }
   private void swap (int x, int y){
     Item tmp = a[x];
     a[x] = a[y];
     a[y] = tmp;
   }
   public boolean isEmpty()                 // is the queue empty?
   {
     return N == 0;
   }
   public int size()                        // return the number of items on the queue
   {
     return N;
   }
   public void enqueue(Item item)           // add the item
   {
     if (item == null) throw new NullPointerException();
     if (N == a.length) resize(2*a.length);
     a[N++] = item;
   }
   public Item dequeue()                    // delete and return a random item
   {
     if (isEmpty()) throw new NoSuchElementException();
     int random = StdRandom.uniform(N);
     swap(random, N-1);
     Item result = a[N-1];
     a[N-1] = null;
     N--;
     if (N > 0 && N == a.length/4) resize(a.length/2);
     return result;
   }
   public Item sample()                     // return (but do not delete) a random item
   {
     if (isEmpty()) throw new NoSuchElementException();
     int random = StdRandom.uniform(N);
     return a[random];
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
     return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        public RandomizedQueueIterator() {
            i = N;
        }
        public boolean hasNext() {
            return i > 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[--i];
        }
    }
   public static void main(String[] args)   // unit testing
   {
     RandomizedQueue<String> test = new RandomizedQueue<String>();
     test.enqueue("TEST1");
     test.enqueue("TEST2");
     test.enqueue("TEST3");
     test.enqueue("TEST4");
     for (String s : test)
       StdOut.println(s);
     StdOut.println("-----------------------");
     StdOut.println(test.dequeue());
     StdOut.println("-----------------------");
     for (String s : test)
       StdOut.println(s);
     
   }
}
