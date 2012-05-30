package ru.spbstu.telematics.objectCatalog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class ObjectCatalog {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("nothing more to do, going to die", e);
		}
	}

	private Connection getConnection() {
		try {
			Properties props = new Properties();
			props.setProperty("user", "kite");
			props.setProperty("password", "l0stman");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost/object_catalog", props);
			return con;
		} catch (SQLException e) {
			throw new RuntimeException("cannot initialize connection", e);
		}
	}

	public ArrayList<TObject> getObjects(String classId) {
		ArrayList<TObject> result = new ArrayList<TObject>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			if (classId!=null && !classId.equals("0")) {
				ps = con.prepareStatement("select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id and t_object.class_id=?");
				ps.setInt(1, Integer.valueOf(classId));
			}
			else
				ps = con.prepareStatement("select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id"); 
			// select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id limit 10 offset 10
			rs = ps.executeQuery();
			while(rs.next()) {
				TObject r = fetchObject(rs);
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}
	
	public ArrayList<TClass> getClasses() {
		ArrayList<TClass> result = new ArrayList<TClass>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select class_id, name, description from t_class");
			// select class_id, name, description from t_class;
			rs = ps.executeQuery();
			while(rs.next()) {
				TClass r = fetchClass(rs);
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}

	private TObject fetchObject(ResultSet rs) throws SQLException {
		int objId = rs.getInt("object_id");
		String objName = rs.getString("object_name");
		String className = rs.getString("class_name");
		TObject o = new TObject(objId, objName, className);
		return o;
	}
	
	private TClass fetchClass(ResultSet rs) throws SQLException {
		int classId = rs.getInt("class_id");
		String className = rs.getString("name");
		String classDesc = rs.getString("description");
		TClass c = new TClass(classId, className, classDesc);
		return c;
	}
}
