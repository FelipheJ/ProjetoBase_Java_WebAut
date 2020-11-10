package br.com.projeto.evidence.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import org.apache.commons.codec.binary.Base64;

/** * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Bean to store evidence message and image in a BASE64Decoder.   *
 *                                                                *
 * @author Elias Nogueira <elias.nogueira@gmai.com>               *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/
public final class SeleniumEvidence {

    private String message;

    private String imageString;

    private BufferedImage image;

    public SeleniumEvidence(String message, String imageString) throws Exception {
        setMessage(message);
        setImageString(imageString);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getImageStringg() {
        return imageString;
    }

    public void setImageString(String imageString) throws Exception {
        this.imageString = imageString;
        setImage(ImageIO.read(new ByteArrayInputStream(toImage(imageString))));
    }

    public static byte[] toImage(String string) throws Exception {
        return Base64.decodeBase64(string);
    }
}
