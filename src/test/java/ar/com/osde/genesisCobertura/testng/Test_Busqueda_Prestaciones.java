package ar.com.osde.genesisCobertura.testng;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ar.com.osde.portalsua.selenium.Base_Test;
import ar.com.osde.portalsua.selenium.Busqueda_Multisujeto;
import ar.com.osde.portalsua.selenium.Consola_Recepcion;
import ar.com.osde.portalsua.selenium.Intranet_Login;
import ar.com.osde.portalsua.selenium.Intranet_Pagina_Principal;
import ar.com.osde.portalsua.selenium.Modo_Usuario;
import ar.com.osde.portalsua.selenium.PortalSua_Pagina_Principal;
import ar.com.osde.portalsua.selenium.Servicio_Sujeto;
import ar.com.osde.portalsua.selenium.Widget_Sujeto_Contacto;

public class Test_Busqueda_Prestaciones {
	

	private Busqueda_Multisujeto buscador;
	private Consola_Recepcion consola;
	private Modo_Usuario modoUsuario;
	private Logger log;
	private Base_Test driver;
    private WebDriver robot;
	
	public Test_Busqueda_Prestaciones() {
		
	}

	/* PRECONDICIONES:
	 *  				Usuario con b�squeda paralela desactivado,
	 * 					Usuario con permisos para recepcionar/atender todos los sectores
	 * 					Usuario logueado a filial/Cap Metro-Metro
	 * 					La filial/cap del usuario logueado debe tener recepcion de sujetos activados
	 */
	
