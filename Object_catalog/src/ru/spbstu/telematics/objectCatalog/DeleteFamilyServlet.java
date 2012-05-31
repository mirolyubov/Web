package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFamilyServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String familyId = req.getParameter("familyId");
		if (familyId!=null)
			catalog.deleteFamily(familyId);
		req.getRequestDispatcher("/family/delete_family.jsp").forward(req, resp);
	}
}
