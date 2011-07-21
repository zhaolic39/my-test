package concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * <p>Title: 这个是消费者类。</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 2011-7-15
 */
public class ConsumerBlockingQueue extends Thread {

  private final BlockingQueue<Integer> queue;
  private final String name;
  
  public ConsumerBlockingQueue(BlockingQueue<Integer> q, String name)
  {
      queue=q;
      this.name=name;
  }
  public void run() {
      try
      {
          while(true)
          {
              consume(queue.take());
              try
              {
                  sleep(100);// 将消费者的睡眠时间设置得比生产者小是为了演示当产品列表为空的情形
              }catch(Exception e){
                  e.printStackTrace();
              }
          }
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  private void consume(int i)
  {
      System.out.println(name+" consume "+i);
  }

}

