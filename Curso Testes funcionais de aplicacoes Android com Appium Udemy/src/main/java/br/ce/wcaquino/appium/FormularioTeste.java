package br.ce.wcaquino.appium;

import java.net.MalformedURLException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.appium.core.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormularioTeste {

	// ################### Teste de instalação do APK ###################
	
	// Criação do driver android para o carregamento do device no Appium
	private AndroidDriver<MobileElement> driver;

	// Seção com as capabilities recuperadas no Appium
	// Aprimorada para otimizar o reuso do código
	@Before
	public void inicializarAppium() throws MalformedURLException {
		
		//criando o driver via classe DriverFactory
		driver = DriverFactory.getDriver();
		
		// Acessar formulario
		driver.findElement(By.xpath("//*[@text='Formulário']")).click();
	}
	
	//encerra o teste e fecha o app
	@After
	public void tearDown() {
		
		//encerrando o driver apos o teste via classe DriverFactory
		DriverFactory.killDriver();
	}

	// ################### Teste de preenchimento do campo nome ###################

	@Test
	public void devePreencherCampoTexto() throws MalformedURLException, InterruptedException {

		// Lista com todos os elementos da tela - utilizando o método findElements do
		// Selenium que tras uma lista de elementos
		List<MobileElement> elementosEncontrados = driver.findElements(By.className("android.widget.TextView"));

		// impressão do elementos apresentados na tela
		/*
		 * for(MobileElement elemento: elementosEncontrados) {
		 * System.out.println(elemento.getText()); }
		 */

		// comando de click no elemento desejado referenciando sua posição numérica
		elementosEncontrados.get(1).click();

		// Escrever nome

		// Mapeando o elemento que receberá o valor nome utilizando o método MobileBy do
		// appium
		// que possibilita mapear pelo método AccessibilitId (content-desc) próprio para
		// Mobile
		MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));

		campoNome.sendKeys("Haroldo");

		// Checar nome escrito
		Assert.assertEquals("Haroldo", campoNome.getText());

	}

	// ################### Teste de seleção de opção no combobox ###################

	@Test
	public void deveInteragirComComboBox() throws MalformedURLException, InterruptedException {

		// clicar no combo
		driver.findElement(MobileBy.AccessibilityId("console")).click();

		// Selecionar elemento no combo
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Nintendo Switch']")).click();

		// Checar se a opção está correta
		// MobileElement opcao =
		// driver.findElement(By.xpath("//android.widget.TextView[@text='Nintendo
		// Switch']"));
		MobileElement opcao = driver.findElement(By.xpath("//android.widget.Spinner/android.widget.TextView"));
		Assert.assertEquals("Nintendo Switch", opcao.getText());

	}

	// ################### Teste de seleção de opção do checklist e switch
	// ###################

	@Test
	public void deveInteragirComSwitchCheckBox() throws MalformedURLException, InterruptedException {

		// verificar status dos elementos

		// Mapeamento via className do Selenium
		MobileElement check = driver.findElement(By.className("android.widget.CheckBox"));
		// Mapeamento via AccessibilitId do appium
		MobileElement switc = driver.findElement(MobileBy.AccessibilityId("switch"));

		// validação utilizando o atributo do elemento
		Assert.assertTrue(check.getAttribute("checked").equals("false"));
		Assert.assertTrue(switc.getAttribute("checked").equals("true"));

		// clicar nos elementos
		check.click();
		switc.click();

		// verificar estados alterados utilizando o Assert do JUnit
		Assert.assertFalse(check.getAttribute("checked").equals("false"));
		Assert.assertFalse(switc.getAttribute("checked").equals("true"));

	}

	// ################### Desafio: Preenchimento do formulario e validação do
	// cadastro ###################

	@Test
	public void preencherFormularioEValidarCadastro() throws MalformedURLException, InterruptedException {

		// Preencher os campos do formulario

		// preenchendo nome
		MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));
		campoNome.sendKeys("Haroldo");

		// preenchendo combobox
		driver.findElement(MobileBy.AccessibilityId("console")).click();
		driver.findElement(By.xpath("//android.widget.ListView/android.widget.CheckedTextView[@text='PS4']")).click();

		// Preechendo checkbox e switch
		driver.findElement(MobileBy.AccessibilityId("check")).click();
		driver.findElement(MobileBy.AccessibilityId("switch")).click();

		// Clicar no botão Salvar
		driver.findElement(By.xpath("//android.widget.TextView[@text='SALVAR']")).click();

		// Validar Cadastro

		// Validação de campo na situação de string composta utilizando o paramentro
		// starts-with do xpath
		MobileElement campoNomeCad = driver
				.findElement(By.xpath("//android.widget.TextView[starts-with(@text,'Nome:')]"));
		// validação do dado cadastrado, utilizando o sifxo da string com o método
		// endsWith do Selenium
		Assert.assertTrue(campoNomeCad.getText().endsWith("Haroldo"));

		MobileElement campoConsoleCad = driver
				.findElement(By.xpath("//android.widget.TextView[starts-with(@text,'Console:')]"));
		Assert.assertTrue(campoConsoleCad.getText().endsWith("ps4"));

		MobileElement campoCheckBoxCad = driver
				.findElement(By.xpath("//android.widget.TextView[starts-with(@text,'Switch:')]"));
		Assert.assertTrue(campoCheckBoxCad.getText().endsWith("Off"));

		MobileElement campoSwitchCad = driver
				.findElement(By.xpath("//android.widget.TextView[starts-with(@text,'Checkbox:')]"));
		Assert.assertTrue(campoSwitchCad.getText().endsWith("Marcado"));

	}

}
