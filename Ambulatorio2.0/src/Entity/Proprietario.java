package Entity;
import Database.ProprietarioDB;

public class Proprietario {
        private String nome;
        private String cognome;
        private String indirizzo;
        private String telefono;
        private String username;

        public Proprietario(String username) {
            ProprietarioDB prop = new ProprietarioDB(username);
            this.nome = prop.getNome();
            this.cognome = prop.getCognome();
            this.indirizzo = prop.getIndirizzo();
            this.telefono = prop.getTelefono();
        }

        public Proprietario(ProprietarioDB prop) {
            this.username = prop.getUsername();
            this.nome = prop.getNome();
            this.cognome = prop.getCognome();
            this.indirizzo = prop.getIndirizzo();
            this.telefono = prop.getTelefono();
        }

        public  Proprietario(String nome, String cognome, String indirizzo, String telefono, String user){
            this.username = user;
            this.nome = nome;
            this.cognome = cognome;
            this.indirizzo = indirizzo;
            this.telefono = telefono;

        }

        public void setNome(String nome) { this.nome = nome;}
        public int aggiungiProprietario(){
            ProprietarioDB proDB = new ProprietarioDB(this.nome, this.cognome, this.indirizzo, this.telefono, this.username);
            return proDB.addtoDB();
        }

        public static boolean checkPro(String Numero){
            ProprietarioDB proDB = new ProprietarioDB(Numero);
            return proDB.checkDB();
        }
        public void setCognome(String cognome) { this.cognome = cognome;}

        public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo;}

        public void setTelefono(String telefono) { this.telefono = telefono;}

        public void setUsername(String username) { this.username = username;}

        public String getNome() { return nome;}

        public String getCognome() { return cognome;}

        public String getIndirizzo() { return indirizzo;}

        public String getTelefono() { return telefono;}

        public String getUsername() { return username;}
}
