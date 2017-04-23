package com.company;
import java.util.Random;

/**
 * Created by matik on 25.03.2017.
 */
public class Pager {
    private final Page[] pagesQueue;
    private final Page[]  pages;
    public Pager(int range, int begin, int size){
        pagesQueue=new Page[size];
        pages=new Page[begin+range+1];
        Random rand=new Random();
        if(range>0 && size>0){
            for(int i=0;i<size;i++) {
                pagesQueue[i]=new Page(begin+rand.nextInt(range + 1));
            }
            for(int k=0;k<range+1+begin;k++)
                pages[k]=new Page(k);

        }



    }


    public void showPagesQueue() {
        for (int i=0;i<pagesQueue.length;i++)
            System.out.println(pagesQueue[i]);
    }
    public void showPages(){
        for (int i=0;i<pages.length;i++)
            System.out.println(pages[i]);

    }


    public Page[] getPages() {
        return pages;
    }

    public Page[] getPagesQueue() {
        return pagesQueue;
    }

}
