package ar.com.lnwArchetype.client.useCases.inicio.pages;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import ar.com.lnwArchetype.client.domain.Persona;
import ar.com.lnwArchetype.client.useCases.inicio.rpc.InicioClientAsync;
import gwt.material.design.client.ui.table.cell.TextColumn;
import net.latin.client.rpc.GwtRespuestAsyncCallback;
import net.latin.client.rpc.GwtRpc;
import net.latin.client.widget.base.GwtPage;
import net.latin.client.widget.base.RespuestRPC;
import net.latin.client.widget.checkbox.GwtCheckBox;
import net.latin.client.widget.modal.GwtModal;
import net.latin.client.widget.table.GwtListDataSource;
import net.latin.client.widget.table.GwtMaterialTable;
import net.latin.client.widget.table.GwtMaterialTableManager;
import net.latin.client.widget.table.GwtTableCategoryFactory;

public class TablasPage extends GwtPage implements GwtMaterialTableManager<Persona> {

	private InicioClientAsync server;
	private GwtMaterialTable<Persona> table;
	private GwtListDataSource<Persona> dataSource;

	public TablasPage() {
		server = (InicioClientAsync)GwtRpc.getInstance().getServer( "InicioCase" );
		
		dataSource=new GwtListDataSource<Persona>();
		table = new GwtMaterialTable<Persona>(this,new GwtTableCategoryFactory<Persona>() {
			public String getCategory(Persona obj) {
				return obj.getProfesion();
			}
		});
		table.setUseLoadOverlay(true);
		table.setPager(dataSource,10);
		
		GwtCheckBox chkCategorias=new GwtCheckBox();
		chkCategorias.setText("Agrupar en categorias");
		chkCategorias.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				agruparEnCategorias(event.getValue());
			}
		});
		
		this.add(chkCategorias);
		this.add(table);
	}
	
	protected void agruparEnCategorias(Boolean agrupar) {
		table.setUseCategories(agrupar);
		table.setRedraw(true);
        table.refreshView();
	}

	@Override
	public void addColumns(GwtMaterialTable<Persona> table) {
		table.setTableTitle("Datos de las personas");
		table.addColumn(new TextColumn<Persona>(){
			public String getValue(Persona object) {
				return object.getNombre();
			}
		},"Nombre");
		table.addColumn(new TextColumn<Persona>(){
			public String getValue(Persona object) {
				return object.getApellido();
			}
		},"Apellido");
		table.addColumn(new TextColumn<Persona>(){
			public String getValue(Persona object) {
				Date fecha =object.getFechaNacimiento(); 
				return fecha.getDate() + "/" + (fecha.getMonth()+1) + "/" + (fecha.getYear() + 1900);
			}
		},"Fecha Nacimiento");
		table.addColumn(new TextColumn<Persona>(){
			public String getValue(Persona object) {
				return object.getProfesion();
			}
		},"Profesion");
	}
	
	@Override
	protected void onVisible() {
		GwtModal.blockScreen("Buscando datos...");
		
		server.getListaPersonas(new GwtRespuestAsyncCallback<RespuestRPC<Persona>>(this) {
			public void onOk(RespuestRPC<Persona> result) {
				dataSource.clear();
				dataSource.add(0, result.getBusinessObjectsList());
				table.getPager().firstPage();
				GwtModal.unblockScreen();
			}
		});
	}
	
}
