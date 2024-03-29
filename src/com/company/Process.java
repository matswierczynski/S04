package com.company;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by matik on 25.03.2017.
 */
public class Process {
    private int frames;
    private Page[] _queueList=null;
    private Page[] _list=null;
    private int actualState;
    private int[] queue;
    int frcounter=0;
    TreeSet<Integer> ts = new TreeSet<>();

    public Process(int availableFrames, Page[] listQueue, Page[] list){
        if(availableFrames>0) {
            frames=availableFrames;
            _list=list;
            _queueList=listQueue;
            actualState=0;
            queue = new int[frames];
            clear();
        }
    }

    public Process(Process proc){
            this.frames=proc.frames;
            this._list=proc._list;
            this._queueList=proc._queueList;
            this.actualState=proc.actualState;
            this.queue = new int[proc.frames];
        clear();
    }

    private  void clear(){
        for(int i=0;i<_list.length;i++) {
            _list[i].setBit(false);
            _list[i].setUsedTime(0);
        }
    }

    private void clearBits(){
        for(int i=0;i<_list.length;i++) {
            _list[i].setBit(false);
        }
    }

    public int getQueueSize(){
        return _queueList.length;
    }

    public void increaseFrames(){
        int [] tmpQueue = new int[frames+1];
        System.arraycopy(queue,0,tmpQueue,0,frames);
        queue = tmpQueue;
        frames++;
    }

    public boolean decreaseFrames(){
        if (frames>=2) {
            frames--;
            System.arraycopy(queue, 0, queue, 0, frames);
            return true;
        }
        return false;
    }

    public void setFrames(int size){frames=size;
        queue = new int[frames];}

    public void setFrames(TreeSet set){
        clearBits();
        queue = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        int i=0;
        while (it.hasNext()) {
            queue[i] = it.next();
            Page page=_list[queue[i]];
            page.setBit(true);
            i++;
        }
        frcounter=queue.length-1;
        ts.clear();
    }

    public boolean isExecuted(){
        return actualState>=_queueList.length;
    }


    public int handle(){

        long lastUsed=0;

        int loadpagecounter=0;
        int framenr=0;
        int runtime=0;

        while (actualState <_queueList.length && runtime<10){
            runtime++;
            Page page=_list[_queueList[actualState].getNr()];
            ts.add(page.getNr());
            if(page.isBit()==false) {
                if (frcounter < queue.length) {
                    queue[frcounter] = page.getNr();
                    frcounter++;
                    loadpagecounter++;
                    page.setBit(true);
                    page.setUsedTime(System.nanoTime());
                }
                else {
                    long currentTime = System.nanoTime();
                    for (int k = 0; k < queue.length; k++) {
                        // System.out.println(currentTime-_list[queue[k]].getUsedTime()+" "+_list[queue[k]].isBit());
                        if (currentTime - _list[queue[k]].getUsedTime() > lastUsed) {
                            lastUsed = currentTime - _list[queue[k]].getUsedTime();
                            framenr = k;
                        }

                    }
                    //   System.out.println("Wywłaszczono: "+framenr);
                    lastUsed = 0;
                    _list[queue[framenr]].setBit(false);
                    _list[queue[framenr]].setUsedTime(0);
                    queue[framenr] = page.getNr();
                    page.setBit(true);
                    loadpagecounter++;
                    page.setUsedTime(System.nanoTime());
                }
            }

            else {
                page.setUsedTime(System.nanoTime());
            }
            actualState++;
        }

        return loadpagecounter;
    }

    public TreeSet<Integer> getTs() {
        return ts;
    }
}
