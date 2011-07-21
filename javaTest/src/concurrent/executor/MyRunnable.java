package concurrent.executor;

import java.util.Random;

public class MyRunnable implements Runnable { 
  private String name = "";
  
  public MyRunnable(int i) {
    name = "task-" + i;
  }

  @Override 
  public void run() { 
    int s = (new Random()).nextInt(500);
    try {
      System.out.println("sleep " + s);
      Thread.currentThread().sleep(s);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name); 
  } 
}