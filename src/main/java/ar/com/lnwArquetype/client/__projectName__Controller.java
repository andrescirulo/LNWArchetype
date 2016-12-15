package ar.com.lnwArchetype.client;

import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.user.client.Window;

import net.latin.client.widget.base.GwtController;
import net.latin.client.widget.base.GwtGroupLoader;
import net.latin.client.widget.base.errorPage.GwtDefaultErrorPage;
import net.latin.client.widget.base.errorPage.GwtErrorPage;

public class ${projectName}Controller extends GwtController{

	public void onClose(CloseEvent<Window> event) {
	}

	@Override
	protected GwtGroupLoader createGroupLoader() {
		return new ${projectName}GroupLoader();
	}

	@Override
	protected void registerPageGroups() {
		
	}

	@Override
	protected String registerLoginGroup() {
		return null;
	}

	@Override
	protected String registerServerBasePath() {
		return "";
	}

	@Override
	protected String registerFirstPageGroup() {
		return null;
	}

	@Override
	protected String registerCommonUseCaseServer() {
		return "securityServer.gwt";
	}

	@Override
	protected String registerPersistenceServer() {
		return null;
	}

	@Override
	protected GwtErrorPage registerErrorPage() {
		return new GwtDefaultErrorPage();
	}

	@Override
	protected String registerMainPageDescription() {
		return null;
	}

	@Override
	protected String registerSessionExpiredUrl() {
		return null;
	}
}
