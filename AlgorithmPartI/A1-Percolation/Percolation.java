public class Percolation {
   private boolean[] open;
   private WeightedQuickUnionUF connect;
   private WeightedQuickUnionUF full;
   private int N;
   public Percolation(int N)              // create N-by-N grid, with all sites blocked
   {
     if (N <= 0 ) throw new IllegalArgumentException();
     open = new boolean[N*N+1];
     open[0]=true;
     for (int i=1;i<N*N+1;i++){
       open[i]=false;
     }
     connect = new WeightedQuickUnionUF(N*N+2);
     full = new WeightedQuickUnionUF(N*N+1);
     this.N = N;
   }
   public void open(int i, int j)         // open site (row i, column j) if it is not already
   {
     if (i <= 0 || i > N || j<=0 || j>N) throw new IndexOutOfBoundsException();
     int cur = N*(i-1)+j;
     open[cur]=true;
     if (cur<=N){//connect the virtual top
       connect.union(0,cur);
       full.union(0,cur);
     }else if(open[cur-N]){
       connect.union(cur,cur-N);
       full.union(cur,cur-N);
     }
     if (cur>= (N*N-N+1)){//connect the virtual bottom
       connect.union(cur,N*N+1);
     }
     else if(open[cur+N]){
       connect.union(cur,cur+N);
       full.union(cur,cur+N);
     }
     if (cur%N !=1 && open[cur-1]){
       connect.union(cur,cur-1);
       full.union(cur,cur-1);
     }
     if (cur%N != 0 && open[cur+1]){
       connect.union(cur,cur+1);
       full.union(cur,cur+1);
     }     
   }
   public boolean isOpen(int i, int j)    // is site (row i, column j) open?
   {
     if (i <= 0 || i > N || j<=0 || j>N) throw new IndexOutOfBoundsException();
     int cur = N*(i-1)+j;
     return open[cur];
   }
   public boolean isFull(int i, int j)    // is site (row i, column j) full?
   {
     if (i <= 0 || i > N || j<=0 || j>N) throw new IndexOutOfBoundsException();
     int cur = N*(i-1)+j;
     //return connect.connected(cur, 0);
     return full.connected(cur, 0);
   }
   public boolean percolates()            // does the system percolate?
   {
     return connect.connected(0,N*N+1);
   }
}