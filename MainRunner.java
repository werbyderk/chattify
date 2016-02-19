import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;


public class MainRunner extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String version = "0.1.2.1";
	private static final String title = "Chattify v"+version;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 450;
	private UsersUI usrUI;
	private Messages msgs;
	//Interface declarations
	private GridBagConstraints c;
	public JPanel panel;
	private Settings stngs;

	
	public MainRunner() {
		//**Constructor**
		stngs = new Settings();
		msgs = new Messages(this);
		usrUI = new UsersUI(msgs);
		try {
			new Client(msgs, stngs);
		} catch (SocketException e) {
			msgs.error(e);
			e.printStackTrace();
		}
		c = new GridBagConstraints();
		panel = new JPanel(new GridBagLayout());
		panel.setSize(WIDTH, HEIGHT);
		
		try {
			new Server(msgs, usrUI, false);
		} catch (IOException e) {
			msgs.error(e);
			e.printStackTrace();
		}
		
		//UI implementation
		c.insets = new Insets(3, 3, 3, 3); //External padding
		
		//UserUI listScroller
		c.weightx = 0.7;
		c.weighty = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 2;
		c.gridheight = 2; 
		c.fill = GridBagConstraints.BOTH;
		draw(usrUI.listScroller, 0, 0);
		
		//Messages messageUI
		c.weightx = 1;
		c.weighty = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		draw(msgs.messageUI, 3, 0);
		
		//Messages message textfield
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = .1;
		c.weighty = 0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		draw(msgs.message, 3, 2);
		
		//Message send button
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0;
		c.weighty = 0;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.fill = GridBagConstraints.HORIZONTAL;
		draw(msgs.send, 5, 2);
		
		//Username text field
		c.gridwidth = 2;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.08;
		c.weighty = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		draw(stngs.settingsButton, 0, 2);
		add(panel);
		
		//JFrame properties
		setSize(WIDTH, HEIGHT);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		new MainRunner();
	}
	public void draw(Component comp, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		panel.add(comp, c);
	}
	
	public int getGridX() { return c.gridx; }
	public void setGridX(int x) { c.gridx = x; }
	public int getGridY() { return c.gridy; }
	public void setGridY(int y) { c.gridy = y; }

}
