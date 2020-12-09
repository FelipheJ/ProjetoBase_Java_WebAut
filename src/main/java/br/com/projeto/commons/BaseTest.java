package br.com.projeto.commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Base64;
import java.awt.Rectangle;
import java.util.ArrayList;
import junit.framework.TestCase;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import br.com.projeto.evidence.model.Evidence;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.projeto.evidence.model.EvidenceReport;
import br.com.projeto.evidence.model.SeleniumEvidence;

public class BaseTest {

    /* Atributos da evidência */
    protected static String errors = null;
    protected static Evidence evidence = null;
    protected static EvidenceReport report = null;
    protected static ArrayList<SeleniumEvidence> evidences = null;
    private static final String DEFAULT_IMAGE_FORMAT = "png";

    /* Atributos do driver */
    protected static WebDriver webDriver;
    protected static WebDriverWait waiter;
    protected static final int MAX_TIME_WAIT = 20;

    protected void initializeEvidence() {
        evidence = new Evidence();
        evidences = new ArrayList<>();
    }

    protected void initializeWebApplication(String driverName) {
        webDriver = DriverFactory.getDriver(driverName);
        waiter = new WebDriverWait(webDriver, MAX_TIME_WAIT);
        webDriver.manage().timeouts().implicitlyWait(MAX_TIME_WAIT, TimeUnit.SECONDS);
    }

    protected static void closeWeb() {
        webDriver.quit();
    }

    private String getScrrenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    protected void screenshot(String imageName, String directory) {
        Robot robot;
        BufferedImage image;
        try {
            robot = new Robot();
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = robot.createScreenCapture(screen);
            Utils.ImageUtils.saveImage(image, directory, imageName, DEFAULT_IMAGE_FORMAT);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    protected void screenshot(String description) {
        Robot robot;
        BufferedImage image;
        try {
            robot = new Robot();
            Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            image = robot.createScreenCapture(screen);
            evidences.add(new SeleniumEvidence(description, Base64.getEncoder().encodeToString(Utils.ImageUtils.getBytes(image, "jpg"))));
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    protected void browserScreenshot(String description) {
        try {
            evidences.add(new SeleniumEvidence(description, getScrrenshotAsBase64(webDriver)));
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }
    }

    protected void setError(Throwable t, WebDriver driver) {
        try {
            evidences.add(new SeleniumEvidence(t.getLocalizedMessage(), getScrrenshotAsBase64(driver)));
            errors = t.toString();
            TestCase.fail(t.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
