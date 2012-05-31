package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewFamilyServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	static ArrayList<TFamily> families;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		families = catalog.getFamilies();
		req.getRequestDispatcher("/family/family_view.jsp").forward(req, resp);
	}
	
	public static String printFamilies(){
		String result = new String();
		for (int i=0; i<families.size(); i++)
		{
			TFamily c = families.get(i);
			result+="<tr><td>"+c.getFamilyId()+"</td>";
			result+="<td>"+c.getName()+"</td>";
			result+="<td>"+c.getDescription()+"</td>";
			result+="<td><input type='button' value='Edit' onclick=\"location.href='/foo/families/edit_family.jsp?familyId="+c.getFamilyId()+"'\"></td></td>";
			result+="<td><input type='button' value='Delete' onclick=\"location.href='/foo/families/delete_family.jsp?familyId="+c.getFamilyId()+"'\"></td></tr>";
		}
		return result;
	}
}
