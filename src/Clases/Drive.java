/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.util.concurrent.Semaphore;

/**
 *
 * @author isa
 *
 */
public class Drive {
    // Aqui como esto es area compartida los atributos son publicos creo
    private int scripts;
    private int scenarios;
    private int animations;
    private int dubbings;
    private int plotTwist;
   
    private final int maxScripts = 25 ;
    private final int maxScenarios = 20;
    private final int maxAnimations = 55;
    private final int maxDubbings = 35;
    private final int maxPlotTwist = 10; 
    
    private int episodes;
    private int episodesWithPT;
    
    
    //Los estados del director y PM se van a expresar de manera binaria
    //0- esta trabajando 1- esta vigilando
    private int directorStatus = 0;
    // 0- esta trabajando 1-esta viendo anime
    private int PMStatus = 0;
    
    private int salaryDiscount = 0;
    private int faults = 0;
    
    //Semaphores
    
    private Semaphore daysMutex = new Semaphore(1); // para controlar el paso del tiempo
    private Semaphore costMutex = new Semaphore(1); // asociado a costos
    private Semaphore workerMutex = new Semaphore(1); // asociado a los cambios que van a hacer los trabajadores
    

    private int daysleftToRelease;

    //Costos de producir un episodio
    private int scriptCost = 0;// no se si se inicializa en 20 (lo que cobra por hora) o si lo inicializo en 0
    private int scenarioCost = 0;
    private int animationsCost = 0;
    private int dubbingsCost = 0;
    private int plotTwistCost = 0;
    private int PMcost = 0;
    private int DirectorCost = 0; 
    
    private float winnings = 0;
    private float totalCosts = 0;
    private float utility = 0;
    
    
    public Drive(){
        this.scripts=0;
        this.scenarios=0;
        this.animations=0;
        this.dubbings=0;
        this.plotTwist=0;
    }
    
    public Drive(int scripts, int scenarios, int animations, int dubbings, int plotTwist){
        this.scripts=scripts;
        this.scenarios=scenarios;
        this.animations=animations;
        this.dubbings=dubbings;
        this.plotTwist=plotTwist;
    }
    
    public void addPart(int type, int part){
        
        switch (type){
            case 0: 
                if (getScripts() + part > getMaxScripts()) {
                 setScripts(getMaxScripts()); 
                }
                else{
                setScripts(getScripts() + part); 
                }
                break;
                
            case 1: 
                if (getScenarios() + part > getMaxScenarios()) {
                 setScenarios(getMaxScenarios()); 
                }else{
                setScenarios(getScenarios() + part); 
                }
                break;
            case 2: 
                if (getAnimations() + part > getMaxAnimations()) {
                 setAnimations(getMaxAnimations()); }
                else{
                setAnimations(getAnimations() + part); 
                }
                break;        
            case 3: 
                if (getDubbings() + part > getMaxDubbings()) {
                 setDubbings(getMaxDubbings()); }
                else{
                setDubbings(getDubbings() + part); 
                }
                break;        
            case 4: 
                if (getPlotTwist() + part > getMaxPlotTwist()) {
                 setPlotTwist(getMaxPlotTwist()); }
                else{
                setPlotTwist(getPlotTwist() + part); 
                }
                break;   
            default:
            break;
                
             }
    }  
    
   
    
    /**
     * @return the directorStatus
     */
    public int getDirectorStatus() {
        return directorStatus;
    }

    /**
     * @param directorStatus the directorStatus to set
     */
    public void setDirectorStatus(int directorStatus) {
        this.directorStatus = directorStatus;
    }

    /**
     * @return the PMStatus
     */
    public int getPMStatus() {
        return PMStatus;
    }

    /**
     * @param PMStatus the PMStatus to set
     */
    public void setPMStatus(int PMStatus) {
        this.PMStatus = PMStatus;
    }

    /**
     * @return the salaryDiscount
     */
    public int getSalaryDiscount() {
        return salaryDiscount;
    }

    /**
     * @param salaryDiscount the salaryDiscount to set
     */
    public void setSalaryDiscount(int salaryDiscount) {
        this.salaryDiscount = salaryDiscount;
    }

    /**
     * @return the faults
     */
    public int getFaults() {
        return faults;
    }

    /**
     * @param faults
     */
    public void setFaults(int faults) {
        this.faults = faults;
    }

    /**
     * @return the daysMutex
     */
    public Semaphore getDaysMutex() {
        return daysMutex;
    }

    /**
     * @param daysMutex the daysMutex to set
     */
    public void setDaysMutex(Semaphore daysMutex) {
        this.daysMutex = daysMutex;
    }

    /**
     * @return the costMutex
     */
    public Semaphore getCostMutex() {
        return costMutex;
    }

    /**
     * @param costMutex the costMutex to set
     */
    public void setCostMutex(Semaphore costMutex) {
        this.costMutex = costMutex;
    }

    /**
     * @return the daysleftToRelease
     */
    public int getDaysleftToRelease() {
        return daysleftToRelease;
    }

    /**
     * @param daysleftToRelease the daysleftToRelease to set
     */
    public void setDaysleftToRelease(int daysleftToRelease) {
        this.daysleftToRelease = daysleftToRelease;
    }

