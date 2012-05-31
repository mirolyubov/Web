package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String styleId = req.getParameter("styleId");
		if (styleId != null)
			catalog.deleteStyle(styleId);
		req.getRequestDispatcher("/style/delete_style.jsp").forward(req, resp);
	}
}
