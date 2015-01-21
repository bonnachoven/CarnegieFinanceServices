//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package databean;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("customerId")
public class Customer {
	
	private int    customerId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private long cash;
	private String addrLine1;
	private String addrLine2;
	private String zip;
	private String city;
	
	private String  hashedPassword = "*";
	private int     salt           = 0;
	
	public int    getCustomerId()   { return customerId;}
	public String getFirstName()    { return firstName; }
	public String getLastName()     { return lastName;  }
	public String getPassword()     { return password;  }
	public String getEmail() 		{ return email;		}
	public long getCash() 		    { return cash;		}
	public String getAddrLine1() 	{ return addrLine1; }
	public String getAddrLine2() 	{ return addrLine2;	}
	public String getZip() 			{ return zip;		}
	public String getCity() 		{ return city;		}

	public void setCustomerId(int i) 	  {	customerId = i; }
	public void setEmail(String s) 		  {	email      = s; }
    public void setFirstName(String s) 	  {	firstName  = s; }
	public void setLastName(String s)     {	lastName   = s; }
	public void setPassword(String s)     {	salt = newSalt(); hashedPassword = hash(s); }
	public void setCash(long l) 		  {	cash       = l;	}
	public void setAddrLine1(String s)    {	addrLine1  = s; }
	public void setAddrLine2(String s)    {	addrLine2  = s; }
	public void setCity(String s) 		  { city       = s;	}
	public void setZip(String s) 		  { zip        = s; }
	
	public String  getHashedPassword() { return hashedPassword; }
	public int     getSalt()           { return salt;           }
	
	public void setSalt(int x)               { salt = x;           }
	public void setHashedPassword(String x)  { hashedPassword = x; }
	
	public int compareTo(Customer other) {
		// Order first by lastName and then by firstName
		int c = lastName.compareTo(other.lastName);
		if (c != 0) return c;
		c = firstName.compareTo(other.firstName);
		if (c != 0) return c;
		return email.compareTo(other.email);
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
	
}
