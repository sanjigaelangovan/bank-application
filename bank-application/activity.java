package eecs1022.lab5;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab5Activity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
    }

    Bank bank = new Bank();

    private void setContentsOfTextView(int id, String newContents)
    {


        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    private String getInputOfTextField(int id)
    {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    private String getItemSelected(int id)
    {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;

    }
    private void setResult(String s){
        setContentsOfTextView(R.id.result,s);
    }
    private void resetResult(){
        setContentsOfTextView(R.id.result," ");
    }

    public void createAccountButtonClicked(View view)
    {


        resetResult();
        String name=getInputOfTextField(R.id.inputClient);
        double balance=Double.parseDouble(getInputOfTextField(R.id.inputBalance));
        bank.AccountCreation(name,balance);
        setResult(bank.getResult());
        bank.ResetResult();

    }

    public void completeTransactionButtonClicked(View view)
    {

        resetResult();
        String acc1=bank.conditioncheck(getInputOfTextField(R.id.inputfromClient),'n');
        String acc2=bank.conditioncheck(getInputOfTextField(R.id.inputtoClient),'n');
        double amount= Double.parseDouble(bank.conditioncheck(getInputOfTextField(R.id.amount),'a'));
        int idFrom=bank.getClientId(acc1);
        int idTo=bank.getClientId(acc2);
        String ser=getItemSelected(R.id.spinner);
        bank.ClientTransaction(ser,idFrom,idTo,amount,acc1,acc2);
        setResult(bank.getResult());
        bank.ResetResult();

    }

    public void getOutputStatementButtonClicked(View view){
        resetResult();
        String name = bank.conditioncheck(getInputOfTextField(R.id.inputClient1),'n');
        int id = bank.getClientId(name);
        bank.Statement(id, name);
        setResult(bank.getResult());
        bank.ResetResult();
    }

}