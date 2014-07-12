package net.cedu.action.examination.invigilator;



import net.cedu.action.BaseAction;

public class InvigilatorShenpiAction extends BaseAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6909862852910859506L;

	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			return INPUT;
		}
		return SUCCESS;
	}
	

}
