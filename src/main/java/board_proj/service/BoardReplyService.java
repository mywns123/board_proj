package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDs;
import board_proj.dto.BoardDTO;

public class BoardReplyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();

	public BoardReplyService() {
		dao.setCon(con);
	}
	
	public BoardDTO getArticle(int board_num) {		
		//board_num에 해당하는 BoardDTO return
		return dao.selectArticle(board_num);
	}

}
