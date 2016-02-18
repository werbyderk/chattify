import java.awt.Dimension;
<<<<<<< ours
<<<<<<< ours
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
=======
>>>>>>> theirs
=======
>>>>>>> theirs
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


<<<<<<< ours
<<<<<<< ours
public class UsersUI extends JDialog implements KeyListener {
=======
public class UsersUI extends JDialog {
>>>>>>> theirs
=======
public class UsersUI extends JDialog {
>>>>>>> theirs
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	private JList<String> list;
	JScrollPane listScroller;
	List<String> ipAddrs;
<<<<<<< ours
<<<<<<< ours
	JTextField username;
=======
>>>>>>> theirs
=======
>>>>>>> theirs
	boolean usrNameValid;
	Messages m;
	
	
	public UsersUI(Messages m) {
		this.m = m;
		usrNameValid = false;
		ipAddrs = new ArrayList<String>();
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Implement dialog box when double-click
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				@SuppressWarnings("unchecked")
				JList<String> theList = (JList<String>) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		        	int index = theList.locationToIndex(mouseEvent.getPoint());
		        	if (index >= 0) {
		        		Object o = theList.getModel().getElementAt(index);
		        		JOptionPane.showMessageDialog(null, o.toString()+"'s IP: "+ipAddrs.get(index), o.toString(),  JOptionPane.PLAIN_MESSAGE);
		        	}
		        }
		     }
		};
		list.addMouseListener(mouseListener);
		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(400, 500));
<<<<<<< ours
<<<<<<< ours
		username = new JTextField();
		username.addKeyListener(this);
=======

>>>>>>> theirs
=======

>>>>>>> theirs
		
		//listModel.addElement("Placeholder");
		
		
	}


<<<<<<< ours
<<<<<<< ours
	@Override
	public void keyTyped(KeyEvent e) {	
	}


	@Override
	public void keyPressed(KeyEvent e) {	
		if (e.getKeyCode() == KeyEvent.VK_ENTER && username.getText().length() > 0) usrNameValid = true;
	}


	@Override
	public void keyReleased(KeyEvent e) {		
	}
=======
>>>>>>> theirs
=======
>>>>>>> theirs
}
