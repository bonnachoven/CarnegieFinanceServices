package databean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean  implements Comparable<TransactionBean> {
	private int    transactionId;
	private int    customerId;
	private int    fundId;
	private String   executeDate;
    private long   shares;
    private int transactionType;
    private long   amount;
    

    public int    getTransactionId()         { return transactionId;    }
    public int    getCustomerId()            { return customerId;       }
    public int    getFundId()                { return fundId;           }
    public String   getExecuteDate()         { return executeDate;      }
    public long   getShares()                { return shares;           }
    public int    getTransactionType()       { return transactionType;  }
    public long   getAmount()                { return amount;           }

    public void   setTransactionId(int i)        { transactionId = i;     }
    public void   setCustomerId(int i)           { customerId = i;        }
    public void   setFundId(int i)               { fundId = i;            }
    public void   setExecuteDate(String s)       { executeDate = s;       }
    public void   setShares(long s)              { shares = s;            }
    public void   setTransactionType(int i)   { transactionType = i;   }
    public void   setAmount(long a)              { amount = a;            }
	
	@Override
	public int compareTo(TransactionBean tb) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	dateFormat.setLenient(false);
		try {
			return dateFormat.parse(this.executeDate).compareTo(dateFormat.parse(tb.executeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
