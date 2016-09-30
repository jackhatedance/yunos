package net.abstractfactory.yunos.server.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.common.ApplicationContextUtils;
import net.abstractfactory.plum.interaction.action.annotation.Action;
import net.abstractfactory.plum.interaction.application.PlumApplicationContextUtils;
import net.abstractfactory.plum.interaction.context.InteractionContextUtils;
import net.abstractfactory.plum.interaction.field.rich.annotation.type.RichString;
import net.abstractfactory.plum.interaction.input.validation.annotation.string.NotEmpty;
import net.abstractfactory.plum.interaction.session.SessionContext;
import net.abstractfactory.plum.interaction.session.SessionContextUtils;
import net.abstractfactory.plum.repository.biz.interafce.Repository;
import net.abstractfactory.plum.security.SecurityService;
import net.abstractfactory.plum.view.context.ViewSessionContextUtils;

@Component
public class System {

	@Autowired
	private Repository repository;

	@Action(index = 1)
	public void login(@NotEmpty String userId, @RichString(masked = true) String password) {

		SecurityService securityService = PlumApplicationContextUtils.getSecurityService();
		boolean ok = securityService.checkPassword(userId, password);
		if (ok) {
			SessionContext sessionContext = SessionContextUtils.getCurrentSessionContext();
			sessionContext.setUserId(userId);

			ViewSessionContextUtils.getWindowManager(sessionContext).getMainWindow()
					.notifyEventListeners("ROLE_CHANGED");
		} else {
			InteractionContextUtils.getInteractionManager().inform("Login Failed", "username or password not correct");
		}

	}

	@Action(index = 2)
	public void logout() {

		SessionContext sessionContext = SessionContextUtils.getCurrentSessionContext();
		sessionContext.setUserId(null);

		// clear desktop
		ViewSessionContextUtils.getWindowManager(sessionContext).getMainWindow().getBodyPanel().removeAllChildren();

		ViewSessionContextUtils.getWindowManager(sessionContext).getMainWindow().notifyEventListeners("ROLE_CHANGED");

	}

	@Action(index = 90)
	public String about() {
		String version = ApplicationContextUtils.getVersion();
		return "version " + version;
	}

}
