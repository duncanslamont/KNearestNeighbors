public class KNN {


    public static int[] KNearest(double[][] matrix, double[] unknown, int k){

        //System.out.println(unknown.length);
        //System.out.println(matrix[0].length);

        boolean flag = false;

        int greatestIndex = -1;
        int[] returner = new int[k];
        for(int o = 0; o < k; o++){
            returner[o] = -1;
        }
        double currentSim = 0.0;
        for(int j = 0; j < k; j++){
            for(int i = 0; i < matrix.length; i++){
                for(int l = 0; l < k; l++){
                    if(returner[l] == i){
                        //System.out.println(returner[l]);
                        flag = true;
                    }
                }
                //System.out.println("Got this far !");
                if((cosSim(matrix[i], unknown) > currentSim) & !flag){
                    //System.out.println("Happened");
                    greatestIndex = i;
                    currentSim = cosSim(matrix[i], unknown);
                }
                flag = false;
            }
            returner[j] = greatestIndex;
            greatestIndex = -1;
            currentSim = 0.0;
        }
        return returner;
    }

    public static double cosSim(double[] v1, double[]v2){
        return cosTop(v1, v2) / cosBottom(v1, v2);
    }

    public static double cosTop(double[] v1, double[]v2){

        double cumlative = 0.0;
        //System.out.println(v1[0]);
        //System.out.println(v2[0]);
        for(int i = 0; i < v1.length; i++){
            cumlative += v1[i] * v2[i];
            //System.out.println(cumlative);
        }

        return cumlative;
    }

    public static double cosBottom(double[] v1, double[]v2){

        double aCumlative = 0.0;
        double bCumlative = 0.0;
        for(int i = 0; i < v1.length; i++){
            aCumlative += Math.pow(v1[i], 2);
            bCumlative += Math.pow(v2[i], 2);
        }

        return Math.sqrt(aCumlative) * Math.sqrt(bCumlative);
    }
    
}