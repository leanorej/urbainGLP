package test.unit;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import chrono.BoundedCounter;

public class TestBoundedCounter {

    private BoundedCounter counter;

    @Before
    public void preparer() {
        //val50, max 100, min 0
        counter = new BoundedCounter(50, 100, 0);
    }

    @Test
    public void testIncrementNormal() {
        counter.increment();
        assertEquals(51, counter.getValue());
    }

    @Test
    public void testIncrementAuMax() {
        BoundedCounter max = new BoundedCounter(100, 100, 0);
        max.increment();
        assertEquals(100, max.getValue());
    }

    @Test
    public void testDecrementNormal() {
        counter.decrement();
        assertEquals(49, counter.getValue());
    }

    @Test
    public void testDecrementAuMin() {
        BoundedCounter min = new BoundedCounter(0, 100, 0);
        min.decrement();
        assertEquals(0, min.getValue());
    }
}