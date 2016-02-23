import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class UsersUI extends JDialog {
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	private JList<String> list;
	JScrollPane listScroller;
	List<String> ipAddrs;
	boolean usrNameValid;

	
	
	public UsersUI() {
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

		//listModel.addElement("Placeholder");
		
		
	}


}
