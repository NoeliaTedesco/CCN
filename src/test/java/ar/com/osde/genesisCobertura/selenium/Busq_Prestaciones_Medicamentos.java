package ar.com.osde.genesisCobertura.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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
	@FindBy(css = "div.itemView")
	private WebElement medicamentos;
	@FindBy(xpath = "//input[contains(@class, 'ui-autocomplete-input']")
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

	public Busq_Prestaciones_Medicamentos(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, 30);
		PageFactory.initElements(driver, this);
		log = Logger.getLogger(getClass().getName());
	}

	public void cargarPrestacion(String busqueda) throws AWTException {
		List<WebElement> lstRegistros = autocompletePrestacion.findElements(By.className("elemento input-prestacion validate params"));
		for (WebElement registros : lstRegistros) {
			List<WebElement> registro  = registros.findElements(By.className("ui-autocomplete-input"));
			for(WebElement autocomplete : registro) {
				autocomplete.click();
				autocomplete.sendKeys(busqueda);
				autocomplete.submit();
				
	/*			Robot rw = new Robot();
				rw.keyPress(KeyEvent.VK_DOWN);
				rw.keyRelease(KeyEvent.VK_DOWN);
				rw.keyPress(KeyEvent.VK_ENTER);
				rw.keyRelease(KeyEvent.VK_ENTER);
*/
			}
		}

	}

	public boolean validarValorCampo(String busqueda) throws AWTException {

		String prestacionSeleccionada = autocompletePrestacion.getText().toString();

		if (prestacionSeleccionada == busqueda) {
			return true;
		} else {
			return false;
		}
	}

}
