package auth;

import database.*;
import DTO.Admin;
import DTO.Student;
import model.AdminModel;
import model.StudentModel;

import java.sql.*;
import java.sql.Connection;
import java.io.IOException;






public class Login {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public Login(){
    	
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet() throws  IOException{
		/*String email = request.getParameter("email");
		String password = request.getParameter("password");*/
		
		try{
			
			MySQLAccess obj = new MySQLAccess();
			Connection conn = null;
			conn = obj.getConnection();
			
			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select * from users where email=? and password=?");
			
			login.setString(1,email);
			login.setString(2,password);
			
			ResultSet result = login.executeQuery();
			
			String dbEmail = "null";
			int userId;
			String dbPassword = "null";
			String type = "null";
			String name = "null";
			String userType = "null";
			//String errorMsg = "null";
			//HttpSession session = request.getSession();
			if(result.next()){
				dbEmail = result.getString("email");
				dbPassword = result.getString("password");
				type = result.getString("type");
			
				System.out.println(email + password);
				System.out.println(dbEmail + dbPassword);
				
				if(type.equals("1")){
					StudentModel studentModel = new StudentModel();
					Student student = studentModel.selectStudent(dbEmail);
					name = student.getFirst_name()+ " " + student.getLast_name();
					userType = "student";
					userId = student.getId();
					session.setAttribute("studentId", userId);
				}else{
					AdminModel adminModel = new AdminModel();
					Admin admin = adminModel.selectAdmin(dbEmail);
					name = admin.getFirst_name()+ " " + admin.getLast_name();
					userType = "admin";
					userId = admin.getId();
				}
				
		        /*session.setAttribute("email", dbEmail);
		        session.setAttribute("password", dbPassword);
		        session.setAttribute("name", name);
		        session.setAttribute("userType", userType);
		        session.setAttribute("userId", userId);*/
		       
		        
		        session.setMaxInactiveInterval(30*60); //session expires in 30 minutes   
		       /* Cookie userEmail = new Cookie("email", dbEmail);
		        Cookie userPassword = new Cookie("password", dbPassword);
		        Cookie UType = new Cookie("userType", userType);
		        Cookie UName = new Cookie("name", name);*/
		        userEmail.setMaxAge(30*60);
		        userPassword.setMaxAge(30*60);
		        response.addCookie(userEmail);
		        response.addCookie(userPassword);
		        response.addCookie(UType);
		        response.addCookie(UName);
				
				//RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
	        	//requestDispatcher.forward(request, response);
	       
			}else{
				System.out.println("Mismatch");
				System.out.println(dbEmail + dbPassword);
				System.out.println(email + password);
				//RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				request.setAttribute("errorMsg", "You email or password does not match.");
	        	requestDispatcher.forward(request, response);
			}
		}
		
		catch(Exception e){
			System.err.println(e.getMessage());

			//RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			request.setAttribute("errorMsg", "Something went wrong. Please contact system admin.");
			requestDispatcher.forward(request, response);
		}
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost() throws  IOException{
		doGet();
	}
	
}
