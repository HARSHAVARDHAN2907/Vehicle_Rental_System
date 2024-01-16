import java.util.*;
import java.time.LocalDate;
class SignUp{
    String s;
    DBFiles db=new DBFiles();
    Scanner in=new Scanner(System.in);
    public void signup(){
    int n=2;
        do {
            System.out.println("Press 1 for SignUp or 0 for Login or 3 to Exit");
            n = in.nextInt();
            System.out.println();
            if (n == 1) {
                getUserDetails();
            } else if (n == 0) {
                getLoginDetails();
            }
            else if(n==3){
                System.out.println("Exiting...");
                return;
            } 
            else {
                System.out.println("Invalid Choice\nTry Again");
            }
        } while (n != 0 && n != 1);
    }
    
    public void getUserDetails() {
        System.out.println("Enter your Email:");
        s = in.next();
        if (!s.contains("@gmail.com")) {
            while (!s.contains("@gmail.com")) {
                System.out.println();
                System.out.println("Invalid Email\nTry Again");
                s = null;
                System.out.println("Enter your Email:");
                s = in.next();
                System.out.println();
            }
    }
    System.out.println();
    System.out.println("Enter your Username:");
    String user=in.next();
    System.out.println();
    System.out.println("Enter your Phone Number:");
    String phone=in.next();
    if(phone.length()!=10){
        while(phone.length()!=10){
            System.out.println();
             System.out.println("Invalid Phone Number\nTry Again");
            phone=null;
            System.out.println("Enter your Phone Number:");
            phone=in.next();
            System.out.println();
        }
    }
    System.out.println();
    System.out.println("Enter DOB");
    System.out.print("Date: ");
    int date=in.nextInt();
    System.out.println();

    System.out.print("Month: ");
    String month=in.next();
    System.out.println();
    
    System.out.print("Year: ");
    String year=in.next();
    System.out.println();

    String dob=year+"-"+month+"-"+Integer.toString(date);

    System.out.println("Choose Your Role:");
    System.out.println("Press 1 for User 2 for Vehicle Lender");
    System.out.println("1.) User");
    System.out.println("2.) Admin");
    int h=in.nextInt();
    String g="";
    if(( h!=1 && h!=2)){
        while( h!=1 || h!=2){
            System.err.println();
            System.err.println("Invalid Choice\nTry Again");
            System.out.println();
            System.out.println("Choose Your Role:");
            System.out.println("Press 1 for User 2 for Vehicle Lender");
            System.out.println("1.) User");
            System.out.println("2.) Admin");
            h=0;
            h=in.nextInt();
            if( h==1 || h==2){
                break;
            }
        }
    }
    if(h==1){
        g+="User";
    }
    else if(h==2){
        g+="Admin";
    }
    System.out.println();
    System.out.println("Choose Your Gender:");
    System.out.println("Press 1 for Male 2 for Female 3 for Others");
    System.out.println("1.) Male");
    System.out.println("2.) Female");
    System.out.println("3.) Others");
    int p=in.nextInt();
    if(p!=1 || p!=2 || p!=3) {
        while(p!=1 && p!=2 && p!=3){
            System.err.println();
            System.err.println("Invalid Choice\nTry Again");
            System.out.println();
            System.out.println("Choose Your Gender:");
            System.out.println("Press 1 for Male 2 for Female 3 for Others");
            System.out.println("1.) Male");
            System.out.println("2.) Female");
            System.out.println("3.) Others");
            p=in.nextInt();
            if(p==1 || p==2 || p==3) {
                break;
            }
        }
    }
    String m="";
    if(p==1){
        m+="Male";
    }
    else if(p==2){
        m+="Female";
    }
    else if(p==3){
        m+="Others";
    }
    System.out.println();
    System.out.println("Enter your password(Must be of 8 characters):");
    String pass=in.next();
    System.out.println();
    System.out.println("Confirm Password:");
    String pass1=in.next();
    System.out.println();
    if((pass.length()!=8)){
        while((pass.length()!=8)){
            System.out.println("Password must be of 8 characters");
            pass=null;
            pass1=null;
            System.out.println("Enter your password(Must be of 8 characters):");
            pass=in.next();
            System.out.println();
            System.out.println("Confirm Password:");
            pass1=in.next();
            System.out.println();
        }
    }
    if(!pass.equals(pass1)){
        while(!pass.equals(pass1)){
            System.out.println("Password and Confirm password must match");
            pass=null;
            pass1=null;
            System.out.println("Enter your password(Must be of 8 characters):");
            pass=in.next();
            System.out.println();
            System.out.println("Confirm Password:");
            pass1=in.next();
            System.out.println();
        }
    }
    
    int f=db.PutSignUpDetials(s, user,phone, g, pass, m, dob);
    if(f==1){
        System.out.println("Registered Successfully");
        if(g.equals("Admin")){
            AfterPage af=new AfterPage();
            af.AdminAfterPage();
        }
        else{
        int k=db.getUserId(s);
        AfterPage af=new AfterPage();
        af.UserAfterPage(k);
        }
    }
    if(f==9){
        System.out.println("Email already registered!! Try Again");
    }
    if(f==10){
        System.out.println("Phone number already registered!! Try Again");
    }
    }
    




