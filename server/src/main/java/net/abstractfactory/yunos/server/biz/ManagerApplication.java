package net.abstractfactory.yunos.server.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.abstractfactory.plum.interaction.action.annotation.Module;
import net.abstractfactory.plum.interaction.action.annotation.View;
import net.abstractfactory.plum.view.component.containers.window.AppView;

/**
 * the application model for current user, at session level.
 * 
 * @author jack
 * 
 */
@Component
@View(concreteViewClass = AppView.class)
@net.abstractfactory.plum.interaction.action.annotation.Application(name = "Djh PIS")
public class ManagerApplication {

	@Module(name = "System")
	@Autowired
	private System system;

	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}

}