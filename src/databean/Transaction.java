package databean;

import java.sql.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class Transaction {
	private int    transactionId;
	private int    customerId;
	private int    fundId;
	private Date   executeDate;
    private long   shares;
    private String transactionType;
    private long   amount;

    public int    getTransactionId()         { return transactionId;    }
    public int    getCustomerId()            { return customerId;       }
    public int    getFundId()                { return fundId;           }
    public Date   getExecuteDate()           { return executeDate;      }
    public long   getShares()                { return shares;           }
    public String getTransactionType()       { return transactionType;  }
    public long   getAmount()                { return amount;           }

    public void   setTransactionId(int i)        { transactionId = i;     }
    public void   setCustomerId(int i)           { customerId = i;        }
    public void   setFundId(int i)               { fundId = i;            }
    public void   setExecuteDate(Date d)         { executeDate = d;       }
    public void   setShares(long s)              { shares = s;            }
    public void   setTransactionType(String s)   { transactionType = s;   }
    public void   setAmount(long a)              { amount = a;            }
}
