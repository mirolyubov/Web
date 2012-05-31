package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFamilyServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String familyName = req.getParameter("familyName");
		String description = req.getParameter("description");
		if (familyName!=null && !familyName.equals("") && description!=null && !description.equals("")){
			catalog.addFamily(familyName, description);
		}
		req.getRequestDispatcher("/family/add_family.jsp").forward(req, resp);
	}
}
