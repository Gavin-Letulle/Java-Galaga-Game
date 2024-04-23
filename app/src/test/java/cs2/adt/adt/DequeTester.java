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
    //assertEquals(dq.peekFront(), dq.peekBack());
    assertEquals(dq.size(), 1);
  }
}
