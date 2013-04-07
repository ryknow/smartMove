package smartMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * SmartMoveGUI creates and renders the GUI to be able to pick the source and destination directories for the moving of
 * image files.
 *
 * @author ryknow
 */
public class SmartMoveGUI implements ActionListener {
  private JFrame frame;
  private JPanel mainPanel, sourcePanel, destPanel, bottomPanel, outputPanel;
  private JLabel progressLabel, sourceLabel, destLabel;
  private JButton goButton, sourceButton, destButton;
  private JTextArea progressOutput;
  private JFileChooser fileChooser;
  private DirectoryListing sourceDir, destDir;
  private static final int PADDING = 5;

  /**
   * The constructor will initialize all of the necessary GUI elements
   */
  public SmartMoveGUI() {
    frame = new JFrame("SmartMove");

    fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
    sourcePanel  = new JPanel(new BorderLayout());
    sourceButton = new JButton("Choose");
    sourceLabel  = new JLabel("source directory....");
    sourcePanel.add(sourceLabel, BorderLayout.WEST);
    sourcePanel.add(sourceButton, BorderLayout.EAST);
    sourcePanel.setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));

    destPanel  = new JPanel(new BorderLayout());
    destButton = new JButton("Choose");
    destLabel  = new JLabel("destination directory....");
    destPanel.add(destLabel, BorderLayout.WEST);
    destPanel.add(destButton, BorderLayout.EAST);
    destPanel.setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));

    bottomPanel   = new JPanel(new BorderLayout());
    progressLabel = new JLabel("");
    goButton      = new JButton("Go!");
    bottomPanel.add(progressLabel, BorderLayout.WEST);
    bottomPanel.add(goButton, BorderLayout.EAST);
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));

    outputPanel    = new JPanel(new BorderLayout());
    progressOutput = new JTextArea(10, 50);
    progressOutput.setEnabled(false);
    progressOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    outputPanel.add(progressOutput);
    outputPanel.setBorder(BorderFactory.createEmptyBorder(PADDING,PADDING,PADDING,PADDING));
  }

  /**
   * Renders and displays the GUI elements and sets up the necessary listeners
   */
  public void renderGUI() {
    mainPanel.add(sourcePanel);
    mainPanel.add(destPanel);
    mainPanel.add(bottomPanel);
    mainPanel.add(outputPanel);
    
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
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
        for (SMFile smFile : sourceDir.getSmFiles()) {
          HashMap<String, String> returnHash = smFile.moveTo(destDir.getDirectoryString());
          // TODO: Decide what to do when a move fails
          progressOutput.append(returnHash.get("message"));
        }
      } else {
        progressLabel.setText("You must choose both a Source and Destination Directory");
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
        sourceLabel.setText(srcPath);
        setSourceDir(srcPath);
        progressLabel.setText(sourceDir.imageCount() + " File(s) to be moved");
        sourceDir.display();
      } else if (button == destButton) {
        String destPath = fileChooser.getSelectedFile().getAbsolutePath();
        destLabel.setText(destPath);
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
