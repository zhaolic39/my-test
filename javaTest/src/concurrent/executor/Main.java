package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main { 
  /** 线程池的大小 */
  private static final int NTHREDS = 1;

  public static void main(String[] args) { 
    // Executors.newFixedThreadPool 创建一个大小为N的线程池，同时只有N个任务同时运行
      ExecutorService executor = Executors.newFixedThreadPool(NTHREDS); 
      for (int i = 0; i < 10; i++) { 
          Runnable worker = new MyRunnable(i); 
          executor.execute(worker); 
//          executor.submit(worker);
      } 
      System.out.println("add task end...");
      executor.shutdown(); 
      while (!executor.isTerminated()) { } 
      System.out.println("Finished all threads"); 
  }

}