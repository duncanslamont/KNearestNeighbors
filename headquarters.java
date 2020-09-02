import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.scene.shape.MeshView;

// In this project I use the official porter stemmer, https://tartarus.org/martin/PorterStemmer/java.txt
// I did not create this stemmer.
public class headquarters {

    public static String c101 = "data/c1/article01.txt";
    public static String c102 = "data/c1/article02.txt";
    public static String c103 = "data/c1/article03.txt";
    public static String c104 = "data/c1/article04.txt";
    public static String c105 = "data/c1/article05.txt";
    public static String c106 = "data/c1/article06.txt";
    public static String c107 = "data/c1/article07.txt";
    public static String c108 = "data/c1/article08.txt";
    public static String c401 = "data/c4/article01.txt";
    public static String c402 = "data/c4/article02.txt";
    public static String c403 = "data/c4/article03.txt";
    public static String c404 = "data/c4/article04.txt";
    public static String c405 = "data/c4/article05.txt";
    public static String c406 = "data/c4/article06.txt";
    public static String c407 = "data/c4/article07.txt";
    public static String c408 = "data/c4/article08.txt";
    public static String c701 = "data/c7/article01.txt";
    public static String c702 = "data/c7/article02.txt";
    public static String c703 = "data/c7/article03.txt";
    public static String c704 = "data/c7/article04.txt";
    public static String c705 = "data/c7/article05.txt";
    public static String c706 = "data/c7/article06.txt";
    public static String c707 = "data/c7/article07.txt";
    public static String c708 = "data/c7/article08.txt";
    public static String fileNames1[] = {c101,c102,c103,c104,c105,c106,c107,c108,c401,c402,c403,c404,c405,c406,c407,c408,c701,c702,c703,c704,c705,c706,c707,c708};
    public static ArrayList<String> lc101 = new ArrayList<String>();
    public static ArrayList<String> lc102 = new ArrayList<String>();
    public static ArrayList<String> lc103 = new ArrayList<String>();
    public static ArrayList<String> lc104 = new ArrayList<String>();
    public static ArrayList<String> lc105 = new ArrayList<String>();
    public static ArrayList<String> lc106 = new ArrayList<String>();
    public static ArrayList<String> lc107 = new ArrayList<String>();
    public static ArrayList<String> lc108 = new ArrayList<String>();
    public static ArrayList<String> lc401 = new ArrayList<String>();
    public static ArrayList<String> lc402 = new ArrayList<String>();
    public static ArrayList<String> lc403 = new ArrayList<String>();
    public static ArrayList<String> lc404 = new ArrayList<String>();
    public static ArrayList<String> lc405 = new ArrayList<String>();
    public static ArrayList<String> lc406 = new ArrayList<String>();
    public static ArrayList<String> lc407 = new ArrayList<String>();
    public static ArrayList<String> lc408 = new ArrayList<String>();
    public static ArrayList<String> lc701 = new ArrayList<String>();
    public static ArrayList<String> lc702 = new ArrayList<String>();
    public static ArrayList<String> lc703 = new ArrayList<String>();
    public static ArrayList<String> lc704 = new ArrayList<String>();
    public static ArrayList<String> lc705 = new ArrayList<String>();
    public static ArrayList<String> lc706 = new ArrayList<String>();
    public static ArrayList<String> lc707 = new ArrayList<String>();
    public static ArrayList<String> lc708 = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> fileArrays1 = new ArrayList<>(Arrays.asList(lc101,lc102,lc103,lc104,lc105,lc106,lc107,lc108,lc401,lc402,lc403,lc404,lc405,lc406,lc407,lc408,lc701,lc702,lc703,lc704,lc705,lc706,lc707,lc708));

    
    public static ArrayList<String> stopWords = preprocessing.stopWords();
    public static String u1 = "unknown/unknown01.txt";
    public static String u2 = "unknown/unknown02.txt";
    public static String u3 = "unknown/unknown03.txt";
    public static String u5 = "unknown/unknown05.txt";
    public static String u6 = "unknown/unknown06.txt";
    public static String u7 = "unknown/unknown07.txt";
    public static String u8 = "unknown/unknown08.txt";
    public static String u9 = "unknown/unknown09.txt";
    public static String u10 = "unknown/unknown10.txt";
    public static String fileNames[] = {u1,u2,u3,u5,u6,u7,u8,u9,u10};
    public static ArrayList<String> u01 = new ArrayList<String>();
    public static ArrayList<String> u02 = new ArrayList<String>();
    public static ArrayList<String> u03 = new ArrayList<String>();
    public static ArrayList<String> u05 = new ArrayList<String>();
    public static ArrayList<String> u06 = new ArrayList<String>();
    public static ArrayList<String> u07 = new ArrayList<String>();
    public static ArrayList<String> u08 = new ArrayList<String>();
    public static ArrayList<String> u09 = new ArrayList<String>();
    public static ArrayList<String> u10l = new ArrayList<String>();

    public static ArrayList<ArrayList<String>> fileArrays = new ArrayList<>(Arrays.asList(u01,u02,u03,u05,u06,u07,u08,u09,u10l));

    public static ArrayList<String> fileArray = new ArrayList<>();

    public static ArrayList<ArrayList<String>> singleUseless = new ArrayList<>(Arrays.asList(fileArray));

    public static ArrayList<Integer> cluster1 = new ArrayList<>();
    public static ArrayList<Integer> cluster2 = new ArrayList<>();
    public static ArrayList<Integer> cluster3 = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> clusterArray = new ArrayList<>(Arrays.asList(cluster1,cluster2,cluster3));



    

    
    public static ArrayList<String> wordList = new ArrayList<String>();
    public static ArrayList<Integer> countList = new ArrayList<Integer>();
    public static ArrayList<Integer> indexes = new ArrayList<Integer>();
    
        
    public static ArrayList<String> newWords = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> wordMatrix = new ArrayList<ArrayList<String>>();

    public static ArrayList<ArrayList<String>> getFileArray(){
        return fileArrays;
    }

    
    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in); 
        System.out.println("Enter file path:");

        String fileName = myObj.nextLine();

        


        for(int i = 0; i < fileNames1.length; i++){
            preprocessing.dataPreparation(i, fileNames1, fileArrays1);
        }
        
        double[][] oldMatrix = matrix.generateMatrix(fileArrays1);

        preprocessing.dataPreparationSingle(fileName, fileArray);

        double[][] myVector = matrix.genVec(singleUseless);

        //System.out.println(myVector.length);
        //System.out.println(oldMatrix[0][0]);

        for(int i = 0; i < fileNames.length; i++){
            preprocessing.dataPreparation(i, fileNames, fileArrays);
        }
        
        double[][] newMatrix = matrix.generateMatrixNoCreate(fileArrays);

        int[] indexess = KNN.KNearest(oldMatrix, myVector[0], 8);

        for(int i = 0; i < 8; i++){
            //System.out.println(indexess[i]);
        }
        analysis.report(indexess, fileName);

    



        

        

        
        
    }  
}