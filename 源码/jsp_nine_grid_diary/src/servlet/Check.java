package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

public class Check extends HttpServlet {
	private static final long serialVersionUID = -4392209351433061589L;

	public Check() {
		super();
	}
	
	public void init() throws ServletException {
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		if (request.getParameter("from") == null)
			return;
		switch (request.getParameter("from")) {
		case "signup":
			signupCheck(request, response);
			break;
		case "login":
			loginCheck(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		}
	}

	/**
	 * ע����¼
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession hs = request.getSession();
		hs.removeAttribute("isLogin");
		hs.removeAttribute("username");
		hs.removeAttribute("email");
		response.sendRedirect("index.jsp");
	}
	/**
	 * �û���¼
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO udao = new UserDAO();
		
		//����û����Ƿ����
		String username = request.getParameter("username");
		try {
			if (!udao.SelectUser(username).next()){
				request.setAttribute("info", "�û��������ڣ�");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		//�����ݿ��ȡ���������
		String password = request.getParameter("password");
		ResultSet rs;
		rs = udao.SelectUser(username);
		String tempPassword = null;
		String email = "";
		int id = 0;
		try {
			rs.next();
			tempPassword = rs.getString(3);
			email = rs.getString(4);
			id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!password.equals(tempPassword)){
			request.setAttribute("info", "�������");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		//��������
		Boolean isRemember = request.getParameter("remember").equals("on");
		if (isRemember){
			response.addCookie(new Cookie("diaryUsername", username));
			response.addCookie(new Cookie("diaryPassword", password));
		}else{
			//���ٱ�����û���������
			Cookie tempCookie = new Cookie("diaryPassword", null);
			tempCookie.setMaxAge(0);
			response.addCookie(tempCookie);
			tempCookie = new Cookie("diaryUsername", null);
			tempCookie.setMaxAge(0);
			response.addCookie(tempCookie);
		}
		
		HttpSession hs = request.getSession();
		hs.setAttribute("isLogin", true);
		hs.setAttribute("username", username);
		hs.setAttribute("email", email);
		hs.setAttribute("id", id);
		response.sendRedirect("index.jsp");
		
	}

	/**
	 * �û�ע��
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void signupCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO udao = new UserDAO();
		String username = request.getParameter("username");
		//����û����Ƿ����
		try {
			if (udao.SelectUser(username).next()){
				request.setAttribute("info", "�뻻���û������û����ظ��ˣ�");
				request.getRequestDispatcher("signup.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		//����������������Ƿ�һ��
		if (!password.equals(repassword)){
			request.setAttribute("info", "�������벻һ�£�");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
			return;
		}
		
		String email = request.getParameter("email");
		
		//��������
		Boolean isRemember = request.getParameter("remember").equals("on");
		if (isRemember){
			response.addCookie(new Cookie("diaryUsername", username));
			response.addCookie(new Cookie("diaryPassword", password));
		}else{
			//���ٱ�����û���������
			Cookie tempCookie = new Cookie("diaryPassword", null);
			tempCookie.setMaxAge(0);
			response.addCookie(tempCookie);
			tempCookie = new Cookie("diaryUsername", null);
			tempCookie.setMaxAge(0);
			response.addCookie(tempCookie);
		}
		
		udao.InsertUser(username, repassword, email);
		
		request.setAttribute("info", "ע��ɹ���");
		response.setHeader("refresh", "1;url=login.jsp");
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

}
