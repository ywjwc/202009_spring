package com.spring.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyDTO {

	private int rnum;
	private int bnum;
	private String writer;
	private String content;
	@JsonFormat(pattern = "yyyy.MM.dd. hh:mm:ss", timezone="Asia/Seoul")
	private Date regdate;

	public ReplyDTO() {
		super();
	}

	public ReplyDTO(int rnum, int bnum, String writer, String content, Date regdate) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ReplyDTO [rnum=" + rnum + ", bnum=" + bnum + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + "]";
	}
}
