package ar.com.lnwArchetype.client.useCases.inicio.pages;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import ar.com.lnwArchetype.client.useCases.inicio.InicioGroup;
import ar.com.lnwArchetype.client.useCases.inicio.pages.tabs.ArchivosTab;
import ar.com.lnwArchetype.client.useCases.inicio.pages.tabs.CheckBoxTab;
import ar.com.lnwArchetype.client.useCases.inicio.pages.tabs.DialogsTab;
import ar.com.lnwArchetype.client.useCases.inicio.pages.tabs.MensajesTab;
import ar.com.lnwArchetype.client.useCases.inicio.pages.tabs.RadioButtonTab;
import ar.com.lnwArchetype.client.useCases.inicio.rpc.InicioClientAsync;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import net.latin.client.rpc.GwtRespuestAsyncCallback;
import net.latin.client.rpc.GwtRpc;
import net.latin.client.utils.ColorUtils;
import net.latin.client.widget.base.CustomBean;
import net.latin.client.widget.base.GwtPage;
import net.latin.client.widget.base.SimpleRespuestRPC;
import net.latin.client.widget.button.GwtButton;
import net.latin.client.widget.button.GwtButtonPopUp;
import net.latin.client.widget.date.GwtDatePicker;
import net.latin.client.widget.date.GwtTimePicker;
import net.latin.client.widget.form.GwtForm;
import net.latin.client.widget.form.GwtFormSubtitle;
import net.latin.client.widget.gwtswitch.GwtSwitch;
import net.latin.client.widget.list.GwtComboBox;
import net.latin.client.widget.list.GwtListBox;
import net.latin.client.widget.list.GwtListBoxAdapter;
import net.latin.client.widget.modal.GwtModal;
import net.latin.client.widget.panels.GwtHorizontalPanel;
import net.latin.client.widget.panels.GwtVerticalPanel;
import net.latin.client.widget.tabs.GwtMaterialTabs;
import net.latin.client.widget.textBox.GwtValidateTextBox;

public class InicioPage extends GwtPage {

	private InicioClientAsync server;
	private MaterialLink lbl;

