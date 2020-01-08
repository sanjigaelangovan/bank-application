package eecs1022.lab5;

/**
 * Created by user on 8/2/18.
 */
public class Transaction

{
    double amount;
    String type;
    public Transaction(){
        this.type=null;
        this.amount=0;
    }

    public Transaction(String type,double amount)
    {
        this.type=type;
        this.amount=amount;
    }

    void setType(String type)
    {
        this.type = type;
    }

    void setAmount(double amount)
    {
        this.amount = amount;
    }

    public String getType(){
        return this.type;
    }
    public double getAmount(){
        return this.amount;
    }
}



