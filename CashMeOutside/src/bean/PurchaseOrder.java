package bean;

public class PurchaseOrder {

	private int poid;
	private int userid;
	private String status;

	/**
	 * @return the poid
	 */
	public int getPoid() {
		return poid;
	}

	/**
	 * @param poid
	 *            the poid to set
	 */
	public void setPoid(int poid) {
		this.poid = poid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
