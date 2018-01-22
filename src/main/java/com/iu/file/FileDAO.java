package com.iu.file;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
	
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE = "FileMapper.";
	
	public List<FileDTO> selectList(int num)throws Exception{
		return sqlSession.selectList(NAMESPACE+"selectList", num);
	}
	
	public int insert(FileDTO fileDTO){
		return sqlSession.insert(NAMESPACE+"insert", fileDTO);
	}
	
	public int update(FileDTO fileDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"update", fileDTO);
	}
	
	public int delete(int num) throws Exception{
		return sqlSession.delete(NAMESPACE+"delete", num);
	}
	
	public int deleteFnum(int fnum) throws Exception{
		return sqlSession.delete(NAMESPACE+"deleteFnum", fnum);
	}
	
}
