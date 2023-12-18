package com.example.fatnassi_chaima_mesure_glycemie.Controller;

import com.example.fatnassi_chaima_mesure_glycemie.Model.Patient;

public class Controller {
    private static Patient patient;
    private static Controller instance =null;
    private Controller()
    {
        super();
    }
    //4-4
    public static Controller getInstance(){
        if(Controller.instance==null)
            Controller.instance=new Controller();
        return instance;
    }
    public void create_patient(int age ,float valeur , boolean isFasting){
        patient=new Patient(age,valeur,isFasting) ;
    }
    //update entre le controller vers la view
    public String getResult(){
        return patient.getResultat();//update patient to controller
    }
}
