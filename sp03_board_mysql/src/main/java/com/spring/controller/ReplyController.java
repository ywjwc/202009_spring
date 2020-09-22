package com.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.ReplyDTO;
import com.spring.service.ReplyService;

// Controller + ResponseBody 기능
@RestController
@RequestMapping("/reply")
public class ReplyController {

	@Resource
	private ReplyService rservice;

	// 댓글
	// @RequestBody : json 형태로 값 받음
	// produces="application/text; charset=utf-8" : 반환값 문자 한글처리
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/text; charset=utf-8")
	public ResponseEntity<String> insert(@RequestBody ReplyDTO rdto) throws Exception {
		System.out.println(rdto);
		rservice.insert(rdto);
		// 응답객체를 생성해서 상태 값 같이 전송
		return new ResponseEntity<>("추가완료", HttpStatus.OK);
		// return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
	}
	
	// 리스트
	@RequestMapping(value = "/{bnum}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyDTO>> list(@PathVariable("bnum") int bnum) throws Exception {
		List<ReplyDTO> list = rservice.selectList(bnum);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 삭제
	@RequestMapping(value = "/{rnum}", method = RequestMethod.DELETE, produces = "application/text; charset=utf-8")
	public ResponseEntity<String> delete(@PathVariable("rnum") int rnum, int bnum) throws Exception {
		rservice.delete(rnum, bnum);
		
		return new ResponseEntity<>("삭제완료", HttpStatus.OK);
	}
	
	// 수정
	@RequestMapping(value = "/{rnum}", method = {RequestMethod.PUT, RequestMethod.PATCH}, produces = "application/text; charset=utf-8")
	public ResponseEntity<String> update(@RequestBody ReplyDTO rdto, @PathVariable("rnum") int rnum) throws Exception {
		rdto.setRnum(rnum);
		rservice.update(rdto);
		
		return new ResponseEntity<>("수정완료", HttpStatus.OK); 
	}
}