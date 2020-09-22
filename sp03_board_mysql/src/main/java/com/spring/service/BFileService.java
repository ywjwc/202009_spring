package com.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.BFileDTO;

@Service
public interface BFileService {
	
	public List<String> fileUploads(List<MultipartFile> files) throws Exception;
	
	public int insert(int bnum, List<String> filenameList) throws Exception;
	
	public List<BFileDTO> selectList(int bnum) throws Exception;

	public void fileDownload(String filename, HttpServletResponse response) throws Exception;
	
	public int delete(int bnum) throws Exception;

	public int delete_part(int bnum, List<Integer> fnumList) throws Exception;
}