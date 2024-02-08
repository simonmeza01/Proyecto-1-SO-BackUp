/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class Worker extends Thread {
    public String rol;
    private float prodByDay;
    private float counter;
    public int wage;
    private long savings;
    protected long dia;
    public Company empresa;

    public Worker(int wage, String rol, float production, long duracion, Company empresa){
        
        this.rol = rol;
        this.counter = 0;
        this.prodByDay = production;
        this.savings = 0;
        this.dia = 1000;
        this.wage = wage;
        this.empresa = empresa;
    }

    @Override
    public void run(){
        
        while(true){
            try{
                sleep(this.dia);
                this.savings += this.wage;
                produce();
            }
            catch(InterruptedException ex){
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void produce(){
        
        this.counter += this.prodByDay;
        
        if((int) this.counter >= 1){
            try{
                empresa.mutex.acquire();
                empresa.estudio.addParts(this.rol, (int) this.counter, empresa.name);
                empresa.mutex.release();
            }
            catch(InterruptedException ex){
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.counter = 0;            
        }
    }
}