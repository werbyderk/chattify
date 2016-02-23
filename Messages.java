import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;

public class Messages implements KeyListener {
	public JScrollPane messageUI;
	private JTextPane txt; //Entire buffer of messages 
	public JButton send;          
	public JTextField message;
	private Border border;
	public StyledDocument doc;
	private StyleContext contxt;
	public Style style;
	public boolean toBeSent;

	public Messages(MainRunner m) {
		//Constructor
		toBeSent = false;
		contxt = new StyleContext();
		doc = new DefaultStyledDocument(contxt);
		style = contxt.getStyle(StyleContext.DEFAULT_STYLE);
		
		border = BorderFactory.createLineBorder(Color.BLACK);
		txt = new JTextPane(doc);
		doc = txt.getStyledDocument();
		message = new JTextField();
		message.addKeyListener(this);
		send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		StyleConstants.setFontSize(style, 13);
		StyleConstants.setForeground(style, Color.BLACK);
		txt.setEditable(false);
		txt.setBackground(Color.LIGHT_GRAY);
		messageUI = new JScrollPane(txt);
		messageUI.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		messageUI.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		messageUI.setViewportBorder(border);
		
		messageUI.setPreferredSize(new Dimension(400, 800));
		message.setPreferredSize(new Dimension(340, 100));
		send.setPreferredSize(new Dimension(50, 100));
		
		StyleConstants.setBold(style, true);
		StyleConstants.setForeground(style, Color.BLACK);
		StyleConstants.setUnderline(style, true);
		appendString("Welcome to Chattify v"+MainRunner.version+'\n', false);
		StyleConstants.setBold(style, false);
		StyleConstants.setUnderline(style, false);
		
		
	}
	
	public void autoscroll() {	 
		JScrollBar vscroll = messageUI.getVerticalScrollBar(); 
		int distanceToBottom = vscroll.getMaximum() - (vscroll.getValue() + vscroll.getVisibleAmount());	
		if (25 > distanceToBottom) {
			vscroll.setValue(vscroll.getMaximum());
		}
	}
	public void sendMessage() {
		
			System.out.println("Message to send: "+message.getText());
			//Client implementation
			toBeSent = true;
			StyleConstants.setForeground(style, Color.BLUE);
			appendString("Me: "+message.getText(), true);
			
	}
	
	public void appendString(String str, boolean r) {
		try {
			if (r)doc.insertString(doc.getLength(), "\n", style);
			doc.insertString(doc.getLength(), str, style);
			autoscroll();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}	
	}
	
	public void setColor(Color color) {
		StyleConstants.setForeground(style, color);
	}
	
	public void error(Exception e) {
		setColor(Color.RED);
		appendString("Error: "+e.getLocalizedMessage(), true);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && message.getText().length() > 0) {			
			sendMessage();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	
}
