package br.info.felseje.evidence.utils;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.io.IOException;
import java.io.FileNotFoundException;
import br.info.felseje.commons.Utils;
import com.itextpdf.text.Font;
import io.cucumber.java.Status;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import br.info.felseje.commons.Path;
import br.info.felseje.commons.Utils.DateUtils;
import br.info.felseje.commons.Utils.FileUtils;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.model.ScreenCapture;
import br.info.felseje.evidence.enums.TableWidthImpl;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static br.info.felseje.evidence.utils.PDFCreator.*;

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
            Utils.ImageUtils.saveImage(image, path, imageName, format);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    public static void createEvidenceFolder(Status status) throws SecurityException {
        if (FileUtils.createIfNotExists(Path.getEvidencePath())) {
            try {
                FileUtils.createIfNotExists(Path.getEvidencePath(status));
            } catch (RuntimeException runtimeException) {
                runtimeException.printStackTrace(System.err);
            }
        }
    }
}
