package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiaryDAO;

public class DiaryDelete extends HttpServlet {
	private static final long serialVersionUID = 4230579646927500361L;

	public DiaryDelete() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new DiaryDAO().DeleteDiary(Integer.parseInt(request.getParameter("id")));
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("<script>alert('É¾³ý³É¹¦£¡');window.location='Diary?Page=1&from=mine'</script>");
	}

	public void init() throws ServletException {
	}

}
