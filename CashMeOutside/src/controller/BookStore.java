package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Client;
import service.Service;

/**
 * Servlet implementation class CashMeOutside
 */
@WebServlet("/BookStore")
public class BookStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookStore() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Service service = new Service();
			this.getServletContext().setAttribute("service", service);
		} catch (ClassNotFoundException e) {

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		service = (Service) this.getServletContext().getAttribute("service");
		try {
			Map<Integer, Client> visitors = service.getVisitors();
			request.setAttribute("visitors", visitors);
			/* System.out.println(visitors.get(1).getFname()); */
			request.getRequestDispatcher("/JSP/index.jspx").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if ("login".equalsIgnoreCase(request.getParameter("login"))) {
			login(request, response);
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = (Service) this.getServletContext().getAttribute("service");
		try {
			if (service.validateUser(request.getParameter("username"))) {
				Client user = service.login(request.getParameter("username"), request.getParameter("password"));
				if (user == null) {
					request.setAttribute("username", request.getParameter("username"));
					request.setAttribute("invalidPassword", "Password Incorrect");
					request.getRequestDispatcher("/JSP/index.jspx").forward(request, response);
				} else {
					request.setAttribute("user", user);
					request.getRequestDispatcher("/JSP/success.jspx").forward(request, response);
				}
			} else {
				request.setAttribute("invalidUser", "Username Incorrect");
				request.getRequestDispatcher("/JSP/index.jspx").forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
