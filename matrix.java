import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class matrix extends preprocessing {
    
    
    public static void writeFile(String s){
        File myObj = new File(s);
    }

   

    public static void writeToFile(double[][] matrix, String filea){
        try {
            FileWriter myWriter = new FileWriter(filea);
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length;j++){
                    myWriter.write(Double.toString(matrix[i][j]));
                    myWriter.write(",");
                }
                myWriter.write("\n");
            }
            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }

    public static double[][] generateMatrix(ArrayList<ArrayList<String>> fileArrays){
        
        for(ArrayList<String> list:fileArrays){
            for(String word:list){
                if(wordList.contains(word)){
                    countList.set(wordList.indexOf(word), countList.get(wordList.indexOf(word)) + 1);
                } else {
                    wordList.add(word);
                    countList.add(1);
                }
            }
        }
        for(String word:wordList){
            if(countList.get(wordList.indexOf(word)) > 5){
                indexes.add(wordList.indexOf(word));
                //System.out.println(word);
            }
        }
        //System.out.println("size");
       // System.out.println(indexes.size());
        indexes.remove(wordList.indexOf("a"));

        int sizer = 0;
        final String words[] = new String[indexes.size()];
        for(Integer i:indexes){
            words[sizer] = wordList.get(i);
            sizer++;
        }
        ArrayList<String> wordArrayList = new ArrayList<String>(Arrays.asList(words));
        //System.out.println("Word array list size :");
        //System.out.println(wordArrayList.size());

        int finalMatrix[][] = new int[fileArrays.size()][indexes.size()];
        //System.out.println("Indexes size :");
        //System.out.println(indexes.size());

        int indexForFile = 0;

        for(ArrayList<String> list:fileArrays){
            for(String word:list){
                if(wordArrayList.contains(word)){
                    finalMatrix[indexForFile][wordArrayList.indexOf(word)] += 1;
                } 
            }
            indexForFile++;
        }
        //printMyMatrix(finalMatrix);
        //idf(wordArrayList);
        double[][] returnMatrix = tdidf(tf(wordArrayList,fileArrays,false),idf(wordArrayList, fileArrays,false,false), wordArrayList);
        writeFile("tfidf.txt");
        writeToFile(returnMatrix,"tfidf.txt");
        //generateKeyWords(returnMatrix, indexes, wordList);
        return returnMatrix;
    }
    public static double[][] generateMatrixNoCreate(ArrayList<ArrayList<String>> fileArrays){
        
        int sizer = 0;
        final String words[] = new String[indexes.size()];
        for(Integer i:indexes){
            words[sizer] = wordList.get(i);
            sizer++;
        }
        ArrayList<String> wordArrayList = new ArrayList<String>(Arrays.asList(words));
        //System.out.println("Word array list size :");
        //System.out.println(wordArrayList.size());

        int finalMatrix[][] = new int[fileArrays.size()][indexes.size()];
        //System.out.println("Indexes size :");
        //System.out.println(indexes.size());

        int indexForFile = 0;

        for(ArrayList<String> list:fileArrays){
            for(String word:list){
                if(wordArrayList.contains(word)){
                    finalMatrix[indexForFile][wordArrayList.indexOf(word)] += 1;
                } 
            }
            indexForFile++;
        }
        //printMyMatrix(finalMatrix);
        //idf(wordArrayList);
        double[][] returnMatrix = tdidf(tf(wordArrayList,fileArrays,false),idf(wordArrayList,fileArrays,true,false), wordArrayList);
        writeFile("tfidf1.txt");
        writeToFile(returnMatrix,"tfidf1.txt");
        //generateKeyWords(returnMatrix, indexes, wordList);
        return returnMatrix;
    }

    public static double[][] genVec(ArrayList<ArrayList<String>> singleUseless){
        
        int sizer = 0;
        final String words[] = new String[indexes.size()];
        for(Integer i:indexes){
            words[sizer] = wordList.get(i);
            sizer++;
        }
        ArrayList<String> wordArrayList = new ArrayList<String>(Arrays.asList(words));
        //System.out.println("Word array list size :");
        //System.out.println(wordArrayList.size());

        int finalMatrix[][] = new int[singleUseless.size()][indexes.size()];
        //System.out.println("Indexes size :");
        //System.out.println(indexes.size());

        int indexForFile = 0;

        for(ArrayList<String> list:singleUseless){
            for(String word:list){
                if(wordArrayList.contains(word)){
                    finalMatrix[indexForFile][wordArrayList.indexOf(word)] += 1;
                } 
            }
            indexForFile++;
        }
        //printMyMatrix(finalMatrix);
        //idf(wordArrayList);
        double[][] returnMatrix = tdidf(tf(wordArrayList,singleUseless, true),idf(wordArrayList,singleUseless,true,true), wordArrayList);
        writeFile("tfidf2.txt");
        writeToFile(returnMatrix,"tfidf2.txt");
        //generateKeyWords(returnMatrix, indexes, wordList);
        return returnMatrix;
    }


    public static double[][] tf(ArrayList<String> wordArrayList, ArrayList<ArrayList<String>> arrayX, boolean b) {
        double newMatrix[][] = new double[arrayX.size()][wordArrayList.size()];
        int goodOldCounter = 0;
        for(String keyword:wordArrayList){
            
            int indexForFile = 0;
            for(ArrayList<String> list:arrayX){
                int fileWordCount = list.size();
                for(String word:list){
                    if(word.equals(keyword)){
                        goodOldCounter++;
                    }
                }
                newMatrix[indexForFile][wordArrayList.indexOf(keyword)] = goodOldCounter / (double) fileWordCount;
                if(b){
                    //System.out.println(goodOldCounter / (double) fileWordCount);
                }

                indexForFile++;
                goodOldCounter = 0;
            }
            
        }
        //System.out.println(wordArrayList.get(0));
        //printMyMatrix(newMatrix, wordArrayList);
        return newMatrix;
    }

    public static double[] idf(ArrayList<String> wordArrayList, ArrayList<ArrayList<String>> arrayX,boolean flag,
            boolean b) {
        double newMatrix[] = new double[wordArrayList.size()];
        for(String keyword:wordArrayList){
            int currentCount = 0;
            for(ArrayList<String> list:arrayX){
                int fileChecker = 0;
                for(String word:list){
                    if(word.equals(keyword) & fileChecker == 0){
                        fileChecker = 1;
                        currentCount++;
                    }
                }
                fileChecker = 0;
            }
            if(flag){
                currentCount++;
            }
            
            newMatrix[wordArrayList.indexOf(keyword)] = Math.log(25/(double)currentCount);
            if(b){
                //System.out.println(currentCount);
            }
        }
        return newMatrix;
    }

    public static double[][] tdidf(double[][] matrix, double[] row,ArrayList<String> wordArrayList){
        double newMatrix[][] = new double[matrix.length][wordArrayList.size()];
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                newMatrix[i][j] = matrix[i][j] * row[j];
            }
        }
        //printMyMatrix(newMatrix, wordArrayList);
        return newMatrix;
    }


  
    public static void printMyMatrix(int matrix[][]){ 
        
        for (int i = 0; i < matrix.length; i++){
            
            for (int j = 0; j < matrix[i].length; j++){ 
                System.out.print(matrix[i][j] + " "); 
            }
            System.out.println(" ");
        }
    } 
    public static void printMyMatrix(double matrix[][]){ 
        
        for (int i = 0; i < matrix.length; i++){
            System.out.println(i);
            
            for (int j = 0; j < matrix[i].length; j++){ 
                System.out.print(matrix[i][j] + " "); 
            }
            System.out.println(" ");
        }
    } 
}