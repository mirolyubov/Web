package ru.spbstu.telematics.objectCatalog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewClassStyleServlet extends HttpServlet{
	ObjectCatalog catalog = new ObjectCatalog();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String classId = req.getParameter("classId");
		if (classId!= null) {
			ArrayList<TStyle> styles = catalog.getStyles(classId);
			ArrayList<TStyle> stylesAll = catalog.getStyles();
			TClass classObj = catalog.getClassObject(classId);
			req.setAttribute("styles", styles);
			req.setAttribute("stylesAll", stylesAll);
			req.setAttribute("classObj", classObj);
			req.setAttribute("classId", classId);
			req.getRequestDispatcher("/class_style/class_style_view.jsp").forward(req, resp);
		}
	}
}
