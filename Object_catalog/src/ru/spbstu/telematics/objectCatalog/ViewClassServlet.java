package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewClassServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ArrayList<TClass> classes = catalog.getClasses();
		req.setAttribute("classes", classes);
		req.getRequestDispatcher("/class/class_view.jsp").forward(req, resp);
	}
	
	public static String printClasses(ArrayList<TClass> classes){
		String result = new String();
		for (int i=0; i<classes.size(); i++) {
			result+="<tr><td>"+classes.get(i).getId()+"</td>";
			result+="<td><a href='/foo/class_styles/class_style_view.jsp?classId="+classes.get(i).getId()+"'>"+classes.get(i).getName()+"</a></td>";
			result+="<td>"+classes.get(i).getDescription()+"</td>";
			result+="<td><input type='button' value='Edit' onclick=\"location='/foo/classes/edit_class.jsp?classId="+classes.get(i).getId()+"'\"/></td>";
			result+="<td><input type='button' value='Delete' onclick=\"location='/foo/classes/delete_class.jsp?classId="+classes.get(i).getId()+"'\"/></td></tr>";
		}
		return result;
	}
}
