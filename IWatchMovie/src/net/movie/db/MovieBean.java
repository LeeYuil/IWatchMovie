package net.movie.db;

import java.sql.Date;

public class MovieBean {
	private int mov_code;
	private String mov_title;
	private String mov_dir;
	private String mov_act;
	private int genre_code;
	private String mov_info;
	private String mov_stday;
	private int mov_time;
	private int mov_sel_pri;
	private String mov_level;
	private String mov_img;
	private String genre_name;
	
	
	public String getMov_img() {
		return mov_img;
	}
	public void setMov_img(String mov_img) {
		this.mov_img = mov_img;
	}
	public int getMov_code() {
		return mov_code;
	}
	public void setMov_code(int mov_code) {
		this.mov_code = mov_code;
	}
	public String getMov_title() {
		return mov_title;
	}
	public void setMov_title(String mov_title) {
		this.mov_title = mov_title;
	}
	public String getMov_dir() {
		return mov_dir;
	}
	public void setMov_dir(String mov_dir) {
		this.mov_dir = mov_dir;
	}
	public String getMov_act() {
		return mov_act;
	}
	public void setMov_act(String mov_act) {
		this.mov_act = mov_act;
	}
	public int getGenre_code() {
		return genre_code;
	}
	public void setGenre_code(int genre_code) {
		this.genre_code = genre_code;
	}
	public String getMov_info() {
		return mov_info;
	}
	public void setMov_info(String mov_info) {
		this.mov_info = mov_info;
	}
	
	public String getMov_stday() {
		return mov_stday;
	}
	public void setMov_stday(String mov_stday) {
		this.mov_stday = mov_stday;
	}
	public int getMov_time() {
		return mov_time;
	}
	public void setMov_time(int mov_time) {
		this.mov_time = mov_time;
	}
	public int getMov_sel_pri() {
		return mov_sel_pri;
	}
	public void setMov_sel_pri(int mov_sel_pri) {
		this.mov_sel_pri = mov_sel_pri;
	}
	public String getMov_level() {
		return mov_level;
	}
	public void setMov_level(String mov_level) {
		this.mov_level = mov_level;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
}
