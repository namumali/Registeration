package user;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;

public class DbThread extends Thread{
	
	static final String DB_URL = "jdbc:mysql://remotemysql.com:3306/WmYVHRqKXV";
	static final String USER = "WmYVHRqKXV";
	static final String PASS = "h5Smc3NnpP";
	
	private JLabel statusLabel = null;
	private Connection db = null;
	private PreparedStatement pStatement = null;
	
	public DbThread(JLabel label) {
		statusLabel = label;
	}

	@Override
	public void run() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			db = DriverManager.getConnection(DB_URL, USER, PASS);
			while (db == null) {}
			if (db != null) {
				this.statusLabel.setForeground(Color.GREEN);
				this.statusLabel.setText("Connected to Database.");
			} else {
				this.statusLabel.setForeground(Color.red);
				this.statusLabel.setText("Failed to connect to db");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	public void addToDb(String name, String email, String password,
			String country, String state, String phoneNo) {
		try {
			
			String sql = String.format("INSERT INTO Registeration VALUES('%s', '%s', '%s', '%s', '%s', '%s')", 
					name, email, password, country, state, phoneNo);
			db.setAutoCommit(false);
			pStatement = db.prepareStatement(sql);
			pStatement.execute();
			db.commit();
			this.statusLabel.setText("Failed to connect to db");
			this.statusLabel.setText("Success...");
					
		} catch (SQLException e) {
			this.statusLabel.setForeground(Color.red);
			statusLabel.setText("Failed...");
			e.printStackTrace();
		}
	}
	
	public void closeDb() {
		if (this.pStatement != null)
			try {
				this.pStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		if (this.db != null)
			try {
				this.db.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}


