package br.com.projeto.commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import br.com.projeto.evidence.model.Evidence;
import br.com.projeto.evidence.model.ScreenCapture;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    /* Atributos da evidência */
    protected static Evidence evidence;
    private static final String DEFAULT_IMAGE_FORMAT = "png";

    /* Atributos do driver */
    protected static WebDriver webDriver;
    protected static WebDriverWait waiter;
    protected static final int MAX_TIME_WAIT = 20;

    protected void initializeEvidence() {
        evidence = new Evidence();
    }

    protected void initializeWebApplication(String driverName) {
        webDriver = DriverFactory.getDriver(driverName);
        waiter = new WebDriverWait(webDriver, MAX_TIME_WAIT);
        webDriver.manage().timeouts().implicitlyWait(MAX_TIME_WAIT, TimeUnit.SECONDS);
    }

    protected static void closeWeb() {
        webDriver.quit();
    }

    protected String getScreenshotAsBase64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    protected void screenshot() {
        try {
            screenshot(null);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    protected void screenshot(String description) throws IOException {
        evidence.getScreenCaptureList().add(new ScreenCapture(getScreenshotAsBase64(webDriver), description));
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
}
