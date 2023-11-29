package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Decoder.BASE64Decoder;
import dao.DiaryDAO;

public class DiarySave extends HttpServlet {
	private static final long serialVersionUID = -5815196502656815479L;

	public DiarySave() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		int userid = (int) request.getSession().getAttribute("id");
		String username = (String) request.getSession().getAttribute("username");
		new DiaryDAO().InsertDiary(title, userid, username);
		int articleid = new DiaryDAO().SelectDiary(userid).get(0).getId();
		GenerateImage(request.getParameter("pic"), String.valueOf(articleid),
				getServletConfig().getServletContext().getRealPath("/") + "images");
		response.sendRedirect("Diary?Page=1&from=mine");
	}

	public static boolean GenerateImage(String imgStr, String imgName, String imgPath) {
		// ��Base64�ַ������벢����ͼƬ
		if (imgStr == null) {
			return false;
		} // ͼ������Ϊ��
		imgStr = imgStr.substring(22);
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64����
			byte[] b = decoder.decodeBuffer(imgStr);
			/*
			 * for (int i = 0; i < b.length; ++i) { if (b[i] < 0) { b[i] += 256;
			 * } // �����쳣���� }
			 */
			File headPath = new File(imgPath);// ��ȡ�ļ���·��
			if (!headPath.exists()) {// �ж��ļ����Ƿ񴴽���û�д����򴴽����ļ���
				headPath.mkdirs();
			}
			// ����ͼƬ·��
			String imgFilePath = imgPath + "/" + imgName + ".jpg";
			// �����ɵ�ͼƬ
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void init() throws ServletException {
	}

}
