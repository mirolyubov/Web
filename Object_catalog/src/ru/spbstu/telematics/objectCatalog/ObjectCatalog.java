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
			props.setProperty("user", "ilya");
			props.setProperty("password", "ilya");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost/ilya", props);
			return con;
		} catch (SQLException e) {
			throw new RuntimeException("cannot initialize connection", e);
		}
	}

	public ArrayList<TObject> getObjects(String classId, String objectName) {
		ArrayList<TObject> result = new ArrayList<TObject>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			if ((classId!=null && !classId.equals("0")) || (objectName!=null && !objectName.equals(""))) {
				if (classId!=null && !classId.equals("0") && objectName!=null && !objectName.equals("")) {
					ps = con.prepareStatement("select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id and t_object.class_id=? and object_name=?");
					ps.setInt(1, Integer.valueOf(classId));
					ps.setString(2, objectName);
				}
				else {
					if (classId!=null && !classId.equals("0")) {
						ps = con.prepareStatement("select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id and t_object.class_id=?");
						ps.setInt(1, Integer.valueOf(classId));
					}
					else 
						if (objectName!=null && !objectName.equals("")) {
							ps = con.prepareStatement("select object_id, object_name, name as class_name from t_object, t_class where t_object.class_id=t_class.class_id and object_name=?");
							ps.setString(1, objectName);
						}
				}
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
	
	public ArrayList<TFamily> getFamilies() {
		ArrayList<TFamily> result = new ArrayList<TFamily>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select family_id, name, description from t_family");
			rs = ps.executeQuery();
			while(rs.next()) {
				TFamily r = fetchFamily(rs);
				result.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		return result;
	}
	
	public TClass getClassObject(String classId) {
		TClass result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select class_id, name, description from t_class where class_id=?");
			ps.setInt(1, Integer.valueOf(classId));
			rs = ps.executeQuery();
			rs.next(); 
			result = fetchClass(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		return result;
	}
	
	public TFamily getFamily(String familyId) {
		TFamily result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select family_id, name, description from t_family where family_id=?");
			ps.setInt(1, Integer.valueOf(familyId));
			rs = ps.executeQuery();
			rs.next(); 
			result = fetchFamily(rs);
			
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
	
	public TStyle getStyle(String styleId) {
		TStyle result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select style_id, is_mandatory, is_multiple, name as family_name from t_style, t_family where t_style.family_id=t_family.family_id and style_id=?"); 
			ps.setInt(1, Integer.valueOf(styleId));
			rs = ps.executeQuery();
			rs.next(); 
			result = fetchStyle(rs);
			
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
	
	public ArrayList<TStyle> getStylesForFamily(String familyId) {
		ArrayList<TStyle> result = new ArrayList<TStyle>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select style_id, is_mandatory, is_multiple, name as family_name from t_style, t_family where t_style.family_id=t_family.family_id and t_style.family_id=?"); 
			ps.setInt(1, Integer.valueOf(familyId));
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
	
	public ArrayList<TStyle> getStyles(String classId) {
		ArrayList<TStyle> result = new ArrayList<TStyle>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select t_style.style_id, is_mandatory, is_multiple, name as family_name from t_style, t_family, t_class_style where t_style.family_id=t_family.family_id and t_class_style.style_id=t_style.style_id and class_id=?"); 
			ps.setInt(1, Integer.valueOf(classId));
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
	
	public String getObjectName(String objectId) {
		String result = new String();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select object_name from t_object where object_id=?");
			ps.setInt(1, Integer.valueOf(objectId));
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString("object_name");
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
	
	private TFamily fetchFamily(ResultSet rs) throws SQLException {
		int familyId = rs.getInt("family_id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		TFamily f = new TFamily(familyId, name, description);
		return f;
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
	
	public void deleteClass(String classId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		//// Deleting t_class_style links
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_class_style where class_id=?");
			ps.setInt(1, Integer.valueOf(classId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		//// Deleting t_object and t_object_values links
		ArrayList<TObject> objectLinks = getObjects(classId, null);
		for (int i=0; i<objectLinks.size(); i++) {
			deleteObject(Integer.toString(objectLinks.get(i).getId()));
		}
		
		//// Deleting class
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_class where class_id=?");
			ps.setInt(1, Integer.valueOf(classId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void deleteFamily(String familyId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		
		//// Deleting t_style and etc
		ArrayList<TStyle> styleLinks = getStylesForFamily(familyId);
		for (int i=0; i<styleLinks.size(); i++) {
			deleteStyle(Integer.toString(styleLinks.get(i).getStyleId()));
		}
		
		//// Deleting style
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_family where family_id=?");
			ps.setInt(1, Integer.valueOf(familyId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
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
	
	public void deleteValue(String valueId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_object_value where object_value_id=?");
			ps.setInt(1, Integer.valueOf(valueId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void deleteClassStyle(String classId, String styleId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_class_style where class_id=? and style_id=?");
			ps.setInt(1, Integer.valueOf(classId));
			ps.setInt(2, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void deleteStyle(String styleId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_class_style where style_id=?");
			ps.setInt(1, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_object_value where style_id=?");
			ps.setInt(1, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("delete from t_style where style_id=?");
			ps.setInt(1, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void editClass(String classId, String className, String description){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("update t_class set (name, description) = (?, ?) where class_id=?");
			// update t_class set (name, description) = ('myclass1', 'mydesc1') where class_id=4
			ps.setString(1, className);
			ps.setString(2, description);
			ps.setInt(3, Integer.valueOf(classId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void editFamily(String familyId, String familyName, String description){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("update t_family set (name, description) = (?, ?) where family_id=?");
			// update t_class set (name, description) = ('myclass1', 'mydesc1') where class_id=4
			ps.setString(1, familyName);
			ps.setString(2, description);
			ps.setInt(3, Integer.valueOf(familyId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void editClassStyle(String classId, String styleId, String newStyleId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("update t_class_style set style_id=?  where class_id=? and style_id=?");
			// update t_class_style set style_id = 3 where class_id=4 and style_id=4
			ps.setInt(1, Integer.valueOf(newStyleId));
			ps.setInt(2, Integer.valueOf(classId));
			ps.setInt(3, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void addClass(String className, String description){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int lastId = 0;
		try {					// Get last index
			con  = getConnection();
			ps = con.prepareStatement("select class_id from t_class order by class_id desc limit 1 offset 0");
			rs = ps.executeQuery();
			rs.next();
			lastId = rs.getInt("class_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_class (class_id, name, description) values ('"+(lastId+1)+"', ?, ?)");
			ps.setString(1, className);
			ps.setString(2, description);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void addFamily(String familyName, String description){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int lastId = 0;
		try {					// Get last index
			con  = getConnection();
			ps = con.prepareStatement("select family_id from t_family order by family_id desc limit 1 offset 0");
			rs = ps.executeQuery();
			rs.next();
			lastId = rs.getInt("family_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_family (family_id, name, description) values ('"+(lastId+1)+"', ?, ?)");
			ps.setString(1, familyName);
			ps.setString(2, description);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void addClassStyle(String classId, String styleId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_class_style (class_id, style_id) values (?, ?)");
			ps.setInt(1, Integer.valueOf(classId));
			ps.setInt(2, Integer.valueOf(styleId));
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
	
	public void addValue(String objectId, String styleId, String value){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int lastId = 0;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select object_value_id from t_object_value order by object_value_id desc limit 1 offset 0");
			rs = ps.executeQuery();
			rs.next();
			lastId = rs.getInt("object_value_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_object_value (object_value_id, object_id, style_id, value) values ('"+(lastId+1)+"', ?, ?, ?)");
			ps.setInt(1, Integer.valueOf(objectId));
			ps.setInt(2, Integer.valueOf(styleId));
			ps.setString(3, value);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void addStyle(String familyId, String isMandatory, String isMultiple){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		int lastId = 0;
		try {
			con  = getConnection();
			ps = con.prepareStatement("select style_id from t_style order by style_id desc limit 1 offset 0");
			rs = ps.executeQuery();
			rs.next();
			lastId = rs.getInt("style_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
		
		try {
			con  = getConnection();
			ps = con.prepareStatement("insert into t_style (style_id, family_id, is_mandatory, is_multiple) values ('"+(lastId+1)+"', ?, '"+isMandatory+"', '"+isMultiple+"')");
			//insert into t_style (style_id, family_id, is_mandatory, is_multiple) values ('55', '2', 'f', 'f')
			ps.setInt(1, Integer.valueOf(familyId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
	public void editStyle(String styleId, String familyId, String isMandatory, String isMultiple){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con  = getConnection();
			ps = con.prepareStatement("update t_style set (family_id, is_mandatory, is_multiple) = (?, '"+isMandatory+"', '"+isMultiple+"') where style_id=?");
			// update t_style set (family_id, is_mandatory, is_multiple) = ('1', 't', 'f') where style_id=15;
			ps.setInt(1, Integer.valueOf(familyId));
			ps.setInt(2, Integer.valueOf(styleId));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clean(ps, rs, con);
		}
	}
	
}
