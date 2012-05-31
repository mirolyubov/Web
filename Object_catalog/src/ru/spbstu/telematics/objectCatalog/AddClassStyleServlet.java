package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddClassStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String styleId = req.getParameter("styleId");
		String classId = req.getParameter("classId");
		if (styleId!=null && !styleId.equals("0") && classId!=null) {
			catalog.addClassStyle(classId, styleId);
		}
		req.setAttribute("classId", classId);
		req.getRequestDispatcher("/class_style/add_class_style.jsp").forward(req, resp);
	}
}
