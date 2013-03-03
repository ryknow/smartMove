package adeptStratagem;

/**
 * AdeptStratagem is meant to inspect the metadata of a directory of images and based on that
 * move the files to their appropriate directory to assist is organization of a large amount of
 * images.  At this point in time it will discover the year and month that the picture was taken
 * and organize into the destination directory based on those.
 *
 * @author ryknow
 * @version 0.0.1
 *
 */
public class AdeptStratagem {

  /**
   * Main method that will create and render the GUI for AdeptStratagem
   *
   * @param args
   */
  public static void main( String[] args ) {
    AdeptStratagemGUI gui = new AdeptStratagemGUI();
    gui.renderGUI();
  }

}
