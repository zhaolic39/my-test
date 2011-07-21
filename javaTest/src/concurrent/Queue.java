package concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: </p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * @author Zhaoli
 * @version 1.0 2011-7-15
 */
public class Queue implements BlockingQueue {

  private List list=new ArrayList();
  public boolean add(Object o) {
      // TODO Auto-generated method stub
      list.add(o);
      return true;
  }

  public Object take() throws InterruptedException {
      // TODO Auto-generated method stub
      while(isEmpty()){}
      return list.remove(0);
  }


  public boolean isEmpty() {
      // TODO Auto-generated method stub
      return list.isEmpty();
  }

  @Override
  public Object remove() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object poll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object element() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object peek() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Iterator iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object[] toArray() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Object[] toArray(Object[] a) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean containsAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean addAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean retainAll(Collection c) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean offer(Object e) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void put(Object e) throws InterruptedException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean offer(Object e, long timeout, TimeUnit unit)
      throws InterruptedException {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Object poll(long timeout, TimeUnit unit) throws InterruptedException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int remainingCapacity() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean remove(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean contains(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int drainTo(Collection c) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int drainTo(Collection c, int maxElements) {
    // TODO Auto-generated method stub
    return 0;
  }

//当然这个类还有其他的方法需要实现，为了清楚起见，我把使用默认实现的方法都去掉了。

}