package com.company;

import java.util.Scanner;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Pager pager=null;
        Scanner scan=new Scanner(System.in);
        System.out.println("\nZ jakiego zakresu losować strony?");
        int range = scan.nextInt();
        System.out.println("\nIle ramek posiada system?");
        int frames= scan.nextInt();

        Planner planner = new Planner(range, frames);

        System.out.println("Equal: "+ planner.equal());
        System.out.println("Proportional: "+ planner.proportional());
        System.out.println("Site Faults: "+ planner.pageFault());
        System.out.println("Working Set Model: "+ planner.workingSetModel());


    }
}