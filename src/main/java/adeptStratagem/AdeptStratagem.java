package adeptStratagem;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Metadata;
import com.drew.imaging.jpeg.JpegMetadataReader;

/**
 * AdeptStratagem is meant to inspect the metadata of a directory of images and based on that
 * move the files to their appropriate directory to assist is organization of a large amount of
 * images.
 *
 * @author ryknow
 * @version 0.0.1
 *
 */
public class AdeptStratagem
{
    public static void main( String[] args ) throws JpegProcessingException, IOException {
        File jpegFile = new File("myFile.jpg");
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
        System.out.println( "Hello World!" );
    }
}
