package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddClassServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String className = req.getParameter("className");
		String description = req.getParameter("description");
		if (className != null && !className.equals("") && description != null && !description.equals("")){
			catalog.addClass(className, description);
		}
		req.getRequestDispatcher("/class/add_class.jsp").forward(req, resp);
	}
	
}
