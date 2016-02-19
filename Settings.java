import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Settings {
	public JButton settingsButton;
	private FileReader in;
	private FileWriter out;
	String username;
	Settings() {
		try {
			//Read username from file
			System.out.println("Reading username file");
			in = new FileReader("chattify.cfg");
			int c;
			username = new String();
			while ((c = in.read()) != -1) {
				username += (char) c;
			}
			in.close();
			System.out.println("Retreived username '"+username+"' from config file");
			if (username.equals("")) writeNewUname(false);
		} catch (FileNotFoundException e) {
			writeNewUname(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeNewUname(false);
			}
		});
	}

	private void writeNewUname(boolean hard) {
		try {
			//Prompt user for a username
			String txt = JOptionPane.showInputDialog(new JTextField(), "Please enter username:", "New Username", JOptionPane.DEFAULT_OPTION);			
			System.out.println("Got username text: "+txt);
			//Exit if there's no username
			if (txt.equals("") && hard) System.exit(0);
			System.out.println("Writing inputted username to config file");
			out = new FileWriter("chattify.cfg");
			out.write(txt);
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();          
		} catch (NullPointerException e) {
			if (hard) System.exit(0);
		}


	}

	public String getUname() {
		try {
			System.out.println("Reading username file");
			in = new FileReader("chattify.cfg");
			int c;
			username = new String();
			while ((c = in.read()) != -1) {
				username += (char) c;
			}
			in.close();
			System.out.println("Retreived username '"+username+"' from config file");
			return username;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
