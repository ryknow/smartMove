
public class Main {

    public static void main(String[] args) {
        File jpegFile = new File("myImage.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
    }
}
