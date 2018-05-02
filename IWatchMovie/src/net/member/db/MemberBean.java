package net.member.db;

import java.sql.Timestamp;

public class MemberBean {
	
	private String member_id;
	private String member_pass;
	private String member_name;
	private String member_tel;
	private int genre_code;
	private String genre_name;
	private String man_grade;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public int getGenre_code() {
		return genre_code;
	}
	public void setGenre_code(int genre_code) {
		this.genre_code = genre_code;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public String getMan_grade() {
		return man_grade;
	}
	public void setMan_grade(String man_grade) {
		this.man_grade = man_grade;
	}
	
	
	
}