	public InicioPage() {
		server = (InicioClientAsync)GwtRpc.getInstance().getServer( "InicioTestCase" );
		
		lbl = new MaterialLink();
		
		
		GwtValidateTextBox text=new GwtValidateTextBox();
		text.setPlaceholder("Texto numerico (sin Label)");
		text.setNumericNatural();
		text.setTabIndex(1);
		
		GwtValidateTextBox text2=new GwtValidateTextBox();
		text2.setLength(100);
		text2.setTrim();
		text2.setTabIndex(2);
		
		GwtValidateTextBox text3=new GwtValidateTextBox();
		text3.setPlaceholder("Textbox con footer");
		text3.setTrim();
		
		
		GwtForm form=new GwtForm("Titulo");
		form.addElement(text);
		form.addElementWithLabel("Elemento con label",text2);
		form.addElementWithFooter(text3, "Este elemento tiene footer");
		form.addSubtitle("Subtitulo de ejemplo");
		
		
		GwtButtonPopUp btnPop=new GwtButtonPopUp("Abrir Opciones");
		btnPop.addOption("Opcion 1", new ClickHandler() {
			public void onClick(ClickEvent event) {
				MaterialToast.fireToast("Elegiste Opcion 1");
			}
		});
		btnPop.addOption("Opcion 2", new ClickHandler() {
			public void onClick(ClickEvent event) {
				MaterialToast.fireToast("Elegiste Opcion 2");
			}
		});
		btnPop.addOption("Opcion 3", new ClickHandler() {
			public void onClick(ClickEvent event) {
				MaterialToast.fireToast("Elegiste Opcion 3");
			}
		});
		
		
		GwtVerticalPanel panelListbox=new GwtVerticalPanel("ListBox",true);
		GwtListBox<CustomBean> lstBox = new GwtListBox<CustomBean>();
		lstBox.setPlaceholder("Listbox de prueba");
		lstBox.setAdapter(new GwtListBoxAdapter<CustomBean>() {
			public String getListBoxDescription(CustomBean itemModel) {
				return itemModel.getString("desc");
			}
		});
		lstBox.addElement((new CustomBean()).put("id", 1).put("desc", "Primer elemento"));
		lstBox.addElement((new CustomBean()).put("id", 2).put("desc", "Segundo elemento"));
		lstBox.addElement((new CustomBean()).put("id", 3).put("desc", "Tercer elemento"));
		lstBox.addElement((new CustomBean()).put("id", 4).put("desc", "Cuarto elemento"));
		panelListbox.add(lstBox);
		
		
		GwtHorizontalPanel btnsListBox = new GwtHorizontalPanel();
		btnsListBox.add(new GwtButton("Agregar al final",new ClickHandler() {
			public void onClick(ClickEvent event) {
				Integer id=lstBox.getAllItems().size()+1;
				lstBox.addElement((new CustomBean()).put("id", id).put("desc", "Elemento " + id));
			}
		}));
		btnsListBox.add(new GwtButton("Agregar en el medio",new ClickHandler() {
			public void onClick(ClickEvent event) {
				Integer id=lstBox.getAllItems().size()+1;
				Integer pos=(int) Math.floor(lstBox.getAllItems().size()/2);
				lstBox.insertItem("Elemento " + id, (new CustomBean()).put("id", id).put("desc", "Elemento " + id), pos);
			}
		}));
		btnsListBox.add(new GwtButton("Quitar del medio",new ClickHandler() {
			public void onClick(ClickEvent event) {
				Integer pos=(int) Math.floor(lstBox.getAllItems().size()/2);
				lstBox.removeItem(pos);
			}
		}));
		panelListbox.add(btnsListBox);
		
		GwtFormSubtitle subtitulo=new GwtFormSubtitle("Ejemplo de ComboBox");
		panelListbox.add(subtitulo);
		GwtComboBox<CustomBean> comboBox = new GwtComboBox<CustomBean>();
		comboBox.setLabel("Combobox de prueba");
		comboBox.setAdapter(new GwtListBoxAdapter<CustomBean>() {
			public String getListBoxDescription(CustomBean itemModel) {
				return itemModel.getString("desc");
			}
		});
		comboBox.addItem((new CustomBean()).put("id", 1).put("desc", "Primer elemento"));
		comboBox.addItem((new CustomBean()).put("id", 2).put("desc", "Segundo elemento"));
		comboBox.addItem((new CustomBean()).put("id", 3).put("desc", "Tercer elemento"));
		comboBox.addItem((new CustomBean()).put("id", 4).put("desc", "Cuarto elemento"));
		panelListbox.add(comboBox);
		form.addElement(panelListbox);
		
		GwtVerticalPanel panelFechas = new GwtVerticalPanel("Fecha y Hora",true);
		GwtDatePicker datePicker = new GwtDatePicker();
		datePicker.setPlaceholder("Date Picker");
		panelFechas.add(datePicker);
		GwtTimePicker timePicker = new GwtTimePicker();
		timePicker.setPlaceholder("Time Picker");
		panelFechas.add(timePicker);
		form.addElement(panelFechas);
		
		
		GwtHorizontalPanel panelSwitch = new GwtHorizontalPanel("Switches",true);
		GwtSwitch sw1=new GwtSwitch();
		sw1.setColor(ColorUtils.BLACK);
		GwtSwitch sw2=new GwtSwitch();
		sw2.setColor(ColorUtils.BLUE);
		GwtSwitch sw3=new GwtSwitch();
		sw3.setColor(ColorUtils.RED);
		GwtSwitch sw4=new GwtSwitch();
		sw4.setColor(ColorUtils.ORANGE);
		panelSwitch.add(sw1);
		panelSwitch.add(sw2);
		panelSwitch.add(sw3);
		panelSwitch.add(sw4);
		form.addElement(panelSwitch);
		
		GwtHorizontalPanel panelBotones = new GwtHorizontalPanel("Botones",true);
		GwtButton btn1=new GwtButton("Boton Rojo");
		btn1.setColor(Color.RED,Color.WHITE);
		GwtButton btn2=new GwtButton("Boton Amarillo");
		btn2.setColor(Color.YELLOW,Color.BLACK);
		GwtButton btn3=new GwtButton("Boton Azul");
		btn3.setColor(Color.BLUE,Color.WHITE);
		GwtButton btn4=new GwtButton("Boton Verde");
		btn4.setColor(Color.GREEN,Color.GREEN_ACCENT_1);
		GwtButton btn5=new GwtButton("Boton Blanco");
		btn5.setColor(Color.WHITE,Color.BLACK);
		GwtButton btn6=new GwtButton("Boton Negro");
		btn6.setColor(Color.BLACK,Color.WHITE);
		btn6.setWaves(WavesType.LIGHT);
		
		panelBotones.add(btn1);
		panelBotones.add(btn2);
		panelBotones.add(btn3);
		panelBotones.add(btn4);
		panelBotones.add(btn5);
		panelBotones.add(btn6);
		form.addElement(panelBotones);
		
		GwtVerticalPanel panelVarios=new GwtVerticalPanel("Varios (Panel vertical)",true);
		panelVarios.add(btnPop);
		
		panelVarios.add(new GwtButton("Bloquear pantalla", new ClickHandler() {
			public void onClick(ClickEvent event) {
				GwtModal.blockScreen("Bloqueando por 5 segundos");
				Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
					public boolean execute() {
						GwtModal.unblockScreen();
						return false;
					}
				}, 5000);
			}
		}));
		form.addElement(panelVarios);
		
		form.addButton(new GwtButton("Ver Tablas", new ClickHandler() {
			public void onClick(ClickEvent event) {
				showPage(InicioGroup.PAGINA_TABLAS);
			}
		}));
		
		GwtMaterialTabs tabs=new GwtMaterialTabs(true);
		tabs.addTab("Mensajes",new MensajesTab(this));
		tabs.addTab("CheckBoxes",new CheckBoxTab(this));
		tabs.addTab("Radios",new RadioButtonTab(this));
		tabs.addTab("Form",form.render());
		tabs.addTab("Archivos",new ArchivosTab(this));
		tabs.addTab("Dialogs",new DialogsTab(this));
		
		this.add(lbl);
		this.add(tabs);
	}
	

	protected void onVisible() {
		server.getTextoInicial(new GwtRespuestAsyncCallback<SimpleRespuestRPC>(this) {
			public void onOk(SimpleRespuestRPC result) {
				lbl.setText(result.getMensaje());
			}
		});
	}
}
