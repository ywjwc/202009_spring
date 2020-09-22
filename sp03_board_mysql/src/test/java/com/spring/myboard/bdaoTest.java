package com.spring.myboard;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;
import com.spring.dto.PageDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class bdaoTest {

	@Resource
	private BoardDAO bdao;

	@Test
	public void testTotCount() throws Exception {
//		fail("Not yet implemented");
		PageDTO pdto = new PageDTO();
		int totCnt = bdao.totCount(pdto);
		System.out.println(totCnt);
//		Assert.assertNotNull(bdao.selectList(new PageDTO()));
	}
	
	@Test
	public void testSelectList() throws Exception {
//		fail("Not yet implemented");
		PageDTO pdto = new PageDTO();
		pdto.setStartNum(11);
		pdto.setEndNum(20);
		List<BoardDTO> blist = bdao.selectList(pdto);
		System.out.println(blist);
//		Assert.assertNotNull(bdao.selectList(new PageDTO()));
	}

	@Test
	public void testSelectOne() throws Exception {
//		fail("Not yet implemented");
		Assert.assertNotNull(bdao.selectOne(1));
	}	

	@Test
	public void testInsert() throws Exception {
//		fail("Not yet implemented");
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(1);
		bdto.setWriter("홍길동");
		bdto.setEmail("hong@gmail.com");
		bdto.setSubject("율도국");
		bdto.setContent("환영");
//		Assert.assertEquals(1, bdao.insert(bdto));
	}

	@Test
	public void testUpdate() throws Exception {
//		fail("Not yet implemented");
		BoardDTO bdto = new BoardDTO();
		bdto.setBnum(1);
		bdto.setEmail("lee@gmail.com");
		bdto.setSubject("임진왜란");
		bdto.setContent("거북선");
		Assert.assertEquals(1, bdao.update(bdto));
		
	}

	@Test
	public void testDelete() throws Exception {
//		fail("Not yet implemented");
		Assert.assertEquals(1, bdao.delete(1));
	}

	@Test
	public void testReadcnt_update() throws Exception {
//		fail("Not yet implemented");
	}

	@Test
	public void testReplycntUp_update() throws Exception {
//		fail("Not yet implemented");
	}

	@Test
	public void testReplycntDown_update() throws Exception {
//		fail("Not yet implemented");
	}
}
