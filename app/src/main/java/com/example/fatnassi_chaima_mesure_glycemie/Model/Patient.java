package com.example.fatnassi_chaima_mesure_glycemie.Model;
public class Patient {
    private int age;
    private float valeur;
    private boolean isFasting;
    private String resultat;
    public Patient() {
    }

    public Patient(int age, float valeur, boolean isFasting) {
        this.age = age;
        this.valeur = valeur;
        this.isFasting = isFasting;
        calculer();
    }
    public int getAge() {
        return age;
    }

    public float getVm() {
        return valeur;
    }
    //update model to controller
    public String getResultat() {
        return resultat;
    }

    public boolean getIsFasting() {
        return isFasting;
    }
    public void calculer(){
        if (isFasting)
        {
            if(age >=13){
                if(valeur<5)
                    resultat="Your glycemia level is LOW";
                else
                if(valeur>=5 && valeur<=7)
                    resultat="Your glycemia level is NORMAL";
                else
                    resultat="Your glycemia level is HIGH ";
            }
            else if(age >=6 && age <=12)
            {
                if(valeur<5)
                    resultat="Your glycemia level is LOW";
                else
                if(valeur>=5 && valeur<=10)
                    resultat="Your glycemia level is NORMAL";
                else
                    resultat="Your glycemia level is HIGH "
                            ;}
            else if(age <6) {
                if(valeur<5.5)
                    resultat="Your glycemia level is LOW";
                else
                if(valeur>=5.5 && valeur<=10)
                    resultat="Your glycemia level is NORMAL";
                else
                    resultat="Your glycemia level is HIGH ";}


        }else //false {
            if(valeur>10.0)
                resultat="Your glycemia level is HIGH";
            else resultat="Your glycemia level is NORMAL";


    }
}
