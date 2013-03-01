package adeptStratagem;

import com.sun.corba.se.impl.logging.ORBUtilSystemException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for AdeptStratagem
 */
public class AdeptStratagemTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AdeptStratagemTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AdeptStratagemTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testAdeptStratagem()
    {
        System.out.println("TESTING APPLICATION");
        assertTrue(true);
    }
}
