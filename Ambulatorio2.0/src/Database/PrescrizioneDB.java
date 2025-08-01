package Database;

import java.time.LocalDateTime;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrescrizioneDB {
    private LocalDateTime data;
    private String diagnosi;
    private String cure;
    private Double costo;

    public PrescrizioneDB() {super();}

    public void setData(LocalDateTime data) {
        this.data = data;
    }



    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public void setDiagnosi(String diagnosi) {
        this.diagnosi = diagnosi;
    }

    public LocalDateTime getData() {
        return data;
    }


    public Double getCosto() {
        return costo;
    }

    public String getCure() {
        return cure;
    }

    public String getDiagnosi() {
        return diagnosi;
    }

    public PrescrizioneDB(LocalDateTime da, String di, String cu, Double co) /*, ArrayList<Farmaco> l)*/ {
        this.data = da;
        this.cure = cu;
        this.diagnosi = di;
        this.costo = co;
    }

    public boolean readDB() {
        String query = "SELECT * FROM Prescrizione WHERE Data_Visita = '" + this.data + "';";
        System.out.println(query);

        try {
            ResultSet res = DBManager.selectQuery(query);
            if(res.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int insertDB() {
        int res = 0;
        String query = "INSERT INTO Prescrizione(Data_Visita, Diagnosi, Costo_Visita, Cure_Mediche) VALUES ('" + this.data + "', '"
                + this.diagnosi + "', '" + this.costo + "', '" + this.cure + "');";
        System.out.println(query);

        try {
            res = DBManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            res = -1;
        }
        return res;
    }

    public ArrayList<PrescrizioneDB> resocontoGiornalieroDB(){
        int res = 0;
        LocalDateTime now = LocalDateTime.now();
        ArrayList<PrescrizioneDB> visiteOdierne = new ArrayList<>();
        //dato che LocalDateTime ha anche le ore, otteniamo l'anno, il mese, e il giorno, e poi settiamo ore minuti e secondi al min e max
        String Query = "SELECT * FROM Prescrizione WHERE Data_Visita BETWEEN '" + now.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0) + "' AND '" + now.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 23, 59, 59) + "'";
        double totaleRicavato = 0.0;

        // Ottieni le visite odierne dal database
        System.out.println(Query);

        try {

            ResultSet result = DBManager.selectQuery(Query);

            while (result.next()) {
                PrescrizioneDB prescrizione = new PrescrizioneDB();
                prescrizione.setData(result.getTimestamp("Data_Visita").toLocalDateTime());
                prescrizione.setDiagnosi(result.getString("Diagnosi"));
                prescrizione.setCosto(result.getDouble("Costo_Visita"));
                visiteOdierne.add(prescrizione);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return visiteOdierne;
    }

//Query per trovare le prescrizione senza visita
    public ArrayList<String> PrescrizioneSenzaVisita() {
        ArrayList<String> visiteSenzaPrescrizione = new ArrayList<>();
        LocalDateTime currentDate = LocalDateTime.now();
        String query = "SELECT Data FROM Visita LEFT JOIN Prescrizione ON Visita.Data = Prescrizione.Data_Visita" +
                " WHERE Prescrizione.Data_Visita IS NULL AND Data <= '" + currentDate + "'";

        try {
            ResultSet result = DBManager.selectQuery(query);

            while (result.next()) {
                String dataVisita = result.getString("Data");
                visiteSenzaPrescrizione.add(dataVisita);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Valori dell'array visiteSenzaPrescrizione:");
        for (String dataVisita : visiteSenzaPrescrizione) {
            System.out.println(dataVisita);
        }

        return visiteSenzaPrescrizione;
    }

}




