package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DiaryDAO;
import tools.Pagination;

public class Diary extends HttpServlet {
	private static final long serialVersionUID = -7454449298783742049L;

	DiaryDAO ddao = new DiaryDAO();
	Pagination pt = null;
	ArrayList<bean.Diary> Diarys = null; // 所有日志
	int pagesize = 5; // 每页显示的记录数
	int page = 1; // 当前页数

	public Diary() {
		super();
	}

	public void init() throws ServletException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String from = request.getParameter("from");
		request.getSession().setAttribute("status", from);
		String pageinfo = null;
		ArrayList<bean.Diary> tDiarys = null;
		page = Integer.parseInt(request.getParameter("Page"));
		pt = new Pagination();
		if (from.equals("index")) {
			Diarys = ddao.SelectAllDiary();
		}
		if (from.equals("mine")) {
			try {
				Diarys = ddao.SelectDiary((int) request.getSession().getAttribute("id"));
			} catch (Exception e) {
				response.sendRedirect("mine.jsp");
				return;
			}
		}
		pt.getInitPage(Diarys, 1, pagesize);
		tDiarys = (ArrayList<bean.Diary>) pt.getAppointPage(page);
		pageinfo = pt.printCtrl(page, from);
		request.setAttribute("diarys", tDiarys);
		request.setAttribute("pageinfo", pageinfo);
		request.getRequestDispatcher(from + ".jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
		super.destroy();
	}

}
