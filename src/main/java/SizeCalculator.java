import java.io.File;

public class SizeCalculator {

    private static char[] multibliers = {'B', 'K', 'M', 'G', 'T'};

    public static String getHumanReadableSize(long size){

        double value = (double) size;
        int counter = 0;
        do{
           if(value < 1024){
               break;
           }
           value /= 1024;
           counter++;
        }while(true);
        String unit;
        switch(counter){
            case 1 -> unit = "K";
            case 2 -> unit = "M";
            case 3 -> unit = "G";
            case 4 -> unit = "T";
            default -> unit = "B";
        }

        return Math.round(value * 100) / 100. +
                unit + (counter > 0 ? "b" : "");

    }

    public static long getSizeFromHumanReadable(String size){
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);

        long result = Long.valueOf(size.replaceAll("[^0-9]", ""));
        int degreeOf;
        switch(sizeFactor){
            case 'K' -> degreeOf = 1;
            case 'M' -> degreeOf = 2;
            case 'G' -> degreeOf = 3;
            case 'T' -> degreeOf = 4;
            default -> degreeOf = 0;
        }

        return result * (long)Math.pow(1024, degreeOf);
    }

    public static long getFolderSize(File folder) {
        if(folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for(File file : files){
            sum += getFolderSize(file);
        }

        return sum;
    }
}
