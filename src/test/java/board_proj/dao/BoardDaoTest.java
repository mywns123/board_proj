package board_proj.dao;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.JdbcUtil;
import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDTO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {
 
	private static final Connection con =JdbcUtil.getConnection();
	private static BoardDaoImpl  dao = new BoardDaoImpl();	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		dao.setCon(con);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectListCount() {
		System.out.println("test02SelectListCount()");
		int res = dao.selectListCount();
		
		Assert.assertNotEquals(-1, res);
		System.out.println("ListCount >> " + res);
	}

	@Test
	public void test03SelectArticleList() {
		System.out.println("test03SelectArticleList()");
		int page =1;
		int limit = 10;
		ArrayList<BoardDTO> list = dao.selectArticleList(page, limit);
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
		System.out.println("============");
		dao.selectArticleList(2, 10).stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectArticle() {
		System.out.println("test05SelectArticle()");
		int board_num = 3;
		BoardDTO res = dao.selectArticle(board_num);		
		Assert.assertNotNull(res);	
		System.out.println("SelectArticle >> " + res);
	}

	@Test
	public void test04InsertArticle() {
		System.out.println("test04InsertArticle()");
		BoardDTO article = new BoardDTO(
				"김상건",
				"1234",
				"5시 퇴근 가능할까",
				"불가능 함",
				"test.txt");
		int res = dao.insertArticle(article);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test10InsertReplyArticle() {
		System.out.println("test10InsertReplyArticle()");
		BoardDTO replyArticle = new BoardDTO("유한솔", "1111", "안돼4", "절대로4", "");
		replyArticle.setBoard_re_ref(48);
		replyArticle.setBoard_re_lev(0);
		replyArticle.setBoard_re_seq(0);
		
		int res = dao.insertReplyArticle(replyArticle);
		Assert.assertEquals(1, res);		
		System.out.println("res >> " + res);	
	}

	@Test
	public void test09UpdateArticle() {
		System.out.println("test09UpdateArticle()");
		int board_num = 43;
		BoardDTO article = dao.selectArticle(board_num);
		int res = dao.updateArticle(article);
		Assert.assertEquals(1, res);
		
		System.out.println("res >> " + res);
	}

	@Test
	public void test08DeleteArticle() {
		System.out.println("test08DeleteArticle()");
		int board_num = dao.nextBoardNum() - 1;
		int res = dao.deleteArticle(board_num);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void test06UpdateReadCount() {
		System.out.println("test06UpdateReadCount()");
		int board_num = 3;
		int res = dao.updateReadCount(board_num);
		Assert.assertNotEquals(-1, res);
		System.out.println("UpdateReadCount >> " + res);
	}

	@Test
	public void test07IsArticleBoardWriter() {
		System.out.println("test07IsArticleBoardWriter()");
		int board_num = 24;
		boolean res = dao.isArticleBoardWriter(board_num, "1234");
		Assert.assertEquals(true, res);
		System.out.println("res >> " + res);
		
	}
	@Test
	public void test01NextBoardNum() {
		System.out.println("test01NextBoardNum()");
		int res = dao.nextBoardNum();
		Assert.assertNotEquals(0, res);
		System.out.println("next Number >> " + res);
	}
}
