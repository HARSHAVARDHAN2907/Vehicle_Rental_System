class DBFiles {
    Model mod=new Model();

    public int PutSignUpDetials(String email, String name, String phone, String role,String pass, String gender, String dob) {
      int g=mod.PutSignUpDetialsToDb(email, name, phone, role, pass, gender, dob);
      return g;
    }

    public int CheckLoginDetails(String email,String pass){
        int g=mod.CheckLoginDetailsInDB(email, pass);
        return g;
    }

    public int InsertVehicleDetails(String name,String number,String type,String total,String status,int rentcount,String serviceStatus,int amount){
        int h=mod.InsertVehicleDetailsToDB(name, number, type, total, status, rentcount, serviceStatus, amount);
        return h;
    }

    public int SearchVehicleById(int id){
        int h=mod.SearchVehicleByIdFromDB(id);
        return h;
    }

    public int SearchVehicleByNumber(String number){
        int h=mod.SearchVehicleByNumberFromDB(number);
        return h;
    }
    public int SearchVehicle(){
        int h=mod.SearchVehicleFromDB();
        return h;
    }
    public int UpdateVehicleById(int id,String factor,int uv){
        int h=mod.UpdateVehicleById(id,factor,uv);
        return h;
    }
    public int UpdateVehicleById(int id,String factor,String uv){
        int h=mod.UpdateVehicleById(id,factor,uv);
        return h;
    }

    public int UpdateVehicleByNumber(String id,String factor,int uv){
        int h=mod.UpdateVehicleByNumber(id,factor,uv);
        return h;
    }
    public int UpdateVehicleByNumber(String id,String factor,String uv){
        int h=mod.UpdateVehicleByNumber(id,factor,uv);
        return h;
    }

    public int DeleteVehicleById(int id){
        int h=mod.DeleteVehicleById(id);
        return h;
    }
    public int DeleteVehicleByNumber(String id){
        int h=mod.DeleteVehicleByNumber(id);
        return h;
    }

    public int SearchVehicleyName(String name){
        int h=mod.SearchVehicleByNameFromDB(name);
        return h;
    }
    public int SearchVehicleyType(String type){
        int h=mod.SearchVehicleByTypeFromDB(type);
        return h;
    }

    public int getUserId(String email){
        int h=mod.getUserId(email);
        return h;
    }
    public String getUserEmail(int email){
        String h=mod.getUserEmail(email);
        return h;
    }
    public String getUserPhone(int email){
        String h=mod.getUserPhone(email);
        return h;
    }
    public String getVehicleName(int email){
        String h=mod.getVehicleName(email);
        return h;
    }
    public String getVehicleType(int email){
        String h=mod.getVehicleType(email);
        return h;
    }
    public String getVehicleNumber(int email){
        String h=mod.getVehicleNumber(email);
        return h;
    }

    public int getAmount(int id){
        int h=mod.getAmount(id);
        return h;
    }

    public int putBookingTodb(int userid,String useremail,String userphone,int vid,String vname,String vno,String vtype,String rentdate,String returndate,String depgiven,int depamount,int rentdays,int rentamount){
    int h=mod.putBookingTodb(userid, useremail, userphone, vid, vname, vno, vtype, rentdate, returndate, depgiven, depamount, rentdays, rentamount);
    return h;
    }

    public void makeThatVehicleOnrent(int vid){
        mod.MakeVehicleOnrent(vid);
    }

    public void viewHistory(){
        mod.viewHistory();
    }

    public int getTotalFromUser(int id){
        int h=mod.getTotalFromUser(id);
        return h;
    }
    public int getDistance(int id){
        int h=mod.getDistance(id);
        return h;
    }
    public int putBill(int id,int total,double ext,double fine,double amt){
        int h=mod.putBillToDb(id, total, ext, fine, amt);
        return h;
    }

    public int updateVehicleStatus(int vid){
        int h=mod.updateVehicleStatus(vid);
        return h;
    }
    public int updateVehicleKms(int vid,int kms){
        int h=mod.updateVehicleKms(vid,kms);
        return h;
    }
    public int updateVehicleRentCount(int vid,int kms){
        int h=mod.updateVehicleRentCount(vid,kms);
        return h;
    }
    public int updateservice(int vid){
        int h=mod.updateservice(vid);
        return h;
    }   

    public int checkKms(int vid){
        int h=mod.checkKms(vid);
        return h;
    }
    public String checkKms(int vid,int a){
        String h=mod.checkKms(vid,a);
        return h;
    }

}
