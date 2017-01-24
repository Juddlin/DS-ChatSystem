/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributedproject1;

/**
 *
 * @author james
 */
public class DistributedProject1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GuiTest.init(args);
        GuiTest.hello();
        GuiTest.appendRoom("a-holes");
        GuiTest.appendRoom("kool kids");
        GuiTest.appendUser("james_is_awesome");
        
        // GUI needs an option to make either a server or client version?
        // If server version is selected, have an idle box saying "Server Listening".
        // If Client, start the client and list commands.
        
    }

}
