package com.company;

/**
 * Created by Mati on 2017-04-22.
 */
public class Planner {
    Processes processes;
    public Planner(int range, int availableFrames){
        processes = new Processes(10);
        for(int i=0;i<10;i++)
            processes.addProcess(range,1000*i,2*(i+1), availableFrames/10);

    }

    public int equal (){
        Process proc [] = new Process [10];
        System.arraycopy(processes.getProc(),0,proc,0,processes.getProc().length);
        int siteError=0;
        while (!isDone(proc)) {
            for (int i = 0; i < proc.length; i++)
                siteError += proc[i].handle();
        }
        return siteError;
    }

    public int proportional (){

        return 0;
    }

    public boolean isDone(Process [] array){
        int done=0;
        for (int i=0;i<array.length;i++) {
            if (array[i].isExecuted() == true)
                done++;
        }
        return done >=array.length;
    }

}
