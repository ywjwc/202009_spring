package com.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;

@Service
public interface BoardService {

public List<BoardDTO> selectList(PageDTO pdto) throws Exception;
	
	public Map<String, Object> selectOne(int bnum) throws Exception;
	
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception;
	
	public int update(BoardDTO bdto, List<Integer> fnumList, List<MultipartFile> bfiles) throws Exception;
	
	public int delete(int bnum) throws Exception;
	
	public int readcnt_update(int bnum) throws Exception;
	
	public int replycntUp_update(int bnum) throws Exception;
	
	public int replycntDown_update(int bnum) throws Exception;
}
