package br.info.felseje.commons;

import br.info.felseje.exceptions.PathNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * WebDriver builder.
 * @author Feliphe Jesus
 * @version 1.0.0
 */
public class DriverFactory {

    public static WebDriver getDriver(String browserName) {
        WebDriver webDriver;
        String path = Path.getBrowserPath(browserName.toUpperCase());
        if (path == null || path.isEmpty()) {
            throw new PathNotFoundException("Did you set up the webdriver.properties file?");
        }
        switch (browserName.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", path);
                webDriver = new ChromeDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", path);
                webDriver = new InternetExplorerDriver();
                break;
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", path);
                webDriver = new FirefoxDriver();
                break;
            case "SAFARI":
                webDriver = new SafariDriver();
                break;
            case "OPERA":
                System.setProperty("webdriver.opera.driver", path);
                webDriver = new OperaDriver();
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver", path);
                webDriver = new EdgeDriver();
                break;
            default:
                throw new WebDriverException("This browser isn't implemented.");
        }
        return webDriver;
    }

    public static WebDriver getDriver(String browserName, MutableCapabilities capabilities) {
        WebDriver webDriver;
        switch (browserName.toUpperCase()) {
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", Path.getBrowserPath("CHROME"));
                webDriver = new ChromeDriver((ChromeOptions) capabilities);
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", Path.getBrowserPath("IE"));
                webDriver = new InternetExplorerDriver((InternetExplorerOptions) capabilities);
                break;
            case "FIREFOX":
                System.setProperty("webdriver.gecko.driver", Path.getBrowserPath("FIREFOX"));
                webDriver = new FirefoxDriver((FirefoxOptions) capabilities);
                break;
            case "SAFARI":
                webDriver = new SafariDriver((SafariOptions) capabilities);
                break;
            case "OPERA":
                System.setProperty("webdriver.opera.driver", Path.getBrowserPath("OPERA"));
                webDriver = new OperaDriver((OperaOptions) capabilities);
                break;
            case "EDGE":
                System.setProperty("webdriver.edge.driver", Path.getBrowserPath("EDGE"));
                webDriver = new EdgeDriver((EdgeOptions) capabilities);
                break;
            default:
                throw new WebDriverException("This browser isn't implemented.");
        }
        return webDriver;
    }
}
