package net.news.db;

public class NewsBean {
	private int news_code;
	private String member_id;
	private String member_name;
	private String news_title;
	private String news_info;
	private String news_date;
	private int news_group;
	
	public int getNews_code() {
		return news_code;
	}
	public void setNews_code(int news_code) {
		this.news_code = news_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_info() {
		return news_info;
	}
	public void setNews_info(String news_info) {
		this.news_info = news_info;
	}
	public String getNews_date() {
		return news_date;
	}
	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}
	public int getNews_group() {
		return news_group;
	}
	public void setNews_group(int news_group) {
		this.news_group = news_group;
	}
	
}
