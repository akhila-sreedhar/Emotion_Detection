/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package emotional_detection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Saanvi
 */
public class utility {
public static void main(String args[])
{
}   

List find_emotion(String msg)
{
    
     List res=new ArrayList();
    
     List a1=new ArrayList();
    a1.add("Good");
    a1.add("Bad");
    
    List Result=new ArrayList();
    
   //String value="I am Good. I am Bad.";
   //String values[]=value.split("\\.");

    System.out.println(a1.size());
    //System.out.println(values.length);
   
   String match="";
   //for(int i=0;i<values.length;i++)
   //{
       String line=msg;
       for(int j=0;j<a1.size();j++)
       {
           
           match=(String)a1.get(j);
           
           String sentence[]=line.split(" ");
           System.out.println(sentence.length); 
           
           for(String word:sentence)
             
               
               if(word.toLowerCase().equals(match.toLowerCase()))
               {
                   System.out.println("Matched");
                   Result.add(match);
                   break;
               }
       }
   
    System.out.println(Result);
    
    return Result;
}
}
