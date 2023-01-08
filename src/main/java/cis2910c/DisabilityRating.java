package cis2910c;

public class DisabilityRating {

	private int percent;
	private String condition;
	
	/**
	 * @param percent
	 * @param condition
	 */
	public DisabilityRating(int percent, String condition) {
		this.percent = percent;
		this.condition = condition;
	}

	/**
	 * @return the percent
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(int percent) {
		this.percent = percent;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
}
