package adeptStratagem;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ASFile (AdeptStratagemFile) Object which inherits from File and adds year and month
 * metadata to the File object.
 */
public class ASFile extends File {
  private String fileYear;
  private String fileMonth;
  private Metadata metadata;

  /**
   * Creates a File object with added metadata information (year and month that image
   * was taken.
   *
   * @param filename    String path of a image file that will be analyzed and moved
   */
  public ASFile(String filename) {
    super(filename);
    setASFileMetadata();
  }

  public String getFileYear() {
    return fileYear;
  }

  public void setFileYear(String year) {
    fileYear = year;
  }

  public String getFileMonth() {
    return fileMonth;
  }

  // TODO: Change month to be in the format of MM - Month i.e. 01 - January
  public void setFileMonth(String month) {
    fileMonth = month;
  }

  /**
   * Analyzes the metadata for the image and sets the fileYear and fileMonth properties to be used
   * while moving the files to the destination directory.
   *
   * @exception NullPointerException        unable to get date information from the image
   */
  public void setASFileMetadata() {
    try {
      metadata = JpegMetadataReader.readMetadata(this);
    } catch (JpegProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
      Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

      DateTime dateTime = new DateTime(date.getTime());

      setFileYear(Integer.toString(dateTime.getYear()));
      setFileMonth(String.format("%02d", dateTime.getMonthOfYear()));
    } catch (NullPointerException e) {
      Logger.getAnonymousLogger().log(Level.SEVERE, "Unable to obtain date information from the specified JPG file");
      e.printStackTrace();
    }
  }

  /**
   * Moves the ASFile object to the destination specified.  If the destination directory doesn't exist it creates it and
   * then performs the move.
   *
   * @param destinationRoot     The root directory that the file will be moved to. Sub-directories are based on image
   *                            metadata (year and month).
   */
  public boolean moveTo(String destinationRoot) {
    String destination  = destinationRoot + "/" + getFileYear() + "/" + getFileMonth();
    File destinationDir = new File(destination);
    if (!destinationDir.isDirectory()) {
      System.out.println("Creating directory....");
      destinationDir.mkdirs();
    }

    File destinationFile = new File(destination + "/" + getName());
    Boolean moved = renameTo(destinationFile);
    if (moved) {
      System.out.println(getName() + " successfully moved...");
    }
    return moved;
  }
}
