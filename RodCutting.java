import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RodCutting {

    public static void main(String[] args) {
        if(args.length < 2){
            System.out.println("Invalid commandline arguments!");
            System.exit(1);
        }

        String infileName = args[0];
        String outfileName = args[1];
        // create out file
        File outfile = new File(outfileName);
        try {
            if(!outfile.createNewFile()){
                outfile.delete();
                outfile.createNewFile();
            }
        }catch (IOException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //read input file
        File infile = new File(infileName);
        Scanner fileReader;
        FileWriter fileWriter;
        try{
            fileReader = new Scanner(infile);
            fileWriter = new FileWriter(outfileName);
            int instanceCount = 0, rodLength = 0;
            if(fileReader.hasNextLine()){
                instanceCount = Integer.parseInt(fileReader.nextLine());
            }
            for(int i=0; i< instanceCount; i++){
                if(fileReader.hasNextLine()){
                    rodLength = Integer.parseInt(fileReader.nextLine());
                    ArrayList<Integer> priceList = new ArrayList<>();
                    for(int j = 0; j< rodLength; j++){
                        priceList.add(Integer.parseInt(fileReader.nextLine()));
                    }
                    int rmax = cutRod(priceList, rodLength);
                    fileWriter.write(String.valueOf(rmax) + "\n");

                }
            }
            fileWriter.close();
        }catch (FileNotFoundException ex){
            System.out.println("Error: invalid input file name.");
            System.exit(1);
        }catch (NumberFormatException ex){
            System.out.println("Error: the input file contains invalid value.");
            System.exit(1);
        }catch (IOException ex){
            System.out.println("Error: invalid out file name.");
            System.exit(1);
        }

    }

    public static int cutRod(ArrayList<Integer> priceList, int rodLength){
        if(rodLength == 0) return 0;
        int q = -1;
        for(int i = 0; i< rodLength; i++){
            q = Math.max(q, priceList.get(i) + cutRod(priceList, rodLength-i-1));
        }
        return q;
    }
}
