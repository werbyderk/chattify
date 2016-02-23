import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings {
	public JButton settingsButton;
	private BufferedReader in;
	private BufferedWriter out;
	public int refreshTime;
	private String readIn;
	private String username;
	private File config;
	
	Settings() {
		try {
			config = new File("chattify.cfg");
			in = new BufferedReader(new InputStreamReader(new FileInputStream(config)));
			//Read username and refresh rate from file
			System.out.println("Reading username/refresh rate file");
			username = new String();
			
			username = in.readLine();
			System.out.println("Retreived username '"+username+"' from config file");
			if (username.equals("")) writeNewUname(false);
			readIn = in.readLine();
			refreshTime = Integer.parseInt(readIn);
			System.out.println("Refresh time: "+refreshTime);
		} catch (FileNotFoundException | NullPointerException e) {
			writeNewUname(true);
			try {
				out.write("500");
				refreshTime = 500;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			refreshTime = 500;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config)));
				out.write("500");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//writeNewUname(false);
				JFrame setWindow = new JFrame();
				JTextField refrsh = new JTextField();
				JTextField uname = new JTextField("New username");
				JButton apply = new JButton("Apply");
				JPanel panel = new JPanel();
				refrsh.setPreferredSize(new Dimension(80, 25));
				uname.setPreferredSize(new Dimension(120, 25));
				setWindow.setTitle("Settings");
				setWindow.setSize(450, 100);
				panel.setSize(new Dimension(setWindow.getWidth(), setWindow.getHeight()));
				panel.add(new JLabel("Discovery refresh rate:"));
				panel.add(refrsh);
				panel.add(new JLabel("ms"));
				panel.add(uname);
				panel.add(apply);
				setWindow.add(panel);
				setWindow.setResizable(false);
				setWindow.setVisible(true);	
				apply.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Applying settings");
						try {
							out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config)));
							//Save username 
							if (uname.getText() != "") {;
								out.write(uname.getText());
								out.newLine();
								uname.setText("");
							} else {
								uname.setText("Error");
							}
							//Apply new refresh rate
							refreshTime = Integer.parseInt(refrsh.getText());
							out.write(refrsh.getText());
							refrsh.setText("");
							System.out.println("Settings applied");
							in.close();
							out.flush();
							out.close();
						} catch (IOException e1) {
							uname.setText("Error");
							e1.printStackTrace();
						} catch (NumberFormatException e2) {
							refrsh.setText("Error");
							e2.printStackTrace();
						}
					}
				});
			}
		});
	}

	private void writeNewUname(boolean hard) {
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(config)));
			//Prompt user for a username
			String txt = JOptionPane.showInputDialog(new JTextField(), "Please enter username:", "New Username", JOptionPane.DEFAULT_OPTION);			
			System.out.println("Got username text: "+txt);
			//Exit if there's no username
			if (txt.equals("") && hard) System.exit(0);
			System.out.println("Writing inputted username to config file");
			out.write(txt);
			out.newLine();
			out.write("500");
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();          
		} catch (NullPointerException e) {
			if (hard) System.exit(0);
		}


	}

	public String getUname() {
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(config)));
			System.out.println("Reading username file");
			in.readLine();
			System.out.println("Retreived username '"+username+"' from config file");
			in.close();
			return username;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
