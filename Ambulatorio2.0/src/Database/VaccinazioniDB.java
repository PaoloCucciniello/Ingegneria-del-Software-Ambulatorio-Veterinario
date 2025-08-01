package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class VaccinazioniDB {

    private String tipo;
    private LocalDate data;

    private String chipAnimale;

    public VaccinazioniDB() {
        super();
    }

    public VaccinazioniDB(String tp, LocalDate giorno, String chp) {
        this.tipo = tp;
        this.data = giorno;
        this.chipAnimale = chp;
    }

    public boolean readDB() {
        String query = "SELECT * FROM Vaccinazione WHERE Data = '" + this.data + "' AND Codice_Animale = '" + this.chipAnimale + "';";
        System.out.println(query);

        try {
            ResultSet res = DBManager.selectQuery(query);
            if (res.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertDB() {
        int res = 0;
        String query = "INSERT INTO Vaccinazione(Tipo, Data, Codice_Animale) VALUES ('" + this.tipo + "', '"
                + this.data + "', '" + this.chipAnimale + "');";
        System.out.println(query);

        try {
            res = DBManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            res = -1;
        }
        return res;
    }

    public static int AggiornaVaccinazione(String CodiceChip, LocalDateTime Data) {
        int result = 0;
        String Query = new String("UPDATE Vaccinazione SET Data = '" + Data + "' WHERE Codice_Animale = '" + CodiceChip + "';");
        System.out.println(Query);
        try {
            result = DBManager.updateQuery(Query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public static int count() {
        int count = 0;
        String Query = new String("SELECT * FROM Vaccinazione;");
        System.out.println(Query);
        try {
            ResultSet result = DBManager.selectQuery(Query);
            if (result.next()) {
                count++;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static String printVaccinazioniScadute() {
        String Query = new String("SELECT * FROM Vaccinazione WHERE Data < NOW();");
        System.out.println(Query);
        try {
            ResultSet result = DBManager.selectQuery(Query);
            if (result.next()) {
                return  result.getString("Codice_Animale")
                        + " Scadenza: " +
                        result.getString("Data")
                        + "Tipo" +
                        result.getString("Tipo");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
