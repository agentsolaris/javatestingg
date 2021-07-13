package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<ArrayList< Integer >, Integer > harta;
    private static final ArrayList < Integer > x = new ArrayList < > ();
    private static final ArrayList < Integer > y = new ArrayList < > ();
    static Boolean plagiat = false;

    public static String checkIfMapContainsTriangles(Integer t, Integer n, ArrayList < Integer > x, ArrayList < Integer > y) {
        Map < ArrayList < Integer > , Integer > harta = new HashMap<>();
        if (t<1 || t>5){
            System.out.println("Please enter an integer between 1 and 5");
            return "-1";
        }

        if(n < 1 || n > 400){
            System.out.println("Please enter an integer between 1 and 400");
            return "-1";
        }
        for ( int i=0 ; i < x.size(); i++){
            if ((x.get(i) < 0 || x.get(i) > 1000000000) || (y.get(i) < 0 || y.get(i) > 1000000000) ){
                System.out.println("Please enter a number between 0 and 10^9");
                return "-1";
            }
        }
        plagiat = false;
        //harta.clear();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n; j++) {
                ArrayList < Integer > pair = new ArrayList < > ();
                pair.add(Math.abs(x.get(j) - x.get(i)));
                pair.add(Math.abs(y.get(j) - y.get(i)));
                if (harta.containsKey(pair)) {
                    Integer curr_value = harta.get(pair);
                    harta.replace(pair, curr_value + 1);
                } else {
                    harta.put(pair, 1);
                }

                if (harta.get(pair) >= 3) {
                    plagiat = true;
                    break;
                }
            }
            if (plagiat)
                break;
        }
        if (plagiat)
            return ("DA\n");
        else
            return ("NU\n");
    }
    public static int checkTMaps(Integer t) {
        try {
            File file = new File("plagiat.in");
            Scanner scanner = new Scanner(file);
            scanner.nextInt();

            //harta = new HashMap < > ();
            StringBuilder result = new StringBuilder();

            for (int nrHarta = 0; nrHarta < t; nrHarta++) {
                //plagiat = false;
                int n = scanner.nextInt();
                x.clear();
                y.clear();
                for (int i = 0; i < n; i++) {
                    x.add(scanner.nextInt());
                    y.add(scanner.nextInt());
                }
                //harta = new HashMap < > ();
                //harta.clear();
                result.append(checkIfMapContainsTriangles(t, n, x, y));
                writeToFile(result.toString());

            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        if (t<1 || t>5){
            //System.out.println("Please enter an integer between 1 and 5");
            return 0;
        }
        return 1;
    }


    public static void writeToFile(String s) {
        try {
            FileWriter myWriter = new FileWriter("plagiat.out");
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing in file.");
            e.printStackTrace();
        }
    }

    //    public void solve(Integer t, Integer n, ArrayList < Integer > x, ArrayList < Integer > y) throws FileNotFoundException {
//        checkTMaps(t);
//    }
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("plagiat.in");
        Scanner scanner = new Scanner(file);
        Integer t = scanner.nextInt();
        checkTMaps(t);
        scanner.close();
    }
}