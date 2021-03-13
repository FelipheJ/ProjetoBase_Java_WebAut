package br.info.felseje.commons;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import br.info.felseje.evidence.model.Evidence;
import br.info.felseje.evidence.model.ScreenCapture;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.info.felseje.evidence.utils.EvidenceUtils.getScreenshotAsBase64;

/**
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class BaseTest {

    /* Atributos da evidência */
    protected static Evidence evidence;

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
}
