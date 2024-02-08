/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.concurrent.Semaphore; 
import java.util.Random;
import Clases.Studio;
import static java.lang.Thread.sleep;
/**
 *
 * @author Admin
 */
public class Director extends Worker {
    
    private Drive drive;
    private boolean working;
    private Studio studio;

    public Director(int wage, String rol, float production, long duracion, Company empresa) {
        super(wage, rol, production, duracion, empresa);
    }
    
    
  
    
    public void run(){
        while(working){
            double clock;
            try{
                drive.getDaysMutex().acquire();
                //Esto es cuando el contador marca O días restantes y todo lo que abarca cuando eso pasa
                if (drive.getDaysleftToRelease() == 0){
                    drive.getWorkerMutex().acquire();
                    drive.setWinnings(drive.getWinnings()+ drive.getEpisodes()+drive.getEpisodesWithPT()); // cosas que habría que arreglar
                    drive.setEpisodes(0);
                    drive.setEpisodesWithPT(0);
                    drive.setDaysleftToRelease(0); // este no se en cuanto lo tenemos que poner
                    drive.getDaysMutex().release();
                    drive.getWorkerMutex().release();
                    sleep(this.dia);
                }else{
                    //Esto es lo que hace el director en días que no tiene que release
                    drive.getDaysMutex().release();
                    
                    //Tenemos que incluir el director en un momento aleatorio del día yendo a chequear al PM
                    Random random = new Random();
                    double hour = this.dia/24; //esto me da el valor de una hora
                    double randomHour = random.nextInt(24)*hour;
                    
                    int counter = 0;
                    while (counter < this.dia){
                        if (counter ==randomHour){ 
                            drive.setDirectorStatus(0); 
                            double minute = hour/60; 
                            sleep(Math.round(35 * minute));
                            drive.setDirectorStatus(1); //35 minutos esra vigilando
                            sleep(Math.round(25 * minute));
                            drive.setDirectorStatus(0);
                      }
                        counter += hour;
                    }
                    
                    
                    
                    
                }
                drive.getCostMutex().acquire();
                drive.setDirectorCost(drive.getDirectorCost()+ this.wage*24);
                drive.getCostMutex().release();
               
            }catch(Exception e){
                
            }
        }
    }
    
    

}
