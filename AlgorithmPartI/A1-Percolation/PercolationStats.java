public class PercolationStats {
   private Percolation model;
   private double[] result;
   private int T;
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {
     if (N <= 0 || T <= 0) throw new IllegalArgumentException();
     model = new Percolation(N);
     this.T=T;
     int times=0;
     result = new double[T+1];
     for (int index=1;index < T;index++){
       model = new Percolation (N);
       while(!model.percolates()){
         int i = StdRandom.uniform(N)+1;
         int j = StdRandom.uniform(N)+1;
         while(model.isOpen(i,j)){
           i = StdRandom.uniform(N)+1;
           j = StdRandom.uniform(N)+1;
         }
         model.open(i,j);
         times++;
       }
       result[index]=(double)times/(N*N);
       times = 0;
       model = null; //clean the memory
     }
   }
   public double mean()                     // sample mean of percolation threshold
   {
     double total = 0;
     for (int i=1;i<T;i++){
       total+= result[i];
     }
     return total/T;
   }
   public double stddev()                   // sample standard deviation of percolation threshold
   {
     double mean = mean();
     double total = 0;
     for (int index = 1; index<T;index++)
       total += (result[index]-mean)*(result[index]-mean);
     return total/(T-1);
   }
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   {
     return (mean()-1.96*stddev()/(Math.sqrt(T)));
   }
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   {
     return (mean()+1.96*stddev()/(Math.sqrt(T)));
   }
   public static void main(String[] args)   // test client, described below
   {
     int N = Integer.parseInt(args[0]);
     int T = Integer.parseInt(args[1]);
     PercolationStats stat = new PercolationStats(N,T);
     System.out.printf("mean                    = %f%n", stat.mean());
     System.out.printf("stddev                  = %f%n", stat.stddev());
     System.out.printf("95%% confidence interval = %f, %f%n", stat.confidenceLo(), stat.confidenceHi());     
   }
}