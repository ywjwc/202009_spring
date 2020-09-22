package com.spring.dto;

public class PageDTO {
	private int nowPage = 1;// 현재 페이지
	private int perPage = 10;// 페이지 당 게시물 수
	private int pageBlock = 10;// 페이지 블록 수
	private int totPage;// 전체 페이지 수
	private int startPage;// 블록의 시작 페이지
	private int endPage;// 블록의 끝 페이지
	private int startNum;// 시작 번호
	private int endNum;// 끝 번호
	private String findKey; // 검색 키
	private String findValue; // 검색 값

	public PageDTO() {
		super();
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getPageBlock() {
		return pageBlock;
	}

	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public String getFindKey() {
		return findKey;
	}

	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}

	public String getFindValue() {
		return findValue;
	}

	public void setFindValue(String findValue) {
		this.findValue = findValue;
	}

	@Override
	public String toString() {
		return "PageDTO [nowPage=" + nowPage + ", perPage=" + perPage + ", pageBlock=" + pageBlock + ", totPage="
				+ totPage + ", startPage=" + startPage + ", endPage=" + endPage + ", startNum=" + startNum + ", endNum="
				+ endNum + ", findKey=" + findKey + ", findValue=" + findValue + "]";
	}
}