    public void getLoginDetails(){
    System.out.println("Enter your Email:");
        String s = in.next();
        if (!s.contains("@gmail.com")) {
            while (!s.contains("@gmail.com")) {
                System.out.println();
                System.out.println("Invalid Email\nTry Again");
                s = null;
                System.out.println("Enter your Email:");
                s = in.next();
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Enter Password:");
        String pass=in.next();
        if((pass.length()!=8)){
        while((pass.length()!=8)){
            System.out.println("Password must be of 8 characters");
            pass=null;
            System.out.println("Enter Password:");
            pass=in.next();
            System.out.println();
        }
    }
    int h=db.CheckLoginDetails(s,pass);
    if(h==1){
        System.out.println("Login Succesfull");
        int k=db.getUserId(s);
        AfterPage af=new AfterPage();
        af.UserAfterPage(k);
    }
    if(h==2){
        System.out.println("Hello Admin!!");
        AfterPage af=new AfterPage();
        af.AdminAfterPage();
    }
    else if(h==0){
        System.out.println("Wrong Password!!!Try Again");
        getLoginDetails();
    }
    else if(h==-1){
        System.out.println("Invalid Email!!!Try Again");
        getLoginDetails();
    }
    }
}
class AfterPage extends SignUp{
    Scanner in=new Scanner(System.in);
    DBFiles db=new DBFiles();
    public void UserAfterPage(int k){
        System.out.println("Your User Id is "+k);
        int n=0;
        do{
        System.out.println();
        System.out.println("1.Search Vehicle");
        System.out.println("2.Book a vehicle");
        System.out.println();
        n=in.nextInt();
        if(n!=1 && n!=2){
            System.out.println("Try Again");
        }
        }while(n!=1 && n!=2);
        if(n==1){
        int j=0;
        do{
        System.out.println();
        System.out.println("1.Search Vehicle By Name");
        System.out.println("2.Search Vehicle By Type");
        System.out.println();
        j=in.nextInt();
        if(j!=1 && j!=2){
            System.out.println("Try Again");
        }
        }while(j!=1 && j!=2 && j!=3);
        if(j==1){
            System.out.print("Enter name: ");
            String y=in.next();
            System.out.println();
            int h=db.SearchVehicleyName(y);
            if(h==1){
            System.out.println("Press 0 to continue or 1 to LogOut");
            int v;
            do{
                v=in.nextInt();
                if(v!=0 && v!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(v!=0 && v!=1);
            if(v==0){
                    UserAfterPage(k);
                }
                else if(v==1){
                    SignUp sd=new SignUp();
                    sd.signup();
            }
        }
        else{
            System.out.println("No such vehicle exists");
            UserAfterPage(k);
        }
    }
    else if(j==2){
System.out.print("Enter type: ");
            String y=in.next();
            System.out.println();
            int h=db.SearchVehicleyType(y);
            if(h==1){
            System.out.println("Press 0 to continue or 1 to LogOut");
            int v;
            do{
                v=in.nextInt();
                if(v!=0 && v!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(v!=0 && v!=1);
            if(v==0){
                    UserAfterPage(k);
                }
                else if(v==1){
                    SignUp sd=new SignUp();
                    sd.signup();
            }
        }
        else{
            System.out.println("No such vehicle exists");
            UserAfterPage(k);
        }
        }
}
else if(n==2){
    System.out.println();
    System.out.print("Enter the Id of vehcile you want to book: ");
    int vid=in.nextInt();
    System.out.println();
    System.out.print("No of days you want to book for (Maximum 3 days are allowed): ");
    int no=in.nextInt();
    System.out.println();
    String user_email=db.getUserEmail(k);
    String user_phone=db.getUserPhone(k);
    String vehicle_name=db.getVehicleName(vid);
    String vehicle_type=db.getVehicleType(vid);
    String vehicle_number=db.getVehicleNumber(vid);
    int vehicle_renting_amount=db.getAmount(vid);

    if(vehicle_name.equals("That vehicle is not available")){
        System.out.println(vehicle_name+"!!!Try Again");
        UserAfterPage(k);
    }
    else{
    System.out.println("Vehicle Name: "+vehicle_name);
    System.out.println("Vehicle Number Plate: "+vehicle_number);
    System.out.println("Vehicle Type: "+vehicle_type);
    System.out.println("Vehicle amount per day: "+vehicle_renting_amount);
    System.out.println("Total Amount: "+vehicle_renting_amount*no);
    String f="";
    System.out.println();
    int x;
    do{
        System.out.println("Press 0 to pay deposit or 1 to cancel booking");
        x=in.nextInt();
        if(x!=0 && x!=1){
            System.out.println("Invalid Input! Try again.");
        }
    }while(x!=0 && x!=1);
    if(x==0){
        f+="Yes";
        int u=0;
    if(vehicle_type.equals("car")){
        u+=10000;
    }
    else{
        u+=3000;
    }
    LocalDate currentDate = LocalDate.now();
    String renting_date=currentDate.toString();
    LocalDate newDate = currentDate.plusDays(no);
    String returning_date=newDate.toString();
    System.out.println();
    System.out.println("Booking date: "+renting_date);
    System.out.println("Returning date: "+returning_date);
    System.out.println();
    int op;
    do{
    System.out.println("1.Complete Booking");
    System.out.println("2.Cancel Booking");
    System.out.println();
    op=in.nextInt();
    if(op!=1 && op!=2){
        System.out.println("Try Again!!1");
    }
    }while(op!=1 && op!=2);
    if(op==1){
    int h=db.putBookingTodb(k, user_email,user_phone, vid, vehicle_name, vehicle_number, vehicle_type, renting_date, returning_date, f,u, no, vehicle_renting_amount*no);
    if(h!=0){
        System.out.println("Booking Completed");
        db.makeThatVehicleOnrent(vid);
        int y;
        do{
    System.out.println("1.Continue");
    System.out.println("2.Exit");
    System.out.println();
    y=in.nextInt();
    if(y!=1 && y!=2){
        System.out.println("Try Again!!");
    }
    }while(y!=1 && y!=2);
    if(y==1){
    UserAfterPage(k);
    }
    else{
        System.out.println("Exiting...");
        return;
    }
    }
    else{
        System.out.println("poda punda");
    }
    }
    else{
        System.out.println("Booking Cancelled");
        int h;
        do{
    System.out.println("1.Continue");
    System.out.println("2.Exit");
    System.out.println();
    h=in.nextInt();
    if(h!=1 && h!=2){
        System.out.println("Try Again!!");
    }
    }while(h!=1 && h!=2);
    if(h==1){
    UserAfterPage(k);
    }
    else{
        System.out.println("Exiting...");
        return;
    }
    }
    }
    if(x==1){
        System.out.println("Booking Cancelled");
        int h;
        do{
    System.out.println("1.Continue");
    System.out.println("2.Exit");
    System.out.println();
    h=in.nextInt();
    if(h!=1 && h!=2){
        System.out.println("Try Again!!");
    }
    }while(h!=1 && h!=2);
    if(h==1){
UserAfterPage(k);
    }
    else{
        System.out.println("Exiting...");
        return;
    }
    }
    }
}       
    }
    public void AdminAfterPage(){
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("For inserting the specifications of a vehicle press 1");
        System.out.println("For viewing all the vehicles's specifications press 2");
        System.out.println("For updating a vehicle Specification press 3");
        System.out.println("For deleting a vehicle Specification press 4");
        System.out.println("For viewing all renting history press 5");
        System.out.println("For billing of renting press 6");

        System.out.println("------------------------------------------------------");
        int n=in.nextInt();
        if(n==1){
        insertVehicle();
        }
        else if(n==2){
        searchVehicle();
        }
        else if(n==3){
        updateVehicle();
        }
        else if(n==4){
        deleteVehicle();
        }
        else if(n==5){
        viewhistory();
        }
        else if(n==6){
        billing();
        }
        else{
            System.out.println("Wrong Input!! Try Again");
            System.out.println();
            AdminAfterPage();
        }
        in.close();
    }

    public int insertVehicle(){
      Scanner in=new Scanner(System.in);
      System.out.println();
      System.out.print("Enter Vehicle Name: ");
      String vehicle_name=in.next();
      System.out.println();
      System.out.print("Enter Vehicle Number Plate: ");
      String vehicle_number_plate=in.next();
      System.out.println();
      System.out.print("Enter Vehicle Type: ");
      String vehicle_type=in.next();
      System.out.println();
      System.out.print("Enter Vehicle's run-off: ");
      String vehicle_total_kms=in.next();
      System.out.println();
      System.out.print("Enter Vehicle's status: ");
      String vehicle_status=in.next();
      System.out.println();
      System.out.print("Enter Vehicle's rentcount: ");
      int vehicle_rentcount=in.nextInt();
      System.out.println();
      System.out.print("Enter Vehicle's Service Status: ");
      String vehicle_service_status=in.next();
      System.out.println();
      System.out.print("Enter Vehicle's renting amount: ");
      int vehicle_renting_amount=in.nextInt();
      System.out.println();
      int j=db.InsertVehicleDetails(vehicle_name.toLowerCase(),vehicle_number_plate.toLowerCase(),vehicle_type.toLowerCase(),vehicle_total_kms.toLowerCase(),vehicle_status.toLowerCase(),vehicle_rentcount,vehicle_service_status.toLowerCase(),vehicle_renting_amount);
      if(j==1){
        System.out.println("Vehicle Inserted Successfully");
        System.out.println("Press 0 to continue or 1 to LogOut");
            int u;
            do{
                u=in.nextInt();
                if(u!=0 && u!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(u!=0 && u!=1);
            if(u==0){
                    AdminAfterPage();
                }
            else if(u==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
      }
      else if(j==9){
        System.out.println("Vehicle Already Exists");
        System.out.println("Press 0 to continue or 1 to LogOut");
            int u;
            do{
                u=in.nextInt();
                if(u!=0 && u!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(u!=0 && u!=1);
            if(u==0){
                    AdminAfterPage();
                }
            else if(u==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
      }
      return 0;
    }

public int viewhistory(){
    db.viewHistory();
    System.out.println("Press 0 to continue or 1 to LogOut");
            int u;
            do{
                u=in.nextInt();
                if(u!=0 && u!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(u!=0 && u!=1);
            if(u==0){
                    AdminAfterPage();
                }
            else if(u==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
    return 0;
}

    public int billing(){
        System.out.println();
        System.out.print("Enter renting id: ");
        int id=in.nextInt();
        System.out.println();
        System.out.print("Damage Level: ");
        String level=in.next().toLowerCase();
        System.out.println();
        System.out.print("Distance run: ");
        int dist=in.nextInt();
        System.out.println();
        System.out.print("Enter vehicle id: ");
        int vid=in.nextInt();
        int tot=db.getTotalFromUser(id);
        // System.out.println(tot);
        int total=30000;
        double dmgamount=0;
        int no=db.getDistance(id);
        int d=0;
        int l=0;
        int y=0;
        double ext=0.0;
        if(dist>500){
        d=dist/no; //90
        l=((int)(d*no)/100)%5; //180/100
        ext=(tot*(l*0.15));
        y=(int)(tot+(tot*(l*0.15)));
        }
        else{
        y=tot;
        }
        double i=0.0;
        if(level.equals("high")){
            // System.out.println(total);
            i=total*(0.75);
            // System.out.println(i);
            double j=total-i;
            dmgamount=j;
        }
        if(level.equals("medium")){
            // System.out.println(total);
            i=total*(0.5);
            // System.out.println(i);
            double j=total-i;
            dmgamount=j;
        }
        if(level.equals("low")){
        //    System.out.println(total);
            i=total*(0.2);
            // System.out.println(i);
            double j=total-i;
            dmgamount=j;
        }
        System.out.println();
        System.out.println("Amount to pay: "+y);
        System.out.println("Amount to return :"+(dmgamount));
        System.out.println();
        int kl;
        do{
            System.out.println("Press 0 to get payment in cash or 1 to deduct in the balance amount");
            kl=in.nextInt();
            if(kl!=0 && kl!=1){
                System.out.println("Try Again!!!");
            }
        }while(kl!=0 && kl!=1);
        if(kl==0){
        System.out.println();
        System.out.println("Amount to pay: "+y);
        System.out.println("Amount to return :"+(dmgamount));
        System.out.println();
        int kl1;
        do{
            System.out.println("Press 0 to generate bill");
            kl1=in.nextInt();
            if(kl1!=0){
                System.out.println("Try Again!!!");
            }
            else{
            int hj=db.putBill(id,tot,ext,i,dmgamount);
            if(hj==1){
                int uio=db.updateVehicleStatus(vid);
                int uvk=db.updateVehicleKms(vid,dist);
                int uvc=db.updateVehicleRentCount(vid,1);
                int ups=db.checkKms(vid);
                String upstype=db.checkKms(vid,1);
                if(ups>3000 && upstype.equals("car")){
                int up=db.updateservice(vid);
            }
            else if(ups>1500 && upstype.equals("bike")){
                int up=db.updateservice(vid);
                }
                // System.out.println(ups);
            }
            }
        }while(kl1!=0);
        if(kl1==0){        
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        }
        if(kl==1){
        System.out.println();
        double bal=dmgamount-y;
        if(bal<0){
            bal*=-1;
            System.out.println("Amount to pay: "+bal);
        }else{
        System.out.println("Amount to return :"+(dmgamount-y));
        }
        System.out.println();
        int kl1;
        do{
            System.out.println("Press 0 to generate bill");
            kl1=in.nextInt();
            int hj=db.putBill(id,tot,ext,i,dmgamount-y);
            if(hj==1){
                int uio=db.updateVehicleStatus(vid);
            }
        }while(kl1!=0);
        if(kl1==0){        
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        }
        return 0;
    }
    public int searchVehicle(){
        int n=0;
        do{
        System.out.println();
        System.out.println("1.Search Vehicle By Id");
        System.out.println("2.Search Vehicle By Number Plate");
        System.out.println("3.View All Vehicles");
        System.out.println();
        n=in.nextInt();
        }while(n!=1 && n!=2 && n!=3);
        if(n==1){
        System.out.print("Enter ID: ");
        int id=in.nextInt();
        int u=db.SearchVehicleById(id);
        if(u!=0){
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }else{
            System.out.println("No such vehicle found");
            AdminAfterPage();
        }
        }
        else if(n==2){
        System.out.print("Enter Number: ");
        String num=in.next();
        int u=db.SearchVehicleByNumber(num.toLowerCase());
        if(u!=0){
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else{
            System.out.println("No such vehicle found");
            AdminAfterPage();
        }
        }
        else if(n==3){
        int u=db.SearchVehicle();
        if(u!=0){
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else{
            System.out.println("No such vehicle found");
            AdminAfterPage();
        }
        }
        return 0;
    }
public int updateVehicle(){
    int n=0;
        do{
        System.out.println();
        System.out.println("1.Update Vehicle By Id");
        System.out.println("2.Update Vehicle By Number Plate");
        System.out.println();
        n=in.nextInt();
        if(n!= 1 && n!=2){
            System.out.println("Invalid Try Again");
        }
        }while(n!=1 && n!=2);
        if(n==1){
        System.out.print("Enter ID: ");
        int id=in.nextInt();
        System.out.println();
        System.out.print("Enter the factor to update:");
        String wtu=in.next();
        System.out.println();
        System.out.println("Enter the updated value: ");
        String uv=in.next();
        System.out.println();
        int a;
        int u;
        if(wtu.equals("vehicle_rentcount") || wtu.equals("vehicle_renting_amount")){
            a=Integer.parseInt(uv);
            u=db.UpdateVehicleById(id,wtu.toLowerCase(),a);
            if(u==1){
            System.out.println("Vehicle Details Updated");
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else if(u==-1){
            System.out.println("No such column found");
            AdminAfterPage();
        }
        else{
            System.out.println("No such vehicle found");
            AdminAfterPage();
        }
        }
        else{
            u=db.UpdateVehicleById(id,wtu.toLowerCase(),uv.toLowerCase());
            if(u==1){
            System.out.println("Vehicle Details Updated");
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else if(u==-1){
            System.out.println("No such column found");
            AdminAfterPage();
        }
        else{
            System.out.println("No such vehcile found");
            AdminAfterPage();
        }
        }
        }
        else if(n==2){
        System.out.print("Enter Number: ");
        String id=in.next();
        System.out.println();
        System.out.print("Enter the factor to update:");
        String wtu=in.next();
        System.out.println();
        System.out.println("Enter the updated value: ");
        String uv=in.next();
        System.out.println();
        int a;
        int u;
        if(wtu.equals("vehicle_rentcount") || wtu.equals("vehicle_renting_amount")){
            a=Integer.parseInt(uv);
            u=db.UpdateVehicleByNumber(id.toLowerCase(),wtu.toLowerCase(),a);
            if(u==1){
            System.out.println("Vehicle Details Updated");
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else if(u==-1){
            System.out.println("No such column found");
            AdminAfterPage();
        }
        else{
            System.out.println("No such vehicle found");
            AdminAfterPage();
        }
        }
        else{
            u=db.UpdateVehicleByNumber(id.toLowerCase(),wtu.toLowerCase(),uv);
            if(u==1){
            System.out.println("Vehicle Details Updated");
            System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
        }
        else if(u==-1){
            System.out.println("No such column found");
            AdminAfterPage();
        }
        else{
            System.out.println("No such vehcile found");
            AdminAfterPage();
        }
        }
        }
        return 0;
        }

public int deleteVehicle(){
    int n=0;
        do{
        System.out.println();
        System.out.println("1.Delete Vehicle By Id");
        System.out.println("2.Delete Vehicle By Number Plate");
        System.out.println();
        n=in.nextInt();
        if(n!= 1 && n!=2){
            System.out.println("Invalid Try Again");
        }
        }while(n!=1 && n!=2);
        if(n==1){
            System.out.print("Enter vehicle id: ");
            int vid=in.nextInt();
            int u=db.DeleteVehicleById(vid);
            if(u==1){
                System.out.println("Deleted Successfully");
                System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
            }
            else{
                System.out.println("No such vehicle found");
                AdminAfterPage();
            }
        }
        if(n==2){
            System.out.print("Enter vehicle number: ");
            String vid=in.next();
            int u=db.DeleteVehicleByNumber(vid.toLowerCase());
            if(u==1){
                System.out.println("Deleted Successfully");
                System.out.println("Press 0 to continue or 1 to LogOut");
            int j;
            do{
                j=in.nextInt();
                if(j!=0 && j!=1){
                    System.out.println("Invalid Input!!Try Again");
                }
            }while(j!=0 && j!=1);
            if(j==0){
                    AdminAfterPage();
                }
                else if(j==1){
                    SignUp v=new SignUp();
                    v.signup();
            }
            }
            else{
                System.out.println("No such vehicle found");
                AdminAfterPage();
            }
        }
    return 0;
}

}

public class View {
    public static void main(String[] args) {
    System.out.println("Welcome to DR4NZY Vehicle Services");
    SignUp sign=new SignUp();
    sign.signup();
    }
}
