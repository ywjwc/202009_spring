package com.spring.dao;

import java.util.List;

import com.spring.dto.BFileDTO;

public interface BFileDAO {
	
	public List<BFileDTO> selectList(int bnum) throws Exception;
	
	public int insert(BFileDTO fdto) throws Exception;
	
	public int update(BFileDTO fdto) throws Exception;
	
	public int delete(int bnum) throws Exception;

	int delete_part(int bnum, String fnums) throws Exception;
}
