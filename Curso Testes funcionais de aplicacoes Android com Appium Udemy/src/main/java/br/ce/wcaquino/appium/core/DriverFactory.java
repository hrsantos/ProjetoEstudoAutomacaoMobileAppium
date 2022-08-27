package br.ce.wcaquino.appium.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	
	/*******************************************************************************
	 * Classe respons�vel por criar e finalizar o driver, garantindo tamb�m que s� *
	 * haja uma inst�ncia do driver em execuss�o                                   *
	 * *****************************************************************************/
	
	private static AndroidDriver<MobileElement> driver;
	
	// singleton - garante que ser� criada uma nova inst�ncia caso nenhuma ainda tenha sido criada
	public static AndroidDriver<MobileElement> getDriver(){
		if(driver == null) {
			createDriver();
		}
		return driver;
	}	
	
	//m�todo que cria pr�piamente o driver passando todas as capabilities
	private static void createDriver() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "android");
		desiredCapabilities.setCapability("noReset", "true");
		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability("deviceName", "0029579436");
		// Instala um app no momento da execuss�o do teste. Caso o app j� esteja
		// instalado, ele reinicia o app
		desiredCapabilities.setCapability(MobileCapabilityType.APP,
				"C:\\Users\\harol\\Documents\\Curso Testes funcionais de aplicacoes Android com Appium Udemy\\src\\main\\resources\\CTAppium_1_2.apk");
		
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//m�todo de finaliza��o do driver
	public static void killDriver() {
		if (driver != null) {
		driver.quit();
		driver = null;
		}
	}
}
