package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class DataOperation {
	static{
		
	}
	
	private static String getValueInSql(Object a)
	{
		if (a.getClass().getName().endsWith("String"))
			return "\""+a+"\"";
		else
			return a.toString();
		
	}
	public static int insert(String tableName,ArrayList<String> colNames,ArrayList<Object> values) throws SQLException
	{
		Connection con=null;
		 PreparedStatement st=null;
		 ResultSet rs=null;
		 try {
			con=Datasource.getConnection();
			String sql="insert into "+tableName+"(";
			int n=colNames.size();
			for (int i=0;i<n;i++)
			{
				sql=sql+colNames.get(i);
				if (i<n-1)
					sql+=",";
			}
			sql+=") values(";
			n=values.size();
			for (int i=0;i<n;i++)
			{
				
				sql+=getValueInSql(values.get(i));
				if (i<n-1)
					sql+=",";
			}
			sql+=")";
			st=con.prepareStatement(sql);
			int res=st.executeUpdate(sql);
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
		finally
		{
			Datasource.release(con,st,rs);
			
		}
		
	}
	
	public static int delete(String tableName,ArrayList<String> colNames,ArrayList<Object> values) throws SQLException
	{
		Connection con=null;
		 PreparedStatement st=null;
		 ResultSet rs=null;
		 try {
			con=Datasource.getConnection();
			String sql="delete from "+tableName+" where ";
			int n=colNames.size();
			for (int i=0;i<n;i++)
			{
				sql+=colNames.get(i)+"="+getValueInSql(values.get(i));
				if (i<n-1)
					sql+=" and ";
			}
			
			st=con.prepareStatement(sql);
			int res=st.executeUpdate();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
		finally
		{
			Datasource.release(con,st,rs);
		}
	}
	
	public static int update(String tableName,ArrayList<String> setColNames,ArrayList<Object> setValues,ArrayList<String> findColNames,ArrayList<Object> findValues) throws SQLException
	{
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		String sql="update "+tableName+" set";
		int n=setColNames.size();
		for (int i=0;i<n;i++)
		{
			sql+=" "+setColNames.get(i)+"="+getValueInSql(setValues.get(i));
			if (i<n-1)
				sql+=" and";
		}
		sql+=" where";
		int m=findColNames.size();
		for (int i=0;i<m;i++)
		{
			sql+=" "+findColNames.get(i)+"="+getValueInSql(findValues.get(i));
			if (i<m-1)
				sql+=" and";
		}
		//System.out.println(sql);
		try{
			con=Datasource.getConnection();
			st=con.prepareStatement(sql);
			int res=st.executeUpdate();
			return res;		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
		finally
		{
			Datasource.release(con,st,rs);
		}
		
		
	}
	
	public static ArrayList<HashMap<String,Object>> select(String tableName,ArrayList<String> getColNames,ArrayList<String> findColNames,ArrayList<Object> findValues) throws SQLException
	{
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql="select ";
		ArrayList<HashMap<String,Object>> res=new ArrayList<HashMap<String,Object>>();
		int n=getColNames.size();
		for (int i=0;i<n;i++)
		{
			sql+=getColNames.get(i);
			if (i<n-1)
			sql+=",";
		}
		sql+=" from "+tableName;
		if (findColNames.size()>0)
		sql+=" where";
		int m=findColNames.size();
		for (int i=0;i<m;i++)
		{
			sql+=" "+findColNames.get(i);
			if (!findColNames.get(i).contains("="))
			sql+="="+getValueInSql(findValues.get(i));
			if (i<m-1)
				sql+=" and";
		}
		try{
			con=Datasource.getConnection();
			st=con.prepareStatement(sql);
			System.out.println(sql);
			rs=st.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next())
			{
				HashMap<String,Object> row=new HashMap<String,Object>();
				int nc=rsmd.getColumnCount();
				for (int i=1;i<=nc;i++)
				{
					String cName=rsmd.getColumnName(i);
					Object value=rs.getObject(cName);
					row.put(cName, value);
				}
				res.add(row);
			}
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException(e);
		}
		finally
		{
			Datasource.release(con, st, rs);
		}
	}
	
	
	
}
