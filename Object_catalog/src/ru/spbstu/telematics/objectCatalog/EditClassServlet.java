package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditClassServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String classId = req.getParameter("classId");
		String className = req.getParameter("className");
		String description = req.getParameter("description");
		boolean flag = false;
		if (className!=null && description!=null && !className.equals("") && !description.equals("")) {
			catalog.editClass(classId, className, description);
			req.getRequestDispatcher("/class/add_class.jsp").forward(req, resp);
			flag=true;
		}
		else
		if (classId != null && !flag) {
			TClass classObj = catalog.getClassObject(classId);
			req.setAttribute("classObj", classObj);
			req.setAttribute("classId", classId);
			req.getRequestDispatcher("/class/edit_class.jsp").forward(req, resp);
		}
		
	}
}
