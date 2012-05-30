package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddObjectServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String objectName = req.getParameter("objectName");
		String classId = req.getParameter("classId");
		if (objectName != null && classId != null) {
			catalog.addObject(objectName, classId);
			req.getRequestDispatcher("/object/add_object.jsp").forward(req, resp);
		}
	}
}
