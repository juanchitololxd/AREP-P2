package eci.arep.services;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class LucasServiceTest extends TestCase {

    public void testGetSequence() {

        int limit = 13;
        List<Integer> numbers = new ArrayList<>();
        LucasService.getSequence(limit, numbers);
        assertEquals (2,(int) numbers.get(0));
        assertEquals (1,(int) numbers.get(1));
        assertEquals (3,(int) numbers.get(2));
        assertEquals (4,(int) numbers.get(3));
        assertEquals (7,(int) numbers.get(4));
        assertEquals (11,(int) numbers.get(5));
        assertEquals (18,(int) numbers.get(6));
        assertEquals (29,(int) numbers.get(7));
        assertEquals (47,(int) numbers.get(8));
        assertEquals (76,(int) numbers.get(9));
        assertEquals (123,(int) numbers.get(10));
        assertEquals (199,(int) numbers.get(11));
        assertEquals (521,(int) numbers.get(13));
    }
}