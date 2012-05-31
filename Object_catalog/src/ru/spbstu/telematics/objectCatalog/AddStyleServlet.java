package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String familyId = req.getParameter("familyId");
		String isMandatory = req.getParameter("isMandatory");
		String isMultiple = req.getParameter("isMultiple");
		
		if (familyId!=null && !familyId.equals("0") && isMandatory!=null && isMultiple!=null) {
			catalog.addStyle(familyId, isMandatory, isMultiple);
		}
		req.getRequestDispatcher("/style/add_style.jsp").forward(req, resp);
	}
}
