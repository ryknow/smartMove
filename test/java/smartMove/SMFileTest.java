package smartMove;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

/**
 *
 */
public class SMFileTest {
  SMFile smFile;

  @Before
  public void setUp() throws Exception {
    smFile = new SMFile(System.getenv("HOME") + "/pics/_DSC0003.JPG");
    smFile.setASFileMetadata();
  }

  @Test
  public void testSetASFileMetadata() throws Exception {
    Assert.assertTrue(smFile.getFileYear().equals("2013"));
    Assert.assertTrue(smFile.getFileMonth().equals("2 - February"));
  }

  @Test
  public void testGetFileYear() throws Exception {
    Assert.assertTrue(smFile.getFileYear().equals("2013"));
  }

  @Test
  public void testSetFileYear() throws Exception {
    smFile.setFileYear("2012");
    Assert.assertTrue(smFile.getFileYear().equals("2012"));
  }

  @Test
  public void testGetFileMonth() throws Exception {
    Assert.assertTrue(smFile.getFileMonth().equals("2 - February"));
  }

  @Test
  public void testSetFileMonth() throws Exception {
    smFile.setFileMonth("01");
    Assert.assertTrue(smFile.getFileMonth().equals("1 - January"));
  }

  @Test
  public void testMoveTo() {
    HashMap<String, String> moved = smFile.moveTo(System.getenv("HOME") + "/web-pics");
    Assert.assertEquals("true", moved.get("success"));
  }

  @Test
  public void testMoveToDirectories() {
    smFile.moveTo(System.getenv("HOME") + "/web-pics");
    File newDirectories = new File(System.getenv("HOME") + "/web-pics/" +
        smFile.getFileYear() + "/" + smFile.getFileMonth());
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