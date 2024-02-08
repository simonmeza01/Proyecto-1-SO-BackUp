/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.concurrent.Semaphore;

import Interfaces.GUI;
/**
 *
 * @author Admin
 */
public class Company {
    public String name;
    public int diasEntrega, diasRestantes;
    private int maxWorkers, guiones, escenarios, animadores, doblajes, PlotTwists, ensambladores;
    public Worker[] workers;
    public Studio estudio;
    private long dia;
    public Semaphore mutex;
    GUI gui;
    
    public Company(String name, int duracionDia, int dias, int guiones, int escenarios, int animadores, int doblajes, int PlotTwists, int ensambladores, GUI gui){
        this.name = name;
        this.diasEntrega = dias;
        this.diasRestantes = dias;
        this.workers = new Worker[12];
        
        this.estudio = new Studio(25, 20, 55, 35, 10, gui);
        this.mutex = new Semaphore(1);
        
        this.guiones = guiones;
        this.escenarios = escenarios;
        this.animadores = animadores;
        this.doblajes = doblajes;
        this.PlotTwists = PlotTwists;
        this.ensambladores = ensambladores;
        
        this.gui = gui;
        
        initWorkers();
    }
    
    public void initWorkers(){
        switch(this.name){
            case "Nickelodeon":
                for(int i = 0; i<this.guiones; i++){
                    Worker worker = new Worker(480, "guionistas", 0.34f, this.dia, this);
                    worker.start();
                    workers[i] = worker;
                }

                for(int i = 0; i<this.escenarios; i++){
                    Worker worker = new Worker(624, "escenarios", 0.34f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones] = worker;
                }

                for(int i = 0; i<this.animadores; i++){
                    Worker worker = new Worker(960, "animadores", 2f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios] = worker;
                }

                for(int i = 0; i<this.doblajes; i++){
                    Worker worker = new Worker(384, "doblajes", 5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores] = worker;
                }

                for(int i = 0; i<this.PlotTwists; i++){
                    Worker worker = new Worker(816, "PlotTwist", 0.5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores+this.doblajes] = worker;
                }

                for(int i = 0; i<this.ensambladores; i++){
                    Worker worker = new Worker(1200, "ensamblador", 0.5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores+this.doblajes+this.PlotTwists] = worker;
                }
                break;
                
            case "Star Channel":
                 for(int i = 0; i<this.guiones; i++){
                    Worker worker = new Worker(480, "guionistas", 0.34f, this.dia, this);
                    worker.start();
                    workers[i] = worker;
                }

                for(int i = 0; i<this.escenarios; i++){
                    Worker worker = new Worker(624, "escenarios", 0.34f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones] = worker;
                }

                for(int i = 0; i<this.animadores; i++){
                    Worker worker = new Worker(960, "animadores", 2f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios] = worker;
                }

                for(int i = 0; i<this.doblajes; i++){
                    Worker worker = new Worker(384, "doblajes", 5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores] = worker;
                }

                for(int i = 0; i<this.PlotTwists; i++){
                    Worker worker = new Worker(816, "PlotTwist", 0.5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores+this.doblajes] = worker;
                }

                for(int i = 0; i<this.ensambladores; i++){
                    Worker worker = new Worker(1200, "ensamblador", 0.5f, this.dia, this);
                    worker.start();
                    workers[i+this.guiones+this.escenarios+this.animadores+this.doblajes+this.PlotTwists] = worker;
                }
                break;
               
        }
        System.out.println("Devs creados "+this.name);
    }

}



