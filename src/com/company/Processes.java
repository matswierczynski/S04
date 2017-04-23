package com.company;

/**
 * Created by Mati on 2017-04-22.
 */
public class Processes {
    private Process proc [];
    private int size=0;

    Processes(int procSize){
        proc = new Process[procSize];

    }


    public void addProcess(int range, int begin, int size, int availableFrames) {
        if (this.size < proc.length) {
            Pager pager = new Pager(range, begin, size);
            proc[this.size] = new Process(availableFrames, pager.getPagesQueue(), pager.getPages());
            this.size++;
        }
    }

    public Process[] getProc() {
        return proc;
    }

    public int getAllPagesSize() {
        int _size = 0;
        for (int i = 0; i < proc.length; i++)
            _size += proc[i].getQueueSize();
        return _size;
    }


}
