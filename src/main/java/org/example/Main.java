/**Main class to run and test things*/
package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        House[] houses = new House[1000];

        String filepath = "C:\\Users\\matth\\OneDrive\\Desktop\\HeapSort\\src\\main\\java\\org\\example\\houses.txt";
        File file = new File(filepath);
        Scanner scnr = new Scanner(file);
        //checks the txt file and adds every house and value to the list
        int i = 0;
        while(scnr.hasNext()){

            String Name = scnr.nextLine();
            String value = scnr.nextLine();
            int val = Integer.parseInt(value);
            houses[i] = (new House(Name, val));
            i++;
        }
       //printHouseArray(houses);
        heapsort(houses);
        printHouseArray(houses);


        PriorityQueueHeap originalHeap = new PriorityQueueHeap();
        originalHeap.add(new House("Alice", 500000));
        originalHeap.add(new House("Bob", 300000));
        originalHeap.add(new House("Charlie", 700000));

        PriorityQueueHeap heapCopy = new PriorityQueueHeap(originalHeap);
        PriorityQueueHeap heapDeepCopy = originalHeap.deepCopy();

        System.out.println("Testing copy constructor:");
        compareHeaps(originalHeap, heapCopy);

        System.out.println("\nTesting deepCopy method:");
        compareHeaps(originalHeap, heapDeepCopy);




    }
    /**Heapsort method takes in a array of items, then converts it into a heap. After that it turns it back into a array but now sorted from greatest*/
    public static void heapsort(House[] a){
        PriorityQueueHeap heap = new PriorityQueueHeap();
        for (int i = 0; i < a.length; i++) {
            heap.add(a[i]);
        }
       // heap.print();
        for (int i = 0; i < a.length; i++) {
            a[i] = heap.getMostExpensive();

        }
    }
    /**Method to print array of houses*/
    public static void printHouseArray(House[] houses) {
        for (int i = 0; i<houses.length;i++){
            System.out.println(houses[i].getOwner() +": " + houses[i].getValue());
        }
    }
    /**Method to compare the Heaps*/
    public static void compareHeaps(PriorityQueueHeap original, PriorityQueueHeap copy) {
        System.out.println("Original heap:");
        original.print();

        System.out.println("Copied heap:");
        copy.print();
    }
}