package board_proj.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class File_downAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) /* throws Exception */ {
		String board_file = request.getParameter("downFile");
		String savePath = "/boardUpload";
		ServletContext context = request.getServletContext();
		String downPath = context.getRealPath(savePath);
		String filePath = downPath + "\\" + board_file;
		byte[] b = new byte[4096];
		
		String mineType = request.getServletContext().getMimeType(filePath);

		if (mineType == null)
			mineType = "application/octet-stream";

		response.setContentType(mineType);
		String agent = request.getHeader("User-agent");
		boolean isBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);

		try {
			if (isBrowser) {
				board_file = URLEncoder.encode(board_file, "UTF-8").replaceAll("\\+", "%20");

			} else {
				board_file = new String(board_file.getBytes("UTF-8"), "iso-8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		response.setHeader("Content-Disposition", "attachment; board_file = " + board_file);

		try	(FileInputStream in = new FileInputStream(filePath);
				ServletOutputStream out2 = response.getOutputStream();) {
			int numRead;
			
			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
			out2.flush();			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
