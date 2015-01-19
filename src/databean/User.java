//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class User implements Comparable<User> {
	private String  userEmail = null;

	private String  hashedPassword = "*";
	private int     salt           = 0;

	private String  userFirstName      = null;
	private String  userLastName       = null;
    private int userId;


	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	public int compareTo(User other) {
		// Order first by lastName and then by firstName
		int c = userLastName.compareTo(other.userLastName);
		if (c != 0) return c;
		c = userFirstName .compareTo(other.userFirstName );
		if (c != 0) return c;
		return userEmail.compareTo(other.userEmail);
	}

	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User other = (User) obj;
			return userEmail.equals(other.userEmail);
		}
		return false;
	}

	public String  getHashedPassword() { return hashedPassword; }
	public String  getUserEmail()       { return userEmail;       }
	public int     getSalt()           { return salt;           }

	public String  getUserFirstName()      { return userFirstName;      }
	public String  getUserLastName()       { return userLastName;       }
    public int getUserId()  {return userId;}

	public int     hashCode()          { return userEmail.hashCode(); }

	public void setHashedPassword(String x)  { hashedPassword = x; }
	public void setPassword(String s)        { salt = newSalt(); hashedPassword = hash(s); }
	public void setSalt(int x)               { salt = x;           }

	public void setUserFirstName(String s)       { userFirstName = s;      }
	public void setUserLastName(String s)        { userLastName = s;       }
    public void setUserId(int s)  { userId= s;    }

	public void setUserEmail(String s)        { userEmail = s;       }

	public String toString() {
		return "User("+getUserEmail()+")";
	}

	private String hash(String clearPassword) {
		if (salt == 0) return null;

		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}

	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}

	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}

