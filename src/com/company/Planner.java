package com.company;

import java.util.Arrays;

/**
 * Created by Mati on 2017-04-22.
 */
public class Planner {
    Processes processes;
    int availableFrames;
    public Planner(int range, int availableFrames){
        processes = new Processes(10);
        this.availableFrames=availableFrames;
        for(int i=0;i<10;i++)
            processes.addProcess(range,1000*i,100*i+1, availableFrames/10);
        System.out.println("Liczba wszystkich stron: "+processes.getAllPagesSize());

    }

    public int equal (){
        Process proc [] = new Process [10];
        for(int i=0;i<proc.length;i++)
            proc[i] = new Process(processes.getProc()[i]); //kopia procesów
        int siteError=0;
        while (!isDone(proc)) { //wykonanie procesów do końca
            for (int i = 0; i < proc.length; i++) {
                siteError += proc[i].handle();
            }
        }
        return siteError;
    }

    public int proportional (){
        Process proc [] = new Process [10];
        int virtualMemSize=0;
        for(int i=0;i<proc.length;i++)
            proc[i] = new Process(processes.getProc()[i]); //kopia procesów
        for (int i=0;i<proc.length;i++)
            virtualMemSize+=proc[i].getQueueSize();
        for(int i=0;i<proc.length;i++){
            float newFramesSize=(((float)proc[i].getQueueSize()/(float)virtualMemSize)*availableFrames);
            int FramesSize = Math.round(newFramesSize);
            if (FramesSize<1)
                FramesSize=1;
            proc[i].setFrames(FramesSize);
        }
        int siteError=0;
        while (!isDone(proc)) { //wykonanie procesów do końca
            for (int i = 0; i < proc.length; i++)
                siteError += proc[i].handle();
        }
        return siteError;
    }

    public int pageFault () {
        Process proc[] = new Process[10];
        int virtualMemSize = 0;
        for (int i = 0; i < proc.length; i++)
            proc[i] = new Process(processes.getProc()[i]); //kopia procesów
        for (int i = 0; i < proc.length; i++)
            virtualMemSize += proc[i].getQueueSize();
        for (int i = 0; i < proc.length; i++) {
            float newFramesSize = (((float) proc[i].getQueueSize() / (float) virtualMemSize) * availableFrames);
            int FramesSize = Math.round(newFramesSize);
            if (FramesSize < 1)
                FramesSize = 1;
            proc[i].setFrames(FramesSize);
        }
        int[] siteFaults = new int[proc.length];
        int siteError = 0;
        while (!isDone(proc)) { //wykonanie procesów do końca
            for (int i = 0; i < proc.length; i++) {
                siteFaults[i] = proc[i].handle();
                siteError += siteFaults[i];
            }
                manageFrames(siteFaults, proc);
            }

        return siteError;
    }

    public int workingSetModel(){
        Process proc [] = new Process [10];
        for(int i=0;i<proc.length;i++)
            proc[i] = new Process(processes.getProc()[i]); //kopia procesów
        int siteError=0;
        while (!isDone(proc)) { //wykonanie procesów do końca
            for (int i = 0; i < proc.length; i++) {
                siteError += proc[i].handle();
                proc[i].setFrames(proc[i].getTs());
            }
        }
        return siteError;
    }

    /* Wyszukiwanie procesu o min i max częstości błędów strony*/
    private void manageFrames(int [] siteFaults, Process [] proc){
        int [] toSort = new int[siteFaults.length];
        System.arraycopy(siteFaults,0,toSort,0,siteFaults.length);
        Arrays.sort(toSort);

        boolean trimmed = false;
        int i=0;
        int index=0;
        while (!trimmed && i<siteFaults.length){
            for (int k=0;k<siteFaults.length;k++) {
                if (siteFaults[k] == toSort[i]) {
                    index = k;
                }
            }
            trimmed=proc[index].decreaseFrames();
            i++;
        }

        if (trimmed){
            for (int k=0;k<siteFaults.length;k++) {
                if (siteFaults[k] == toSort[toSort.length-1]) {
                    index = k;
                }
            }
            proc[index].increaseFrames();
        }

    }



    /*Sprawdzanie czy wszystkie odwołania procesu zostały obsłużone */
    public boolean isDone(Process [] array){
        int done=0;
        for (int i=0;i<array.length;i++) {
            if (array[i].isExecuted() == true)
                done++;
        }
        return done >=array.length;
    }

}
