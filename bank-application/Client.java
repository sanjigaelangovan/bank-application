package eecs1022.lab5;

/**
 * Created by user on 8/2/18.
 */
public class Client
{
    String name;
    Transaction[] history;
    int not;
    final int MAX_NUM_TRANS =10;
    double balance = 0;


    public Client()
    {
        history = new Transaction[MAX_NUM_TRANS];
        not = 0;


    }

    public Client(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
        this.history = new Transaction[MAX_NUM_TRANS];
        this.not = 0;


    }

    void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return this.balance;
    }

    public int getNot()
    {
        return this.not;
    }


    public boolean Max_numReached()
    {
        if (this.MAX_NUM_TRANS == this.not)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void addTransaction(String type, double amount)
    {
        if (this.not < this.MAX_NUM_TRANS)
        {
            this.history[not] = new Transaction(type, amount);
            not++;
        }

    }

    public Transaction getTransactionById(int id)
    {
        if (id < not && id >= 0)
        {
            return this.history[id];
        } else
        {
            return null;
        }
    }


}