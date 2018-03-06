import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

public class MatrixTest {
    private static Matrix m1, m2;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        m1 = new Matrix(2, 4);
        double[][] d = {{1, 2, 3}, {4, 5, 6}};
        m2 = new Matrix(d);
    }

    @Test
    public void setElement() {
        m1.setElement(10, 3, 1.0);
        m1.setElement(1, 3, 2.0);
        assertEquals(2.0, m1.getElement(1, 3));
    }

    @Test
    public void getElement() {
        assertEquals(4.0, m2.getElement(1,0));
    }

    @Test
    public void getLength() {
        assertEquals(2, m1.getLength());
        assertEquals(2, m2.getLength());
    }

    @Test
    public void getHeight() {
        assertEquals(4, m1.getHeight());
        assertEquals(3, m2.getHeight());
    }

    @Test
    public void multiply() {
        m1.multiply(m2);
    }

    @Test
    public void transpose() {
        assertFalse(m2.getElement(2, 0) == 3.0);
        assertEquals(3.0, m2.transpose().getElement(2, 0));
    }

    @Test
    public void applySigmoid() {
        m1.applySigmoid();
        m2.applySigmoid();
    }
}