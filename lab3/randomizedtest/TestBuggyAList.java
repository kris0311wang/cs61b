package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void test1() {
        AList<Integer> alist = new AList<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int[] addlist = {4, 5, 6, 7, 8, 9, 19, 18, 17, 5, 3, 6};
        for (int i : addlist) {
            alist.addLast(i);
            buggy.addLast(i);
        }
        int len = addlist.length;
        int[] expected = new int[len];
        int[] get = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            expected[i] = alist.removeLast();
            get[i] = buggy.removeLast();
        }
        assertArrayEquals(expected, get);
    }

    @Test
    public void test2() {
        AList<Integer> alist = new AList<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int[] addlist = {1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (int i : addlist) {
            alist.addLast(i);
            buggy.addLast(i);
        }
        int len = addlist.length;
        int[] expected = new int[len];
        int[] get = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            expected[i] = alist.removeLast();
            get[i] = buggy.removeLast();
        }
        assertArrayEquals(expected, get);
    }
    public void randomizedTest(int N) {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }

    @Test
    public void testRandomizedTestSmall() {
        randomizedTest(500);
    }
}
