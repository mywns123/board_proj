package board_proj.service;

import java.sql.Connection;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDs;
import board_proj.dto.BoardDTO;

public class BoardWriteService {
	private BoardDaoImpl daoImpl = BoardDaoImpl.getInstance();
	private Connection con = JndiDs.getConnection();

	public BoardWriteService() {
		daoImpl.setCon(con);
	}

	public boolean registerArticle(BoardDTO boardDTO) {
		return daoImpl.insertArticle(boardDTO) == 1 ? true : false;
	}

}
