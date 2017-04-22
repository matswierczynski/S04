package com.company;

/**
 * Created by matik on 25.03.2017.
 */
public class Page {
    private final int nr;
    private boolean bit;
    private long usedTime;

    public Page(int nr){
        this.nr=nr;
        bit=false;
        usedTime=0;
    }

    public boolean isBit() {
        return bit;
    }

    public int getNr() {
        return nr;
    }

    public void setBit(boolean bit) {
        this.bit = bit;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(long usedTime) {
        this.usedTime = usedTime;
    }

    public String toString()
    {
        return "Numer strony: "+nr+" Załadowana: "+bit;
    }
}
