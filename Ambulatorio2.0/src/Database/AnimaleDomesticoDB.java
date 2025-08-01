package Database;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AnimaleDomesticoDB {
    private String chip;
    private String nome;
    private String razza;
    private String colore;
    private String taglia;
    private String proprietario;

    public AnimaleDomesticoDB() { super();}

    //Funzione per popolare un entita AnimaleDomestico partendo dal suo chip, chiave primaria nel database
    public AnimaleDomesticoDB(String chip) {
        this.chip = chip;
        caricaDaDB();
    }

    public AnimaleDomesticoDB(String ch, String no, String raz, String colo, String tagl, String prop){
        this.chip = ch;
        this.nome = no;
        this.razza = raz;
        this.colore = colo;
        this.taglia = tagl;
        this.proprietario = prop;
    }

    public boolean caricaDaDB() {
        String query = "SELECT * FROM AnimaleDomestico WHERE Codice = '" + this.chip + "';";
        System.out.println(query);

        try {
            ResultSet res = DBManager.selectQuery(query);

            if(res.next()) {
                this.setNome(res.getString("nome"));
                this.setColore(res.getString("colore"));
                this.setTaglia(res.getString("taglia"));
                this.setRazza(res.getString("razza"));
                this.setProprietario(res.getString("Username_Proprietario"));
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
//Aggiungi al database un animale
    public int insertDB() {
        int ret;
        String query = "INSERT INTO AnimaleDomestico(Codice, Nome, Razza, Colore, Taglia, Username_Proprietario) VALUES ('"
                + this.chip + "', '" + this.nome + "', '" + this.razza + "', '" + this.colore + "', '" + this.taglia + "', '" + this.proprietario + "');";
        System.out.println(query);

        try {
            ret = DBManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret = 0;
        }
        return ret;
    }

    //Funzione per contare tutti gli animali appartenenti ad un Proprietario
    public static int CountAnimali(String Username){
        String Query = new String("SELECT * FROM AnimaleDomestico WHERE Username_Proprietario = '" + Username + "';");
        System.out.println(Query);
        int count = 0;
        try{
            ResultSet result = DBManager.selectQuery(Query);
            while (result.next()){
                count++;
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    public static String AnimalName(String Username, int posizione){
        String Query = new String("SELECT * FROM AnimaleDomestico WHERE Username_Proprietario = '" + Username + "'" +
                "LIMIT 1 OFFSET " + posizione + ";");
        System.out.println(Query);
        String Temp = null;
        try{

            ResultSet result = DBManager.selectQuery(Query);

            while(result.next()){
                System.out.println("Codice Chip: " + result.getString("Codice"));
                Temp =  result.getString("Codice")
                        + " " +
                        result.getString("Nome");
            }

        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return Temp;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public void setRazza(String razza) {
        this.razza = razza;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getNome() {
        return nome;
    }

    public String getChip() {
        return chip;
    }

    public String getRazza() {
        return razza;
    }

    public String getTaglia() {
        return taglia;
    }

    public String getColore() {
        return colore;
    }

    public String getProprietario() {
        return proprietario;
    }
}
