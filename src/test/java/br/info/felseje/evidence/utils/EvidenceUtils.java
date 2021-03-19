package br.info.felseje.evidence.utils;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import io.cucumber.java.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import br.info.felseje.commons.Path;
import br.info.felseje.evidence.model.Evidence;

import static br.info.felseje.commons.Utils.*;

/**
 * Contains useful methods for manipulating evidence.
 *
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class EvidenceUtils {

    public static final String DEFAULT_IMAGE_FORMAT = "png";

    public static String getScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static void takesScreenshot(String path, String imageName, String format) {
        Robot robot;
        BufferedImage image;
        try {
            robot = new Robot();
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = robot.createScreenCapture(screen);
            ImageUtils.saveImage(image, path, imageName, format);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void createEvidenceFolder(Status status) throws SecurityException {
        if (FileUtils.createIfNotExists(Path.getEvidencePath())) {
            FileUtils.createIfNotExists(Path.getEvidencePath(status));
        }
    }

    public static String getEvidencePath(Status status){
        return Path.getEvidencePath(status);
    }

    public static String getEvidenceFileName(Evidence evidence) {
        return evidence.getTestIdNumber() + "_" + DateUtils.formatarData(DateUtils.obterDataAtual(), "ddMMyyyy_HHmmss") + ".pdf";
    }
}
