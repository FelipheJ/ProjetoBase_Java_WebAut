package br.com.projeto.bean.enums;

import java.net.URL;
import java.net.MalformedURLException;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.DesktopOptions;
import br.com.projeto.bean.interfaces.DesktopApplication;


public enum Desktop implements DesktopApplication {

    WINIUM {
        @Override
        public WiniumDriver getDriver(String filePath, String remoteLink) {
            WiniumDriver driver;
            DesktopOptions options;
            options = new DesktopOptions();
            options.setApplicationPath(filePath);
            try {
                driver = new WiniumDriver(new URL(remoteLink), options);
            } catch (MalformedURLException e) {
                driver = null;
                e.printStackTrace();
            }
            return driver;
        }
    }

}
