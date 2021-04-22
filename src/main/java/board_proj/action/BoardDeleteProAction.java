package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) /* throws Exception */ {
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String pass = request.getParameter("board_pass");
		
		BoardDeleteService service = new BoardDeleteService();
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		
		System.err.println("isArticleWriter" +isArticleWriter);
		
		ActionForward forward = null;
		
		if(!isArticleWriter) {
			SendMessage.sendMessage(response,"삭제할 권한이 없습니다.");
			return forward;
		}
		
		boolean isDeleteSuccess = service.removeArticle(board_num);

		if(!isDeleteSuccess) {
			SendMessage.sendMessage(response,"삭제 실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardList.do?page=" + page);
		
		return forward;
	}

	

}
