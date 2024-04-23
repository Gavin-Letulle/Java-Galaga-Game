package cs2.adt.adt;

import org.junit.jupiter.api.*;

import cs2.adt.MyDeque;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class DequeTester {
  /* Place your thorough tester code here to test MyDeque
   * Be sure to test all of the required elements of the Deque interface,
   * including the exceptions that must be thrown.
   */

  private MyDeque<Integer> dq;
  
  @BeforeEach
  void init(){
    dq = new MyDeque<Integer>();
  }

  @Test
  void testPrepOnce(){
    assertTrue(dq.isEmpty());
    dq.prepend(1);
    assertFalse(dq.isEmpty());
    assertEquals(dq.size(), 1);
  }

  @Test
  void testAppOnce(){
    assertTrue(dq.isEmpty());
    dq.append(2);
    assertFalse(dq.isEmpty());
    assertEquals(dq.size(), 1);
  }

  @Test
  void testBothOnce(){
    assertTrue(dq.isEmpty());
    dq.prepend(1);
    assertFalse(dq.isEmpty());
    dq.append(2);
    assertEquals(dq.peekFront(), 1);
    assertEquals(dq.peekBack(), 2);
    assertEquals(dq.size(), 2);
  }

  @Test
  void testBack(){
    assertTrue(dq.isEmpty());
    dq.prepend(3);
    assertFalse(dq.isEmpty());
    dq.append(4);
    dq.append(5);
    assertEquals(dq.size(), 3);
    dq.back();
    assertEquals(dq.peekBack(), 4);
    assertEquals(dq.peekFront(), 3);
    assertEquals(dq.size(), 2);
    dq.append(6);
    assertEquals(dq.size(), 3);
    assertEquals(dq.peekBack(), 6);
    dq.back();
    assertEquals(dq.peekBack(), 4);
  }

  @Test
  void testFront(){
    assertTrue(dq.isEmpty());
    dq.prepend(2);
    assertFalse(dq.isEmpty());
    dq.append(3);
    dq.append(4);
    assertEquals(dq.size(), 3);
    assertEquals(dq.peekFront(), 2);
    assertEquals(dq.peekBack(), 4);
    assertEquals(dq.front(), 2);
    assertEquals(dq.size(), 2);
    assertEquals(dq.peekBack(), 4);
  }

  @Test
  void testAll(){
    assertTrue(dq.isEmpty());
    assertThrows(NoSuchElementException.class, () -> dq.peekFront());
    assertThrows(NoSuchElementException.class, () -> dq.peekBack());
    assertThrows(NoSuchElementException.class, () -> dq.front());
    assertThrows(NoSuchElementException.class, () -> dq.back());
    assertEquals(dq.size(), 0);
    dq.append(1);
    dq.append(2);
    dq.prepend(3);
    dq.prepend(4);
    //[4312]
    assertEquals(dq.size(), 4);
    assertEquals(dq.peekFront(), 4);
    assertEquals(dq.peekBack(), 2);
    assertEquals(dq.back(), 2);
    assertEquals(dq.back(), 1);
    assertEquals(dq.size(), 2);
    //43
    dq.prepend(5);
    dq.append(6);
    //5436
    assertEquals(dq.size(), 4);
    assertEquals(dq.front(), 5);
    assertEquals(dq.front(), 4);
    assertEquals(dq.size(), 2);
    //36
    assertEquals(dq.peekFront(), 3);
    assertEquals(dq.peekBack(), 6);
    dq.front();
    dq.back();
    assertEquals(dq.size(), 0);
    assertThrows(NoSuchElementException.class, () -> dq.peekFront());
    assertThrows(NoSuchElementException.class, () -> dq.peekBack());
    assertThrows(NoSuchElementException.class, () -> dq.front());
    assertThrows(NoSuchElementException.class, () -> dq.back());
  }
}