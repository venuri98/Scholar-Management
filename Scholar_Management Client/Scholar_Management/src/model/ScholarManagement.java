package model;

import java.sql.*;

public class ScholarManagement {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbdb?useTimezone=true&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

	public String readscholordata() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>SID</th><th>Name</th>" + "<th>Email</th>"
					+ "<th>approvedProjectTitle</th>" + "<th>approvedDate</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from scholor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			
			while (rs.next()) {
				String sid = Integer.toString(rs.getInt("sid"));
				String name = rs.getString("name");
				String email = rs.getString("email");
				String approvedProjectTitle = rs.getString("approvedProjectTitle");
				String approvedDate = rs.getString("approvedDate");
				// Add into the html table
				output += "<td>" + sid + "</td>";			
				output += "<td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + approvedProjectTitle + "</td>";
				output += "<td>" + approvedDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + sid + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + sid + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertscholordata(String name,String email,String approvedProjectTitle,String approvedDate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into scholor (`sid`,`name`,`email`,`approvedProjectTitle`,`approvedDate`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, approvedProjectTitle);
			preparedStmt.setString(5, approvedDate);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readscholordata();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
//			output = "Inserted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatescholordata(String sid, String name,String email,String approvedProjectTitle,String approvedDate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE scholor SET name=?,email=?,approvedProjectTitle=?,approvedDate=? WHERE sid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, approvedProjectTitle);
			preparedStmt.setString(4, approvedDate);
			preparedStmt.setInt(5, Integer.parseInt(sid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readscholordata();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteResearch(String sid) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from scholor where sid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(sid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readscholordata();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	} catch (Exception e) {
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
