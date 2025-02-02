package ArrayList;
import java.util.ArrayList;
import java.util.Random;

public class BankAcct
{
    //attributes
    private double balance;

    //constructors
    public BankAcct()
    {
        balance =0.0;
    }

    //Accessors
    public double getBalance()
    {
        return balance;
    }

    //Mutator
    public double deposit(double amt)
    {
        balance = balance + amt;
        return balance;
    }

    public double setBalance(double amt)
    {
        balance = amt;
        return balance;
    }


    public static void aList3()
    {
        //printing arrayList

        //instantiate an arrayList of Time objects

        //put ten random Time objects into the array
        //instantiate an arrayList of Time objects
        ArrayList<Time> timeList = new ArrayList<Time>();


        //put ten random Time objects into the array
        timeList.add(new Time (3,30,30,true));
        timeList.add(new Time (3,30,30,true));
        timeList.add(new Time (3, 20, 30, true));
        timeList.add(new Time (4, 30, 40, true));
        timeList.add(new Time (5, 15, 20, true));
        timeList.add(new Time (6, 20, 30, true));
        timeList.add(new Time (11, 15, 20, true));
        timeList.add(new Time (10, 20, 30, true));
        timeList.add(new Time (9, 10, 45, true));
        timeList.add(new Time (6, 15, 20, true));

        //Print the arrayList with title "Original arrayList"

        System.out.println("Original ArrayList");
        for(Time time: timeList){
            System.out.println(time. getHour() + ":" + time.getMinute() + ":" + time.getSeconds());
        }

        //adding all morning time objects to end of arrayList
        int indexToAddMorningObj = timeList.size()-1;
        for(int i=0;i<timeList.size();i++) {
            Time time = timeList.get(i);
            if(time.getAM())
            {
                timeList.remove(i);
                timeList.add(indexToAddMorningObj--, time);
            }
        }

    }

    public static void alist4()
    {
        //declare b0fA as an arrayList of 10 BankAcct objects
        ArrayList<BankAcct> bofa = new ArrayList<>();

        //declare chase as an ordinary array of 10 BankAcct objects
        BankAcct[] chase = new BankAcct[10];

        //initialize all accounts with random balances between
        //$100 and $500
        for(int i=0;i<chase.length;i++) {
            BankAcct account = new BankAcct();
            Random random = new Random();
            double balance =   ((Math.random() * (400)) + 100);
            account.setBalance(balance);
            chase[i] = account;

        }

        for(int i=0;i<10;i++) {
            BankAcct account = new BankAcct();
            Random random = new Random();
            double balance =   ((Math.random() * (400)) + 100);
            account.setBalance(balance);
            bofa.add(account);
        }


        //print the arrayList accounts followed by title "original b0fA"
        System.out.println("Original bofA");
        for(BankAcct account: bofa) {
            System.out.println(account.getBalance());
            System.out.println();
        }
        //print the arry accounts followed by title "original chase"
        System.out.println("Original chase");
        for(BankAcct account: chase) {
            System.out.println(account.getBalance());
            System.out.println();
        }

        //in the arrayList accounts put all the account with more than
        //$300 in front of the arrayList
        int startindex = 0;
        for(int i=0;i<bofa.size();i++) {

            BankAcct account = bofa.get(i);
            double balance = account.getBalance();
            if(balance > 300) {
                bofa.remove(i);
                bofa.add(startindex++, account);
            }
        }
        //print the arrayList accounts followed by title "big b0fA balance in front"
        System.out.println("big b0fA balance in front");
        for(BankAcct account: bofa) {
            System.out.println(account.getBalance());
            System.out.println();
        }

        //declare a new array chase2. Copy all the chase accounts larger
        //than $300 into chase2 follow by the remaining chase accounts.

        BankAcct[] chase2 = new BankAcct[10];
        int startIndex=0;
        int endIndex=chase2.length-1;
        for(BankAcct account: chase) {

            double balance = account.getBalance();
            if(balance > 300) {
                chase2[startIndex++] = account;
            }
            else chase2[endIndex--] = account;
        }

        //set chase to the new chase2 array.
        chase = chase2;

        //print the chase accounts followed by title "big chase balance in front"
        System.out.println("big chase balance in front");
        for(BankAcct account: chase) {
            System.out.println(account.getBalance());
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        BankAcct account = new BankAcct();
        aList3();
        alist4();
    }
}
