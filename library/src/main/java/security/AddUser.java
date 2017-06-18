package security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SecurityDaoImpl;

public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SecurityDaoImpl securityDaoImpl;

	public AddUser() {
	}

	@Override
	public void init() throws ServletException {
		try {
			securityDaoImpl = new SecurityDaoImpl();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("pass");
		String role = "user";
		long dateOfRegistration = System.currentTimeMillis();

		securityDaoImpl.addUser(login, password, role, dateOfRegistration);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

}
