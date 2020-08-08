/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emotional_detection;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


import java.rmi.server.*;
import java.rmi.*;
import java.util.*;
import java.io.*;
import java.net.NetworkInterface;
import java.net.SocketException;

/**
 *
 * @author Saanvi
 */
public class Utility_Class {

    String Node1_IPAddress = "192.168.3.3";
    String Node2_IPAddress = "192.168.3.3";
    String Node3_IPAddress = "192.168.3.3";
    String Node4_IPAddress = "192.168.3.3";
    String Node5_IPAddress = "192.168.3.5";
    String Node6_IPAddress = "192.168.3.5";
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    List Node_Status = new ArrayList();
    List DB_Val = new ArrayList();


    List find_emotion(String msg) throws Exception
{
    
    int positive=0;
    int negative=0;
    
        
     List words=new ArrayList();
    
    words=master_words_fetch();
    // List a1=new ArrayList();
   // a1.add("Good");
   // a1.add("Bad");
    
    List Result=new ArrayList();
    
   //String value="I am Good. I am Bad.";
   //String values[]=value.split("\\.");

   // System.out.println(a1.size());
    //System.out.println(values.length);
   
   String match="";
   //for(int i=0;i<values.length;i++)
   //{
       String line=msg;
       for(int j=0;j<words.size();j++)
       {
           
           match=(String)words.get(j);
           
           String word1=match.split(" ")[0];
           String word2=match.split(" ")[1];
         //  System.out.println("word1 "+word1); 
         //  System.out.println("word2 "+word2);  
           
           
           
           String sentence[]=line.split(" ");
           //System.out.println(sentence.length); 
           
           
             for(int i=0;i<sentence.length;i++)
             {
                 
             String word=sentence[i];
             
            
             if(i==0)
             {
                 if(word.toLowerCase().equals(word1.toLowerCase()))
               {
                 if(word2.toLowerCase().equals("p"))    
                 {
                     positive++;
                     
                 }
                 if(word2.toLowerCase().equals("n"))    
                 {
                     negative++;
                     
                 }
         
               } 
             }
             else if(i>0)
             {
             
                 if(word.toLowerCase().equals(word1.toLowerCase()))
                 {    
                  String prev=sentence[i-1];
                  
                  
                /* Fot Not validation : Start*/  
                 if(prev.toLowerCase().equals("not"))
                  {
         
                if(word2.toLowerCase().equals("p"))    
                 {
                     negative++;
                     
                 }
                 if(word2.toLowerCase().equals("n"))    
                 {
                     positive++;
                     
                 }
                 }
                  
                  /* Fot Not validation : End*/  
                 else
                 {
                       if(word2.toLowerCase().equals("p"))    
                 {
                     positive++;
                     
                 }
                 if(word2.toLowerCase().equals("n"))    
                 {
                     negative++;
                     
                 }
         
                 }
     }
                 
             }   
             }
       }
   
       
      System.out.println("Positive "+positive); 
      System.out.println("Negative "+negative); 
   // System.out.println(Result);

      String val=positive + " "+negative;
      Result.add(val);
    return Result;
}

    
    public static void main(String args[]) throws Exception
    {
        Utility_Class uc=new Utility_Class();
        uc.master_words_fetch();
        uc.find_emotion("I am not peaceful.I am purity");
    }       
       
       
       // Inserting Server Routing Table
         public void server_insert(List list) throws Exception {

        System.out.println("server_insert");
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception" + e);
        }

        try {
        
            System.out.println(list);
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/didrip", "root", "password");
            st = cn.createStatement();

            st.execute("delete from routing_table_server");
            int n = st.executeUpdate("insert into routing_table_server values(" + list.get(0) + "," + list.get(1) + "," + list.get(2) + "," + list.get(3)+ "," + "999" + "," + "999" + ")");
            n = st.executeUpdate("insert into routing_table_server values(" + list.get(4) + "," + list.get(5) + "," + list.get(6) + "," + list.get(7) + ","+"999" + "," + "999" + ")");
            n = st.executeUpdate("insert into routing_table_server values(" + list.get(8) + "," + list.get(9) + "," + list.get(10) + "," + list.get(11) + ","+ "999" + "," + "999" + ")");
            n = st.executeUpdate("insert into routing_table_server values(" + list.get(12) + "," + list.get(13) + "," + list.get(14) + "," + list.get(15) + ","+"999" + "," + "999" + ")");
            n = st.executeUpdate("insert into routing_table_server values(" + "999" + "," + "999" + "," + "999" + "," + "999" + "," + "999" + "," + "999" + ")");
            n = st.executeUpdate("insert into routing_table_server values(" + "999" + "," + "999" + "," + "999" + "," + "999" + "," + "999" + "," + "999" + ")");
        
         
        //cn.close();
        } catch (Exception e) {
            System.out.println("Table not Found  " + e);
        }
        cn.close();
        rs.close();
        st.close();
    }
         
         
       
      public List master_words_fetch() throws Exception {

        System.out.println("master_words_fetch");
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        DB_Val = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception" + e);
        }

        try {
           
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/emotion", "root", "password");
            st = cn.createStatement();

            rs = st.executeQuery("select * from emotion_words ");
            while (rs.next()) {
                DB_Val.add(rs.getString(1)+" "+rs.getString(3));
            }
            
            
            
        //cn.close();
        } catch (Exception e) {
            System.out.println("Table not Found  " + e);
        }
        System.out.println(DB_Val);
        cn.close();
        rs.close();
        st.close();
        return DB_Val;
    }

}


