package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

/** Performs some basic tests for MaxArrayDeque */
public class MaxArrayDequeTest {

    // Comparator for integers in reverse order
    private static final Comparator<Integer> reverseComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(b, a);  // Reverse order: bigger numbers come first
        }
    };
    private static final Comparator<Integer> normalComparator = new Comparator<Integer>(){
        public int compare(Integer a,Integer b){
            return Integer.compare(a,b);
        }
    };
    @Test
    /** Test for basic functionality (addFirst, addLast, and max with default comparator). */
    public void basicAddAndMaxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();

        mad.addFirst(1);
        mad.addLast(2);
        mad.addLast(3);
        mad.addFirst(0);

        // Testing max() method with default comparator (natural order)
        assertEquals("Max should be 3", Integer.valueOf(3), mad.max(normalComparator));

        mad.removeFirst();
        assertEquals("Max should be 3", Integer.valueOf(3), mad.max(normalComparator));

        mad.removeLast();
        assertEquals("Max should be 3", Integer.valueOf(2), mad.max(normalComparator));
    }

    @Test
    /** Test for max() using a custom comparator (reverse order). */
    public void customComparatorMaxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(reverseComparator);

        mad.addFirst(1);
        mad.addLast(2);
        mad.addLast(3);
        mad.addFirst(0);

        // Using reverseComparator: larger numbers should come first
        assertEquals("Max should be 0", Integer.valueOf(0), mad.max(reverseComparator));

        mad.removeFirst();
        assertEquals("Max should be 1", Integer.valueOf(1), mad.max(reverseComparator));

        mad.removeLast();
        assertEquals("Max should be 1", Integer.valueOf(1), mad.max(reverseComparator));
    }

    @Test
    /** Test for an empty deque (max should return null). */
    public void emptyMaxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        assertNull("Max should be null for an empty deque", mad.max(normalComparator));
    }

    @Test
    /** Test for deque with only one element (max should return the element itself). */
    public void singleElementMaxTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addFirst(10);
        assertEquals("Max should be 10", Integer.valueOf(10), mad.max(normalComparator));

        mad.removeFirst();
        assertNull("Max should be null after removal", mad.max(normalComparator));
    }
}