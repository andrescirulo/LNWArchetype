package ar.com.lnwArchetype.client;

import com.google.gwt.core.client.GWT;

import ar.com.lnwArchetype.client.useCases.inicio.InicioGroup;
import net.latin.client.test.inicio.InicioTestGroup;
import net.latin.client.widget.base.GwtController;
import net.latin.client.widget.base.GwtGroupLoader;

public class ${projectName}GroupLoader extends GwtGroupLoader {

	@Override
	public void loadGroup(String pageGroup, GwtController controller) {
		if ("InicioCase".equals(pageGroup)) {
			GWT.runAsync(new GwtRunAsyncCallback() {
				public void onSuccess() {
					controller.addPageGroup("InicioCase", new InicioGroup());
					controller.finishGroupLoading(pageGroup);
				}
			});
		}
	}

}
