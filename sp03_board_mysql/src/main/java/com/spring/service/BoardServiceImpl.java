package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;

@Repository
public class BoardServiceImpl implements BoardService {

	@Resource
	private BoardDAO bdao;
	@Resource
	private BFileService fservice;
	@Resource 
	private ReplyService rservice;

	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		// TODO Auto-generated method stub
		int totCnt = bdao.totCount(pdto);
		
		int perPage = pdto.getPerPage();
		
		int totPage;
		if (totCnt % perPage == 0) {
			totPage = totCnt / perPage;
		} else {
			totPage = totCnt / perPage + 1;
		}
		pdto.setTotPage(totPage);

		int nowPage = pdto.getNowPage();
		pdto.setNowPage(nowPage);
		
		int startNum = (nowPage - 1) * perPage;	// mysql 0번부터 시작
		pdto.setStartNum(startNum);
		
		int endNum = startNum + perPage - 1;
		pdto.setEndNum(endNum);
		
		int pageBlock = pdto.getPageBlock();
		
		int startPage = nowPage - ((nowPage - 1) % pageBlock);
		pdto.setStartPage(startPage);
		
		int endPage = startPage + (pageBlock - 1);
		if (endPage > totPage) {
			pdto.setEndPage(totPage);
		} else {
			pdto.setEndPage(endPage);				
		}
		return bdao.selectList(pdto);
	}

	@Override
	public Map<String, Object> selectOne(int bnum) throws Exception {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("board", bdao.selectOne(bnum));
		map.put("flist", fservice.selectList(bnum));
		
		return map;
	}

	@Transactional
	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		// TODO Auto-generated method stub
		bdao.insert(bdto, bfiles);
		int bnum = bdto.getBnum();
				
		List<String> filenameList = fservice.fileUploads(bfiles);
		fservice.insert(bnum, filenameList);
		
		return bnum;
	}

	@Override
	public int update(BoardDTO bdto, List<Integer> fnumList, List<MultipartFile> bfiles) throws Exception {
		// TODO Auto-generated method stub
		bdao.update(bdto);
		int bnum = bdto.getBnum();
		
		fservice.delete_part(bnum, fnumList);

		List<String> filenameList = fservice.fileUploads(bfiles);

		fservice.insert(bnum, filenameList);
		return 0;
	}

	@Transactional
	@Override
	public int delete(int bnum) throws Exception {
		// TODO Auto-generated method stub
		
		rservice.delete_bnum(bnum);
		
		fservice.delete(bnum);
		
		bdao.delete(bnum);
		
		return 0;
	}

	@Override
	public int readcnt_update(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return bdao.readcnt_update(bnum);
	}

	@Override
	public int replycntUp_update(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return bdao.replycntUp_update(bnum);
	}

	@Override
	public int replycntDown_update(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return bdao.replycntDown_update(bnum);
	}
}