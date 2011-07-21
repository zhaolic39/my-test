package concurrent;

import java.util.concurrent.BlockingQueue;

/**
 * <p>Title: ������������ࡣ</p> 
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
                  sleep(100);// �������ߵ�˯��ʱ�����õñ�������С��Ϊ����ʾ����Ʒ�б�Ϊ�յ�����
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

