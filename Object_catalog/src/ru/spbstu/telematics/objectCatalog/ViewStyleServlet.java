package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	static ArrayList<TStyle> styles;
	static ArrayList<TFamily> families;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		styles = catalog.getStyles();
		families = catalog.getFamilies();
		req.getRequestDispatcher("/style/style_view.jsp").forward(req, resp);
	}
	
	public static String printStyles(){
		String result = new String();
		for (int i=0; i<styles.size(); i++)
		{
			TStyle c = styles.get(i);
			result+="<tr><td>"+c.getStyleId()+"</td>";
			result+="<td>"+c.getFamily()+"</td>";
			result+="<td>"+c.isMandatory()+"</td>";
			result+="<td>"+c.isMultiple()+"</td>";
			result+="<td><input type='button' value='Edit' onclick=\"location.href='/foo/styles/edit_style.jsp?styleId="+c.getStyleId()+"'\"></td></td>";
			result+="<td><input type='button' value='Delete' onclick=\"location.href='/foo/styles/delete_style.jsp?styleId="+c.getStyleId()+"'\"></td></tr>";
		}
		return result;
	}
	
	public static String printFamilyOptions(){
		String result = new String();
		result+="<option selected='selected' value='0'>-</option>";
		for (int i=0; i<families.size(); i++)
		{
			TFamily c = families.get(i);
			result+="<option value='"+c.getFamilyId()+"'>"+c.getName()+"</option>";
		}
		return result;
	}
}
