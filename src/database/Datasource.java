package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
public class Datasource 
{
	private static ComboPooledDataSource ds = null;
	static
	{
		try
		{
			ds=new ComboPooledDataSource();
		}
		catch (Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		
			return ds.getConnection();

	}
	
	 public static void release(Connection conn,Statement st,ResultSet rs){
		          if(rs!=null){
		              try{
		                  
		                  rs.close();
		              }catch (Exception e) {
		                  e.printStackTrace();
		              }
		              rs = null;
		          }
		          if(st!=null){
		              try{
		                  
		                  st.close();
		              }catch (Exception e) {
		                  e.printStackTrace();
		              }
		          }
		          
		          if(conn!=null){
		              try{
		                  
		                  conn.close();
		              }catch (Exception e) {
		                  e.printStackTrace();
		              }
		         }
		      }
	 
	/* public static void main(String []args)
	 {
		 Connection con=null;
		 PreparedStatement st=null;
		 ResultSet rs=null;
		 try {
			con=Datasource.getConnection();
			String sql="insert into enroll_tbl values(-1,\"2222\",\"A+\",1)";
			st=con.prepareStatement(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			release(con,st,rs);
		}
		 
	 }*/
}

