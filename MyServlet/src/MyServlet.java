
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/javascript");
		response.setCharacterEncoding("utf-8");
		// 加载json文件进行处理
		String file = this.getServletContext().getRealPath("/")+"/WEB-INF/province.json";
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line=br.readLine())!=null){
			sb.append(line);
		}
	    br.close();
	    isr.close();
	    fis.close();    
	    JSONArray array = (JSONArray) JSONSerializer.toJSON(sb.toString());
//	    System.out.println(array.toString());
	    PrintWriter pw = response.getWriter(); 
	    if("table".equals(request.getParameter("table"))){
	    	JSONArray a = array.getJSONArray(array.size()-1);
	    	pw.write(a.toString());
	    	pw.flush();
	    }else{
	    	String text = request.getParameter("text");
		    if(text != null){
		    	JSONArray a = array.getJSONArray(0);
		 	    pw.print(a.toString());
		 	    pw.flush();
		    }else{
		    	int id = Integer.parseInt(request.getParameter("id"));
		    	int name = Integer.parseInt(request.getParameter("name"));
//		    	System.out.println(array.getJSONArray(id).getJSONObject(name).toString());
		    	JSONObject a = array.getJSONArray(id).getJSONObject(name);
		    	pw.print(a.toString());
//		    	System.out.println(a.toString());
		 	    pw.flush();   	 	    
		    }
	    }
	    pw.close(); 
	  		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
