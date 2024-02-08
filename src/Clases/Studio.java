/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Interfaces.GUI;
/**
 *
 * @author Admin
 */

public class Studio {
     public int guiones, escenarios, animadores, doblajes, plotTwists, normalEpisodes, PTepisode;
    public int maxGuiones, maxEscenarios, maxAnimadores, maxDoblajes, maxPT;
    GUI gui;
    public long ingreso;

    public Studio(int maxGuiones, int maxEscenarios, int maxAnimadores, int maxDoblajes, int maxPT, GUI gui){
        this.guiones = 0;
        this.escenarios = 0;
        this.animadores = 0;
        this.doblajes = 0;
        this.plotTwists = 0;

        this.maxGuiones = maxGuiones;
        this.maxEscenarios = maxEscenarios;
        this.maxAnimadores = maxAnimadores;
        this.maxDoblajes = maxDoblajes;
        this.maxPT = maxPT;
        
        this.normalEpisodes = 0; 
        this.PTepisode = 0;
        this.ingreso = 0;
        
        this.gui = gui; 
    }

    public void addParts(String rol, int qty, String empresa){
        switch(rol){
            
            case "guionistas":

                if (this.guiones < this.maxGuiones){
                    this.guiones += qty;
                    System.out.println(empresa + " guiones: " + this.guiones);
                }
                break;

            case "escenarios":

                if (this.escenarios < this.maxEscenarios){
                    this.escenarios += qty;
                    System.out.println(empresa + " escenarios: " + this.escenarios);
                }
                break;

            case "animadores":

                if (this.animadores < this.maxAnimadores){
                    this.animadores += qty;
                    System.out.println(empresa + " animadores: " + this.animadores);
                }
                break;

            case "doblajes":

                if (this.doblajes < this.maxDoblajes){
                    this.doblajes += qty;
                    System.out.println(empresa + " doblajes: " + this.doblajes);
                }
                break;

            case "PlotTwists":

                if (this.plotTwists < this.maxPT){
                    this.plotTwists += qty;
                    System.out.println(empresa + " PlotTwists: " + this.plotTwists);
                }
                break;
                
            case "ensambladores":
                switch(empresa){
                    case "Nickelodeon":
                        if((this.escenarios>=1) && (this.guiones>=2) && (this.animadores>=4) && (this.doblajes>=4)){
                            if(this.normalEpisodes == (this.PTepisode*6)){
                                this.normalEpisodes += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capítulos estándar: " + this.normalEpisodes);
                            }else if((this.normalEpisodes % 5 == 0)){
                                if(this.plotTwists>=2){
                                    this.PTepisode += qty;
                                    deleteParts(empresa, true);
                                    System.out.println("Capítulos con Plot Twist: " + this.PTepisode);
                                }
                            }else{
                                this.normalEpisodes += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + " Capítulo estándar: " + this.normalEpisodes);
                            }
                        }
                        break;
                        
                    case "Star Channel":
                        if((this.escenarios>=3) && (this.guiones>=2) && (this.animadores>=4) && (this.doblajes>=6)){
                            if(this.normalEpisodes == (this.PTepisode*3)){
                                this.normalEpisodes += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capítulos estándar: " + this.normalEpisodes);
                            }else if((this.normalEpisodes % 3 == 0)){
                                if(this.plotTwists>=5){
                                    this.PTepisode += qty;
                                    deleteParts(empresa, true);
                                    System.out.println("Capítulos con PlotTwist: " + this.PTepisode);
                                }
                            }else{
                                this.normalEpisodes += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capítulos estándar: " + this.normalEpisodes);
                            }
                        }
                        break;
                }
                break;
        }
        gui.updateValues();
    }
    
    public void deleteParts(String empresa, boolean withPT){
        switch(empresa){
            case "Nickelodeon":
                this.guiones -= 2;
                this.escenarios-=1;
                this.animadores -= 4;
                this.doblajes -= 4;
                
                if(withPT){
                    this.plotTwists -= 2;
                }
                break;
                
            case "Star Channel":
                this.guiones -=2;
                this.escenarios -=3;
                this.animadores -= 4;
                this.doblajes -= 6;
                
                if(withPT){
                    this.plotTwists-=5;
                }
                break;
        }
    }
    
        public void distribucion(String empresaNombre){
        
        switch(empresaNombre){
            case "Nickelodeon":
                this.ingreso += (this.PTepisode*500000) + (this.normalEpisodes*450000);
                break;
                
            case "StarChannel":
                this.ingreso += (this.PTepisode*800000) + (this.normalEpisodes*350000);
                break;
        }
        this.gui.actualizarIngreso(this.ingreso, empresaNombre);
        this.gui.actualizarProfit();
        this.PTepisode = 0;
        this.normalEpisodes = 0;
    }
}