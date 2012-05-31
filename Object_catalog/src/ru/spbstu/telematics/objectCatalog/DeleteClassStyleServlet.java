package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteClassStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String styleId = req.getParameter("styleId");
		String classId = req.getParameter("classId");
		if (styleId!=null && classId!=null) {
			catalog.deleteClassStyle(classId, styleId);
		}
		req.setAttribute("classId", classId);
		req.getRequestDispatcher("/class_style/delete_class_style.jsp").forward(req, resp);
	}
}
