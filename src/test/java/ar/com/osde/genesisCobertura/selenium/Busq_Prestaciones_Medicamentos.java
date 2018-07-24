package ar.com.osde.genesisCobertura.selenium;

import org.openqa.selenium.WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


public class Busq_Prestaciones_Medicamentos {
	
	@CacheLookup
	@FindBy(id = "resultadoPrestacion")	    
	private WebElement ResultadoPrestaciones;
	@FindBy(css = "div.itemView")	    
	private WebElement medicamentos;

	@FindBy(className = "ui-autocomplete-input")	    
	private WebElement autocompletePrestacion;
	@FindBy(css = "select.validate.ajaxComboBox")	    
	private WebElement comboContexto;
	@FindBy(xpath = "//img[contains(@src, 'http://concobergent.intranet.osde:9380/ccn-frontend/images/usabilidad/iconos_on/plus.png')]")    
	private WebElement imagenAgregar;
	@FindBy(xpath = "//img[contains(@src, 'http://concobergent.intranet.osde:9380/ccn-frontend/images/limpiar.png')]")    
	private WebElement imagenBorrar;
	@FindBy(xpath = "//div[contains(@textContent, 'Domicilio')]")	    
	private WebElement filtroDomicilio;
	@FindBy(xpath = "//img[contains(@src, 'http://concobergent.intranet.osde:9380/ccn-frontend/images/usabilidad/iconos_on/editar-on.gif')]")    
	private WebElement imagenEditar;
	@FindBy(xpath = "//div[contains(@textContent, 'Zona')]")	    
	private WebElement filtroZona;
	@FindBy(xpath = "//div[contains(@title, 'Filial')]")	    
	private WebElement comboFilial;
	@FindBy(xpath = "//div[contains(@title, 'Zona')]")	    
	private WebElement comboZona;
	@FindBy(css = "select.subzona.notNull.validate.ajaxComboBox")	    
	private WebElement comboSubZona;
	@FindBy(xpath = "//div[contains(@textContent, 'Prestador')]")	    
	private WebElement filtroPrestador;
	@FindBy(css = "select.filial.ajaxComboBox")	    
	private WebElement comboFilialPrestador;
	@FindBy(id = "prestador")	    
	private WebElement autocompletePrestador;
	@FindBy(id = "btnBuscar")	    
	private WebElement btnBuscar;
	@FindBy(id = "btnLimpiar")	    
	private WebElement btnLimpiar;
	
	
	public boolean cargarPrestacion(String busqueda) throws AWTException {
		autocompletePrestacion.click();
		autocompletePrestacion.sendKeys(busqueda);
		Robot rw = new Robot();
		rw.keyPress(KeyEvent.VK_DOWN);
		rw.keyRelease(KeyEvent.VK_DOWN);
		rw.keyPress(KeyEvent.VK_ENTER);
		rw.keyRelease(KeyEvent.VK_ENTER);
		
		String prestacionSeleccionada = autocompletePrestacion.getText().toString();

		if (prestacionSeleccionada == busqueda){
		return true;
		} else {
			return false;
		}
	}
	
}
