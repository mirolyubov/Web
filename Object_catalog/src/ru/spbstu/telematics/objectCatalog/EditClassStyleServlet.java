package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditClassStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	static String styleId;
	static ArrayList<TStyle> styles;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		styleId = req.getParameter("styleId");
		String classId = req.getParameter("classId");
		String newStyleId = req.getParameter("newStyleId");
		if (styleId!=null && classId!=null && newStyleId!=null && !newStyleId.equals("0")){
			catalog.editClassStyle(classId, styleId, newStyleId);
			req.setAttribute("classId", classId);
			req.getRequestDispatcher("/class_style/add_class_style.jsp").forward(req, resp);
		}
		else
		if (styleId!=null && classId!=null){
			styles = catalog.getStyles();
			req.setAttribute("classId", classId);
			req.setAttribute("styleId", styleId);
			req.getRequestDispatcher("/class_style/edit_class_style.jsp").forward(req, resp);
		}
		
	}
	
	public static String printStylesOptions(){
		String result = new String();
		result+="<option value='0'>-</option>";
		for (int i=0; i<styles.size(); i++)
		{
			TStyle c = styles.get(i);
			if (c.getStyleId()==Integer.valueOf(styleId))
				result+="<option selected='selected' value='"+c.getStyleId()+"'>"+c.getFamily()+", ";
			else
				result+="<option value='"+c.getStyleId()+"'>"+c.getFamily()+", ";
			if (c.isMandatory())
				result+="is mandatory, ";
			else
				result+="is not mandatory, ";
			
			if (c.isMultiple())
				result+="is multiple";
			else
				result+="is not multiple";
			result+="</option>";
		}
		return result;
	}
}
