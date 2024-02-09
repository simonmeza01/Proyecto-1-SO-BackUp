/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;


public class Estudio {
    public int guiones, escenarios, animaciones, doblajes, PT, capituloE, capituloPT;
    public int maxGuiones, maxEscenarios, maxAnimaciones, maxDoblajes, maxPT;
    public long ingreso;
    DashBoard gui;
    

    public Estudio(int maxGuiones, int maxEscenarios, int maxAnimaciones, int maxDoblajes, int maxPT, DashBoard gui){
        this.guiones = 0;
        this.escenarios = 0;
        this.animaciones = 0;
        this.doblajes = 0;
        this.PT = 0;

        this.maxGuiones = maxGuiones;
        this.maxEscenarios = maxEscenarios;
        this.maxAnimaciones = maxAnimaciones;
        this.maxDoblajes = maxDoblajes;
        this.maxPT = maxPT;
        
        this.capituloE = 0; 
        this.capituloPT = 0;
        this.ingreso = 0;
        
        this.gui = gui; 
    }

    public void addParts(String rol, int qty, String empresa){
        switch(rol){
            
            case "guiones":

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

            case "animaciones":

                if (this.animaciones < this.maxAnimaciones){
                    this.animaciones += qty;
                    System.out.println(empresa + " animaciones: " + this.animaciones);
                }
                break;

            case "doblajes":

                if (this.doblajes < this.maxDoblajes){
                    this.doblajes += qty;
                    System.out.println(empresa + " doblajes: " + this.doblajes);
                }
                break;

            case "PT":

                if (this.PT < this.maxPT){
                    this.PT += qty;
                    System.out.println(empresa + " PTs: " + this.PT);
                }
                break;
                
            case "ensamblador":
                switch(empresa){
                    case "Nick":
                        if((this.escenarios>=1) && (this.guiones>=2) && (this.animaciones>=4) && (this.doblajes>=4)){
                            if(this.capituloE == (this.capituloPT*5)){
                                this.capituloE += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capitulo estándar: " + this.capituloE);
                            }else if((this.capituloE % 5 == 0)){
                                if(this.PT>=3){
                                    this.capituloPT += qty;
                                    deleteParts(empresa, true);
                                    System.out.println("Capitulos con PT: " + this.capituloPT);
                                }
                            }else{
                                this.capituloE += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + " CApitulos estándar: " + this.capituloE);
                            }
                        }
                        break;
                        
                    case "Star":
                        if((this.escenarios>=3) && (this.guiones>=2) && (this.animaciones>=4) && (this.doblajes>=6)){
                            if(this.capituloE == (this.capituloPT*6)){
                                this.capituloE += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capitulos estándar: " + this.capituloE);
                            }else if((this.capituloE % 6== 0)){
                                if(this.PT>=5){
                                    this.capituloPT += qty;
                                    deleteParts(empresa, true);
                                    System.out.println("Capitulos con PT: " + this.capituloPT);
                                }
                            }else{
                                this.capituloE += qty;
                                deleteParts(empresa, false);
                                System.out.println(empresa + "Capitulos estándar: " + this.capituloE);
                            }
                        }
                        break;
                }
                break;
        }
        gui.updateValues();
    }
    
    public void deleteParts(String empresa, boolean conPT){
        switch(empresa){
            case "Nick":
                this.guiones -= 2;
                this.escenarios--;
                this.animaciones -= 4;
                this.doblajes -= 4;
                
                if(conPT){
                    this.PT -= 2;
                }
                break;
                
            case "Star":
                this.guiones --;
                this.escenarios -=2;
                this.animaciones -= 6;
                this.doblajes -= 5;
                
                if(conPT){
                    this.PT--;
                }
                break;
        }
    }
    
        public void distribucion(String empresaNombre){
        
        switch(empresaNombre){
            case "Nick":
                this.ingreso += (this.capituloPT*500000) + (this.capituloE*450000);
                break;
                
            case "Star":
                this.ingreso += (this.capituloPT*800000) + (this.capituloE*350000);
                break;
        }
        this.gui.actualizarIngreso(this.ingreso, empresaNombre);
        this.gui.actualizarProfit();
        this.capituloPT = 0;
        this.capituloE = 0;
    }
}
