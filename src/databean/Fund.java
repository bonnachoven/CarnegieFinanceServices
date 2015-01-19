//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package databean;



import org.genericdao.PrimaryKey;

@PrimaryKey("fundId")
public class Fund {
	private int    fundId;
	private String fundName;
	private String ticker;
	private long price;
	
	public int    getFundId()                { return fundId;           }
    public String getFundName()             { return fundName;         }
    public String getTicker()          { return ticker;     }
    public long getPrice()         { return price;    }

    public void   setFundId(int i)           { fundId = i;              }
	public void   setFundName(String s)      { fundName = s;            }
	public void   setTicker(String s)     { ticker= s;        }
	public void   setPrice (long i) { price = i;       }
	
}
