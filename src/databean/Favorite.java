//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package databean;



import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class Favorite {
	private int    id;
	private String url;
	private String comment;
	private int clickcount;
	private int userId;
	
	public int    getId()                { return id;           }
    public String getUrl()             { return url;         }
    public String getComment()          { return comment;     }
    public int getClickCount()         { return clickcount;    }
    public int getUserId()          { return userId;     }

    public void   setId(int i)           { id = i;              }
	public void   setUrl(String s)      { url = s;            }
	public void   setUserId(int i)     { userId= i;        }
	public void   setClickCount (int s) { clickcount = s;       }
	public void   setComment(String s)  { comment = s;        }
	
}
