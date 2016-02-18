import java.awt.Color;
import java.io.*;
import java.net.*;

import javax.swing.text.StyleConstants;

public class Server implements Runnable {
	private MulticastSocket socket;
	private static ServerSocket servSocket;
	private static final String GROUP_ADDR = "228.5.6.7";
	private static final int LOCAL_UDP_PORT = 2456;
	public static final int LOCAL_TCP_PORT = 2457;
	public String username;
	private DatagramPacket packet;
	private Messages m;
	private String message;
	private boolean isChild;
	private UsersUI usr;
	private int loc;
<<<<<<< ours
<<<<<<< ours
=======
	private BufferedReader in;
	private DataOutputStream out;
>>>>>>> theirs
=======
	private BufferedReader in;
	private DataOutputStream out;
>>>>>>> theirs
	public Server(Messages m, UsersUI usr, boolean isChild) throws IOException {
		socket = new MulticastSocket(LOCAL_UDP_PORT);
		InetAddress group = InetAddress.getByName(GROUP_ADDR);
		socket.joinGroup(group);
		this.m = m;
		this.usr = usr;
		this.isChild = isChild;
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("Server run method invoked");
		if (!isChild) {
			m.message.setEnabled(false);
			m.send.setEnabled(false);
			m.message.setText("No connected users");
			StyleConstants.setForeground(m.style, Color.BLACK);
			m.appendString("Searching for users...", false);
		}

		//Receive packet
		byte[] data;
		byte[] buff = new byte[1024];
		packet = new DatagramPacket(buff, buff.length);
		System.out.println("Server waiting for UDP packet...");

		while (true) {
			try {
				socket.receive(packet);
				data = packet.getData();
				if (data[0] == NetConstants.CLIENT_AWAITING_REQUEST[0]) {
					if (!packet.getAddress().getHostAddress().equals(InetAddress.getLocalHost().getHostAddress())) {
						//If the packet is a request packet, and isn't from localhost
						break;
					} else {
						System.out.println("Received localhost packet. Ignoring.");
						Thread.sleep(3000);
					} 
				} else {
					System.out.println("Corrupted or incorrect packet");
					Thread.sleep(3000);
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				m.error(e);		
			}
		}
		System.out.println("Server received UDP packet");

		try {

			data = packet.getData();
			//If first byte is a request byte & not localhost

			//Ensure client connecting isn't on same IP as a connected client
			boolean isAlreadyConnected = false;
			for (String s : usr.ipAddrs) {
				System.out.println("Comparing "+s+" to "+packet.getAddress().getHostAddress());
				if (s.equals(packet.getAddress().getHostAddress())) isAlreadyConnected = true;
			}
			if (isAlreadyConnected) {
				System.out.println("Client attempting to connect is already connected");
				packet.setData(NetConstants.SERVER_REQUEST_DENIED);
				socket.send(packet);
				System.out.println("Creating new child server");
				Thread.sleep(3000);
				new Server(m, usr, true);
				return;
			}

			System.out.println("Request to connect received by remote client. Granting connection");
			data[0] = NetConstants.SERVER_REQUEST_GRANTED[0];
			packet.setData(data);
			socket.send(packet);

			System.out.println("Waiting for TCP connection...");
			if (!isChild) {
				StyleConstants.setForeground(m.style, Color.BLACK);
				m.appendString("connecting...", false);
			}
			//Wait for TCP connection
			if (!isChild) servSocket = new ServerSocket(LOCAL_TCP_PORT);
			Socket connSocket = servSocket.accept();
			System.out.println("TCP connection established.");
<<<<<<< ours
<<<<<<< ours
			BufferedReader in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));

=======
			in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			out = new DataOutputStream(connSocket.getOutputStream());
>>>>>>> theirs
=======
			in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			out = new DataOutputStream(connSocket.getOutputStream());
>>>>>>> theirs
			//First TCP message will be username
			if (!isChild) {
				StyleConstants.setForeground(m.style, Color.BLACK);
				m.appendString("awaiting username...", false);
			}
			System.out.println("Server waiting for username...");
			username = in.readLine();
			System.out.println("Server received username '" + username + "'");
<<<<<<< ours
<<<<<<< ours
			//TODO: validate username isn't similar to other connected usernames
=======

			//Validate username isn't similar to other connected usernames
>>>>>>> theirs
			String existingUsrname;
			System.out.println("Server validating username...");
			//TODO: code cleanup
			if (usr.listModel.size() == 0) {
				System.out.println("Username is OK, allowing access");
				out.writeBytes(NetConstants.SERVER_REQUEST_GRANTED.toString()+'\n');
				//Write IP to userui
				usr.listModel.addElement(username);
				usr.ipAddrs.add(connSocket.getInetAddress().getHostAddress());
				loc = usr.ipAddrs.size() - 1;
				System.out.println("Server adding IP to array @ " + loc);
				if (!isChild) {
					m.setColor(Color.BLACK);
					m.appendString("done.\n", false);
					//TODO: make sure client is connected before enabling
					m.message.setEnabled(true);
					m.message.setText("");
					m.send.setEnabled(true);
				}
			} else {
				for (int i = 0; i < usr.listModel.size()+1; i++) {
					existingUsrname = usr.listModel.elementAt(i);
					System.out.println("Checking '"+username+"' against '"+existingUsrname+"'");
					if (username.equals(existingUsrname)) {
						System.out.println("User trying to connect has identical username!");
						out.writeBytes(NetConstants.SERVER_REQUEST_DENIED.toString()+"\n");
						new Server(m, usr, true);
						return;
					} else { 
						System.out.println("Username is OK. Sending permission to continue");
						out.writeBytes(NetConstants.SERVER_REQUEST_GRANTED.toString()+'\n');
						//Write IP to userui
						usr.listModel.addElement(username);
						usr.ipAddrs.add(connSocket.getInetAddress().getHostAddress());
						loc = usr.ipAddrs.size() - 1;
						System.out.println("Server adding IP to array @ " + loc);
						if (!isChild) {
							m.setColor(Color.BLACK);
							m.appendString("done.\n", false);
							//TODO: make sure client is connected before enabling
							m.message.setEnabled(true);
							m.message.setText("");
							m.send.setEnabled(true);

						}

					}
				}
<<<<<<< ours
			}
			usr.listModel.addElement(username);
			usr.ipAddrs.add(connSocket.getInetAddress().getHostAddress());
			loc = usr.ipAddrs.size() - 1;
			System.out.println("Server adding IP to array @ " + loc);
			if (!isChild) {
				m.setColor(Color.BLACK);
				m.appendString("done.\n", false);
				m.message.setEnabled(true);
				m.message.setText("");
				m.send.setEnabled(true);

=======

			//Validate username isn't similar to other connected usernames
			String existingUsrname;
			System.out.println("Server validating username...");
			//TODO: code cleanup
			if (usr.listModel.size() == 0) {
				System.out.println("Username is OK, allowing access");
				out.writeBytes(NetConstants.SERVER_REQUEST_GRANTED.toString()+'\n');
				//Write IP to userui
				usr.listModel.addElement(username);
				usr.ipAddrs.add(connSocket.getInetAddress().getHostAddress());
				loc = usr.ipAddrs.size() - 1;
				System.out.println("Server adding IP to array @ " + loc);
				if (!isChild) {
					m.setColor(Color.BLACK);
					m.appendString("done.\n", false);
					//TODO: make sure client is connected before enabling
					m.message.setEnabled(true);
					m.message.setText("");
					m.send.setEnabled(true);
				}
			} else {
				for (int i = 0; i < usr.listModel.size()+1; i++) {
					existingUsrname = usr.listModel.elementAt(i);
					System.out.println("Checking '"+username+"' against '"+existingUsrname+"'");
					if (username.equals(existingUsrname)) {
						System.out.println("User trying to connect has identical username!");
						out.writeBytes(NetConstants.SERVER_REQUEST_DENIED.toString()+"\n");
						new Server(m, usr, true);
						return;
					} else { 
						System.out.println("Username is OK. Sending permission to continue");
						out.writeBytes(NetConstants.SERVER_REQUEST_GRANTED.toString()+'\n');
						//Write IP to userui
						usr.listModel.addElement(username);
						usr.ipAddrs.add(connSocket.getInetAddress().getHostAddress());
						loc = usr.ipAddrs.size() - 1;
						System.out.println("Server adding IP to array @ " + loc);
						if (!isChild) {
							m.setColor(Color.BLACK);
							m.appendString("done.\n", false);
							//TODO: make sure client is connected before enabling
							m.message.setEnabled(true);
							m.message.setText("");
							m.send.setEnabled(true);

						}

					}
				}
>>>>>>> theirs
=======
>>>>>>> theirs
			}

			//Start new server thread to listen to other clients
			System.out.println("Creating child server");
			new Server(m, usr, true);

			//Receive & print data
			message = "";
			System.out.println("Server listening for messages...");
			while (!message.equals(null)) { //null *should* mean client disconnected
				message = in.readLine();
				System.out.println("Received TCP message: " + message);

				Thread.sleep(10); //Conflict workaround with writing local and remote messages
				m.setColor(Color.BLACK);
				//TODO: add text wrap
				m.appendString(username + ": " + message, true);
				Thread.sleep(100);
				m.autoscroll();
			}
			System.out.println("Client disconnected from server");
			System.out.println("Removing IP from array @ " + loc);
			usr.listModel.removeElement(username);
			usr.ipAddrs.remove(loc);
			return; 
		} catch (IOException | InterruptedException e)  {
			e.printStackTrace();
			m.error(e);
		}
	}
}

