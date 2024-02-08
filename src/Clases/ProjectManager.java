/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author simon
 */
public class ProjectManager extends Worker {
    
    private boolean working;
    private int registerDayAccount;
    private Drive drive;

    public ProjectManager(int wage, String rol, float production, long duracion, Company empresa) {
        super(wage, rol, production, duracion, empresa);
    }
    
   

   public void run() {
       
       while (working){
           try{
               double halfhour = this.dia/ 48;
               int counter = 0;
               
               // este es el espacio de tiempo en el que el PM ve media hora anime y trabaja media hora
               while (counter < 16){
                   
                   drive.setPMStatus(0);
                   
                   if (drive.getDirectorStatus() == 1){ // Cuando el director esta vigilando
                       drive.setFaults( drive.getFaults() + 1);
                       
                       
                       drive.getCostMutex().acquire();
                       drive.setPMcost(drive.getPMcost()-100);
                       drive.getCostMutex().release();
                   }
                   
                   sleep(Math.round(halfhour));
                   drive.setPMStatus(1);
                   sleep(Math.round(halfhour));
                   counter++;     
               
               }
               drive.setPMStatus(0);
               sleep(Math.round(halfhour*16));
               drive.getDaysMutex().acquire();
               drive.setDaysleftToRelease(drive.getDaysleftToRelease()-1);
               drive.getDaysMutex().release();
               
               
               drive.getCostMutex().acquire();
               
               drive.setPMcost(drive.getPMcost() + this.wage*24);
               drive.getCostMutex().release();
               
           }
           catch(Exception e){}
       }
   }
  
 
    
    
}