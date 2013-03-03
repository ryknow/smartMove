package adeptStratagem;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class DirectoryListingTest {
  DirectoryListing dirList, badDirList;
  @Before
  public void setUp() throws Exception {
    dirList = new DirectoryListing(System.getenv("HOME") + "/pics");
  }

  @Test
  public void testGetDirectoryString() throws Exception {
    Assert.assertTrue(dirList.getDirectoryString().equals(System.getenv("HOME") + "/pics"));
  }

  @Test
  public void testSetDirectoryString() throws Exception {
    dirList.setDirectoryString(System.getenv("HOME"));
    Assert.assertTrue(dirList.getDirectoryString().equals(System.getenv("HOME")));
  }

  @Test(expected = NullPointerException.class)
  public void testExceptionCatching() {
    badDirList = new DirectoryListing(System.getenv("HOME") + "/web-pics");
  }
}
