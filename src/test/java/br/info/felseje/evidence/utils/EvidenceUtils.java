package br.info.felseje.evidence.utils;

import java.util.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import br.info.felseje.evidence.model.Evidence;
import io.cucumber.java.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import br.info.felseje.commons.Path;

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

    public static Map<String, String> getFieldAndValueMap(Evidence evidence) {
        Map<String, String> fieldAndValue = new HashMap<>();
        fieldAndValue.put("Teste", evidence.getTestName());
        fieldAndValue.put("Número", evidence.getTestIdNumber());
        fieldAndValue.put("Ciclo", evidence.getTestCycle());
        fieldAndValue.put("Executor", evidence.getTesterName());
        fieldAndValue.put("Duração", evidence.getTestRuntime());
        fieldAndValue.put("Data", evidence.getTestDate());
        fieldAndValue.put("Situação", evidence.getTestStatus().name());
        return fieldAndValue;
    }

    public static List<Map<String, String>> getFieldAndValueList(Evidence evidence) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>(), map2 = new HashMap<>();
        map1.put("Teste", evidence.getTestName());
        map2.put("Número", evidence.getTestIdNumber());
        map2.put("Ciclo", evidence.getTestCycle());
        map2.put("Executor", evidence.getTesterName());
        map2.put("Duração", evidence.getTestRuntime());
        map2.put("Data", evidence.getTestDate());
        map2.put("Situação", evidence.getTestStatus().name());
        return Arrays.asList(map1, map2);
    }
}
