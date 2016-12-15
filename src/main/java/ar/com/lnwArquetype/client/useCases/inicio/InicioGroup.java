package ar.com.lnwArchetype.client.useCases.inicio;

import com.google.gwt.core.client.GWT;

import ar.com.lnwArchetype.client.useCases.inicio.pages.InicioPage;
import ar.com.lnwArchetype.client.useCases.inicio.pages.TablasPage;
import ar.com.lnwArchetype.client.useCases.inicio.rpc.InicioClient;
import net.latin.client.rpc.GwtRpc;
import net.latin.client.rpc.GwtServerCreator;
import net.latin.client.widget.base.GwtPageGroup;

public class InicioGroup extends GwtPageGroup implements GwtServerCreator{

	public static final String PAGINA_INICIO="Inicio";
	public static final String PAGINA_TABLAS="Tablas";
	public static final String PAGINA_ARCHIVOS = "Archivos";
	
	@Override
	protected void registerRpcServers() {
		GwtRpc.getInstance().addServer( "InicioCase", "inicio.gwt" , this );
	}

	@Override
	protected void registerPages() {
		addPage(PAGINA_INICIO, new InicioPage());
		addPage(PAGINA_TABLAS, new TablasPage());
	}

	@Override
	protected String registerFirstPage() {
		return PAGINA_INICIO;
	}

	@Override
	public Object createServer() {
		return GWT.create(InicioClient.class);
	}

}
