package com.spring.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;

public interface BoardDAO {

	public int totCount(PageDTO pdto) throws Exception;
	
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	
	public BoardDTO selectOne(int bnum) throws Exception;
	
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception;
	
	public int update(BoardDTO bdto) throws Exception;
	
	public int delete(int bnum) throws Exception;
	
	public int readcnt_update(int bnum) throws Exception;
	
	public int replycntUp_update(int bnum) throws Exception;
	
	public int replycntDown_update(int bnum) throws Exception;
}
