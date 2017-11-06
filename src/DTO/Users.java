package DTO;

public class Users {
	private String email,password;
	private int type;

	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.type=type;
	}
    public Users() {
    	
    }
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String toString() {
        return this.email;
    }
	
   public void setType(int type) {
	   this.type=type;
   }
	
   public int getType() {
	   return this.type;
   }
}
