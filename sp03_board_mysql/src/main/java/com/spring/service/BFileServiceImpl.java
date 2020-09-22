package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.BFileDAO;
import com.spring.dto.BFileDTO;

@Repository
public class BFileServiceImpl implements BFileService {

	@Resource
	private BFileDAO fdao;
	@Resource(name = "saveDir")
	String saveDir;

	@Override
	public int insert(int bnum, List<String> filenameList) throws Exception {
		// TODO Auto-generated method stub
		for (String filename : filenameList) {
			BFileDTO fdto = new BFileDTO();
			fdto.setBnum(bnum);
			fdto.setFilename(filename);

			fdao.insert(fdto);
		}
		return 0;
	}

	@Override
	public List<String> fileUploads(List<MultipartFile> files) throws Exception {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();

		for (MultipartFile mf : files) {
			
			if (mf.getOriginalFilename() != "") {
				
				long ct = System.currentTimeMillis();
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_hhmmss");
				String currentTime = sdf.format(new Date(ct));
	
				String filename = currentTime + mf.getOriginalFilename();
				File f = new File(saveDir, filename);
	
				try {
					mf.transferTo(f);
					list.add(filename);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<BFileDTO> selectList(int bnum) throws Exception {
		// TODO Auto-generated method stub
		return fdao.selectList(bnum);
	}

	@Override
	public void fileDownload(String filename, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String fileUrl = saveDir + "\\" + filename;

		try {
			FileInputStream fis = new FileInputStream(fileUrl);

//			filename = URLEncoder.encode(filename, "utf-8");
			filename = new String(filename.getBytes("utf-8"), "iso-8859-1");

			response.setHeader("Content-Disposition", "attachment;filename=" + filename);

			ServletOutputStream out = response.getOutputStream();

			FileCopyUtils.copy(fis, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int delete(int fnum) throws Exception {
		// TODO Auto-generated method stub
		return fdao.delete(fnum);		
	}
	
	@Override
	public int delete_part(int bnum, List<Integer> fnumList) throws Exception {
		// TODO Auto-generated method stub
		String fnums = "";
		
		if (fnumList != null) {
			fnums = fnumList.toString().replace("[", "").replace("]", "");
		}
		fdao.delete_part(bnum, fnums);

		return 0;
	}
}