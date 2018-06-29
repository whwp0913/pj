package org.zerock.test;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;
import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTests {

	
	@Setter(onMethod_= {@Autowired})
	private BoardMapper mapper;

	@Test
	public void testList() {
		
		for(int i = 0; i < 20; i++) {
			BoardVO vo = new BoardVO();
			vo.setTitle("Test Title....."+i);
			vo.setContent("Test Content....."+i);
			vo.setMid("nara");
			vo.setGno("100");
			vo.setKno("30");
			mapper.insert(vo);
		}
		
		
	}
	
	@Test
	public void addFile() {

	}
	

	
	
}
