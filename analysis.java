import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedList;

public class analysis extends headquarters{
    public static void writeFile(String s){
        File myObj = new File(s);
    }
    public static void report(int[] indexes, String fileName){
        writeFile("report.txt");

        String predCluster = "C1";
        int predNum = 0;

        for(int i = 0; i < 8; i++){
            cluster1.add(i);
        }
        for(int i = 0; i < 8; i++){
            cluster2.add(8+i);
        }
        for(int i = 0; i < 8; i++){
            cluster3.add(16+i);
        }

        int clusterOneCount = 0;
        int clusterTwoCount = 0;
        int clusterThreeCount = 0;

        for(int i = 0; i<indexes.length; i++){
            if(cluster1.contains(indexes[i])){
                clusterOneCount++;
            } else if(cluster2.contains(indexes[i])){
                clusterTwoCount++;
            } else if(cluster3.contains(indexes[i])){
                clusterThreeCount++;
            }
        }

        if(clusterTwoCount > clusterOneCount){
            if (clusterThreeCount > clusterTwoCount){
                predCluster = "C7";
                predNum = 2;
            } else{
                predCluster = "C4";
                predNum = 1;
            }
        }
        


        double percent1 = 100 *((double)clusterOneCount / indexes.length);
        double percent2 = 100 *((double)clusterTwoCount / indexes.length);
        double percent3 = 100 *((double)clusterThreeCount / indexes.length);


        try {
            FileWriter myWriter = new FileWriter("report.txt");
            myWriter.write("The program thought document -" + fileName + "- belonged in cluster: ");
            myWriter.write(predCluster);
            int i = 0;
            myWriter.write("\n");
            myWriter.write("Percent C1: ");
            myWriter.write(Double.toString(percent1));
            myWriter.write("%");
            myWriter.write("\n");
            myWriter.write("Percent C4: ");
            myWriter.write(Double.toString(percent2));
            myWriter.write("%");
            myWriter.write("\n");
            myWriter.write("Percent C7: ");
            myWriter.write(Double.toString(percent3));
            myWriter.write("%");
            myWriter.write("\n");
            myWriter.write("\n");
            myWriter.flush();
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        writeConfunsionMatrix(indexes,predNum);

    }

    public static String padder(String s){
        int num = 20 - s.length();
        for(int i = 0; i < num; i++){
            s = s + " ";
        }
        return s;
    }

    public static void writeConfunsionMatrix(int[] indexes, int predNum){

        try {
            File f = new File("report.txt");
            PrintWriter out = new PrintWriter(new FileWriter(f, true));
            //System.out.println(i);
            out.append("Confusion Matrix: \n");
            out.append("\n");
            out.append("\n");
            out.append(padder(" "));
            out.append(padder("True"));
            out.append(padder("False"));
            out.append(padder("Precision"));
            out.append("\n");
            out.append(padder("Tested True"));
            int correct = 0;
            for(int element:clusterArray.get(predNum)){
                for(int j = 0; j < indexes.length; j++){
                    if(element == indexes[j]){
                        correct++;
                    }
                }

            }
            
            int truePositives = correct;
            int falsePositives = indexes.length - correct;
            int falseNegatives = 8 - correct;
            int trueNegatives = 24-indexes.length - falseNegatives;
            double truePositives1 = truePositives;
            double falsePositives1 = falsePositives;
            double falseNegatives1 = falseNegatives;
            double trueNegatives1 = trueNegatives;
            double precisionPositive = 100.0 * (truePositives1 / (truePositives1 + falsePositives1));
            double precisionNegative = 100.0 * (trueNegatives1 / (trueNegatives1 + falseNegatives));
            double recallPositive = 100.0 * (truePositives1 / (truePositives1 + falseNegatives1));
            double recallNegative = 100.0 * (trueNegatives1 / (trueNegatives1 + falsePositives1));
            out.append(padder(Integer.toString(truePositives)));
            out.append(padder(Integer.toString(falsePositives)));
            out.append(padder(Double.toString(precisionPositive)));
            out.append("\n");
            out.append(padder("Tested false"));
            out.append(padder(Integer.toString(falseNegatives)));
            out.append(padder(Integer.toString(trueNegatives)));
            out.append(padder(Double.toString(precisionNegative)));
            out.append("\n");
            out.append(padder("Recall"));
            out.append(padder(Double.toString(recallPositive)));
            out.append(padder(Double.toString(recallNegative)));
            out.append("\n");
            out.append("\n");
            out.append("\n");

           out.close();
            //System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}


