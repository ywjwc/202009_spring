package com.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.BoardDAO;
import com.spring.dao.ReplyDAO;
import com.spring.dto.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Resource
	private ReplyDAO rdao;
	@Resource
	private BoardDAO bdao;
	
	@Transactional
	@Override
	public int insert(ReplyDTO rdto) throws Exception {
		// TODO Auto-generated method stub
		bdao.replycntUp_update(rdto.getBnum());
		return rdao.insert(rdto);
	}

	@Override
	public int update(ReplyDTO rdto) throws Exception {
		// TODO Auto-generated method stub
		return rdao.update(rdto);
	}

	@Transactional
	@Override
	public int delete(int rnum, int bnum) throws Exception {
		// TODO Auto-generated method stub
		bdao.replycntDown_update(bnum);
		return rdao.delete(rnum);
	}

	@Override
	public List<ReplyDTO> selectList(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return rdao.selectList(bnum);
	}

	@Override
	public ReplyDTO selectOne(int rnum) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete_bnum(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return rdao.delete_bnum(bnum);
	}	
}
