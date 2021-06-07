
package user;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;

public class Registeration extends JFrame {

    private static final long serialVersionUID = 1L;

    JLabel lTitle, lName, lEmail, lPasswd, lConPasswd, lCountry, lState, lPhone, lMsg;
    JTextField txtName, txtEmail, txtCountry, txtState, txtPhone;
    JButton btnSubmit, btnClear;
    JPasswordField pfPasswd, pfConPasswd;
    Font defFont = new Font("Poppins", Font.PLAIN, 16);
    Color white = Color.white;
    private DbThread dbThread = null;
    
    private void initWidgets() {
        lMsg = new JLabel("Connecting to Database...");
        lMsg.setBounds(50, 400, 400, 30);
        lMsg.setFont(defFont);
        lMsg.setForeground(white);
        add(lMsg);

        lTitle = new JLabel("Registeration form");
        lTitle.setForeground(Color.cyan);
        lTitle.setFont(new Font("Poppins", Font.PLAIN, 22));
        lTitle.setBounds(100, 30, 400, 30);
        add(lTitle);

        lName = new JLabel("Name :");
        lName.setBounds(80, 70, 200, 30);
        lName.setFont(defFont);
        lName.setForeground(white);
        add(lName);
        txtName = new JTextField();
        txtName.setFont(defFont);
        txtName.setBounds(300, 70, 200, 30);
        add(txtName);

        lEmail = new JLabel("Email :");
        lEmail.setBounds(80, 110, 200, 30);
        lEmail.setFont(defFont);
        lEmail.setForeground(white);
        add(lEmail);
        txtEmail = new JTextField();
        txtEmail.setFont(defFont);
        txtEmail.setBounds(300, 110, 200, 30);
        add(txtEmail);

        lPasswd = new JLabel("Password :");
        lPasswd.setBounds(80, 150, 200, 30);
        lPasswd.setFont(defFont);
        lPasswd.setForeground(white);
        add(lPasswd);
        pfPasswd = new JPasswordField();
        pfPasswd.setFont(defFont);
        pfPasswd.setBounds(300, 150, 200, 30);
        add(pfPasswd);

        lConPasswd = new JLabel("Confirm Password :");
        lConPasswd.setBounds(80, 190, 200, 30);
        lConPasswd.setFont(defFont);
        lConPasswd.setForeground(white);
        add(lConPasswd);
        pfConPasswd = new JPasswordField();
        pfConPasswd.setFont(defFont);
        pfConPasswd.setBounds(300, 190, 200, 30);
        add(pfConPasswd);

        lCountry = new JLabel("Country :");
        lCountry.setBounds(80, 230, 200, 30);
        lCountry.setFont(defFont);
        lCountry.setForeground(white);
        add(lCountry);
        txtCountry = new JTextField();
        txtCountry.setFont(defFont);
        txtCountry.setBounds(300, 230, 200, 30);
        add(txtCountry);

        lState = new JLabel("State :");
        lState.setBounds(80, 270, 200, 30);
        lState.setFont(defFont);
        lState.setForeground(white);
        add(lState);
        txtState = new JTextField();
        txtState.setFont(defFont);
        txtState.setBounds(300, 270, 200, 30);
        add(txtState);

        lPhone = new JLabel("Phone no :");
        lPhone.setBounds(80, 310, 200, 30);
        lPhone.setFont(defFont);
        lPhone.setForeground(white);
        add(lPhone);
        txtPhone = new JTextField();
        txtPhone.setFont(defFont);
        txtPhone.setBounds(300, 310, 200, 30);
        add(txtPhone);

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(defFont);
        btnSubmit.setBackground(Color.green);
        btnSubmit.addActionListener(e-> { this.onSubmitPressed(); });
        btnSubmit.setBounds(50, 350, 100, 30);
        add(btnSubmit);

        btnClear = new JButton("Clear");
        btnClear.setFont(defFont);
        btnClear.addActionListener(e -> { this.onClearPressed(); });
        btnClear.setBounds(170, 350, 100, 30);
        add(btnClear);

    }

    Registeration() {
        initWidgets();
        setSize(700, 700);
        setTitle("Java Registeration form");
        dbThread = new DbThread(lMsg);
        dbThread.start();
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		if (dbThread != null) {
					dbThread.closeDb();
					System.out.println("Closed the db connection..");
        		}
        		super.windowClosing(e);
        	}
        });
        
    }
    
    private void onSubmitPressed() {
    	dbThread.addToDb(
    			txtName.getText(),
    			txtEmail.getText(),
    			pfPasswd.getPassword().toString(),
    			txtCountry.getText(),
    			txtState.getText(),
    			txtPhone.getText()
    			);
    }
    
    private void onClearPressed () {
    	txtName.setText("");
    	txtEmail.setText("");
    	pfPasswd.setText("");
    	pfConPasswd.setText("");
    	txtCountry.setText("");
    	txtState.setText("");
    	txtPhone.setText("");
    }

}
