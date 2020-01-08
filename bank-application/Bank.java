package eecs1022.lab5;

/**
 * Created by user on 8/2/18.
 */
public class Bank


{

    Client[] clients;
    final int Max_num;
    int noc;
    String result;
    public Bank(){
        this.Max_num=6;
        this.clients=new Client[Max_num];
        this.noc=0;
        this.result=" ";
    }
    public void addClient(String name,double balance)
    {
        if(this.noc<this.Max_num)
        {
            this.clients[this.noc] = new Client(name, balance);
            this.noc++;
        }
    }
    public boolean Max_numReached(){
        if(this.Max_num==this.noc)
            return true;
        else
            return false;
    }
    public int getnoc()
    {
        return this.noc;
    }
    public int getClientId(String name){
        for(int i=0;i<noc;i++){
            if(clients[i].getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public Client getClientbyId(int id){
        if(id<this.noc&&id>=0){
            return this.clients[id];
        }
        else{
            return null;
        }
    }
    public String getResult(){
        return this.result;
    }
    public Client getClientbyName(String name){
        for(int i=0;i<noc;i++){
            if(clients[i].getName().equals(name)){
                return clients[i];
            }
        }
        return null;
    }
    public void setClientbyId(int id,Client c){
        if(id<Max_num&&id>=0){
            this.clients[id]=c;
        }
    }
    public void ResetResult(){
        this.result=" ";
    }
    public void deposit(int id,double d){
        this.clients[id].setBalance(this.clients[id].getBalance()+d);

    }
    public void withdraw(int id,double w){
        this.clients[id].setBalance(this.clients[id].getBalance()-w);
    }
    public void transfer(double a,int idFrom,int idTo){
        this.withdraw(idFrom,a);
        this.deposit(idTo,a);
    }
    public void AccountCreation(String name,double amount){
        if(!this.Max_numReached()){
            int e=this.getClientId(name);
            if(e!=-1){
                this.result=String.format("Error: Client %s already exists",name);
            }
            else if(amount<=0){
                this.result="Error: Non-Positive Initial Balance";
            }
            else{
                this.addClient(name,amount);
                this.result=this.setNameandBalance();
            }
        }
        else{
            this.result="Error: Maximum number of Clients Reached";
        }
    }
    public void Statement(int id,String name){
        if(id!=-1){
            Client c=this.getClientbyId(id);
            Transaction t;
            String s=String.format("Statement of Client %s (Current balance $%.2f)\n", c.getName(), c.getBalance());
            for(int i=0;i<c.getNot();i++){
                t=c.getTransactionById(i);
                s+=String.format("Transaction %s: $%.2f\n",t.getType(),t.getAmount());
            }
            this.result=s;
        }
        else{
            this.result=String.format("Error: Client %s does not exist.",name);
        }
    }
    private String setNameandBalance(){
        String s="Updated balances of Clients:\n";
        for(int i=0;i<this.noc;i++){
            s+=String.format("%s: $%.2f\n",this.clients[i].getName(),this.clients[i].getBalance());
        }
        return s;
    }
    public void ClientTransaction(String s,int idFrom,int idTo,double amount,String account1,String account2){
        if(s.equals("deposit"))
        {
            if(idTo==-1){
                this.result=String.format("Error: Client %s does not exist.",account2);
            }
            else if(amount<=0){
                this.result="Error: Non-Positive Amount";
            }
            else{
                this.deposit(idTo,amount);
                this.clients[idTo].addTransaction("deposit",amount);
                this.result=this.setNameandBalance();
            }

        }
        else if(s.equals("withdraw")){
            if(idFrom==-1){
                this.result=String.format("Error: Client %s does not exist.",account1);
            }
            else if(amount<=0){
                this.result="Error: Non-Positive Amount";
            }
            else if(amount>this.getClientbyId(idFrom).getBalance()){
                this.result="Error: Amount too large to withdraw.";
            }
            else{
                this.withdraw(idFrom,amount);
                this.clients[idFrom].addTransaction("withdraw",amount);
                this.result=this.setNameandBalance();
            }

        }
        else if(s.equals("transfer")){
            if(idFrom==-1){
                this.result=String.format("Error: Client %s does not exist.",account1);
            }
            else if(idTo==-1){
                this.result=String.format("Error: Client %s does not exist.",account2);
            }
            else if(amount<=0){
                this.result="Error: Non-Positive Amount";
            }
            else if(amount>this.getClientbyId(idFrom).getBalance()){
                this.result="Error: Amount too large to Transfer.";
            }
            else{
                this.transfer(amount,idFrom,idTo);
                this.clients[idFrom].addTransaction("withdraw",amount);
                this.clients[idTo].addTransaction("deposit",amount);
                this.result=this.setNameandBalance();
            }

        }
    }
    public String conditioncheck(String s,char id){
        if(id=='a'){
            if(s.equals(null))
                s="0";
        }
        if(id=='n'){
            if(s.equals(null))
                s=" ";
        }
        return s;
    }
}

