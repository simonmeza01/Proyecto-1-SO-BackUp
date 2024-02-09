/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.logging.Level;
import java.util.logging.Logger;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Director extends Thread {
    private int wage;
    private long savings;
    private long horaAleatoria;
    private long dia;
    private long tiempo35min;
    private boolean vigila;
    private boolean distribuye;
    private boolean administrativo;
    private Empresa empresa; 
    DashBoard gui;
    
    public Director(int wage, long dia, Empresa empresa, DashBoard gui){
        this.wage = wage;
        this.empresa = empresa;
        this.dia = dia;
        this.tiempo35min = (35/1440)*dia;
        this.savings = 0;
        this.vigila = false;
        this.administrativo = true;
        this.distribuye = false;
        this.gui = gui;
    }
    
    @Override
    public void run(){
        while(true){
            //System.out.println("Tiempo 25 minutos");
            try{
                this.horaAleatoria = (int) (Math.random()*(this.dia - this.tiempo35min));
                sleep(this.horaAleatoria);
                this.vigila = true;
                this.administrativo = false;
                this.distribuye = false;
                this.gui.estadoDirector(vigila, administrativo, distribuye, this.empresa);
                
                if(!(empresa.pm.vigilado(this.vigila))){
                    sleep(this.tiempo35min);
                    empresa.pm.vigilado(this.vigila);
                }else{
                    sleep(this.tiempo35min);
                }
                
                this.vigila = false;
                this.administrativo = true;
                this.distribuye = false;
                this.gui.estadoDirector(vigila, administrativo, distribuye, this.empresa);
                
                sleep(this.dia-this.horaAleatoria-this.tiempo35min);
                
                this.savings += this.wage;
                if(empresa.deadline == 0){
                    this.vigila = false;
                    this.administrativo = false;
                    this.distribuye = true;
                    this.gui.estadoDirector(vigila, administrativo, distribuye, this.empresa);
                    distribuirCapitulos();
                                
                }
            }
            catch(InterruptedException ex){
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void distribuirCapitulos(){
        try{
            empresa.mutex.acquire();
            empresa.estudio.distribucion(empresa.name);
            empresa.mutex.release();
            empresa.deadline = empresa.diasEntrega;
            sleep(this.dia);
            this.vigila = false;
            this.administrativo = true;
            this.distribuye = false;
        }
        catch(InterruptedException ex){
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

