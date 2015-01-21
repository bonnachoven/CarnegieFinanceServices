package databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId,fundId")
public class Position {
	private int    fundId;
	private int    customerId;
	private long   shares;
	
	public int    getFundId()          { return fundId;        }
	public int    getCustomerId()      { return customerId;    }
    public long   getShares()          { return shares;        }

    public void   setFundId(int i)      { fundId = i;         }
	public void   setCustomerId(int i)  { customerId = i;     }
	public void   setShares(long l)     { shares= l;          }
	
}
