package Database;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB {
    private String username;
    private String password;

    public AccountDB() {
        super();
    }
    public AccountDB(String username){
        this.username = username;
    }
    public AccountDB(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Funzione che prende i dati dal database
    public boolean caricaDaDB() {
        String query = new String("SELECT * FROM Account WHERE Username = '" + this.username + "' AND Password = '" + this.password + "';");
        System.out.println(query);

        try {
            ResultSet res = DBManager.selectQuery(query);
            if(res.next()) {
                this.setPassword(res.getString("Password"));
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Funzione per aggiungere un Account al database
    public int addtoDB() {
        int result = 0;
        String Query = new String ("INSERT INTO Account(Username, Password, Tipo) VALUES ('" + this.username + "', '" + this.password + "', 0)");
        System.out.println(Query);
        try {
            result = DBManager.updateQuery(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }


//Funzione per eliminare un Account da un database
    public int deleteDB(){
        int ret = 0;
        String query = "DELETE FROM Account WHERE Username = " + this.username + "')";
        System.out.println(query);

        try {
            ret = DBManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;                   //errore di scrittura
        }
        return ret;
    }

    //Funzione per controllare se è presente un account nel database
    public boolean checkAccount(){
        String Query = new String("SELECT * FROM Account WHERE Username = \'" + this.username + "\' AND Tipo = 1");
        System.out.println(Query);
        try{

            ResultSet result = DBManager.selectQuery(Query);

            if(result.next()){
                return true;
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return false;
    }


//Funzione per controllare le credenzialità di un account nel database
    public boolean checkCredenziali(){
        String Query = new String("SELECT * FROM Account WHERE Username = \'" + this.username + "\' AND Password = \'" + this.password + "\';");
        System.out.println(Query);
        try{

            ResultSet result = DBManager.selectQuery(Query);

            if(result.next()){
                return true;
            }

        } catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
