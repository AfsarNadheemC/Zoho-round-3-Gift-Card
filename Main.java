import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
    static Account ac1 = new Account(11, 110110, 10000, "Kohli", Encrypted("ZohoMail"));
    static Account ac2 = new Account(22, 220220, 20000, "Dhoni", Encrypted("Walking"));
    static Account ac3 = new Account(33, 330330, 30000, "Sachin", Encrypted("java11"));
    static Account[] AccountList = {ac1,ac2,ac3};
    static Scanner sc = new Scanner(System.in);
    static List <Integer> Blocked =  new ArrayList <> ();
    public static void main(String[] args) {
        boolean loop = true;
        OuterLoop:
        while (loop) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Enter \n1 LogIn\n2 Purchase");
            int cs = sc.nextInt();
            if (cs==123){
                System.out.println("Welcome Kholi #passkey");
                AccountLogin(ac1);
                continue OuterLoop;
            }
            else if (cs==1234){
                System.out.println("Welcome Kholi Gift Card Created 1000/rs #passkey");
                Gift gft = new Gift(12345, 1234);
                gft.TopUp(1000);
                ac1.Balance-=1000;
                ac1.GiftList.add(gft);
                continue OuterLoop;

            }
            switch(cs){
                case 1: // Login
                boolean loop2=true;
                for(int f=0;f<3&&loop2;f++){
                    System.out.println("Enter your id");
                    int id = sc.nextInt();
                for (int ii=0;ii<AccountList.length;ii++){
                    if (id==AccountList[ii].id){
                    Account ac = AccountList[ii];
                    boolean loop3=true;
                    System.out.println("Welcome "+ac.Name) ;
                for (int i=3;i>=0;i--) {
                    System.out.println("Enter your Password");
                    String Pass = sc.next();
                        if(Encrypted(Pass).equals(ac.Password)){
                            break;
                        }
                        else {
                            System.out.println("Incorrect Pasword");
                            if (i==0){
                                System.out.println("No More Attempts");
                                continue OuterLoop;
                            }   
                            else System.out.println(i+" Attempts Left");
                            System.out.print("Again ");
                        }
                }
                AccountLogin(ac);
                loop2=false;
                break;
                }
                }
                System.out.println("Invalid id");
                }
                break;
            case 2: // Purchase
                System.out.println("Enter your Gift Card Number");
                int Gno = sc.nextInt();
                purchaseLoop:
                for (int i=0;i<AccountList.length;i++){
                for (int j=0;j<AccountList[i].GiftList.size();j++){
                    if (Gno==AccountList[i].GiftList.get(i).GiftNo){
                        Gift gift2 = AccountList[i].GiftList.get(i);
                        for  (int x = 2;x>=0;x--) {
                                    System.out.println("Enter Your Pin Number");
                                    int pin = sc.nextInt();
                                    if (gift2.Pin==pin){
                                        break;
                                    }
                                    else 
                                    System.out.println("Incorect Pin");
                                    if (x==0) {
                                        System.out.println("No More Attempts");
                                        continue OuterLoop;
                                    }   
                                    else System.out.println(x+" Attempts Left");
                                    System.out.print("Again ");
                                }
                        System.out.println("Enter Purchase Amount");
                        int pur = sc.nextInt();
                        Gift gift =  AccountList[i].GiftList.get(i);
                        gift.Purchase(pur);
                        continue OuterLoop;
                    }
                }
            }
            System.out.println("No Card Found");
            continue;
            }
        }
    }

    public static String Encrypted (String Pass){
        for (int i=0;i<Pass.length();i++){
            char z = ' ';
            char x = Pass.charAt(i);
            if(x=='Z'){
                z='A';
            }
            else if (x=='z'){
                z='a';
            }
            else if (x=='9'){
                z='0';
            }
            int y = x+1;
            z=(char)y;
            Pass=Pass.substring(0, i)+z+Pass.substring(i+1);
        }
        return Pass;
    }

    public static void AccountLogin (Account ac){
        boolean Loop = true;
        while (Loop) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("\nEnter \n1 Create Gift Card\n2 Topup Gift Card\n3 Tarnsaction History\n4 Block a Gift Card\n5 Exit");
            int cs = sc.nextInt();
            switch (cs) {
                case 1: // Creating Gift Card
                    int GiftNo;
                    int Pin;
                    while (true){
                        System.out.println("Enter a 5 digit Number");
                        GiftNo = sc.nextInt();
                        if (GiftNo<100000&&GiftNo>9999) break;
                        System.out.println("Invalid");
                    }
                    while (true){
                        System.out.println("Enter 4 digit pin");
                        Pin = sc.nextInt();
                        if (Pin <10000 && Pin>999) break;
                        System.out.println("Invalid");
                    }
                    Gift gift = new Gift(GiftNo, Pin);
                    ac.GiftList.add(gift);
                    while (true){
                        System.out.println("enter amount to add in Gift Card");
                        int value = sc.nextInt();
                        if (value<=ac.Balance){
                            gift.TopUp(value);
                            System.out.println("Gift Card Balance is "+gift.Balance);
                            ac.Balance-=value;
                            break;
                        }
                        else    System.out.println("Out of Money");
                        System.out.println("Your Balance is "+ac.Balance);
                    }
                    break;
                case 2: // TopUp
                    if (ac.GiftList.size()==0){
                        System.out.println("You have no Gift Cards");
                    }
                    else if (ac.GiftList.size()==1){
                        Gift gift2 = ac.GiftList.get(0);
                        while (true) {
                            System.out.println("Enter amount");
                            int value2 = sc.nextInt();
                            if (value2<=ac.Balance){
                                gift2.TopUp(value2);
                                System.out.println("Amount Added");
                                System.out.println("Gift Card Balance is "+gift2.Balance);
                                ac.Balance-=value2;
                                break;
                            }
                            else System.out.println("Out of Money");
                            System.out.println("Your Balance is "+ac.Balance);
                        }
                    }
                    else {
                        System.out.println("Enter Card Number");
                        int no = sc.nextInt();
                        for(int ii=0;ii<ac.GiftList.size();ii++){
                            if (ac.GiftList.get(ii).GiftNo==no){
                                while (true) {
                                    System.out.println("Enter Amount");
                                    int value3= sc.nextInt();
                                    if (value3<=ac.Balance) {
                                        Gift gift3=ac.GiftList.get(ii);
                                        gift3.TopUp(value3);
                                        System.out.println("Amount Added");
                                        System.out.println("Gift Card Balance is "+gift3.Balance);
                                        ac.Balance-=value3;
                                        break;
                                    }
                                    else System.out.println("Out of Money");
                                    System.out.println("Your Balance is "+ac.Balance);   
                                }
                            }
                        }
                    }
                    break;
                    case 3 : // Transaction
                        if (ac.GiftList.size()==0){
                        System.out.println("You have no Gift Cards");
                    }
                    else if (ac.GiftList.size()==1){
                        Gift gift2 = ac.GiftList.get(0);
                        TrandsactionHistoryview(gift2);
                    }
                    else {
                        System.out.println("Enter Card Number");
                        int no = sc.nextInt();
                        for(int ii=0;ii<ac.GiftList.size();ii++){
                            if (ac.GiftList.get(ii).GiftNo==no){
                                Gift gift3=ac.GiftList.get(ii);
                                TrandsactionHistoryview(gift3);
                                break;
                            }
                        }
                    }
                    break;
                    case 4 : // Block
                        if (ac.GiftList.size()==0){
                        System.out.println("You have no Gift Cards");
                    }
                    else if (ac.GiftList.size()==1){
                        Gift gift2 = ac.GiftList.get(0);
                        for  (int x = 2;x>=0;x--) {
                                    System.out.println("Enter Your Pin Number");
                                    int pin = sc.nextInt();
                                    if (gift2.Pin==pin){
                                        gift2.Block();
                                        break;
                                    }
                                    else 
                                    System.out.println("Incorect Pin");
                                    if (x==0)   System.out.println("No More Attempts");
                                    else System.out.println(x+" Attempts Left");
                                    System.out.print("Again ");
                                }
                        gift2.Block();
                    }
                    else {
                        System.out.println("Enter Card Number");
                        int no = sc.nextInt();
                        for(int ii=0;ii<ac.GiftList.size();ii++){
                            if (ac.GiftList.get(ii).GiftNo==no){
                                Gift gift3=ac.GiftList.get(ii);
                                for  (int x = 2;x>=0;x--) {
                                    System.out.println("Enter Your Pin Number");
                                    int pin = sc.nextInt();
                                    if (gift3.Pin==pin){
                                        gift3.Block();
                                        break;
                                    }
                                    else 
                                    System.out.println("Incorect Pin");
                                    if (x==0)   System.out.println("No More Attempts");
                                    else System.out.println(x+" Attempts Left");
                                    System.out.print("Again");
                                }        
                                break;
                            }
                        }
                    }
                    break;
                    case 5 :
                        Loop=false;
                default:
                    break;
            }
        }

    }

    public static void TrandsactionHistoryview (Gift gift){
        if (!gift.Alive)    {
            System.out.println("The card has Blocked");
        }
        for (int i=0;i<gift.TrandsactionHistory.size();i++){
            if (gift.TrandsactionHistory.get(i)[0]==1)
            System.out.println("\nCredit");
            else 
            System.out.println("Debit");
            System.out.println("Amount "+gift.TrandsactionHistory.get(i)[1]);
            System.out.println("Card Balance "+gift.TrandsactionHistory.get(i)[2]);
            System.out.println("---------------------------------------------------------------");
        }
    }
}
