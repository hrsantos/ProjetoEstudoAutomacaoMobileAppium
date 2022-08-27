package br.ce.wcaquino.appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.Assert;


public class CalculadoraMotoG4Teste {
	
	@Test
	public void deveSomarDoisValores() throws MalformedURLException, InterruptedException{
		
		
		//Se��o com as capabilities recuperadas no Appium
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "android");
	    desiredCapabilities.setCapability("noReset", "true");
	    desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
	    desiredCapabilities.setCapability("automationName", "uiautomator2");
	    desiredCapabilities.setCapability("deviceName", "0029579436");
	    desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
	    desiredCapabilities.setCapability("ignoreUnimportantViews", "true");
	    desiredCapabilities.setCapability("disableAndroidWatchers", "true");
	    
	    //Cria��o do driver android para o carregamento do device no Appium
	    AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

	    //Mapeamento dos elementos e a��es
	    MobileElement el1 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_2");
	    el1.click();
	    MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("plus");
	    el2.click();
	    MobileElement el3 = (MobileElement) driver.findElementById("com.android.calculator2:id/digit_2");
	    el3.click();
	    MobileElement el4 = (MobileElement) driver.findElementById("com.android.calculator2:id/result");

	    //M�todo nativo de assert do JUnit
	    org.junit.Assert.assertEquals("4", el4.getText());
	    
	    
	    driver.quit();
	}
}