	@Parameters({"browser", "ipNodo","usuario","password","ambiente", "sSujetoAnular","appGerencia"})
	@BeforeClass
	public void startUp(@Optional("iexplorer") String browser, String ipNodo, String usuario, String password, String ambiente, String sSujetoAnular, String appName, ITestContext ctx) throws InterruptedException {
		//Se espera un tiempo para cerrar el explorer anterior
		Thread.sleep(3000);
		log = Logger.getLogger(getClass().getName());
		if (log.isInfoEnabled()) {
			log.info("************** INICIA SUITE DE PRUEBA **************");
			log.info("Suite: " + ctx.getCurrentXmlTest().getSuite().getName());
			log.info("Inicia StartUp");			
		}
		//Se abre el browser
		driver = new Base_Test(browser, ipNodo); 
		// Creamos un objeto de tipo Login_Intranet
		Intranet_Login loginIntra = new Intranet_Login(driver.getDriver());
		// Hacemos el Login Completo
		loginIntra.login(usuario, password);
		// Creamos un objeto de tipo Intranet
		Intranet_Pagina_Principal intranet = new Intranet_Pagina_Principal(driver.getDriver());
		// Abrimos Portal desde integra
		intranet.AbrirAplicacion(appName);		
		// Creamos un objeto de tipo Portal SUA
		PortalSua_Pagina_Principal portalSUA = new PortalSua_Pagina_Principal(driver.getDriver());
		//Abrimos Portal en el ambiente que corresponda
		portalSUA.abrirPortal(ambiente);
		// Comprobamos que portal se haya cargado
		if(!portalSUA.seMuestraPortal()) portalSUA.recargarPortal();		
		Assert.assertTrue(portalSUA.seMuestraPortal());		
		// Creamos un objeto de tipo Login_Portal
		buscador = new Busqueda_Multisujeto(driver.getDriver()); 
		// Creamos un objeto de tipo Modo Usuario
		modoUsuario = new Modo_Usuario(driver.getDriver());		
		// Creamos un objeto de tipo Consola Recepcion
		consola = new Consola_Recepcion(driver.getDriver());		
		Thread.sleep(5000);
		consola.irConsolaRecepcion();
		// habilitamos el modo recepcion
		modoUsuario.habilitar_modo_recepcion();
		//Si encuentra el socio en consola lo anula				
		consola.anularSujeto(sSujetoAnular);
	}
	
	
	@Parameters({"nombreCarpetaServicio", "nombreSubCarpetaServicio", "idServicio", "sSocio"})
	@BeforeMethod 
	public void beforeMethod(String nombreCarpetaServicio, String nombreSubCarpetaServicio, String idServicio, String sSujeto) throws InterruptedException {
		Thread.sleep(1000);    	    			
		// Se Busca el sujeto
		buscador.buscarSujeto(sSujeto);    	    	
		// Se crea un objeto de tipo Caracteristica_Sujeto
		Widget_Sujeto_Contacto wdgCaracteristica = new Widget_Sujeto_Contacto(driver.getDriver());
		// Se valida que este visible el widget de caracteristicas
		Assert.assertTrue(wdgCaracteristica.esVisibleCaracteristica());
		Servicio_Sujeto wdg_ServiciosSujetos = new Servicio_Sujeto(driver.getDriver());
		//Expandimos el panel izquierdo
		wdg_ServiciosSujetos.expandirPanelIzquierdo();
		// Validamos que este visible el widget de busqueda de servicios de sujetos
		Assert.assertTrue(wdg_ServiciosSujetos.esVisibleWidgetBuscadorServiciosSujeto());
		//Abrimos el arbol de servicios del sujeto
		wdg_ServiciosSujetos.abrirArbolServicio();
		//Abrimos la carpeta del arbol de servicios del sujeto		
		wdg_ServiciosSujetos.expandirCarpetaArbolServicio(nombreCarpetaServicio, nombreSubCarpetaServicio);
		robot = driver.getDriver();
		JavascriptExecutor js = ((JavascriptExecutor) robot);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);
		//Abrimos el arbol de servicios del sujeto
		Assert.assertTrue(wdg_ServiciosSujetos.esVisibleServicio(idServicio));
		wdg_ServiciosSujetos.abrirServicio(idServicio);
		Thread.sleep(9000);	
		Assert.assertTrue(wdg_ServiciosSujetos.esVisibleWidgetServiciosSujeto(idServicio));
	}
	
	@Parameters({"sSocio","idServicio","nombreCarpetaServicio","nombreSubCarpetaServicio"})
	@Test
	public void abrirServicioSocio(String idSocio, String idServicio, String nombreCarpetaServicio, String nombreSubCarpetaServicio) throws InterruptedException {
		
		// Buscamos el socio con el indicador de observaciones de socio
		buscador.buscarSujeto(idSocio);    	    	
		//Creamos un objeto de tipo Caracteristica_Sujeto
		Widget_Sujeto_Contacto wdg_caracteristica = new Widget_Sujeto_Contacto(driver.getDriver());
		// Validamos que este visible el widget de caracteristicas
		Assert.assertTrue(wdg_caracteristica.esVisibleCaracteristica());    	
		Servicio_Sujeto wdg_ServiciosSujetos = new Servicio_Sujeto(driver.getDriver());
		//Expandimos el panel izquierdo
		wdg_ServiciosSujetos.expandirPanelIzquierdo();
		// Validamos que este visible el widget de busqueda de servicios de sujetos
		Assert.assertTrue(wdg_ServiciosSujetos.esVisibleWidgetBuscadorServiciosSujeto());
		//Abrimos el arbol de servicios del sujeto
		wdg_ServiciosSujetos.abrirArbolServicio();
		//Abrimos la carpeta del arbol de servicios del sujeto		
		wdg_ServiciosSujetos.expandirCarpetaArbolServicio(nombreCarpetaServicio, nombreSubCarpetaServicio);
		//Abrimos el arbol de servicios del sujeto
		wdg_ServiciosSujetos.abrirServicio(idServicio);
		Thread.sleep(9000);	
		Assert.assertTrue(wdg_ServiciosSujetos.esVisibleWidgetServiciosSujeto(idServicio));
		// Finalizamos el contacto
		wdg_caracteristica.finalizarContacto();
	}
	

}
