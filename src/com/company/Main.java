package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Pager pager=null;
        Scanner scan=new Scanner(System.in);
        System.out.println("\nZ jakiego zakresu losować strony?");
        int range = scan.nextInt();
        System.out.println("\nIle ramek posiada system?");
        int frames= scan.nextInt();

        Planner planner = new Planner(range, frames);

        System.out.println("Process: "+ planner.equal());

    }
}