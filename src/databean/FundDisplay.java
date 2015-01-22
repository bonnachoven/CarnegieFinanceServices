package databean;

public class FundDisplay {
	private String fundName;
	private String ticker;
	private String shares;
	private String total;
	private int fundId;
	
	public FundDisplay() {}
	
	public String getFundName()       {  return fundName;  }
	public String getTicker()         {return ticker;}
	public String getShares() {return shares;}
	public String getTotal() {return total;}
	
	public void setFundName(String v) {  this.fundName = v;}
	public void setTicker(String v) {this.ticker = v;}
	public void setShares (String v) {this.shares = v;}
	public void setTotal (String v) {this.total = v;}
	public int getFundId() {return fundId;}
	public void setFundId (int v) {this.fundId = v;}
}