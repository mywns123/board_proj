package board_proj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import board_proj.dao.BoardDao;
import board_proj.dto.BoardDTO;

public class BoardDaoImpl implements BoardDao {
	private static final BoardDaoImpl Instance = new BoardDaoImpl();
	private Connection con;

	public static BoardDaoImpl getInstance() {
		return Instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int selectListCount() {
		String sql = "SELECT count(*)from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<BoardDTO> selectArticleList(int page, int limit) {
		String sql = "select board_num, board_name, board_pass, board_subject,"
				+ " board_content, board_file, board_re_ref, board_re_lev,"
				+ " board_re_seq, board_readcount, board_date" + " from board"
				+ " order by board_re_ref desc, board_re_seq asc limit ?, ?";
		int startRow = (page - 1) * limit;
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, limit);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
					do {
						list.add(getBoardDTO(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BoardDTO getBoardDTO(ResultSet rs) throws SQLException {
		int board_num = rs.getInt("board_num");
		String board_name = rs.getString("board_name");
		String board_pass = rs.getString("board_pass");
		String board_subject = rs.getString("board_subject");
		String board_content = rs.getString("board_content");
		String board_file = rs.getString("board_file");
		int board_re_ref = rs.getInt("board_re_ref");
		int board_re_lev = rs.getInt("board_re_lev");
		int board_re_seq = rs.getInt("board_re_seq");
		int board_readcount = rs.getInt("board_readcount");
		Date board_date = rs.getDate("board_date");

		return new BoardDTO(board_num, board_name, board_pass, board_subject, board_content, board_file, board_re_ref,
				board_re_lev, board_re_seq, board_readcount, board_date);
	}

	@Override
	public BoardDTO selectArticle(int board_num) {
		String sql = "select board_num, board_name, board_pass, board_subject,"
				+ " board_content, board_file, board_re_ref, board_re_lev,"
				+ " board_re_seq, board_readcount, board_date from board where board_num = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					return getBoardDTO(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertArticle(BoardDTO article) {
		String sql = "insert into board"
				+ "(board_num, board_name, board_pass, board_subject, board_content,board_file, board_re_ref)"
				+ " values (?,?,?,?,?,?,?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			int nextNum = nextBoardNum();
			pstmt.setInt(1, nextNum);
			pstmt.setString(2, article.getBoard_name());
			pstmt.setString(3, article.getBoard_pass());
			pstmt.setString(4, article.getBoard_subject());
			pstmt.setString(5, article.getBoard_content());
			pstmt.setString(6, article.getBoard_file());
			pstmt.setInt(7, nextNum);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateArticle(BoardDTO article) {
		String sql = "update board" + " set board_subject =?, board_content = ?" + " where board_num = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, article.getBoard_subject());
			pstmt.setString(2, article.getBoard_content());
			pstmt.setInt(3, article.getBoard_num());
//			System.out.println("pstmt : " + pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteArticle(int board_num) {
		String sql = "delete from board where board_num = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, board_num);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override // 조회수 업데이트
	public int updateReadCount(int board_num) {
		String sql = "update board set board_readcount = board_readcount + 1" + " where board_num  = " + board_num;
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isArticleBoardWriter(int board_num, String pass) {
		String sql = "SELECT 1 from board where board_num = ? and board_pass = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, board_num);
			pstmt.setString(2, pass);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override // 답변 글
	public int insertReplyArticle(BoardDTO article) {
		int next_board_num = nextBoardNum();
		int re_ref = article.getBoard_re_ref();
		int re_lev = article.getBoard_re_lev();
		int re_seq = article.getBoard_re_seq();

		String sql1 = "update board set board_re_seq  = board_re_seq + 1"
					+ " where board_re_ref  =? and board_re_seq > ?";
		
		String sql2 = "insert into board"
					+ " (board_num, board_name, board_pass,"
					+ " board_subject, board_content, board_file,"
					+ " board_re_ref, board_re_lev, board_re_seq)"				
					+ " VALUEs(?,?,?,?,?,'',?,?,?);";
		
		try {
			con.setAutoCommit(false);
			
			try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
				pstmt.setInt(1, re_ref);
				pstmt.setInt(2, re_seq);	
				System.out.println(pstmt);
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			re_seq +=1;
			re_lev +=1;
			
			try(PreparedStatement pstmt = con.prepareStatement(sql2)){
				pstmt.setInt(1, next_board_num);
				pstmt.setString(2, article.getBoard_name());	
				pstmt.setString(3, article.getBoard_pass());	
				pstmt.setString(4, article.getBoard_subject());	
				pstmt.setString(5, article.getBoard_content());
				pstmt.setInt(6, re_ref);
				pstmt.setInt(7, re_lev);
				pstmt.setInt(8, re_seq);
				System.out.println("pstmt :" + pstmt);
				pstmt.executeUpdate();		
			}
		
			con.commit();
			return 1;
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int nextBoardNum() {
		String sql = "select max(board_num) from board";
		try (PreparedStatement pstmt = con.prepareStatement(sql); ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

}
