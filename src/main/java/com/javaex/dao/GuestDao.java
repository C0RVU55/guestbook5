package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	int count = 0;
	
	// 생성자
	// 메소드 겟셋
	// 메소드 일반

	// 내용 출력(리스트)
	public List<GuestVo> getList() {
		List<GuestVo> gList = sqlSession.selectList("guestbook.selectList");

		System.out.println(gList.toString());
		
		return gList;
	}

	// 내용 삭제 (조건 2개 달아서 쿼리문 1개로 처리)
	public int contentDelete(int no, String password) {
		System.out.println("dao "+no+" "+password);
		
		GuestVo gVo = new GuestVo(no, password);
		count = sqlSession.insert("guestbook.delete", gVo); 
	
		return count;
	}

	// 내용 등록
	public int contentAdd(GuestVo gVo) {
		System.out.println("dao "+gVo);
		
		count = sqlSession.insert("guestbook.insert", gVo); 
		
		return count;
	}

}
