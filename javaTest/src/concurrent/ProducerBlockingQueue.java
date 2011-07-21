package concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * <p>Title: 这个是生产者类。</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 2011-7-15
 */
public class ProducerBlockingQueue extends Thread{
  
  private final BlockingQueue<Integer> queue;
  private final String name;
  private static int i=0;
  public ProducerBlockingQueue(BlockingQueue<Integer> q, String name)
  {
      queue=q;
      this.name=name;
  }
  
  public void run() {
      try
      {
          while(true)
          {
              queue.add(produce());
              try
              {
                  sleep(10000);
              }catch(Exception e){
                  e.printStackTrace();
              }
          }
              
      }catch(Exception e){
          e.printStackTrace();
      }

  }
  
  private int produce()
  {
      System.out.println(name+" producing "+i);
      return i++;
  }

}