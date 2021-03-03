package br.info.felseje.commons;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DSL {

    /* Construtores */
    public DSL(WebDriver driver) {
        webDriver = driver;
        driverWait = new WebDriverWait(webDriver, 20);
    }

    /* Atributos */
    private static WebDriver webDriver;
    private static WebDriverWait driverWait;

    /* Cliques */
    public void clicar() {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(webDriver.switchTo().activeElement()))).click().perform();
    }

    public void clicar(By localizador) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(obterElemento(localizador)))).click().perform();
    }

    public void clicar(WebElement elemento) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(elemento))).click().perform();
    }

    /* Escritas */
    public void escrever(String txt) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(webDriver.switchTo().activeElement()))).sendKeys(txt).perform();
    }

    public void escrever(Keys key) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(webDriver.switchTo().activeElement()))).sendKeys(key).perform();
    }

    public void escrever(By localizador, String txt) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(obterElemento(localizador)))).sendKeys(txt).perform();
    }

    public void escrever(By localizador, Keys key) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(obterElemento(localizador)))).sendKeys(key).perform();
    }

    public void escrever(WebElement elemento, String txt) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(elemento))).sendKeys(txt).perform();
    }

    public void escrever(WebElement elemento, Keys key) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(elemento))).sendKeys(key).perform();
    }

    /* Limpar */
    public void limpar() {
        WebElement e = webDriver.switchTo().activeElement();
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(e))).perform();
        e.clear();
    }

    public void limpar(By localizador) {
        WebElement e = obterElemento(localizador);
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(e))).perform();
        e.clear();
    }

    public void limpar(WebElement elemento) {
        new Actions(webDriver).moveToElement(driverWait.until(ExpectedConditions.elementToBeClickable(elemento))).perform();
        elemento.clear();
    }

    /* Visibilidade e Presença */
    public boolean estaVisivel() {
        boolean retorno;
        try {
            retorno = driverWait.until(ExpectedConditions.visibilityOf(webDriver.switchTo().activeElement())).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    public boolean estaVisivel(By localizador) {
        boolean retorno;
        try {
            retorno = driverWait.until(ExpectedConditions.visibilityOf(obterElemento(localizador))).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    public boolean estaVisivel(WebElement elemento) {
        boolean retorno;
        try {
            retorno = driverWait.until(ExpectedConditions.visibilityOf(elemento)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    public boolean estaPresente(By localizador) {
        boolean retorno = true;
        try {
            driverWait.until(ExpectedConditions.presenceOfElementLocated(localizador));
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
            retorno = false;
        }
        return retorno;
    }

    /* Elementos */
    public WebElement obterElemento(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    public WebElement obterElemento(By localizador) {
        return webDriver.findElement(localizador);
    }

    public List<WebElement> obterElementos(String xpath) {
        return webDriver.findElements(By.xpath(xpath));
    }

    public List<WebElement> obterElementos(By localizador) {
        return webDriver.findElements(localizador);
    }
}
