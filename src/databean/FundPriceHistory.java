package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;
@PrimaryKey("fundId")
public class FundPriceHistory {
	private int fundId;
	private Date priceDate;
	private long price;
	
	public int getFundId()           { return fundId; }
	public Date getPriceDate()       {return priceDate;}
	public long getPrice()           {return price;}
	
	public void setFundId(int f)     {fundId = f;}
	public void setPriceDate(Date p) {priceDate = p;}
	public void setPrice(long pr)    {price = pr;}	
}
