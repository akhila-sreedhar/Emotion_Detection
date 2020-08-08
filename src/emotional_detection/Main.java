/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emotional_detection;

/**
 *
 * @author Saanvi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String msg="I am  Not feeling good.";
        
         boolean flag=msg.toLowerCase().contains("not");
         System.out.println(flag);
             
    }

}
