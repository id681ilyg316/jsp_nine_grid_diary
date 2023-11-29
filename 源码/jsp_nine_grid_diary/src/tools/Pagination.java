package tools;

import java.util.ArrayList;
import java.util.List;

import bean.Diary;

public class Pagination {
	public List<Diary> list = null;
	private int recordCount = 0;
	private int pagesize = 0;
	private int maxPage = 0;

	/**
	 * 初始化分页信息
	 * 
	 * @param list
	 *            保存的查询结果
	 * @param page
	 *            指定当前页面
	 * @param pagesize
	 *            指定每页显示的记录数
	 * @return 当前页数据
	 */
	public List<Diary> getInitPage(List<Diary> list, int page, int pagesize) {
		List<Diary> newList = new ArrayList<Diary>();
		this.list = list;
		recordCount = list.size();
		this.pagesize = pagesize;
		this.maxPage = recordCount % pagesize == 0 ? recordCount / pagesize : recordCount / pagesize + 1;
		for (int i = (page - 1) * pagesize; i <= page * pagesize - 1; i++) {
			if (i >= recordCount)
				break;
			newList.add(list.get(i));
		}
		return newList;
	}

	/**
	 * 获取指定页数据
	 * 
	 * @param page
	 *            指定当前页数
	 * @return 指定页数据
	 */
	public List<Diary> getAppointPage(int page) {
		List<Diary> newList = new ArrayList<Diary>();
		for (int i = (page - 1) * pagesize; i <= page * pagesize - 1; i++) {
			if (i >= recordCount)
				break;
			newList.add(list.get(i));
		}
		return newList;
	}

	/**
	 * 获取最大记录数
	 * 
	 * @return 最大记录数
	 */
	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public int getRecordSize() {
		return recordCount;
	}

	/**
	 * 获取当前页数
	 * 
	 * @param str
	 * @return
	 */
	public int getPage(String str) {
		if (str == null)
			str = "0";
		int page = Integer.parseInt(str);
		if (page < 1)
			page = 1;
		else if (((page - 1) * pagesize + 1) > recordCount)
			page = maxPage;
		return page;
	}

	/**
	 * 输出记录导航
	 * 
	 * @param page
	 *            当前页数
	 * @param url
	 *            地址
	 * @param para
	 *            要传递的参数
	 * @return
	 */
	public String printCtrl(int page, String url) {
		String strHtml = "";
		String urlServlet = "Diary";
		if (page > 1) {
			strHtml += "<a href='" + urlServlet + "?Page=1" + "&from=" + url + "'>&lt;&lt;</a>";
			strHtml += "<a href='" + urlServlet + "?Page=" + (page - 1) + "&from=" + url + "'>&lt;</a>";
		}
		if (page + 5 <= maxPage) {
			strHtml += "<a><b>" + page + "</b></a>";
			for (int i = page + 1; i < page + 5; i++)
				strHtml += "<a href='" + urlServlet + "?Page=" + i + "&from=" + url + "'>" + i + "</a>";
		} else {
			for (int i = maxPage - 5 > 1 ? maxPage - 5 : 1; i <= maxPage; i++)
				if (i != page)
					strHtml += "<a href='" + urlServlet + "?Page=" + i + "&from=" + url + "'>" + i + "</a>";
				else strHtml += "<a><b>" + page + "</b></a>";
		}
		if (page < maxPage) {
			strHtml += "<a href='" + urlServlet + "?Page=" + (page + 1) + "&from=" + url + "'>&gt;</a>";
			strHtml += "<a href='" + urlServlet + "?Page=" + maxPage + "&from=" + url + "'>&gt;&gt;</a>";
		}
		strHtml += "</div>";
		return strHtml;
	}
}
