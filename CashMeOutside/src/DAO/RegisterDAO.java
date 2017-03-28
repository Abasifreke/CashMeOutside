package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import bean.Client;
import DAO.ClientDAO;

public class RegisterDAO {
	DataSource ds;

	public RegisterDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/bookstore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Client registerUser(String username, String password, String fname, String lname, String type)
			throws SQLException, ClassNotFoundException {
		String query1 = "INSERT INTO client (userid, username, password, fname, lname, type, last_time, isActive)"
				+ "VALUES (NULL, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, '0')";
		Connection con = this.ds.getConnection();
		PreparedStatement p1 = con.prepareStatement(query1);
		p1.setString(1, username);
		p1.setString(2, password);
		p1.setString(3, fname);
		p1.setString(4, lname);
		p1.setString(5, type);
		int success = p1.executeUpdate(query1);
		Client client = null;
		if (success == 1) {
			client = new ClientDAO().getClientByUsername(username);
		}
		p1.close();
		con.close();
		return client;
	}

	public boolean registerAddress(String username, String street, String province, String city, String country,
			String zip, String phone) throws SQLException, ClassNotFoundException {
		Client client = new ClientDAO().getClientByUsername(username);
		int userid = client.getUserid();
		String query1 = "INSERT INTO address (userid, street, province, city, country, zip, phone)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection con = this.ds.getConnection();
		PreparedStatement p1 = con.prepareStatement(query1);
		p1.setInt(1, userid);
		p1.setString(2, street);
		p1.setString(3, province);
		p1.setString(4, city);
		p1.setString(5, country);
		p1.setString(6, zip);
		p1.setString(7, phone);
		int success = p1.executeUpdate(query1);
		if (success == 1) {
			return true;
		} else {
			return false;
		}
	}
}
