package ar.com.osde.genesisCobertura.selenium;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class Busqueda_Prestadores {
	
	@CacheLookup
	@FindBy(id = "btnListado")	    
	private WebElement btnListado;
	@FindBy(id = "btnPrestacion")	    
	private WebElement btnPrestacion;	
	@FindBy(id = "btnVolver")	    
	private WebElement btnVolver;
	@FindBy(id = "btnVerMapa")	    
	private WebElement btnMapa;
	@FindBy(id = "btnRegistrar")	    
	private WebElement btnRegistrar;
	@FindBy(id = "btnEnviarCorreo")	    
	private WebElement btnEnviarCorreo; 
	@FindBy(id = "btnImprimir")	    
	private WebElement btnImprimir;
	@FindBy(xpath = "//div[contains(@textContent, 'Seleccionar Todos')]")    
	private WebElement checkSeleccionarTodos;
	@FindBy(css = "div.itemView.prestadores")    
	private WebElement checkSeleccionarPrestador;
	@FindBy(xpath = "//a[contains(@text, 'Cercanía')]")	    
	private WebElement checkOrdenarCercania;
	@FindBy(className  = "ordenCantidad  ")    
	private WebElement checkOrdenarCantPrest;
	@FindBy(css  = "div.prestacionesPrestador.prestacionNoEncontrada.generic-btn")    
	private WebElement prestacionNoEncontrada;
	@FindBy(css  = "div.prestacionesPrestador")    
	private WebElement prestacionEncontrada;
	
	
	
	
}