    /**
     * @return the scripts
     */
    public int getScripts() {
        return scripts;
    }

    /**
     * @param scripts the scripts to set
     */
    public void setScripts(int scripts) {
        this.scripts = scripts;
    }

    /**
     * @return the scenarios
     */
    public int getScenarios() {
        return scenarios;
    }

    /**
     * @param scenarios the scenarios to set
     */
    public void setScenarios(int scenarios) {
        this.scenarios = scenarios;
    }

    /**
     * @return the animations
     */
    public int getAnimations() {
        return animations;
    }

    /**
     * @param animations the animations to set
     */
    public void setAnimations(int animations) {
        this.animations = animations;
    }

    /**
     * @return the dubbings
     */
    public int getDubbings() {
        return dubbings;
    }

    /**
     * @param dubbings the dubbings to set
     */
    public void setDubbings(int dubbings) {
        this.dubbings = dubbings;
    }

    /**
     * @return the plotTwist
     */
    public int getPlotTwist() {
        return plotTwist;
    }

    /**
     * @param plotTwist the plotTwist to set
     */
    public void setPlotTwist(int plotTwist) {
        this.plotTwist = plotTwist;
    }

    /**
     * @return the maxScripts
     */
    public int getMaxScripts() {
        return maxScripts;
    }

    /**
     * @return the maxScenarios
     */
    public int getMaxScenarios() {
        return maxScenarios;
    }

    /**
     * @return the maxAnimations
     */
    public int getMaxAnimations() {
        return maxAnimations;
    }

    /**
     * @return the maxDubbings
     */
    public int getMaxDubbings() {
        return maxDubbings;
    }

    /**
     * @return the maxPlotTwist
     */
    public int getMaxPlotTwist() {
        return maxPlotTwist;
    }

    /**
     * @return the scriptCost
     */
    public int getScriptCost() {
        return scriptCost;
    }

    /**
     * @param scriptCost the scriptCost to set
     */
    public void setScriptCost(int scriptCost) {
        this.scriptCost = scriptCost;
    }

    /**
     * @return the scenarioCost
     */
    public int getScenarioCost() {
        return scenarioCost;
    }

    /**
     * @param scenarioCost the scenarioCost to set
     */
    public void setScenarioCost(int scenarioCost) {
        this.scenarioCost = scenarioCost;
    }

    /**
     * @return the animationsCost
     */
    public int getAnimationsCost() {
        return animationsCost;
    }

    /**
     * @param animationsCost the animationsCost to set
     */
    public void setAnimationsCost(int animationsCost) {
        this.animationsCost = animationsCost;
    }

    /**
     * @return the dubbingsCost
     */
    public int getDubbingsCost() {
        return dubbingsCost;
    }

    /**
     * @param dubbingsCost the dubbingsCost to set
     */
    public void setDubbingsCost(int dubbingsCost) {
        this.dubbingsCost = dubbingsCost;
    }

    /**
     * @return the plotTwistCost
     */
    public int getPlotTwistCost() {
        return plotTwistCost;
    }

    /**
     * @param plotTwistCost the plotTwistCost to set
     */
    public void setPlotTwistCost(int plotTwistCost) {
        this.plotTwistCost = plotTwistCost;
    }

    /**
     * @return the PMcost
     */
    public int getPMcost() {
        return PMcost;
    }

    /**
     * @param PMcost the PMcost to set
     */
    public void setPMcost(int PMcost) {
        this.PMcost = PMcost;
    }

    /**
     * @return the DirectorCost
     */
    public int getDirectorCost() {
        return DirectorCost;
    }

    /**
     * @param DirectorCost the DirectorCost to set
     */
    public void setDirectorCost(int DirectorCost) {
        this.DirectorCost = DirectorCost;
    }

    /**
     * @return the winnings
     */
    public float getWinnings() {
        return winnings;
    }

    /**
     * @param winnings the winnings to set
     */
    public void setWinnings(float winnings) {
        this.winnings = winnings;
    }

    /**
     * @return the totalCosts
     */
    public float getTotalCosts() {
        return totalCosts;
    }

    /**
     * @param totalCosts the totalCosts to set
     */
    public void setTotalCosts(float totalCosts) {
        this.totalCosts = totalCosts;
    }

    /**
     * @return the utility
     */
    public float getUtility() {
        return utility;
    }

    /**
     * @param utility the utility to set
     */
    public void setUtility(float utility) {
        this.utility = utility;
    }

    /**
     * @return the workerMutex
     */
    public Semaphore getWorkerMutex() {
        return workerMutex;
    }

    /**
     * @param workerMutex the workerMutex to set
     */
    public void setWorkerMutex(Semaphore workerMutex) {
        this.workerMutex = workerMutex;
    }

    /**
     * @return the episodes
     */
    public int getEpisodes() {
        return episodes;
    }

    /**
     * @param episodes the episodes to set
     */
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    /**
     * @return the episodesWithPT
     */
    public int getEpisodesWithPT() {
        return episodesWithPT;
    }

    /**
     * @param episodesWithPT the episodesWithPT to set
     */
    public void setEpisodesWithPT(int episodesWithPT) {
        this.episodesWithPT = episodesWithPT;
    }

    

 

    
}
