
package clases;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker extends Thread {
    public String rol;
    private float prodByDay;
    private float counter;
    public int wage;
    private long savings;
    private long dia;
    public Empresa empresa;

    public Worker(int wage, String rol, float production, long duracion, Empresa empresa){
        
        this.rol = rol;
        this.counter = 0;
        this.prodByDay = production;
        this.savings = 0;
        this.dia = duracion;
        this.wage = wage;
        this.empresa = empresa;
    }

    @Override
    public void run(){
        
        while(true){
            try{
                sleep(this.dia);
                this.savings += this.wage;
                producir();
            }
            catch(InterruptedException ex){
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void producir(){
        
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