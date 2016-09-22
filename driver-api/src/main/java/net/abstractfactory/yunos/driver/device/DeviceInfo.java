package net.abstractfactory.yunos.driver.device;


/**
 * device descriptions
 * 
 * @author jack
 * 
 */
public class DeviceInfo {

	private String id;
	private Model model;
	/**
	 * configures combined by device configure and model configure. device
	 * configure has higher priority.
	 */
	private Object configure;

	public DeviceInfo(String id, Model model) {
		this.id = id;
		this.model = model;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Object getConfigure() {
		return configure;
	}

	public void setConfigure(Object configure) {
		this.configure = configure;
	}

}
