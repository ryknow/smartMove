package adeptStratagem;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

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
    Assert.assertTrue(asFile.getFileMonth().equals("2 - February"));
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
    Assert.assertTrue(asFile.getFileMonth().equals("2 - February"));
  }

  @Test
  public void testSetFileMonth() throws Exception {
    asFile.setFileMonth("01");
    Assert.assertTrue(asFile.getFileMonth().equals("1 - January"));
  }

  @Test
  public void testMoveTo() {
    HashMap<String, String> moved = asFile.moveTo(System.getenv("HOME") + "/web-pics");
    Assert.assertEquals("true", moved.get("success"));
  }

  @Test
  public void testMoveToDirectories() {
    asFile.moveTo(System.getenv("HOME") + "/web-pics");
    File newDirectories = new File(System.getenv("HOME") + "/web-pics/" +
        asFile.getFileYear() + "/" + asFile.getFileMonth());
    Assert.assertTrue(newDirectories.isDirectory());
  }

  @After
  /**  Moves file back to its original location and removes directories that were created
   *   as a result of the testing.
   */
  public void tearDown() throws Exception {
    String destString = System.getenv("HOME") + "/web-pics/2013/2 - February";
    File testFile = new File(destString + "/_DSC0003.JPG");
    if (testFile.exists()) {
      Boolean moved = testFile.renameTo(new File(System.getenv("HOME") + "/pics/_DSC0003.JPG"));
      if (moved) {
        File monthDir = new File(destString);
        Boolean monthDeleted = monthDir.delete();
        if (monthDeleted) {
          File yearDir = new File(System.getenv("HOME") + "/web-pics/2013");
          yearDir.delete();
        }
      }
    }
  }

}