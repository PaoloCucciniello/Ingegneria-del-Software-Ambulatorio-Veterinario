package Entity;
import Database.AccountDB;

public class Account{
    private  String username;
    private String password;
    private static int tipo;


    public Account(String user, String pass, int type){
        this.username = user;
        this.password = pass;
        this.tipo = type;
    }

    public Account(String user, String pass){
        this.username = user;
        this.password = pass;
    }

    public Account(AccountDB acc) {
        this.username = acc.getUsername();
        this.password = acc.getPassword();
    }

    public  String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkAcc(){
        AccountDB accDB = new AccountDB(this.username);
        return accDB.checkAccount();
    }

    public boolean checkCrede(){
        AccountDB accDB = new AccountDB(this.username, this.password);
        return accDB.checkCredenziali();
    }

    public  int aggiungiAccount(){
      AccountDB accDB = new AccountDB(this.username, this.password);
        return accDB.addtoDB();
    }
}