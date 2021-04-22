package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.BoardDTO;
import board_proj.service.BoardModifyProService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) /* throws Exception */ {
	
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		
		BoardModifyProService service = new BoardModifyProService();
		BoardDTO article =new BoardDTO();
		article.setBoard_num(board_num);
		String board_subject =  request.getParameter("board_subject");
		article.setBoard_subject(board_subject);
		String board_content =  request.getParameter("board_content");
		article.setBoard_content(board_content);
		
		ActionForward forward = null;
		
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		
		if(!isArticleWriter) {
			SendMessage.sendMessage(response,"수정할 권한이 없습니다.");
			return forward;
		}
		
		boolean isModifyArticle= service.modifyArticle(article);


		if(!isModifyArticle) {
			SendMessage.sendMessage(response,"수정 실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardDetail.do?board_num=" + board_num +"&" + "page=" + page);		
		
		return forward;
	}	

}
