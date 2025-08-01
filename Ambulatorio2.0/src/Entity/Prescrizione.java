package Entity;
import Database.PrescrizioneDB;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Prescrizione {
    private LocalDateTime data;
    private String diagnosi;
    private String cure;
    private Double costo;
    private ArrayList<Farmaco> listaFarmaci;

    public Prescrizione() { super();}

    public Prescrizione(LocalDateTime da, String di, String cu, Double co, ArrayList<Farmaco> l) {
        this.data = da;
        this.diagnosi = di;
        this.cure = cu;
        this.costo = co;
        this.listaFarmaci = l;
    }

    public LocalDateTime getData() { return data;}
    public String getDiagnosi() { return diagnosi;}
    public Double getCosto() { return costo;}
    public String getCure() { return cure;}
    public ArrayList<Farmaco> getListaFarmaci() { return listaFarmaci;}

    public void setCosto(Double costo) { this.costo = costo;}
    public void setCure(String cure) { this.cure = cure;}
    public void setData(LocalDateTime data) { this.data = data;}
    public void setDiagnosi(String diagnosi) { this.diagnosi = diagnosi;}
    public void setListaFarmaci(ArrayList<Farmaco> listaFarmaci) { this.listaFarmaci = listaFarmaci;}


    public  ArrayList<Prescrizione> resocontoGiornaliero(){
        ArrayList<PrescrizioneDB> listaPresc = new ArrayList<PrescrizioneDB>();
        ArrayList<Prescrizione> resoconto = new ArrayList<Prescrizione>();
        PrescrizioneDB preDB = new PrescrizioneDB();
        listaPresc = preDB.resocontoGiornalieroDB();
        for (PrescrizioneDB visita : listaPresc)
        {
            Prescrizione pre = new Prescrizione();
            pre.costo = visita.getCosto();
            pre.data = visita.getData();
            resoconto.add(pre);


        }
        return resoconto;


    }

    public ArrayList<String> VisiteSenzaPre() {
        PrescrizioneDB preDB = new PrescrizioneDB();
        return preDB.PrescrizioneSenzaVisita();
    }

    public int addToDB(){
        PrescrizioneDB preDB = new PrescrizioneDB(this.data, this.diagnosi, this.cure,this.costo/*, this.listaFarmaci*/ );
       return preDB.insertDB();

    }
}
