package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	
	///////////////////Map 적용////////////////////
	
	
	/*
	//리스트(불가)
	public Map<String, Object> getList2(){
		System.out.println("dao list2");
		
		//guestbook.xml에서 resultType="map" 해도 selectList가 List로 만들어줌. map을 모은 List가 돼서 오류남.
		Map<String, Object> gMap = (Map<String, Object>) sqlSession.selectList("guestbook.selectList2");
		
		System.out.println("dao : "+gMap.toString());
		return gMap;
	}
	*/
	
	// 내용 등록2 (테스트O)
	public int contentAdd2(String name, String password, String content) {
		System.out.println("dao "+name+" "+password+" "+content);
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		gMap.put("name", name);
		gMap.put("password", password);
		gMap.put("content", content);
		
		System.out.println("dao gMap "+gMap.toString());
		
		count = sqlSession.insert("guestbook.insert2", gMap); 
		
		return count;
	}
	
	//삭제2 (테스트O)
	public int contentDelete2(int no, String pw) {
		System.out.println("dao "+no+" "+pw);
		
		Map<String, Object> gMap = new HashMap<String, Object>();
		gMap.put("no", no);
		gMap.put("password", pw); //키값이 컬럼명과 다르면 오류남.
		
		System.out.println("dao gMap "+gMap.toString());
		
		count = sqlSession.delete("guestbook.delete2", gMap);
		
		return count;
	}
	
}
