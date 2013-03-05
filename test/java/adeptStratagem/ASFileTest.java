package adeptStratagem;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class ASFileTest {
  ASFile asFile;

  @Before
  public void setUp() throws Exception {
    asFile = new ASFile(System.getenv("HOME") + "/pics/_DSC0003.JPG");
    asFile.setASFileMetadata();
  }

  @Test
  public void testSetASFileMetadata() throws Exception {
    Assert.assertTrue(asFile.getFileYear().equals("2013"));
    Assert.assertTrue(asFile.getFileMonth().equals("02"));
  }

  @Test
  public void testGetFileYear() throws Exception {
    Assert.assertTrue(asFile.getFileYear().equals("2013"));
  }

  @Test
  public void testSetFileYear() throws Exception {
    asFile.setFileYear("2012");
    Assert.assertTrue(asFile.getFileYear().equals("2012"));
  }

  @Test
  public void testGetFileMonth() throws Exception {
    Assert.assertTrue(asFile.getFileMonth().equals("02"));
  }

  @Test
  public void testSetFileMonth() throws Exception {
    asFile.setFileMonth("01");
    Assert.assertTrue(asFile.getFileMonth().equals("01"));
  }

  @Test
  public void testMoveTo() {

  }
}
