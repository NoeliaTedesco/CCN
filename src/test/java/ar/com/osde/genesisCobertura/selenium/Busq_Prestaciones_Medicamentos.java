<<<<<<< HEAD
package ar.com.osde.genesisCobertura.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Busq_Prestaciones_Medicamentos {

	private WebDriver driver;
	private WebDriverWait wait;
	private Logger log;

	@CacheLookup
	@FindBy(id = "resultadoPrestacion")
	private WebElement ResultadoPrestaciones;

	@FindBy(xpath = "//input[contains(@class, 'ui-autocomplete-input')]")
	private WebElement divInput;

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

	public Busq_Prestaciones_Medicamentos(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 30);
		PageFactory.initElements(driver, this);
		log = Logger.getLogger(getClass().getName());
	}

	public void cargarPrestacion(String NombreServicio) {

		List<WebElement> listaServicios = driver.findElements(By.xpath("//*[starts-with(@id, 'suaServicioWidget')]"));
		if (listaServicios.size() > 0) {
			ArrayList<String> serviciosAbiertos = new ArrayList<String>();

			for (WebElement servicio : listaServicios) {
				serviciosAbiertos.add(servicio.getAttribute("widgettitle"));
				if ((servicio.getAttribute("widgettitle")).equals(NombreServicio)) {
					List<WebElement> inputs = servicio
							.findElements(By.xpath("//input[contains(@class, 'ui-autocomplete-input')]"));
					int i = 0;
					for (WebElement input : inputs) {
						JavascriptExecutor js = ((JavascriptExecutor) driver);
						js.executeScript("document.getElementsByClassName('ui-autocomplete-input')[" + i
								+ "].setAttribute('value', 'CONSULTA EN CONSULTORIO')");
						i++;
					}
				}

			}
		}
		btnBuscar.click();

	}

	/*
	 * public boolean validarValorCampo(String busqueda) throws AWTException {
	 * 
	 * String prestacionSeleccionada = autocompletePrestacion.getText().toString();
	 * 
	 * if (prestacionSeleccionada == busqueda) { return true; } else { return false;
	 * }
	 */

}
=======
package ar.com.osde.genesisCobertura.selenium;

public class Busq_Prestaciones_Medicamentos {

}
>>>>>>> branch 'master' of https://github.com/NoeliaTedesco/CCN.git
