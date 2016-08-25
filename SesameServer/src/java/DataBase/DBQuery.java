/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;



import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author win 7
 */
public class DBQuery {
    public Connection con=null;
    public Statement st=null;
    public ResultSet rs=null;
      public static Map<String, Double> regIdMap= new HashMap<String, Double>();
      public static ArrayList alPid=new ArrayList();
      public static ArrayList alDis=new ArrayList();
      public static TreeMap tm = new TreeMap();
    
      
      public int get_user_count() throws ClassNotFoundException, SQLException{
      int i=0;
      String q="select count(*) from user_details";
       con=DBConnection.getConnection();
                st = con.createStatement();
         rs=st.executeQuery(q);
         if(rs.next())
         {
         i=rs.getInt(1);
         }
               
      return i;
      }
      
      
      
     public int add_user(int uid,String speech) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="insert into user_details set uid='"+uid+"', speech='"+speech+"'";
        System.out.println(">>"+q);
        
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        
        return i;
    }
     public int verify_user(String uid,String speech) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="select * from user_details where uid='"+uid+"' and speech = '"+speech+"'";
        System.out.println(">>"+q);
        
      rs=st.executeQuery(q);
      if(rs.next())
      {
      i=1;
      }
        
        return i;
    }
     
      public String verify_app(String uid,String app) throws ClassNotFoundException, SQLException
    {
       String pass="";
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="select * from app_details where uid='"+uid+"' and app = '"+app+"'";
        System.out.println(">>"+q);
        
      rs=st.executeQuery(q);
      if(rs.next())
      {
     pass=rs.getString("pass");
      }
        
        return pass;
    }
      public int add_app_details(String uid,String app,String pass) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="insert into app_details values('"+uid+"','"+app+"','"+pass+"')";
        System.out.println(">>"+q);
        
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        
        return i;
    }
     
     
    public String get_rfid_vtype(String rfid) throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from user_details where rfid='"+rfid+"'";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      if(rs.next())
      {
     
      details=rs.getString("vtype");
      
      }
      
    return details;
    } 
    
       public String login(String mob,String pass) throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from login where mob='"+mob+"' and pass='"+pass+"'";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      if(rs.next())
      {
      
      details=rs.getString("utype");
      
      }
      
    return details;
    } 
       public int update_vehicle_count(String road,String vtype) throws ClassNotFoundException, SQLException
       {
       int i=0;    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
               
       String q="select count1 from road_vehicle where road='"+road+"' and vtype='"+vtype+"'";
       i=st.executeUpdate(q);
       
       i++;
       String q1="update road_vehicle set count1='"+i+"' where road='"+road+"' and vtype='"+vtype+"' ";
       st.executeUpdate(q1);
       return i;
       }
         public int delete_vehicle_count(String road,String vtype) throws ClassNotFoundException, SQLException
       {
       int i=0;    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
               
       String q="select count1 from road_vehicle where road='"+road+"' and vtype='"+vtype+"'";
       i=st.executeUpdate(q);
       
       i--;
       String q1="update road_vehicle set count1='"+i+"' where road='"+road+"' and vtype='"+vtype+"' ";
       st.executeUpdate(q1);
       return i;
       }
     
         public String get_list_quick_request() throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from quick_post_request";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      while(rs.next())
      {
      int id=rs.getInt("id");
      
      String dtype=rs.getString("dtype");
      String type=rs.getString("type");
      String lat=rs.getString("lat");
      String lon=rs.getString("lon");
      String addr=rs.getString("addr");
      String name=rs.getString("name");
      String mob=rs.getString("mob");
      String qu=rs.getString("quantity");
      String status=rs.getString("status");
      
      String det=id+"="+dtype+"="+type+"="+lat+"="+lon+"="+addr+"="+name+"="+mob+"="+qu+"="+status+"##";
      details+=det;
      }
      
    return details;
    } 
     public String get_id_details(String id) throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from msg_post where id='"+id+"'";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      if(rs.next())
      {
     
      String name=rs.getString("name");
      String mob=rs.getString("mobile");
      String type=rs.getString("type");
      String location=rs.getString("location");
      String addr=rs.getString("address");
      String message=rs.getString("message");
      String img_path=rs.getString("img_path");
      String a_path=rs.getString("a_path");
      String v_path=rs.getString("v_path");
      int status=rs.getInt("status");
      String det=id+"="+name+"="+mob+"="+type+"="+location+"="+addr+"="+message+"="+img_path+"="+a_path+"="+v_path+"="+status+"##";
      details+=det;
      }
      
    return details;
    } 
        public String get_id_details_qw(String id) throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from quick_post_warning where id='"+id+"'";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      if(rs.next())
      {
     
      
      String type=rs.getString("type");
      String lat=rs.getString("lat");
      String lon=rs.getString("lon");
      String addr=rs.getString("addr");
     
      int status=rs.getInt("status");
      String det=id+"="+type+"="+lat+"="+lon+"="+addr+"="+status+"##";
      details+=det;
      }
      
    return details;
    } 
        
           public String get_id_details_qr(String id) throws ClassNotFoundException, SQLException{
    
    String details="";
      con=DBConnection.getConnection();
                st = con.createStatement();
                
                
      String q="select * from quick_post_request where id='"+id+"'";
        System.out.println(""+q);
      rs=st.executeQuery(q);
      
      if(rs.next())
      {
     
      
      String type=rs.getString("type");
      String lat=rs.getString("lat");
      String lon=rs.getString("lon");
      String addr=rs.getString("addr");
      String name=rs.getString("name");
      String mob=rs.getString("mob");
      String quantity=rs.getString("quantity");
     
      int status=rs.getInt("status");
      String det=id+"="+type+"="+lat+"="+lon+"="+addr+"="+name+""+mob+"="+quantity+"="+status+"##";
      details+=det;
      }
      
    return details;
    } 
    public int add_image(String id,String img_path) throws ClassNotFoundException, SQLException
    {
    int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update msg_post set img_path='"+img_path+"' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    return i;
    }
    public int add_Audio(String id,String audio_path) throws ClassNotFoundException, SQLException
    {
    int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update msg_post set a_path='"+audio_path+"' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    return i;
    }
    
    
    public int attend(String id,String name,String mob) throws ClassNotFoundException, SQLException
    {
    
     int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update msg_post set status='1' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    
    
    String q1="insert into msg_post_attend values('"+id+"','"+name+"','"+mob+"')";
    i=st.executeUpdate(q1);
    
    return i;
    
    
    
    }
       public int attend_qr(String id,String name,String mob) throws ClassNotFoundException, SQLException
    {
    
     int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update quick_post_request set status='1' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    
    
    String q1="insert into quick_post_attend_qr values('"+id+"','"+name+"','"+mob+"')";
    i=st.executeUpdate(q1);
    
    return i;
    
    
    
    }
    public int attended(String id,String mob) throws ClassNotFoundException, SQLException
    {
    
     int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update msg_post set status='2' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    
    
    
    return i;
    
    
    
    }
      public int attended_qr(String id,String mob) throws ClassNotFoundException, SQLException
    {
    
     int i=0;
    con=DBConnection.getConnection();
                st = con.createStatement();
      String q="update quick_post_request set status='2' where id='"+id+"'";     
        System.out.println(""+q);
    i=st.executeUpdate(q);
    
    
    
    return i;
    
    
    
    }
     public int quick_post_warning(String type,String lat,String lon,String addr) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="insert into quick_post_warning set type='"+type+"', lat='"+lat+"',lon='"+lon+"',addr='"+addr+"', status='0'";
        System.out.println(">>"+q);
        
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        String q1="select MAX(id) from quick_post_warning";
        rs=st.executeQuery(q1);
        if(rs.next())
        {
        i=rs.getInt(1);
        }
        return i;
    }
      public int quick_post_request(String dtype,String type,String lat,String lon,String addr,String name,String mob,String qu) throws ClassNotFoundException, SQLException
    {
          int i= 0;
                con=DBConnection.getConnection();
                st = con.createStatement();
                
               
                
        String q="insert into quick_post_request set dtype='"+dtype+"',type='"+type+"', lat='"+lat+"',lon='"+lon+"',addr='"+addr+"', name='"+name+"', mob='"+mob+"', quantity='"+qu+"', status='0'";
        System.out.println(">>"+q);
        
      
        System.out.println(""+q);
        i=st.executeUpdate(q);
        String q1="select MAX(id) from quick_post_warning";
        rs=st.executeQuery(q1);
        if(rs.next())
        {
        i=rs.getInt(1);
        }
        return i;
    }
}
