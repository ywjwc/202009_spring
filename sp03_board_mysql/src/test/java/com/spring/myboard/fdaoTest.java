package com.spring.myboard;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.BFileDAO;
import com.spring.dto.BFileDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class fdaoTest {
	
	@Resource
	private BFileDAO fdao;

	@Test
	public void testSelectList() throws Exception {
		Assert.assertNotNull(fdao.selectList(1));
	}

	@Test
	public void testInsert() throws Exception {
		BFileDTO fdto = new BFileDTO();
		fdto.setBnum(1);
		fdto.setFnum(1);
		fdto.setFilename("lion.png");
		Assert.assertEquals(1, fdao.insert(fdto));
	}

	@Test
	public void testUpdate() throws Exception {
		BFileDTO fdto = new BFileDTO();
		fdto.setBnum(1);
		fdto.setFnum(1);
		fdto.setFilename("pig.png");
		Assert.assertEquals(1, fdao.update(fdto));
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertEquals(1, fdao.delete(1));
	}
}
