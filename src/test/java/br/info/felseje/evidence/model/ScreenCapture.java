package br.info.felseje.evidence.model;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import com.itextpdf.text.pdf.codec.Base64;

/**
 * Class that contains screenshots.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class ScreenCapture {

    public ScreenCapture(String imageString, String description) throws IOException {
        this.imageString = imageString;
        this.description = description;
        this.image = toImage(imageString);
    }

    public ScreenCapture(Image image, String description) throws IOException {
        this.image = image;
        this.description = description;
        this.imageString = toImageString(image);
    }

    public ScreenCapture(String imageString) throws IOException {
        this.description = "";
        this.imageString = imageString;
        this.image = toImage(imageString);
    }

    public ScreenCapture(Image image) throws IOException {
        this.image = image;
        this.description = "";
        this.imageString = toImageString(image);
    }

    private Image image;
    private String imageString;
    private String description;

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) throws IOException {
        this.imageString = imageString;
        this.image = toImage(imageString);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] toByteArray() {
        return Base64.decode(imageString);
    }

    private Image toImage(String imageString) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(Base64.decode(imageString)));
    }

    private String toImageString(Image image) throws IOException {
        ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(ImageIO.createImageInputStream(image)), "png", bAOS);
        return Base64.encodeBytes(bAOS.toByteArray());
    }
}
