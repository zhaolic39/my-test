package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
  public static void main(String[] args) {
    BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(10);
    ProducerBlockingQueue p = new ProducerBlockingQueue(q, "p");
    ProducerBlockingQueue p1 = new ProducerBlockingQueue(q, "p1");
    ConsumerBlockingQueue c = new ConsumerBlockingQueue(q, "c");
    ConsumerBlockingQueue c1 = new ConsumerBlockingQueue(q, "c1");
    p.start();
    p1.start();
    c.start();
    c1.start();
  }
}
