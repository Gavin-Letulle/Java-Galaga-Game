package cs2.adt;

import java.util.NoSuchElementException;

public class MyDeque<T> implements Deque<T> {
  /* 
   * Complete the Deque interface methods below.
   */

  private T[] arr;
  private int len;
  private int beg;

  public MyDeque(){
    arr = (T[]) new Object[10];
    len = 0;
    beg = 0;
  }

  public void prepend(T item){
    if(len == arr.length) {
      T[] tmp = (T[])new Object[len * 2];
      for(int i=0; i<len; i++) {
        tmp[i] = arr[(beg + i) % len];
      }
      beg = 0;
      arr = tmp;
    }
    arr[(beg + len) % arr.length] = item;
    len++;
  }

  public void append(T item){
    
  }

  /**
   * Removes and returns the item at the front of the deque
   * @return the item at the front of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T front() throws NoSuchElementException{
    return null;
  }

  /**
   * Removes and returns the item at the back of the deque
   * @return the item at the back of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T back() throws NoSuchElementException{
    return null;
  }

  /**
   * Returns the item at the front of the deque without removing it
   * @return the item at the front of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T peekFront() throws NoSuchElementException{
    return null;
  }

  /**
   * Returns the item at the back of the deque without removing it
   * @return the item at the back of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T peekBack() throws NoSuchElementException{
    return null;
  }

  /**
   * Returns true if the deque is empty, false otherwise
   * @return true if the deque is empty, false otherwise
   */
  public boolean isEmpty(){
    return len == 0;
  }

  /**
   * Returns the number of items in the deque
   * @return the number of items in the deque
   */
  public int size(){
    return len;
  }
}