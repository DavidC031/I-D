/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package close.pairs.divide.and.conquer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author edcalderon
 */
public class ClosePairsDivideAndConquer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        start();
        // TODO code application logic here
    }

    public static void start() {
        Point P[] = {new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4), new Point(5, 4), new Point(60, 4)};
        System.out.println("la distancia mas cercana es " + closest(P));
    }

    public static double closest(Point[] p) {
        Arrays.sort(p, (p1, p2) -> p1.x - p2.x);
        return closestUtil(p, 0, p.length - 1);
    }

    private static double closestUtil(Point[] p, int i, int j) {
        if (j - i + 1 <= 3) {
            return bruteForce(p, i, j);
        }
        int n = j - i + 1;
        int mid = i + (j - i) / 2;
        Point midPoint = p[mid];
        double dl = closestUtil(p, i, mid);
        double dr = closestUtil(p, mid + 1, j);
        double d = Math.min(dl, dr);
        List<Point> list = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (Math.abs(p[k].x - midPoint.x) < d) {
                list.add(p[k]);
            }
        }
        return Math.min(d, stripClosest(list.toArray(new Point[0]), d));
    }

    private static double bruteForce(Point[] p, int start, int end) {
        double min = Double.MAX_VALUE;
        if (p != null && end - start > 0) {
            for (int i = start; i < end - 1; i++) {
                for (int j = i + 1; j < end; j++) {
                    min = Math.min(min, dist(p[i], p[j]));
                }
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    private static double stripClosest(Point[] strip, double d) {
        double tinicial, tfinal;
        double cont=0;
        // aqui generar 3 vectores	
        double min = d;
        int runs = 10;
        int reps =360;
        int size = strip.length;
        double[][] statistics = new double[3][runs];
        double[] sizes = statistics[0];
        double[] etimes = statistics[1];
        double[] operations = statistics[2];
        Arrays.sort(strip, (p1, p2) -> p1.y - p2.y);
        for (int k = 0; k != runs; k++) {
            tinicial = System.nanoTime();
            sizes[k] = ((double) size);
            statistics[0][k] = sizes[k];
            for (int i = 0; i < strip.length; i++) {
                for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                    double distance = dist(strip[i], strip[j]);
                    if (distance < min) {

                        min = distance;

                    }
                    cont = cont+1;
                    operations[k] = cont;
                    statistics[2][k] = operations[k];
                    /*System.out.println("peraciones "+statistics[2][k]);*/
                }
            }
            tfinal = System.nanoTime();
            double temp = (tfinal - tinicial);
            etimes[k] = temp;
            statistics[1][k] = etimes[k];
             System.out.println("time"+statistics[1][k]);
            size *= 2;
        }
        export(statistics,operations);
        return min;
    }

    private static void recursiveClosestPair(Point[] P) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class Point {

        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }

    private static double[] replicate(int size) // repeats the numeric experiment
    {
        int reps = 256;
        //conectar operation y times !!!!!!!
        double etime = 0;
        double operations = 0;
        for (int i = 0; i != reps; ++i) {
            // resets counter
            operations = 0;
            Point P[] = {new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4), new Point(5, 4), new Point(60, 4)};
            // solves the closest pair problem recursively
            recursiveClosestPair(P);
            // updates the elapsed-time and #operations
            etime += etime;
            operations += ((double) operations);
        }

        // computes the averages and return statistics
        double avg_etime = (etime / reps);
        double avg_operations = (operations / reps);
        double[] statistics = {avg_etime, avg_operations};
        return statistics;
    }
    
    private static void export(double stats[][],double op[]) // exports the elapsed-time and #operations with respect to size
    {
        try {
            String file = ("time.dat");
            PrintWriter out = new PrintWriter(file);
            // conducts the experiments			
            // gets the number of experiments (or runs)
            int runs = stats[1].length;
            System.out.println("runs = "+runs);
            for (int i = 0; i != runs; ++i) {
                // gets size, elapsed-time, and #operations
                double size = stats[0][i];
                double etime = stats[1][i];
                double opers = stats[2][i]+op[i];
                System.out.println("opers"+opers);
                // writes data to file in tabulated format
                String fmt = ("%16.8e %16.8e %16.8e\n");
                out.printf(fmt, size, etime, opers);
            }
            out.close();
        } catch (FileNotFoundException err) {
            err.printStackTrace();
        }
    }

    /*private static class Ensemble {

        private static int operations;

        private static Vector genDataSet(int size) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public Ensemble() {
        }
    }*/
}
