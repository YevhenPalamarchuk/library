package security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SecurityDaoImpl;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SecurityDaoImpl securityDaoImpl;

	public Login() {
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
		request.getSession(false).invalidate();
		HttpSession session = request.getSession(true);

		int authentificationState = 0;
		long authenticationTime = session.getCreationTime();
		authentificationState = securityDaoImpl.doAuthentication(login, password, session.getId(), authenticationTime);

		if (authentificationState > 0) {
			session.setAttribute("user", authentificationState);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-book.jsp");
			dispatcher.forward(request, response);
		} else {

			if (authentificationState == 0) {
				session.setAttribute("error", "Login error !!!");
			} else {
				session.setAttribute("error", "Password error !!!");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
