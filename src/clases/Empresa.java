/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.concurrent.Semaphore;

public class Empresa {
    public String name;
    public int diasEntrega, deadline;
    private int guiones, escenarios, animaciones, doblajes, PT, ensambladores;
    public Worker[] devs;
    public Estudio estudio;
    private long dia;
    public Semaphore mutex;
    DashBoard gui;
    public PM pm; 
    public Director director; 
    
    public Empresa(String name, int duracionDia, int dias, int guiones, int escenarios, int animaciones, int doblajes, int PT, int ensambladores, DashBoard gui){
        this.name = name;
        this.diasEntrega = dias;
        this.deadline = dias;
        this.devs = new Worker[12];
        this.dia = duracionDia;
        this.estudio = new Estudio(25, 20, 55, 35, 10, gui);
        this.mutex = new Semaphore(1);
        
        this.guiones = guiones;
        this.escenarios = escenarios;
        this.animaciones = animaciones;
        this.doblajes = doblajes;
        this.PT = PT;
        this.ensambladores = ensambladores;
        
        this.gui = gui;
        
        initDevs();
    }
    
    public void initDevs(){
            
            for(int i = 0; i<this.guiones; i++){
                Worker dev = new Worker(480, "guiones", 0.34f, this.dia, this);
                dev.start();
                this.devs[i] = dev;
            }

            for(int i = 0; i<this.escenarios; i++){
                Worker dev = new Worker(624, "escenarios", 0.34f, this.dia, this);
                dev.start();
                this.devs[i+this.guiones] = dev;
            }

            for(int i = 0; i<this.animaciones; i++){
                Worker dev = new Worker(960, "animaciones", 2f, this.dia, this);
                dev.start();
                this.devs[i+this.guiones+this.escenarios] = dev;
            }

            for(int i = 0; i<this.doblajes; i++){
                Worker dev = new Worker(384, "doblajes", 5f, this.dia, this);
                dev.start();
                this.devs[i+this.guiones+this.escenarios+this.animaciones] = dev;
            }

            for(int i = 0; i<this.PT; i++){
                Worker dev = new Worker(816, "PT", 0.5f, this.dia, this);
                dev.start();
                this.devs[i+this.guiones+this.escenarios+this.animaciones+this.doblajes] = dev;
            }

            for(int i = 0; i<this.ensambladores; i++){
                Worker dev = new Worker(1200, "ensamblador", 0.5f, this.dia, this);
                dev.start();
                this.devs[i+this.guiones+this.escenarios+this.animaciones+this.doblajes+this.PT] = dev;
            }

            for(int i = 0; i<this.guiones; i++){
                Worker dev = new Worker(480, "guiones", 0.25f, this.dia, this);
                dev.start();
                this.devs[i] = dev;
            }

            System.out.println("Devs creados "+this.name);
            
            pm = new PM(960, this.dia, this, this.gui);
            pm.start();
            director = new Director(1440, this.dia, this, this.gui);
            director.start();
        }
        
    
}
