package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditStyleServlet extends HttpServlet{
	static ArrayList<TFamily> families;
	static TStyle style;
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String styleId = req.getParameter("styleId");
		String familyId = req.getParameter("familyId");
		String isMandatory = req.getParameter("isMandatory");
		String isMultiple = req.getParameter("isMultiple");
		families = catalog.getFamilies();
		if (familyId!=null && isMandatory!=null && isMultiple!=null && styleId!=null){
			catalog.editStyle(styleId, familyId, isMandatory, isMultiple);
			req.getRequestDispatcher("/style/add_style.jsp").forward(req, resp);
		}
		else
		if (styleId!=null){
			style = catalog.getStyle(styleId);
			req.setAttribute("styleId", styleId);
			req.getRequestDispatcher("/style/edit_style.jsp").forward(req, resp);
		}
	}
	
	public static String printFamilyOptions(){
		String result = new String();
		for (int i=0; i<families.size(); i++)
		{
			TFamily c = families.get(i);
			if (c.getName().equals(style.getFamily()))
				result+="<option selected='selected' value='"+c.getFamilyId()+"'>"+c.getName()+"</option>";
			else
			result+="<option value='"+c.getFamilyId()+"'>"+c.getName()+"</option>";
		}
		return result;
	}
	
	public static String printMandatoryOpt(){
		String result = new String();
		if (style.isMandatory()) {
			result+="<option value='f'>is not mandatory</option>";
			result+="<option selected='selected' value='t'>is mandatory</option>";
		}
		else {
			result+="<option selected='selected' value='f'>is not mandatory</option>";
			result+="<option value='t'>is mandatory</option>";
		}
		return result;
	}

	public static String printMultipleOpt() {
		String result = new String();
		if (style.isMultiple()) {
			result+="<option value='f'>is not multiple</option>";
			result+="<option selected='selected' value='t'>is multiple</option>";
		}
		else {
			result+="<option selected='selected' value='f'>is not multiple</option>";
			result+="<option value='t'>is multiple</option>";
		}
		return result;
	}
}
