package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteValueServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String valueId = req.getParameter("valueId");
		String objectId = req.getParameter("objectId");
		if (valueId != null) {
			catalog.deleteValue(valueId);
		}
		if (objectId != null) {
			req.setAttribute("objectId", objectId);
			req.getRequestDispatcher("/value/delete_value.jsp").forward(req, resp);
		}
	}
}
