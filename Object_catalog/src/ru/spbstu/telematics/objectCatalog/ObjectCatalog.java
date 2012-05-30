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
			clean(ps, rs, con);
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
			clean(ps, rs, con);
		}
		return result;
	}

	private void clean(PreparedStatement ps, ResultSet rs, Connection con) {
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
	
	public ArrayList<TObjectValue> getObjectValues(String objectId) {
		ArrayList<TObjectValue> result = new ArrayList<TObjectValue>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select object_name, object_value_id, value, is_mandatory, is_multiple from t_object_value, t_object, t_style where t_object.object_id=t_object_value.object_id and t_object_value.style_id=t_style.style_id and t_object_value.object_id=?");
			ps.setInt(1, Integer.valueOf(objectId));
			rs = ps.executeQuery();
			while(rs.next()) {
				TObjectValue r = fetchObjectValue(rs);
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		return result;
	}
	
	public ArrayList<TStyle> getStyles() {
		ArrayList<TStyle> result = new ArrayList<TStyle>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select style_id, is_mandatory, is_multiple, name as family_name from t_style, t_family where t_style.family_id=t_family.family_id"); 
			//ps.setInt(1, Integer.valueOf(objectId));
			rs = ps.executeQuery();
			while(rs.next()) {
				TStyle r = fetchStyle(rs);
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
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
	
	private TStyle fetchStyle(ResultSet rs) throws SQLException {
		int styleId = rs.getInt("style_id");
		String family = rs.getString("family_name");
		boolean isMandatory = rs.getBoolean("is_mandatory");
		boolean isMultiple = rs.getBoolean("is_multiple");
		TStyle s = new TStyle(styleId, family, isMandatory, isMultiple);
		return s;
	}
	
	private TClass fetchClass(ResultSet rs) throws SQLException {
		int classId = rs.getInt("class_id");
		String className = rs.getString("name");
		String classDesc = rs.getString("description");
		TClass c = new TClass(classId, className, classDesc);
		return c;
	}
	
	private TObjectValue fetchObjectValue(ResultSet rs) throws SQLException {
		int valueId = rs.getInt("object_value_id");
		String value = rs.getString("value");
		boolean isMandatory = rs.getBoolean("is_mandatory");
		boolean isMultiple = rs.getBoolean("is_multiple");
		String objectName = rs.getString("object_name");
		TObjectValue v = new TObjectValue(valueId, value, isMultiple, isMandatory, objectName);
		return v;
	}
	
	public void deleteObject(String objectId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_object_value where object_id=?");
			ps.setInt(1, Integer.valueOf(objectId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_object where object_id=?");
			ps.setInt(1, Integer.valueOf(objectId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void addObject(String objectName, String classId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int lastId = 0;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select object_id from t_object order by object_id desc limit 1 offset 0");
			rs = ps.executeQuery();
			rs.next();
			lastId = rs.getInt("object_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_object (object_id, object_name, class_id) values ('"+(lastId+1)+"', ?, ?)");
			ps.setString(1, objectName);
			ps.setInt(2, Integer.valueOf(classId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
}
