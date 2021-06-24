package com.magic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.magic.dto.EmployeesVO;

public class EmployeesDAO {

	private EmployeesDAO() {

	}

	private static EmployeesDAO instance = new EmployeesDAO();

	public static EmployeesDAO getInstance() {

		return instance;
	}

	Connection getConnection() {
		Connection conn = null;
		Context initContext;

		try {
			initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public int userCheck(String userid, String pwd, String lev) {
		int result = 1;
		Connection conn = null;
		String sql = "select * from employees where id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 비밀번호가일치하고
				if (pwd.equals(rs.getString("pass"))) {
					// 회원등급이 일치하면
					if (lev.contentEquals(rs.getString("lev"))) {
						result = 2; // 관리자로 로그인 성공
						if (lev.contentEquals("B")) {
							result = 3; // 일반회원으로 로그인 성공
						}
					} else { // 레벨 불일치 로그인 실패
						result = 1;
					}
				} else { // 비밀번호 불일치 로그인 실패.
					result = 0;
				}
			} else { // 아이디 불일치 로그인 실패
				result = -1; 
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public EmployeesVO getMember(String id) {
		EmployeesVO member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from employees where id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new EmployeesVO();
				member.setId(rs.getString("id"));
				member.setPass(rs.getString("pass"));
				member.setName(rs.getString("name"));
				member.setLev(rs.getString("lev"));
				member.setEnter(rs.getDate("enter"));
				member.setGender(rs.getString("gender"));
				member.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public void insertMember(EmployeesVO member) {
		String sql = "insert into employees values(?,?,?,?,sysdate,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getLev());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getPhone());
			
			System.out.println(pstmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int updateMember(EmployeesVO evo) {
		int result=-1;
		String sql="update employees set gender=?,pass=?,name=?,lev=?,phone=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,evo.getGender());
			pstmt.setString(2,evo.getPass());
			pstmt.setString(3,evo.getName());
			pstmt.setString(4,evo.getLev());
			pstmt.setString(5,evo.getPhone());
			pstmt.setString(6,evo.getId());
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}












