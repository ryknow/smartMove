package adeptStratagem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AdeptStratagemGUI creates and renders the GUI to be able to pick the source and destination directories for the moving of
 * image files.
 *
 * @author ryknow
 */
public class AdeptStratagemGUI implements ActionListener {
  private JFrame frame;
  private JPanel mainPanel, sourcePanel, destPanel, bottomPanel;
  private JLabel progressLabel;
  private JButton goButton, sourceButton, destButton;
  private JFileChooser fileChooser;
  private JTextField sourceText, destText;
  private DirectoryListing sourceDir, destDir;

  /**
   * The constructor will initialize all of the necessary GUI elements
   */
  public AdeptStratagemGUI() {
    frame = new JFrame("AdeptStratagem");

    fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
    sourcePanel  = new JPanel(new BorderLayout());
    sourceButton = new JButton("Choose");
    sourceText  = new JTextField("source directory....", 20);
    sourceText.setEnabled(false);
    sourcePanel.add(sourceText, BorderLayout.WEST);
    sourcePanel.add(sourceButton, BorderLayout.EAST);

    destPanel  = new JPanel(new BorderLayout());
    destButton = new JButton("Choose");
    destText   = new JTextField("destination directory....", 20);
    destText.setEnabled(false);
    destPanel.add(destText, BorderLayout.WEST);
    destPanel.add(destButton, BorderLayout.EAST);


    bottomPanel   = new JPanel(new BorderLayout());
    progressLabel = new JLabel("");
    goButton      = new JButton("Go!");
    bottomPanel.add(progressLabel, BorderLayout.WEST);
    bottomPanel.add(goButton, BorderLayout.EAST);
  }

  /**
   * Renders and displays the GUI elements and sets up the necessary listeners
   */
  public void renderGUI() {
    mainPanel.add(sourcePanel);
    mainPanel.add(destPanel);
    mainPanel.add(bottomPanel);
    
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    sourceButton.addActionListener(this);
    destButton.addActionListener(this);
    goButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent ev) {
    if (ev.getSource() == sourceButton || ev.getSource() == destButton) {
      populateDirectoryText(ev.getSource());
    } else if (ev.getSource() == goButton) {
      if (sourceDir != null && destDir != null) {
        for (ASFile asFile : sourceDir.getAsFiles()) {
          asFile.moveTo(destDir.getDirectoryString());
          // TODO: Update progress bar based on files being moved
        }
      } else {
        progressLabel.setText("You must choose both a Source and Destination Directory to perform this action.");
      }
    }

  }

  /**
   * Executed from the actionPerformed Listener to update the Text Field text to show the directory that
   * was picked from the file chooser.  When the source directory is picked this will also up date the progress
   * label with the number of files that will be moved.
   *
   * @param button    The button that was clicked to execute this method
   */
  private void populateDirectoryText(Object button) {
    int returnVal = fileChooser.showOpenDialog(frame);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      if (button == sourceButton) {
        String srcPath = fileChooser.getSelectedFile().getAbsolutePath();
        sourceText.setText(srcPath);
        setSourceDir(srcPath);
        progressLabel.setText(sourceDir.imageCount() + " File(s) to be moved");
        sourceDir.display();
      } else if (button == destButton) {
        String destPath = fileChooser.getSelectedFile().getAbsolutePath();
        destText.setText(destPath);
        setDestDir(destPath);
      }
    }
  }

  /**
   * Sets the sourceDir as a new DirectoryListing object
   *
   * @param path    path to the directory that images will be moved from
   */
  public void setSourceDir(String path) {
    sourceDir = new DirectoryListing(path, true);
  }

  /**
   * Sets the destDir as a new DirectoryListing object
   *
   * @param path    path to the directory that images will be moved to
   */
  public void setDestDir(String path) {
    destDir = new DirectoryListing(path, false);
  }
}
