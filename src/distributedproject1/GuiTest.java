package distributedproject1;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;

public class GuiTest {

    static JFrame gui;
    static JTextField input;
    static JTextArea output;
    static JTextArea rooms;
    static JTextArea users;
    static JScrollPane outputScroll;
    static JScrollPane roomScroll;
    static JScrollPane usersScroll;

    /* http://docs.oracle.com/javase/tutorial/uiswing/components/text.html
	   gives examples of other text components that might be useful. */
    public static void hello() {
        output.append("Enter username\n");
        input.grabFocus();
    }

    public static void appendRoom(String room) {
        rooms.append(room);
        rooms.append("\n");
        input.grabFocus();
    }

    public static void appendUser(String user){
        users.append(user);
        users.append("\n");
        input.grabFocus();
    }
    public static void init(String argv[]) {
        //The JFrame is the window itself.
        gui = new JFrame("GuiDemo");

        /* A WindowAdapter anonymous inner class specifies functions to 
		   handle various events that the window might post.  In this case,
		   it only cares about the window being exited.  Any code in 
		   windowClosing will be run when the window is closed. */
        gui.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        input = new JTextField();
        output = new JTextArea();
        rooms = new JTextArea();
        users = new JTextArea();
        outputScroll = new JScrollPane(output);
        roomScroll = new JScrollPane(rooms);
        usersScroll = new JScrollPane(users);
        rooms.append("CHAT ROOMS\n");
        users.append("USERS\n");
        /* Another anonymous inner class is used to listen for events from
		   many swing components.  For a JTextField, the event will be 
		   signaled when you hit Enter while focused on the text field.  
		   Whatever code is in actionPerformed will be run in this case. */
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Print the contents to the terminal and clear the text field
                //System.out.println(input.getText());
                output.append(input.getText());
                output.append("\n");
                input.setText("");
            }
        });

        /* The content pane of the window is divided into north, south,
		   east, west, and center sections that can be used for simple
		   organization of gui components.  Multiple components may be
		   placed in each section.

		   +------------------+
		   |     NORTH        |
		   +--+------------+--+
		   |W |            |E |
		   |E |            |A |
		   |S |  CENTER    |S |
		   |T |            |T |
		   +--+------------+--+
		   |     SOUTH        |
		   +------------------+ */
        gui.getContentPane().add(input, BorderLayout.SOUTH);
        gui.getContentPane().add(outputScroll, BorderLayout.CENTER);
        gui.getContentPane().add(roomScroll, BorderLayout.WEST);
        gui.getContentPane().add(usersScroll, BorderLayout.EAST);
        gui.setSize(640, 640);
        gui.setVisible(true);
        /* The gui has its own thread.  As long as it's visible when main
		   returns, the program will not exit until the window is closed. */
    }
}
