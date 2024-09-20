package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	public static ResultSet getAll(String tableName, String condition, Object[] conditionValues,
			boolean includeForeignKeys) {
		String optional = condition.length() != 0 ? " WHERE " + condition : "";
		String getAllQuery = "SELECT * FROM " + tableName + optional + ";";
		ResultSet rs = null;

		try {
			// Fetch data from the main table
			PreparedStatement pstmt = connection.prepareStatement(getAllQuery);
			if (conditionValues != null) {
				for (int i = 0; i < conditionValues.length; i++) {
					pstmt.setObject(i + 1, conditionValues[i]);
				}
			}
			rs = pstmt.executeQuery();

			// If foreign key data should be included
			if (includeForeignKeys) {
				List<String> foreignKeyTables = getForeignKeyTables(tableName);
				if (!foreignKeyTables.isEmpty()) {
					// Build the JOIN queries for foreign key tables
					StringBuilder joinQuery = new StringBuilder("SELECT * FROM " + tableName);
					for (String fkTable : foreignKeyTables) {
						joinQuery.append(" LEFT JOIN ").append(fkTable).append(" ON ").append(tableName)
								.append(".id = ").append(fkTable).append(".").append(tableName).append("_id");
					}
					joinQuery.append(" WHERE ").append(condition);

					// Execute the join query
					pstmt = connection.prepareStatement(joinQuery.toString());
					if (conditionValues != null) {
						for (int i = 0; i < conditionValues.length; i++) {
							pstmt.setObject(i + 1, conditionValues[i]);
						}
					}
					rs = pstmt.executeQuery();
				}
			}

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

	private static List<String> getForeignKeyTables(String tableName) {
		List<String> foreignKeyTables = new ArrayList<>();
		String query = "SELECT DISTINCT tc.table_name " + "FROM information_schema.key_column_usage AS kcu "
				+ "JOIN information_schema.table_constraints AS tc " + "ON kcu.constraint_name = tc.constraint_name "
				+ "WHERE tc.constraint_type = 'FOREIGN KEY' " + "AND kcu.table_name = ?";

		try (PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, tableName);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					foreignKeyTables.add(rs.getString("table_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foreignKeyTables;
	}

}
