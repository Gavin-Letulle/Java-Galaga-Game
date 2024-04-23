package cs2.adt;

import java.util.NoSuchElementException;

public class MyDeque<T> implements Deque<T> {

    private T[] arr;
    private int len;
    private int beg;

    public MyDeque() {
        arr = (T[]) new Object[10];
        len = 0;
        beg = 0;
    }

    public void prepend(T item) {
      if(len == arr.length) {
        T[] newArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < len; i++) {
            newArr[i + 1] = arr[(beg + i) % arr.length];
        }
        arr = newArr;
        beg = 0;
      } 
      else{
        beg = (beg - 1 + arr.length) % arr.length;
      }
      arr[beg] = item;
      len++;
    }

    public void append(T item) {
      if (len == arr.length) {
        T[] newArr = (T[]) new Object[arr.length * 2];
        for(int i = 0; i < len; i++) {
          newArr[i] = arr[(beg + i) % arr.length];
        }
        arr = newArr;
        beg = 0;
      }
      arr[(beg + len) % arr.length] = item;
      len++;
    }

    public T back() throws NoSuchElementException {
      if(isEmpty()) {
        throw new NoSuchElementException();
      }
      len--;
      int backIndex = (beg + len) % arr.length;
      return arr[backIndex];
    }

    public T front() throws NoSuchElementException {
      if(isEmpty()) {
        throw new NoSuchElementException();
      }
      T frontItem = arr[beg];
      beg = (beg + 1) % arr.length;
      len--;
      return frontItem;
    }

    public T peekBack() throws NoSuchElementException {
      if(isEmpty()) {
        throw new NoSuchElementException();
      }
      int backIndex = (beg + len - 1) % arr.length;
      return arr[backIndex];
    }

    public T peekFront() throws NoSuchElementException {
      if(isEmpty()) {
        throw new NoSuchElementException();
      }
      return arr[beg];
    }

    public boolean isEmpty() {
      return len == 0;
    }

    public int size() {
      return len;
    }
}