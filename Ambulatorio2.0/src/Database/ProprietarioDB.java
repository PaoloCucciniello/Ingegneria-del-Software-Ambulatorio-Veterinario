package Database;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ProprietarioDB {
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String username;

    public ProprietarioDB() { super();}

    public ProprietarioDB(String u) {
        this.username = u;
        checkDB();
    }

    public  ProprietarioDB(String nom, String cogn, String ind, String tel, String user){
        this.nome = nom;
        this.cognome = cogn;
        this.indirizzo = ind;
        this.telefono = tel;
        this.username = user;
    }

    public boolean checkDB() {
        String Query = "SELECT * FROM Proprietario WHERE NumTelefono = '" + this.telefono + "';";
        System.out.println(Query);

        try {
            ResultSet res = DBManager.selectQuery(Query);
            if(res.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public  int addtoDB() {
        int result = 0;
        String Query = new String ("INSERT INTO Proprietario(Username_Account, Nome, Cognome, Indirizzo, NumTelefono) VALUES ('" +
                this.username + "', '"  + this.nome  + "', '"  + this.cognome + "', '"  + this.indirizzo + "', '"  + this.telefono + "')");
        try {
            result = DBManager.updateQuery(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }
}
