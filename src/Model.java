import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

// import com.mysql.cj.xdevapi.PreparableStatement;
public class Model {
    Connection con;
    public Model() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int PutSignUpDetialsToDb(String email, String name, String phone, String role,String pass, String gender, String dob) {
        
      String q = "Select * from User_Details where user_email=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(q);
         pst.setString(1, email);
         ResultSet res=pst.executeQuery();
         if(res.next()){
            return 9;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      String qr = "Select * from User_Details where user_phone=?";
      try {
         pst=con.prepareStatement(qr);
         pst.setString(1, phone);
         ResultSet res=pst.executeQuery();
         if(res.next()){
            return 10;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      String query = "INSERT INTO User_Details (user_email, user_name, user_phone, user_role,user_pass, user_gender, user_dob) VALUES (?, ?, ?,?, ?, ?, ?)";
        try {
           pst = con.prepareStatement(query);
           pst.setString(1,email);
          pst.setString(2,name);
          pst.setString(3,phone);
          pst.setString(4,role);
          pst.setString(5,pass);
          pst.setString(6,gender);
          pst.setString(7,dob);
          int p=pst.executeUpdate();
          System.out.println(p);
          if(p!=0){
            return 1;
         //   System.out.println("Registered Successfully");
          }
        } catch (SQLException e) {
           e.printStackTrace();
        }
          return 0;
      }
      public int CheckLoginDetailsInDB(String email,String pass){
        String query = "Select user_pass from User_Details where user_email=?";
        PreparedStatement pst;
        try{
        pst=con.prepareStatement(query);
        pst.setString(1, email);
        ResultSet res=pst.executeQuery();
        if(res.next()){
           String user_pass=res.getString("user_pass");
           if(user_pass.equals(pass)){
            //   System.out.println("Login Successful");
               String q="SELECT user_role from User_Details where user_email=?";
               try {
                 pst=con.prepareStatement(q);
                 pst.setString(1, email);
                 ResultSet rs=pst.executeQuery();
                 if(rs.next()){
                    if(rs.getString("user_role").equals("User")){
                    return 1;
                    }
                    else if(rs.getString("user_role").equals("Admin")){
                    return 2;
                    }
                 }
               } catch (Exception e) {
               }
           }
           else{
            return 0;
           }
        }
        else{
        //    System.out.println("Invalid Email Try Again");
           return -1;
        }
        }
        catch(Exception e){
           System.out.println(e);
        }
        return 1;
      }

      public int InsertVehicleDetailsToDB(String name,String number,String type,String total,String status,int rentcount,String serviceStatus,int amount){
      PreparedStatement pst;

      String q = "Select * from vehicle_inventory where vehicle_number_plate=?";
      try {
         pst=con.prepareStatement(q);
         pst.setString(1, number);
         ResultSet res=pst.executeQuery();
         if(res.next()){
            return 9;
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      String query="Insert into vehicle_inventory(vehicle_name, vehicle_number_plate,vehicle_type,vehicle_total_kms,vehicle_status,vehicle_rentcount,vehicle_service_status,vehicle_renting_amount) values(?,?,?,?,?,?,?,?)";
      try {
         pst=con.prepareStatement(query);
         pst.setString(1,name );
         pst.setString(2,number );
         pst.setString(3,type );
         pst.setString(4,total);
         pst.setString(5,status);
         pst.setInt(6,rentcount);
         pst.setString(7,serviceStatus);
         pst.setInt(8,amount);
         int h=pst.executeUpdate();
         if(h==1){
            return 1;
         }
      } catch (Exception e) {
      }
         return 0;
     }

   public int SearchVehicleByNumberFromDB(String id){
      String query="Select * from vehicle_inventory where vehicle_number_plate = ?";
      PreparedStatement pst;
      int g=0;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, id);
         ResultSet rs;
         try {
            rs=pst.executeQuery();
         } catch (Exception e) {
            return g;
         }
         while(rs.next()){
            g++;
            String vehicle_id=rs.getString(1);
            String vehicle_name=rs.getString(2);
            String vehicle_number_plate=rs.getString(3);
            String vehicle_type=rs.getString(4);
            String vehicle_total_kms=rs.getString(5);
            String vehicle_status=rs.getString(6);
            int vehicle_rentcoount=rs.getInt(7);
            String vehicle_service_status=rs.getString(8);
            int vehicle_renting_amount=rs.getInt(9);
              System.out.println("ID: "+vehicle_id+"::  Name: "+vehicle_name+"::  Number_Plate: "+vehicle_number_plate+"::  Type:  "+vehicle_type+"::  Run-off:  "+vehicle_total_kms+"::  Status:  "+vehicle_status+"::  Rent-Count:  "+vehicle_rentcoount+":: Service-Status:  "+vehicle_service_status+":: Cost: "+vehicle_renting_amount);
         } 
      } catch (Exception e) {
         // TODO: handle exception
      }
      return g;
   }
   public int SearchVehicleByNameFromDB(String name){
      String query="Select * from vehicle_inventory where vehicle_name = ? and vehicle_service_status=?";
      PreparedStatement pst;
      int g=0;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, name);
         pst.setString(2, "done");
         ResultSet rs;
         try {
            rs=pst.executeQuery();
         } catch (Exception e) {
            return g;
         }
         while(rs.next()){
            g++;
            String vehicle_id=rs.getString(1);
            String vehicle_name=rs.getString(2);
            String vehicle_number_plate=rs.getString(3);
            String vehicle_type=rs.getString(4);
            String vehicle_total_kms=rs.getString(5);
            String vehicle_status=rs.getString(6);
            int vehicle_rentcoount=rs.getInt(7);
            String vehicle_service_status=rs.getString(8);
            int vehicle_renting_amount=rs.getInt(9);
              System.out.println("ID: "+vehicle_id+"::  Name: "+vehicle_name+"::  Number_Plate: "+vehicle_number_plate+"::  Type:  "+vehicle_type+"::  Run-off:  "+vehicle_total_kms+"::  Status:  "+vehicle_status+"::  Rent-Count:  "+vehicle_rentcoount+":: Service-Status:  "+vehicle_service_status+":: Cost: "+vehicle_renting_amount);
         } 
      } catch (Exception e) {
         // TODO: handle exception
      }
      return g;
   }
   public int SearchVehicleByTypeFromDB(String name){
      String query="Select * from vehicle_inventory where vehicle_type = ? and vehicle_service_status=?";
      PreparedStatement pst;
      int g=0;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, name);
         pst.setString(2, "done");
         ResultSet rs;
         try {
            rs=pst.executeQuery();
         } catch (Exception e) {
            return g;
         }
         while(rs.next()){
            g++;
            String vehicle_id=rs.getString(1);
            String vehicle_name=rs.getString(2);
            String vehicle_number_plate=rs.getString(3);
            String vehicle_type=rs.getString(4);
            String vehicle_total_kms=rs.getString(5);
            String vehicle_status=rs.getString(6);
            int vehicle_rentcoount=rs.getInt(7);
            String vehicle_service_status=rs.getString(8);
            int vehicle_renting_amount=rs.getInt(9);
              System.out.println("ID: "+vehicle_id+"::  Name: "+vehicle_name+"::  Number_Plate: "+vehicle_number_plate+"::  Type:  "+vehicle_type+"::  Run-off:  "+vehicle_total_kms+"::  Status:  "+vehicle_status+"::  Rent-Count:  "+vehicle_rentcoount+":: Service-Status:  "+vehicle_service_status+":: Cost: "+vehicle_renting_amount);
         } 
      } catch (Exception e) {
         // TODO: handle exception
      }
      return g;
   }
   public int SearchVehicleByIdFromDB(int id){
      String query="Select * from vehicle_inventory where vehicle_id = ?";
      PreparedStatement pst;
      int g=0;
      try {
         pst=con.prepareStatement(query);
         pst.setInt(1, id);
         ResultSet rs;
         try {
            rs=pst.executeQuery();
         } catch (Exception e) {
            return g;
         }
         while(rs.next()){
            g++;
            String vehicle_id=rs.getString(1);
            String vehicle_name=rs.getString(2);
            String vehicle_number_plate=rs.getString(3);
            String vehicle_type=rs.getString(4);
            String vehicle_total_kms=rs.getString(5);
            String vehicle_status=rs.getString(6);
            int    vehicle_rentcoount=rs.getInt(7);
            String vehicle_service_status=rs.getString(8);
            int vehicle_renting_amount=rs.getInt(9);
            System.out.println("ID: "+vehicle_id+"::  Name: "+vehicle_name+"::  Number_Plate: "+vehicle_number_plate+"::  Type:  "+vehicle_type+"::  Run-off:  "+vehicle_total_kms+"::  Status:  "+vehicle_status+"::  Rent-Count:  "+vehicle_rentcoount+":: Service-Status:  "+vehicle_service_status+":: Cost: "+vehicle_renting_amount);
         } 
      } catch (Exception e) {
         // TODO: handle exception
      }
      return g;
   }
   public int SearchVehicleFromDB(){
      String query="Select * from vehicle_inventory";
      PreparedStatement pst;
      int g=0;
      try {
         pst=con.prepareStatement(query);
         ResultSet rs;
         try {
            rs=pst.executeQuery();
         } catch (Exception e) {
            return g;
         }
         while(rs.next()){
            g++;
            String vehicle_id=rs.getString(1);
            String vehicle_name=rs.getString(2);
            String vehicle_number_plate=rs.getString(3);
            String vehicle_type=rs.getString(4);
            String vehicle_total_kms=rs.getString(5);
            String vehicle_status=rs.getString(6);
            int    vehicle_rentcoount=rs.getInt(7);
            String vehicle_service_status=rs.getString(8);
            int vehicle_renting_amount=rs.getInt(9);
            System.out.println("ID: "+vehicle_id+"::  Name: "+vehicle_name+"::  Number_Plate: "+vehicle_number_plate+"::  Type:  "+vehicle_type+"::  Run-off:  "+vehicle_total_kms+"::  Status:  "+vehicle_status+"::  Rent-Count:  "+vehicle_rentcoount+":: Service-Status:  "+vehicle_service_status+":: Cost: "+vehicle_renting_amount);
         } 
      } catch (Exception e) {
         // TODO: handle exception
      }
      return g;
   }
   public int UpdateVehicleById(int id,String factor,int uv){
         String query = "UPDATE vehicle_inventory SET " + factor + " = ? WHERE vehicle_id = ?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setInt(1, uv);
         pst.setInt(2, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public int UpdateVehicleById(int id,String factor,String uv){
         String query = "UPDATE vehicle_inventory SET " + factor + " = ? WHERE vehicle_id = ?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, uv);
         pst.setInt(2, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public int UpdateVehicleByNumber(String id,String factor,int uv){
         String query = "UPDATE vehicle_inventory SET " + factor + " = ? WHERE vehicle_number_plate= ?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setInt(1, uv);
         pst.setString(2, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public int UpdateVehicleByNumber(String id,String factor,String uv){
         String query = "UPDATE vehicle_inventory SET " + factor + " = ? WHERE vehicle_number_plate = ?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, uv);
         pst.setString(2, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }

   public int DeleteVehicleById(int id){
      String query = "delete from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setInt(1, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public int DeleteVehicleByNumber(String id){
      String query = "delete from vehicle_inventory where vehicle_number_plate=?";
      PreparedStatement pst;
      try {
         pst=con.prepareStatement(query);
         pst.setString(1, id);
         int rs;
         try {
         rs=pst.executeUpdate();
         } catch (Exception e) {
           return -1;
         }
         if(rs!=0){
         return 1;
         }
         else{
            return 0;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }


   public int getUserId(String email){
      String q="Select user_id from User_Details where user_email=?";
      PreparedStatement pst;
      int g=0;
      try {
      pst=con.prepareStatement(q);
      pst.setString(1, email);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getInt(1);
            }
            else {
               return 99;
            }
         } catch (Exception e) {
            return g;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public int getAmount(int email){
      String q="Select vehicle_renting_amount from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      int g=0;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, email);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getInt(1);
            }
            else {
               return 99;
            }
         } catch (Exception e) {
            return g;
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }
   public String getUserEmail(int id){
      String q="Select user_email from User_Details where user_id=?";
      PreparedStatement pst;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getString(1);
            }
            else {
               return "99";
            }
         } catch (Exception e) {
            return "0";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "0";
   }
   public String getUserPhone(int id){
      String q="Select user_phone from User_Details where user_id=?";
      PreparedStatement pst;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getString(1);
            }
            else {
               return "99";
            }
         } catch (Exception e) {
            return "0";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "0";
   }
   public String getVehicleName(int id){
      String q="Select vehicle_name from vehicle_inventory where vehicle_id=? and vehicle_status=? and vehicle_service_status=?";
      PreparedStatement pst;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      pst.setString(2, "available");
      pst.setString(3, "done");
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getString(1);
            }
            else {
               return "That vehicle is not available";
            }
         } catch (Exception e) {
            return "0";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "0";
   }
   public String getVehicleType(int id){
      String q="Select vehicle_type from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getString(1);
            }
            else {
               return "No such vehicle exist";
            }
         } catch (Exception e) {
            return "0";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "0";
   }
   public String getVehicleNumber(int id){
      String q="Select vehicle_number_plate from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      try {
      pst=con.prepareStatement(q);
      pst.setInt(1, id);
      ResultSet rs;
         try {
            rs=pst.executeQuery();
            if(rs.next()){
               return rs.getString(1);
            }
            else {
               return "No such vehicle exist";
            }
         } catch (Exception e) {
            return "0";
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return "0";
   }

   public int putBookingTodb(int userid,String useremail,String userphone,int vid,String vname,String vno,String vtype,String rentdate,String returndate,String depgiven,int depamount,int rentdays,int rentamount){
      
      String q="Insert into renting_details(user_id,user_email,user_phone,vehicle_id,vehicle_name,vehicle_number_plate,vehicle_type,renting_date,returning_date,deposit_given,deposit_amount,renting_days,renting_amount) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, userid);
        pst.setString(2, useremail);
        pst.setString(3, userphone);
        pst.setInt(4, vid);
        pst.setString(5, vname);
        pst.setString(6, vno);
        pst.setString(7, vtype);
        pst.setString(8, rentdate);
        pst.setString(9, returndate);
        pst.setString(10, depgiven);
        pst.setInt(11, depamount);
        pst.setInt(12, rentdays);
        pst.setInt(13, rentamount);
        try {
         int h=pst.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
        

      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }

public void MakeVehicleOnrent(int vid){
    String q="update vehicle_inventory set vehicle_status=? where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setString(1, "on-rent");
        pst.setInt(2, vid);
        try {
         pst.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
}
public int getTotalFromUser(int vid){
    String q="select renting_amount from renting_details where renting_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, vid);
        ResultSet rs;
        rs=pst.executeQuery();
        if(rs.next()){
         return rs.getInt("renting_amount");
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
}
public int getDistance(int vid){
    String q="Select renting_days from renting_details where renting_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, vid);
        ResultSet rs;
        rs=pst.executeQuery();
        if(rs.next()){
         System.out.println(rs.getInt(1));
         return rs.getInt("renting_days");
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
}
 public int viewHistory(){
      String query="Select * from renting_details";
      PreparedStatement pst;
      // int g=0;
      try {
         pst=con.prepareStatement(query);
         ResultSet rs;
         try {
             rs=pst.executeQuery();
         while(rs.next()){
            int renting_id=rs.getInt(1);
                        int user_id=rs.getInt(2);
            String user_email=rs.getString(3);
            String user_phone=rs.getString(4);
            int vid=rs.getInt(5);
            String vehicle_name=rs.getString(6);
            String vehicle_number_plate=rs.getString(7);
            String vehicle_type=rs.getString(8);
            String rental_date=rs.getString(9);
            String returning_date=rs.getString(10);
            String depgiven=rs.getString(11);
            int depamount=rs.getInt(12);
            int rentdays=rs.getInt(13);
            int rentamount=rs.getInt(14);
               System.out.println("Renting id: "+renting_id+
               "::  User ID: " + user_id +
               "::  User Email: " + user_email +
               "::  User Phone: " + user_phone +
               "::  Vehicle ID: " + vid +
               "::  Vehicle Name: " + vehicle_name +
               "::  Vehicle Number Plate: " + vehicle_number_plate +
               "::  Vehicle Type: " + vehicle_type +
               "::  Rental Date: " + rental_date +
               "::  Returning Date: " + returning_date +
               "::  Deposit Given: " + depgiven +
               "::  Deposit Amount: " + depamount +
               "::  Rental Days: " + rentdays +
               "::  Rental Amount: " + rentamount);
               System.out.println();
         } 
         } catch (Exception e) {
           System.out.println(e);
         }
        
      } catch (Exception e) {
         // TODO: handle exception
      }
   return 0;
   }
   public int putBillToDb(int id,int total,double ext,double fine,double amt){
      String q="Insert into billing(renting_id,bill_amount,extra_amnount,bill_fine,amount_to_return) values(?,?,?,?,?)";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, id);
        pst.setInt(2, total);
        pst.setInt(3, (int)ext);
        pst.setInt(4, (int)fine);
        pst.setInt(5, (int)amt);
        try {
         int h=pst.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }

   public int updateVehicleStatus(int id){
      String q="Update vehicle_inventory set vehicle_status=? where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setString(1, "available");
        pst.setInt(2, id);
        try {
         int h=pst.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }
   public int updateVehicleKms(int id,int kms){
      String q="Update vehicle_inventory set vehicle_total_kms=vehicle_total_kms+? where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, kms);
        pst.setInt(2, id);
        try {
         int h=pst.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }
   public int updateVehicleRentCount(int id,int kms){
      String q="Update vehicle_inventory set vehicle_rentcount=vehicle_rentcount+? where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, kms);
        pst.setInt(2, id);
        try {
         int h=pst.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }

   public int checkKms(int vid){
      String q="Select vehicle_total_kms from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, vid);
        ResultSet rs;
        try {
         rs=pst.executeQuery();
         if(rs.next()){
            return rs.getInt(1);
         }
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }
   public String checkKms(int vid,int a){
      String q="Select vehicle_type from vehicle_inventory where vehicle_id=?";
      PreparedStatement pst;
      try {
        pst=con.prepareStatement(q);
        pst.setInt(1, vid);
        ResultSet rs;
        try {
         rs=pst.executeQuery();
         if(rs.next()){
            return rs.getString(1);
         }
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return "0";
   }

   public int updateservice(int vid){
      String q="Update vehicle_inventory set vehicle_service_status=? where vehicle_id=?";
      String q1="Update vehicle_inventory set vehicle_total_kms=? where vehicle_id=?";
      PreparedStatement pst;
      PreparedStatement pst1;
      try {
        pst=con.prepareStatement(q);
        pst1=con.prepareStatement(q1);
        pst.setString(1, "pending");
        pst.setInt(2, vid);
        pst1.setInt(1, 0);
        pst1.setInt(2, vid);
        try {
         int h=pst.executeUpdate();
         int e=pst1.executeUpdate();
        return h;
        } catch (Exception e) {
         System.out.println(e);
        }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return 0;
   }
}