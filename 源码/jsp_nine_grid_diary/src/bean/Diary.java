package bean;

public class Diary {
	private int id;					//�ռǱ��
	private String title = "";		//����
	private String address = "";	//�ռ�ͼƬ��ַ
	private String time = null;		//����
	private String timeBeg = null;  //����ǰʮλ�������գ�
	private String timeEnd = null;  //���ں��漸λ��ʱ�䣩
	private int userid;				//�û����
	private String username = "";		//�û���
	
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
