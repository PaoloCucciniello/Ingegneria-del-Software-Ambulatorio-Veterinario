package Database;

import Entity.AnimaleDomestico;
import Entity.Proprietario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class VisitaDB {

    private LocalDateTime dataPrenotata;


    public VisitaDB(){ super();}

    public VisitaDB(LocalDateTime giorno /* , Proprietario prop, AnimaleDomestico a*/){
        this.dataPrenotata = giorno;
       // this.proprietario = prop;
        // this.animale = a;

    }

    public boolean checkDB(){
        String Query = new String("SELECT * FROM Visita WHERE Data = \'" + this.dataPrenotata + "\';");
        System.out.println(Query);
        try{
            ResultSet result = DBManager.selectQuery(Query);
            if(result.next()){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean checkPrenotazione(String CodiceChip){
        String Query = new String("SELECT * FROM Visita WHERE Codice_Animale = \'" + CodiceChip + "\';");
        System.out.println(Query);
        try{
            ResultSet result = DBManager.selectQuery(Query);
            if(result.next()){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static String printDB(int position){
        String Query = new String("SELECT * FROM Visita LIMIT 1 OFFSET " + position + ";");
        System.out.println(Query);
        try{
            ResultSet result = DBManager.selectQuery(Query);
            while(result.next()){
                return result.getString("Data");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int VisitePassate( ){
        int result = 0;
        String Query = new String("UPDATE Visita SET Status = 2 WHERE Data = '" + this.dataPrenotata + "';");
        System.out.println(Query);
        try{
            result = DBManager.updateQuery(Query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public  int AggiungiVisitaAlDB() {
        int result = 0;
        String Query = new String("INSERT INTO Visita(Data, Status) VALUES(\'" + this.dataPrenotata + "\', 0)");
        System.out.println(Query);
        try{
            result = DBManager.updateQuery(Query);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            result = -1;
        }
        return result;
    }

    public static String VisitePrenotabili(int position){
        String Query = new String("SELECT * FROM Visita WHERE Status = 0 LIMIT 1 OFFSET " + position + ";");
        System.out.println(Query);
        try{
            ResultSet result = DBManager.selectQuery(Query);
            while(result.next()){
                return result.getString("Data");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int ConteggioVisiteTotali(){
        String Query = new String("SELECT * FROM Visita");
        System.out.println(Query);
        int count = 0;
        try{
            ResultSet result = DBManager.selectQuery(Query);
            while (result.next()) {
                count++;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int PrenotaVisita(String Username, String Chip, String Data) {
        int result = 0;
        String Query = new String("UPDATE Visita SET Username_Proprietario = \'" + Username + "\' , Codice_Animale = \'" + Chip + "\' , Status = 1 WHERE Data = \'" + Data + "\';");
        System.out.println(Query);
        try {
            result = DBManager.updateQuery(Query);
        } catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
                result = -1;
        }
        return result;
    }

}
