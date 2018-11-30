package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cubrid.sql.*;
import cubrid.jdbc.driver.*;

public class junsu_add_userDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public junsu_add_userDAO() {
		try {
			//String dbURL = "jdbc:cubrid:192.168.0.1:33000:junsuyoun:::";
			String dbURL = "jdbc:cubrid:192.168.103.48:33000:junsuyoun:::";
			String dbID = "dba";
			String dbPassword = "cubrid";
			Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
			
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public int junsu_add_user(String userID, String userPassword, String userName, String userGender,String userEmail) {
		String SQL = "INSERT INTO user_tbl(userid,userpassword,username,usergender,usremail) VALUES(?,?,?,?,?);";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			pstmt.setString(2,userPassword);
			pstmt.setString(3,userName);
			pstmt.setString(4,userGender);
			pstmt.setString(5,userEmail);
			rs = pstmt.executeQuery();
			
				if(rs.next()) {
					if(rs.getString(1).equals("")) {
						return 0; //ID 미 입력
					}else
					if(rs.getString(2).equals("")) {
						return -1; //암호 미 입력
					}else
					if(rs.getString(3).equals("")) {
						return -2; // 이름 미 입력
					}else
					if(rs.getString(4).equals("")) {
						return -3; // 성별 미 입력
					}else
					if(rs.getString(5).equals("")) {
						return -4; // 이메일 미 입력
					}			
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		return 1;
		}
	}
