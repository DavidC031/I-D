/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closet_pairs_with_linked_list;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author User
 */
public class Closet_Pairs_With_Linked_List {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         testBrute("brute.txt");
    }

    private static void testBrute(String brutetxt) {
        create(name);
        Distance d = new Distance();
        PrintWriter printer = new PrintWriter(name); //To change body of generated methods, choose Tools | Templates.
        for (int i = 2; i <= 18; i++) {
            int points = (int) Math.pow(2, i);
            double[] closest = new double[5];
            generate(points, (int) Math.pow(4, i), 1000);
            long start = System.nanoTime();
            closest = d.bruteForce(pointList);
            long end = System.nanoTime();
            long time = end - start;
            printer.printf("%s\n", points + " " + d.getIter() + " " + time);
            d.resetCounter();
        }
        printer.close();
    }
    //Performs test with the divide and conquer algorithm
    public static void testDivide(String name) {
        create(name);
        Distance d = new Distance();
        try {
            PrintWriter printer = new PrintWriter(name);
            for (int i = 2; i <= 18; i++) {
                int points = (int) Math.pow(2, i);
                long sumIter = 0;
                long sumTime = 0;
                for (int j = 1; j <= 4; j++) {
                    double[] closest = new double[5];
                    generate(points, (int) Math.pow(4, i), 1000);
                    long start = System.nanoTime();
                    closest = d.divideAndConquer(pointList);
                    long end = System.nanoTime();
                    long time = end - start;
                    sumIter = sumIter + d.getIter();
                    sumTime = sumTime + time;
                    d.resetCounter();
                }
                printer.printf("%s\n", points + " " + sumIter / 4 + " " + sumTime / 4);
            }
            printer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //Creates the file
    public static void create(String name) {
        try {
            File f = new File(name);
            if (f.createNewFile()) {
                System.out.println("Created: " + name + " Successfully!");
            } else {
                System.out.println(name + " already exists!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
