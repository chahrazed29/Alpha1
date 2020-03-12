package com.example.alpha;

public class Entreprise extends Utilisateur {

        private long numrc;


        public Entreprise( String username, long phone,long numrc , String email, String password) {
            super( username,phone, email, password);
            this.numrc = numrc;
        }

        public Entreprise(){

        }

        public long getNumrc() {
            return numrc;
        }

        public void setNumrc(long numrc) {
            this.numrc = numrc;
        }

}
