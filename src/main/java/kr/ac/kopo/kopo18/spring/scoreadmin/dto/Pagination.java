package kr.ac.kopo.kopo18.spring.scoreadmin.dto;

import javax.persistence.Column;
import javax.persistence.Entity;

public class Pagination {
	int ppPage;		// <<
	int pPage;		// <
	int cPage;		// 현재 페이지
	int	nPage;		// >
	int nnPage;		// >>
	int sList;		// 페이지 목록 시작 번호
	int eList;		// 페이지 목록 끝번호
	int countPerPage;	// 페이지당 출력 데이터 수
	int pageSize; 		// pager Size

	public Pagination() {}		// 기본 생성자
	
	// Getters & Setters
	public int getPpPage() {
		return ppPage;
	}
	public void setPpPage(int ppPage) {
		this.ppPage = ppPage;
	}
	public int getpPage() {
		return pPage;
	}
	public void setpPage(int pPage) {
		this.pPage = pPage;
	}
	public int getcPage() {
		return cPage;
	}
	public void setcPage(int cPage) {
		this.cPage = cPage;
	}
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}
	public int getNnPage() {
		return nnPage;
	}
	public void setNnPage(int nnPage) {
		this.nnPage = nnPage;
	}
	public int getsList() {
		return sList;
	}
	public void setsList(int sList) {
		this.sList = sList;
	}
	public int geteList() {
		return eList;
	}
	public void seteList(int eList) {
		this.eList = eList;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
