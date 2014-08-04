public class Subset {
  public static void main(String[] args){
    int N = Integer.parseInt(args[0]);
    RandomizedQueue<String> test = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            test.enqueue(item);
        }
    for (int i=0; i < N; i++){
      StdOut.println(test.dequeue());
    }
  }
}