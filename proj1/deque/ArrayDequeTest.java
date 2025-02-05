package deque;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        // Edge case: addFirst and addLast with large strings
        lld1.addFirst("beginning");
        assertEquals(4, lld1.size());

        lld1.addLast("end");
        assertEquals(5, lld1.size());

        // Check if printDeque correctly prints the current elements
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that the deque is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

        // Test adding and removing multiple elements
        lld1.addFirst(20);
        lld1.addLast(30);
        assertEquals(2, lld1.size());

        lld1.removeFirst();
        assertEquals(1, lld1.size());
        lld1.removeLast();
        assertTrue("lld1 should be empty after all removals", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        assertTrue("lld1 should be empty after removals", lld1.isEmpty());

        // Test repeated removals from an empty deque
        assertNull("Should return null when removeFirst is called on an empty Deque.", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque.", lld1.removeLast());
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types */
    public void multipleParamTest() {
        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

        // Ensure the types match the expected values
        assertEquals("string", s);
        assertEquals(3.14159, d, 0.0);
        assertTrue(b);
    }

    @Test
    /* check if null is returned when removing from an empty ArrayDeque */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        assertNull("Should return null when removeFirst is called on an empty Deque.", lld1.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque.", lld1.removeLast());
    }

    @Test
    /* Add a large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        // Add a large number of elements to the deque
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        // Ensure the first half is correct
        for (int i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (int) lld1.removeFirst());
        }

        // Ensure the second half is correct
        for (int i = 999999; i >= 500000; i--) {
            assertEquals("Should have the same value", i, (int) lld1.removeLast());
        }
    }

    @Test
    /* Test edge cases like adding and removing a single element */
    public void singleElementTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<String>();
        lld1.addFirst("test");
        assertEquals("test", lld1.removeFirst());
        assertTrue("Deque should be empty after removing the single element", lld1.isEmpty());
    }

    @Test
    /* Test if deque maintains its order when elements are added and removed in a mixed order */
    public void mixedOrderTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        lld1.addFirst(1);
        lld1.addLast(2);
        lld1.addFirst(0);
        lld1.addLast(3);

        assertEquals(0, (int) lld1.removeFirst());
        assertEquals(1, (int) lld1.removeFirst());
        assertEquals(2, (int) lld1.removeFirst());
        assertEquals(3, (int) lld1.removeFirst());
    }

    @Test
    /* Test that adding and removing elements preserves the size */
    public void sizeConsistencyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addLast(20);
        lld1.addFirst(5);

        assertEquals(3, lld1.size());

        lld1.removeFirst();
        assertEquals(2, lld1.size());
        lld1.removeLast();
        assertEquals(1, lld1.size());
        lld1.removeFirst();
        assertEquals(0, lld1.size());
    }
    @Test
    /* Test that iterator works well */
    public void iterationTest(){
        ArrayDeque<Integer> lld1=new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);
        StringJoiner sj=new StringJoiner(" ");
        for(int i:lld1){
            sj.add(String.valueOf(i));
        }
        assertEquals("30 20 10",sj.toString());
    }
    @Test
    /* Test equals */
    public void equalsTest(){
        ArrayDeque<Integer> lld1=new ArrayDeque<Integer>();
        ArrayDeque<Integer> lld2=new ArrayDeque<Integer>();
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);
        lld2.addFirst(10);
        lld2.addFirst(20);
        lld2.addFirst(30);
        assertTrue(lld1.equals(lld2));
        lld2.addFirst(40);
        assertFalse(lld1.equals(lld2));
    }
}