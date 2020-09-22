package com.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.dto.BFileDTO;

@Repository
public class BFileDAOImpl implements BFileDAO {
	
	@Resource
	private SqlSession session;

	@Override
	public List<BFileDTO> selectList(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("fileMapper.selectList", bnum);
	}

	@Override
	public int insert(BFileDTO fdto) throws Exception {
		// TODO Auto-generated method stub
		return session.insert("fileMapper.insert", fdto);
	}

	@Override
	public int update(BFileDTO fdto) throws Exception {
		// TODO Auto-generated method stub
		return session.update("fileMapper.update", fdto);
	}

	@Override
	public int delete(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return session.delete("fileMapper.delete", bnum);
	}
	
	@Override
	public int delete_part(int bnum, String fnums) throws Exception {
		return session.delete("fileMapper.delete_part", bnum);
	}
}
