package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteObjectServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String objectId = req.getParameter("objectId");
		if (objectId != null) {
			catalog.deleteObject(objectId);
			req.getRequestDispatcher("/object/delete_object.jsp").forward(req, resp);
		}
	}
}
