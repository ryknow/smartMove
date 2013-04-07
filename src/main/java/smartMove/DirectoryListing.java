package smartMove;

import java.io.File;
import java.util.ArrayList;

/**
 * Directory listing of Files that contain year and month metadata (SMFile)
 *
 * @author ryknow
 */
public class DirectoryListing {
  private String directoryString;
  private File directory;
  private ArrayList<SMFile> smFiles;

  /**
   * Constructor to create a DirectoryListing object based on path.
   * Creates an ArrayList of SMFile objects for the items in the directory.
   *
   * @param dir             String path to directory that a DirectoryListing is being created for
   * @param createFiles     Boolean flag signifying whether the directory contains files being moved
   */
  DirectoryListing(String dir, Boolean createFiles) {
    directory = new File(dir);
    if (!directory.isDirectory()) {
      try {
        throw new Exception("Must provide a directory");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    directoryString = directory.getAbsolutePath();

    if (createFiles) {
      smFiles = new ArrayList<SMFile>();
      createSMFiles();
    }
  }

  public String getDirectoryString() {
    return directoryString;
  }

  public void setDirectoryString(String dir) throws Exception {
    File dirFile = new File(dir);
    if (!dirFile.isDirectory()) throw new Exception("Must provide a directory");
    directoryString = dirFile.getAbsolutePath();
  }

  public int imageCount() {
    return smFiles.size();
  }

  public ArrayList<SMFile> getSmFiles() {
    return smFiles;
  }

  /**
   * Displays the files that are within the DirectoryList object
   */
  public void display() {
    if (directory.isDirectory()) {
      for (String file : directory.list()) {
          System.out.println(file);
      }
    } else {
      System.out.println(directory.getAbsolutePath());
    }
  }

  /**
   * Iterates over the directory and creates an SMFile object for any image files located in the directory.
   * Currently restricted to jpg files.
   */
  private void createSMFiles() {
    for (String file : directory.list() ) {
      if (file.toLowerCase().indexOf(".jpg") > 0) {
        smFiles.add(new SMFile(directoryString + "/" + file));
      }
    }
  }
}
