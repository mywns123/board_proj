package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDs;
import board_proj.dto.BoardDTO;

public class BoardReplyProService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();

	public BoardReplyProService() {
		dao.setCon(con);
	}
	
	public boolean replyArticle(BoardDTO article) {		
		//board_num에 해당하는 BoardDTO return
		return dao.insertReplyArticle(article) == 1 ? true:false;
	}

}
