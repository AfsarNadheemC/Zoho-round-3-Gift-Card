import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Gift {
    Scanner sc = new Scanner(System.in);
    int GiftNo;
    int Pin;
    int Balance;
    Boolean Alive;
    List <int[]> TrandsactionHistory = new ArrayList<> ();
    int points;


    public Gift (int GiftNo, int Pin){
        this.GiftNo=GiftNo;
        this.Pin=Pin;
        this.points=0;
        this.Alive=true;
    }

    public void TopUp (int value){
        if (!this.Alive){
            System.out.println("Card  has Bloked");
        }
        this.Balance+=value;
        int [] tr = new int[3];
        tr [0] = 1;
        tr [1] = value;
        tr [2] = Balance;
        TrandsactionHistory.add(tr);
    }

    public void Purchase (int value){
        if (!this.Alive){
            System.out.println("Card  has Bloked");
        }
        if (this.Balance<value){
            System.out.println("Out of Balance");
            System.out.println("Your Balance "+Balance);            
        }
        this.Balance-=value;
        System.out.println("\nPurchase Amount "+value);
        System.out.println("You have Earned "+(value/100)+" points");
        this.points+=(value/100);
        if (points>=10){
            System.out.println("Total points "+points+"\n\nDo you want to add your points to wallet\n1 yes\n2 no");
            int wallet = sc.nextInt();
            if(wallet==1){
                Balance+=points;
                points=0;
            }
        } 
        System.out.println("Card Balance "+this.Balance);
        int [] tr = new int[3];
        tr [0] = 1;
        tr [1] = value;
        tr [2] = Balance; 
        TrandsactionHistory.add(tr);
    }

    public void Block (){
        this.Alive=false;
        System.out.println("Your Card Has Bloked");
    }   
}
