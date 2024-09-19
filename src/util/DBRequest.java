package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

public class DBRequest {
	private static DBConnection dbInstance = DBConnection.getInstance();
	private static Connection connection = dbInstance.connect();

	public static void createTable(String tableName, String columns, String inheritance) {
		try {
			Statement statement = connection.createStatement();
			String createTableSQL;

			if (inheritance.equals("SEQUENCE")) {
				createTableSQL = "CREATE SEQUENCE IF NOT EXISTS " + tableName;
			} else {
				createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + columns + ") " + inheritance + ";";
			}

			statement.execute(createTableSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getAll(String tableName, String condition, Object[] conditionValues) {
		String optional = condition.length() != 0 ? " WHERE " + condition : "";
		String getAllQuery = "SELECT * FROM " + tableName + optional + ";";
		ResultSet rs = null;

		try {
			PreparedStatement pstmt = connection.prepareStatement(getAllQuery);
			if (conditionValues != null) {
				for (int i = 0; i < conditionValues.length; i++) {
					pstmt.setObject(i + 1, conditionValues[i]);
				}
			}
			rs = pstmt.executeQuery();
			return rs;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean hasResults(ResultSet rs) {
		try {
			if (rs != null && rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void insert(String table, String columns, Object... values) {
		String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
		String sql = "INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				statement.setObject(i + 1, values[i]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void update(String table, int id, String columns, Object... values) {
		String[] columnArray = columns.split(",\\s*");
		String setClause = String.join(" = ?, ", columnArray) + " = ?";
		String sql = "UPDATE " + table + " SET " + setClause + " WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			for (int i = 0; i < values.length; i++) {
				statement.setObject(i + 1, values[i]);
			}
			statement.setInt(values.length + 1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void delete(String table, int id) {
		String sql = "DELETE FROM " + table + " WHERE id = ?";

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
