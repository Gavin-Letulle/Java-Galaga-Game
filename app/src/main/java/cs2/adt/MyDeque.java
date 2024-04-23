package cs2.adt;

import java.util.EmptyStackException;
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
    if(len == arr.length) {
      T[] tmp = (T[])new Object[len * 2];
      for(int i=0; i<len; i++) {
        tmp[i] = arr[i];
      }
      arr = tmp;
    }
    arr[len] = item;
    len += 1;
  }

  /**
   * Removes and returns the item at the front of the deque
   * @return the item at the front of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T back() throws NoSuchElementException{
    if(isEmpty()) {
      throw new EmptyStackException();
    }
    beg = (beg + 1) % arr.length;
    len--;
    return arr[beg-1];
  }

  /**
   * Removes and returns the item at the back of the deque
   * @return the item at the back of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T front() throws NoSuchElementException{
    if(isEmpty()) {
      throw new EmptyStackException();
    }
    T frontItem = arr[beg];
    beg = (beg + 1) % arr.length;
    len--;
    return frontItem;
  }

  /**
   * Returns the item at the front of the deque without removing it
   * @return the item at the front of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T peekBack() throws NoSuchElementException{
    if(isEmpty()) {
      throw new EmptyStackException();
    }
    return arr[len - 1];
  }

  /**
   * Returns the item at the back of the deque without removing it
   * @return the item at the back of the deque
   * @throws NoSuchElementException if the deque is empty
   */
  public T peekFront() throws NoSuchElementException{
    if(isEmpty()) {
      throw new EmptyStackException();
    }
    return arr[0];
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