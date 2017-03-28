package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import bean.Client;

public class ClientDAO {
	DataSource ds;

	public ClientDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/bookstore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, Client> getClients() throws SQLException {
		String query = "select * from client";
		Map<Integer, Client> rv = new HashMap<Integer, Client>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			int userid = r.getInt("userid");
			String username = r.getString("username");
			String fname = r.getString("fname");
			String lname = r.getString("lname");
			String type = r.getString("type");
			Client visitor = new Client(userid, username, fname, lname, type);
			rv.put(userid, visitor);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public Client login(String username, String password) throws SQLException {
		String query1 = "select * from client where username= ? and password = ?";
		Connection con = this.ds.getConnection();
		PreparedStatement p1 = con.prepareStatement(query1);
		p1.setString(1, username);
		p1.setString(2, password);
		ResultSet r = p1.executeQuery();
		Client visitor = null;
		if (r.next()) {
			int userid = r.getInt("userid");
			String fname = r.getString("fname");
			String lname = r.getString("lname");
			String type = r.getString("type");
			visitor = new Client(userid, username, password, fname, lname, type, 1);
			String query2 = "UPDATE client SET isActive = '1' WHERE username = ?";
			PreparedStatement p2 = con.prepareStatement(query2);
			p2.setString(1, username);
			p2.executeUpdate();
			p2.close();
		}
		r.close();
		p1.close();
		con.close();
		return visitor;
	}

	public boolean validateUser(String username) throws SQLException {
		String query = "select username from client where username = ?";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, username);
		ResultSet r = p.executeQuery();
		return r.next();
	}

	public Client getClientByUsername(String username) throws SQLException {
		String query = "select userid, username, fname, lname, type from client where username = ?";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, username);
		ResultSet r = p.executeQuery();
		Client client = null;
		if(r.next()){
			int userid = r.getInt("userid");
			String fname = r.getString("fname");
			String lname = r.getString("lname");
			String type = r.getString("type");
			client = new Client(userid, username, fname, lname, type);
		}
		r.close();
		p.close();
		con.close();
		return client;
	}
}
