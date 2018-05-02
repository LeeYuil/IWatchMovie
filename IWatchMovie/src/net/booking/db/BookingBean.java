package net.booking.db;

public class BookingBean {
	
	private String book_code;
	private String member_id;
	private String mov_title;
	private String book_time;
	private String wat_date;
	private String wat_sttime;
	private String hal_name;
	private int hal_code;
	private String seat_name;
	private int seat_code;
	
	
	public String getBook_code() {
		return book_code;
	}
	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}
	public int getHal_code() {
		return hal_code;
	}
	public void setHal_code(int hal_code) {
		this.hal_code = hal_code;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMov_title() {
		return mov_title;
	}
	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
	}
	public String getBook_time() {
		return book_time;
	}
	public void setBook_time(String book_time) {
		this.book_time = book_time;
	}
	public String getHal_name() {
		return hal_name;
	}
	public void setHal_name(String hal_name) {
		this.hal_name = hal_name;
	}
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
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
	public int getSeat_code() {
		return seat_code;
	}
	public void setSeat_code(int seat_code) {
		this.seat_code = seat_code;
	}
	
}
