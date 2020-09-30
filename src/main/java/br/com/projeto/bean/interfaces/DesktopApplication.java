package br.com.projeto.bean.interfaces;

import org.openqa.selenium.winium.WiniumDriver;

public interface DesktopApplication {

    WiniumDriver getDriver(String filePath, String remoteLink);
}
