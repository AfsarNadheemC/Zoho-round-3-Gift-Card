import java.util.ArrayList;
import java.util.List;

public class Account {
    int id;
    int AccountNo;
    int Balance;
    String Name;
    String Password;
    List <Gift> GiftList = new ArrayList<>();

    public Account (int id , int AccountNo , int Balance, String Name, String Password){
        this.AccountNo = AccountNo;
        this.Balance = Balance;
        this.Name=Name;
        this.Password=Password;
        this.id=id;
    }
     
}