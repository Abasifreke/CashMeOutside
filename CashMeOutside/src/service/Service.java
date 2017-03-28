package service;

import java.sql.SQLException;
import java.util.Map;

import DAO.*;
import bean.Client;

public class Service {

	ClientDAO visitorDAO;
	RegisterDAO registerDAO;

	public Service() throws ClassNotFoundException {
		visitorDAO = new ClientDAO();
	}

	public Map<Integer, Client> getVisitors() throws SQLException {
		return visitorDAO.getClients();
	}

	public Client login(String username, String password) throws SQLException {
		return visitorDAO.login(username, password);
	}

	public Boolean validateUser(String username) throws SQLException {
		return visitorDAO.validateUser(username);
	}

	public Client registerUser(String username, String password, String fname, String lname, String type)
			throws SQLException, ClassNotFoundException {
		return registerDAO.registerUser(username, password, fname, lname, type);
	}

	public boolean registerAddress(String username, String street, String province, String city, String country,
			String zip, String phone) throws SQLException, ClassNotFoundException {
		return registerDAO.registerAddress(username, street, province, city, country, zip, phone);
	}
}
