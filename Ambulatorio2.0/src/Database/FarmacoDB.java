package Database;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FarmacoDB {
    private String nome;
    private String produttore;
    private LocalDateTime data;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FarmacoDB() {
    }

    public boolean caricaDaDB() {
        String query = "SELECT * FROM Farmaco WHERE Data_Prescrizione = '" + this.data + "';";
        System.out.println(query);

        try {
            ResultSet res = DBManager.selectQuery(query);

            if(res.next()) {
                this.setNome(res.getString("Nome"));
                this.setProduttore(res.getString("Produttore"));
                this.setData(LocalDateTime.parse(res.getString("Data_Prescrizione"))); //da vedere sto parse
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    //Aggiungi al Database
    public int insertDB() {
        int ret = 0;

        String query = "INSERT INTO Farmaco(Nome, Produttore, Data_Prescrizione) VALUES ('"
               + this.nome + "', '" + this.produttore + "', '" + this.data + "');";
        System.out.println(query);

        try {
            ret = DBManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = -1;
        }
        return ret;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProduttore(String produttore) {
        this.produttore = produttore;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
