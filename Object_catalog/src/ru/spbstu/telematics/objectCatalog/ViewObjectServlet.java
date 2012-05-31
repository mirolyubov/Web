package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewObjectServlet extends HttpServlet{
	private ObjectCatalog catalog = new ObjectCatalog(); 
	static private int selectedClassId = 0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String page = req.getParameter("page");
		String classId = req.getParameter("classId");
		String objectName = req.getParameter("objectName");
		if (classId != null)
			selectedClassId = Integer.valueOf(classId).intValue();
		//if (page == null)
		//	page = "1";
		ArrayList<TObject> objects = catalog.getObjects(classId, objectName);
		ArrayList<TClass> classes = catalog.getClasses();
		req.setAttribute("objects", objects);
		req.setAttribute("classes", classes);
		req.setAttribute("objectName", objectName);
		req.getRequestDispatcher("/object_view.jsp").forward(req, resp);
	}
	
	public static String printClassesOptions(ArrayList<TClass> classes){
		String result = new String();
		if (selectedClassId == 0)
			result+="<option selected='selected' value='0'>-</option>";
		else
			result+="<option value='0'>-</option>";
		for (int i=0; i<classes.size(); i++)
		{
			TClass c = classes.get(i);
			if (c.getId() == selectedClassId)
				result+="<option selected='selected' value='"+c.getId()+"'>"+c.getName()+"</option>";
			else
				result+="<option value='"+c.getId()+"'>"+c.getName()+"</option>";
		}
		return result;
	}
}
