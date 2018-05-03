package net.admin.db;

import java.sql.Date;

public class AdminBean {

	private int wat_code;
	private int hal_code;
	private int mov_code;
	private int mov_sel_pri;
	private String wat_date;
	private String wat_sttime;
	private String mov_title;
	private String hal_name;
	
	private int rev_code;
	private String member_id;
	private String rev_title;
	private Date rev_date;
	private int book_code;
	private String rev_info;
	private int rev_best;
	
	
	
	public int getWat_code() {
		return wat_code;
	}
	public void setWat_code(int wat_code) {
		this.wat_code = wat_code;
	}
	public int getHal_code() {
		return hal_code;
	}
	public void setHal_code(int hal_code) {
		this.hal_code = hal_code;
	}
	public int getMov_code() {
		return mov_code;
	}
	public int getMov_sel_pri() {
		return mov_sel_pri;
	}
	public void setMov_sel_pri(int mov_sel_pri) {
		this.mov_sel_pri = mov_sel_pri;
	}
	public void setMov_code(int mov_code) {
		this.mov_code = mov_code;
	}
	public String getWat_date() {
		return wat_date;
	}
	public void setWat_date(String wat_date) {
		this.wat_date = wat_date;
	}
	public String getWat_sttime() {
		return wat_sttime;
	}
	public void setWat_sttime(String wat_sttime) {
		this.wat_sttime = wat_sttime;
	}
	public String getMov_title() {
		return mov_title;
	}
	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
	}
	public String getHal_name() {
		return hal_name;
	}
	public void setHal_name(String hal_name) {
		this.hal_name = hal_name;
	}
	
	public int getRev_code() {
		return rev_code;
	}
	public void setRev_code(int rev_code) {
		this.rev_code = rev_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getRev_title() {
		return rev_title;
	}
	public void setRev_title(String rev_title) {
		this.rev_title = rev_title;
	}
	public Date getRev_date() {
		return rev_date;
	}
	public void setRev_date(Date rev_date) {
		this.rev_date = rev_date;
	}
	public int getBook_code() {
		return book_code;
	}
	public void setBook_code(int book_code) {
		this.book_code = book_code;
	}
	public String getRev_info() {
		return rev_info;
	}
	public void setRev_info(String rev_info) {
		this.rev_info = rev_info;
	}
	public int getRev_best() {
		return rev_best;
	}
	public void setRev_best(int rev_best) {
		this.rev_best = rev_best;
	}
	
	
}
