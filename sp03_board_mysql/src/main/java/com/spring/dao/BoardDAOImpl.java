package com.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Resource
	private SqlSession session;

	@Override
	public int totCount(PageDTO pdto) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne("boardMapper.totCount", pdto);
	}
	
	@Override
	public List<BoardDTO> selectList(PageDTO pdto) throws Exception {
		return session.selectList("boardMapper.selectList", pdto);
	}

	@Override
	public BoardDTO selectOne(int bnum) throws Exception {
		return session.selectOne("boardMapper.selectOne", bnum);
	}

	@Override
	public int insert(BoardDTO bdto, List<MultipartFile> bfiles) throws Exception {
		return session.insert("boardMapper.insert", bdto);
	}

	@Override
	public int update(BoardDTO bdto) throws Exception {
		return session.update("boardMapper.update", bdto);
	}

	@Override
	public int delete(int bnum) throws Exception {
		return session.delete("boardMapper.delete", bnum);
	}

	// 조회수 +1
	@Override
	public int readcnt_update(int bnum) throws Exception {
		return session.update("boardMapper.readcnt_update", bnum);
	}

	// 댓글수 +1
	@Override
	public int replycntUp_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntUp_update", bnum);
	}

	// 댓글수 -1
	@Override
	public int replycntDown_update(int bnum) throws Exception {
		return session.update("boardMapper.replycntDown_update", bnum);
	}
}
