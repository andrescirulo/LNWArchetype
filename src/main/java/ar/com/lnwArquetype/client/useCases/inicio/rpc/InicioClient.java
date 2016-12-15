package ar.com.lnwArchetype.client.useCases.inicio.rpc;

import ar.com.lnwArchetype.client.domain.Persona;
import net.latin.client.rpc.GwtRpcInterface;
import net.latin.client.widget.base.RespuestRPC;
import net.latin.client.widget.base.SimpleRespuestRPC;
import net.latin.client.widget.uploader.rpc.GwtDownloadFileClient;
import net.latin.client.widget.uploader.rpc.GwtViewFileClient;

public interface InicioClient extends GwtRpcInterface,GwtViewFileClient,GwtDownloadFileClient{

	SimpleRespuestRPC getTextoInicial();
	
	RespuestRPC<Persona> getListaPersonas();
}
