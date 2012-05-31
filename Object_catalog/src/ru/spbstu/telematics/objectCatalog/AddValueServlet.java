package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddValueServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String objectId = req.getParameter("objectId");
		String styleId = req.getParameter("styleId");
		String value = req.getParameter("value");
		if (objectId != null && styleId != null && value != null && !styleId.equals("0")) {
			catalog.addValue(objectId, styleId, value);
		}
		if (objectId != null) {
			req.setAttribute("objectId", objectId);
			req.getRequestDispatcher("/value/add_value.jsp").forward(req, resp);
		}
	}
}
