package bean;

public class Diary {
	private int id;					//日记编号
	private String title = "";		//标题
	private String address = "";	//日记图片地址
	private String time = null;		//日期
	private String timeBeg = null;  //日期前十位（年月日）
	private String timeEnd = null;  //日期后面几位（时间）
	private int userid;				//用户编号
	private String username = "";		//用户名
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.address = "images/" + id + ".jpg";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
		this.timeBeg = time.substring(0, 10);
		this.timeEnd = time.substring(11, 16);
	}
	public void setTimeBeg(String timeBeg) {
		this.timeBeg = timeBeg;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public String getTimeBeg() {
		return timeBeg;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
