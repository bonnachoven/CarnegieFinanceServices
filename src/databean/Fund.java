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
	private float price;
	
	public int    getFundId()                { return fundId;           }
    public String getFundName()             { return fundName;         }
    public String getTicker()          { return ticker;     }
    public int getPrice()         { return price;    }

    public void   setId(int i)           { id = i;              }
	public void   setUrl(String s)      { url = s;            }
	public void   setUserId(int i)     { userId= i;        }
	public void   setClickCount (int s) { clickcount = s;       }
	public void   setComment(String s)  { comment = s;        }
	
}
