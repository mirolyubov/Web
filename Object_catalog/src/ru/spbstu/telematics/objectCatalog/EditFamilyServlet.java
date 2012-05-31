package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFamilyServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String familyId = req.getParameter("familyId");
		String familyName = req.getParameter("familyName");
		String description = req.getParameter("description");
		if (familyId!=null && familyName!=null && description!=null && !familyName.equals("") && !description.equals("")) {
			catalog.editFamily(familyId, familyName, description);
			req.getRequestDispatcher("/family/add_family.jsp").forward(req, resp);
		}
		else
		if (familyId != null) {
			TFamily familyObj = catalog.getFamily(familyId);
			req.setAttribute("familyObj", familyObj);
			req.setAttribute("familyId", familyId);
			req.getRequestDispatcher("/family/edit_family.jsp").forward(req, resp);
		}
	}
}
