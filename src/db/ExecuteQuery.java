package db;

import java.sql.ResultSet;
import java.sql.Statement;

public class ExecuteQuery {
	protected boolean executeQuery(DataBase db, String sql) {
		db.connect();
		try {
			Statement stmt = db.getC().createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			db.disconnect();
			e.printStackTrace();
			return false;
		}
		db.disconnect();
		return true;
	}
}
