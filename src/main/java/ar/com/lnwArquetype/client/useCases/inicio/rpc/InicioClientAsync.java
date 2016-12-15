package ar.com.lnwArchetype.client.useCases.inicio.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ar.com.lnwArchetype.client.domain.Persona;
import net.latin.client.rpc.GwtRpcInterfaceAsync;
import net.latin.client.widget.base.RespuestRPC;
import net.latin.client.widget.base.SimpleRespuestRPC;
import net.latin.client.widget.uploader.rpc.GwtDownloadFileClientAsync;
import net.latin.client.widget.uploader.rpc.GwtViewFileClientAsync;

public interface InicioClientAsync extends GwtRpcInterfaceAsync,GwtViewFileClientAsync,GwtDownloadFileClientAsync{

	void getTextoInicial(AsyncCallback<SimpleRespuestRPC> callback);

	void getListaPersonas(AsyncCallback<RespuestRPC<Persona>> callback);
}
