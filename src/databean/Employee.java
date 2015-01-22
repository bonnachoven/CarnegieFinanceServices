
package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;




@PrimaryKey("employeeName")
public class Employee implements Comparable<Employee> {
	private String  employeeName = null;
	private String  hashedPassword = "*";
	private int     salt           = 0;
	private String  employeeFirstName      = null;
	private String  employeeLastName       = null;
	private int employeeID  = 0;

	

	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	public int compareTo(Employee other) {
		// Order first by lastName and then by firstName
		int c = employeeLastName.compareTo(other.employeeLastName);
		if (c != 0) return c;
		c = employeeFirstName .compareTo(other.employeeFirstName );
		if (c != 0) return c;
		return employeeName.compareTo(other.employeeName);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			Employee other = (Employee) obj;
			return employeeName.equals(other.employeeName);
		}
		return false;
	}

	public String  getHashedPassword() { return hashedPassword; }
	public String  getEmployeeName()       { return employeeName;       }
	public int     getSalt()           { return salt;           }
	public String  getEmployeeFirstName()      { return employeeFirstName;      }
	public String  getEmployeeLastName()       { return employeeLastName;       }
	public int     hashCode()          { return employeeName.hashCode(); }
	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setHashedPassword(String x)  { hashedPassword = x; }
	public void setPassword(String s)        { salt = newSalt(); hashedPassword = hash(s); }
	public void setSalt(int x)               { salt = x;           }

	public void setEmployeeFirstName(String s)       { employeeFirstName = s;      }
	public void setEmployeeLastName(String s)        { employeeLastName = s;       }

	public void setEmployeeName(String s)        { employeeName = s;       }

	public String toString() {
		return "employee("+getEmployeeName()+")";
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

