package security;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDaoImpl;
import model.User;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginDaoImpl loginDaoImpl;

	public Login() {
	}

	@Override
	public void init() throws ServletException {
		try {
			loginDaoImpl = new LoginDaoImpl();
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				login2Site(request, response);
			} else {
				// route to the appropriate method
				switch (theCommand) {

				case "ADD":
					addUser(request, response);
					break;

				case "LOGIN":
					login2Site(request, response);
					break;

				default:
					login2Site(request, response);
				}
			}
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read user info from form
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		long dateReg = System.currentTimeMillis();

		User theUser = new User(login, pass, dateReg);

		loginDaoImpl.addUser(theUser);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);

	}

	private void login2Site(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<User> users = loginDaoImpl.getUsers();

		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		if (users.size() != 0) {
			for (int i = 0; i < users.size(); i++) {
				if (login.equals(users.get(i).getUserLogin())) {
					if (pass.equals(users.get(i).getUserPass())) {
						HttpSession session = request.getSession(true);
						session.setAttribute("l", login);
						RequestDispatcher dispatcher = request.getRequestDispatcher("/list-book.jsp");
						dispatcher.forward(request, response);
					} else {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
						dispatcher.forward(request, response);
					}
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/add-user.jsp");
			dispatcher.forward(request, response);
		}

	}

}
