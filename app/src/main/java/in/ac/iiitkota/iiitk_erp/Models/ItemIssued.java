package in.ac.iiitkota.iiitk_erp.Models;

public class ItemIssued {
    public String itemName,lastDate,issuedDate,code;

    public String t1,t2,t3;
    public ItemIssued(){ }
    public ItemIssued(String itemName,String lD,String iD,String code){
        this.itemName = itemName;
        this.code = code;
        lastDate = lD;
        issuedDate = iD;

        t1 = itemName;
        t2 = "Last Date: "+lastDate+" Issued Date: "+issuedDate;
        t3 = "Item Code: "+code;
    }
}
