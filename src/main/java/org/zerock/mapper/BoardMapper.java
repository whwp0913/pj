package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> list(Criteria cri);
	
	public BoardVO read(int bno);
	
	public int insert(BoardVO vo);
	
	public int update(BoardVO vo);
	
	public int delete(int bno);
	
	public int count(Criteria cri);
	
	public List<BoardVO> searchList(Criteria cri);

	public int addViews(int bno);
	
	// 파일 업로드 
	public int addFile(BoardVO vo);
	// 파일 삭제
	public int removeFile(int bno);
	// 파일 조회 
	public String[] searchFile(int bno);

}
