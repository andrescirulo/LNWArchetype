package ar.com.lnwArchetype.client.useCases.inicio.pages.tabs;

import ar.com.lnwArchetype.client.useCases.inicio.rpc.InicioClientAsync;
import net.latin.client.rpc.GwtRpc;
import net.latin.client.widget.msg.GwtMensajesHandler;
import net.latin.client.widget.panels.GwtVerticalPanel;
import net.latin.client.widget.tabs.GwtMaterialTab;
import net.latin.client.widget.uploader.GwtFileUploader;

public class ArchivosTab extends GwtMaterialTab {

	private InicioClientAsync server;

	public ArchivosTab(GwtMensajesHandler handler) {
		super(handler);
		server = (InicioClientAsync)GwtRpc.getInstance().getServer( "InicioTestCase" );
		
		GwtFileUploader upload=new GwtFileUploader(this);
		GwtVerticalPanel panel = new GwtVerticalPanel("Subir Archivos",true);
		panel.add(upload);
		this.add(panel);
		upload.setAcceptedFiles("application/pdf");
		upload.setViewServer(server);
		upload.setDownloadServer(server);
	}
	
}
