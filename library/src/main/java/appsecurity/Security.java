package appsecurity;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SecurityDaoImpl;
import model.User;

public class Security extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SecurityDaoImpl securityDaoImpl;

	public Security() {
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
		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession(true);
		}

		int authentificationState = 0;
		long authenticationTime = session.getCreationTime();
		authentificationState = securityDaoImpl.doAuthentication(login, password, session.getId(), authenticationTime);


		if (authentificationState > 0) {
			session.setAttribute("u", authentificationState);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-book.jsp");
			dispatcher.forward(request, response);
		} else{
			System.out.println(authentificationState);
			if (authentificationState == 0){
				session.setAttribute("error", "Login error !!!");
			} else {
				session.setAttribute("error", "Password error !!!");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user info from form
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		long dateReg = System.currentTimeMillis();

		User theUser = new User(login, pass, dateReg);

		securityDaoImpl.addUser(theUser);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);

	}

	private void login2Site(HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

}
