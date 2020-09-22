package com.spring.dto;

import java.util.Date;

public class BFileDTO {

	private int bnum;
	private int fnum;
	private String filename;
	private Date regdate;

	public BFileDTO() {
		super();
	}

	public BFileDTO(int bnum, int fnum, String filename, Date regdate) {
		super();
		this.bnum = bnum;
		this.fnum = fnum;
		this.filename = filename;
		this.regdate = regdate;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public int getFnum() {
		return fnum;
	}

	public void setFnum(int fnum) {
		this.fnum = fnum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "FileDTO [bnum=" + bnum + ", fnum=" + fnum + ", filename=" + filename + ", regdate=" + regdate + "]";
	}
}
