package mx.com.mindbits.argos.inventory.webapp.form;

import java.io.Serializable;

public class ResultsFilter implements Serializable {
	
	private static final long serialVersionUID = 4683052642598190906L;

	private String filterName;
	
	private String filter1;
	
	private String filter2;
	
	private String filter3;

	/**
	 * @return the filterName
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	 * @param filterName the filterName to set
	 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	/**
	 * @return the filter1
	 */
	public String getFilter1() {
		return filter1;
	}

	/**
	 * @param filter1 the filter1 to set
	 */
	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}

	/**
	 * @return the filter2
	 */
	public String getFilter2() {
		return filter2;
	}

	/**
	 * @param filter2 the filter2 to set
	 */
	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

	/**
	 * @return the filter3
	 */
	public String getFilter3() {
		return filter3;
	}

	/**
	 * @param filter3 the filter3 to set
	 */
	public void setFilter3(String filter3) {
		this.filter3 = filter3;
	}
	
}
