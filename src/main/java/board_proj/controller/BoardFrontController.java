package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.action.ActionForward;
import board_proj.action.NullAction;

@WebServlet(urlPatterns = { "*.do" }, loadOnStartup = 1, initParams = {
		@WebInitParam(name = "configFile", value = "/WEB-INF/commandAction.properties") })

public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actionMap = new HashMap<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() - config " + config.getInitParameter("configFile"));

		String configFile = config.getInitParameter("configFile");
		try (InputStream is = config.getServletContext().getResourceAsStream(configFile)) {
			Properties props = new Properties();
			props.load(is);

			for (Entry<Object, Object> entry : props.entrySet()) {
//					System.out.println(entry.getKey() + " : " + entry.getValue());
				Class<?> cls;
				Action action = null;
				try {
					cls = Class.forName((String) entry.getValue());
					action = (Action) cls.newInstance();
				} catch (ClassNotFoundException e) {
					action = new NullAction();
					e.printStackTrace();
				}
				actionMap.put((String) entry.getKey(), action);
			}
			
			/*
			 * for(Entry<String,Action> entry : actionMap.entrySet()) {
			 * System.out.println(entry.getKey() + " : " + entry.getValue()); }
			 */
		} catch (IOException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getServletPath();
		System.out.println(command);

		Action action = actionMap.get(command);		
		ActionForward forward = action.execute(request, response);

//		
//		try{
//			if (command.equals("/boardWriteForm.do")) {
//				action = new BoardWriteFormAction();
//				forward = action.execute(request, response);//				
//			}else if (command.equals("/boardWritePrd.do")) {
//				action = new BoardWriteProAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardList.do")) {
//				action = new BoardListAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardDetail.do")) {
//				action = new BoardDetailAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardDeleteForm.do")) {
//				action = new BoardDeleteFormAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardDeletePro.do")) {
//				action = new BoardDeleteProAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/file_downAction.do")) {
//				action = new File_downAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardModifyForm.do")) {
//				action = new BoardModifyFormAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardModifyPro.do")) {
//				action = new BoardModifyProAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardReplyForm.do")) {
//				action = new BoardReplyFormAction();
//				forward = action.execute(request, response);
//			}else if (command.equals("/boardReplyPro.do")) {
//				action = new BoardReplyProAction();
//				forward = action.execute(request, response);//			
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
				;
			}

		}
	}
}
