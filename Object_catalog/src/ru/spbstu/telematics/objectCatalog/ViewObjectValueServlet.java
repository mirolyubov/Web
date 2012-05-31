package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewObjectValueServlet extends HttpServlet{
	private static int selectedStyleId = 0;
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String objectId = req.getParameter("objectId");
		if (objectId != null) {
			ArrayList<TObjectValue> values = catalog.getObjectValues(objectId);
			ArrayList<TStyle> styles = catalog.getStyles();
			String objectName = catalog.getObjectName(objectId);
			req.setAttribute("values", values);
			req.setAttribute("objectId", objectId);
			req.setAttribute("objectName", objectName);
			req.setAttribute("styles", styles);
			req.getRequestDispatcher("/object/object_value_view.jsp").forward(req, resp);
		}
	}
	
	public static String printStylesOptions(ArrayList<TStyle> styles){
		String result = new String();
		result+="<option selected='selected' value='0'>-</option>";
		for (int i=0; i<styles.size(); i++)
		{
			TStyle c = styles.get(i);
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
