package br.com.projeto.evidence.model;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;

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
        return Base64.decodeBase64(imageString);
    }

    private Image toImage(String imageString) throws IOException {
        return ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(imageString)));
    }

    private String toImageString(Image image) throws IOException {
        ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(ImageIO.createImageInputStream(image)), "png", bAOS);
        return Base64.encodeBase64String(bAOS.toByteArray());
    }
}
