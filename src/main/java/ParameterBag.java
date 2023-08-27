import java.io.File;

public class ParameterBag {

    private String path;
    private long limit;

    public ParameterBag(String[] args){
        if(args.length != 4){
            throw new IllegalArgumentException("укажите два параметра: -l (объём лимита) и -d (путь к папке)");
        };
        limit = 0;
        path = "";
        for(int i = 0; i < args.length; i = i + 2){
            System.out.println(i);
            if(args[i].equals("-l")){
                limit = SizeCalculator.getSizeFromHumanReadable(args[i + 1]);
            }else if(args[i].equals("-d")) {
                this.path = args[i + 1];
            }
        }

        if(limit <= 0 ){
            throw new IllegalArgumentException("Лимит указан невернот");
        }

        File folder = new File(path);
        if(!folder.exists()) {
            if (!folder.isDirectory()) {
                throw new IllegalArgumentException("Путь к папку указан неверно");
            }
        }

    }

    public String getPath() {
        return path;
    }

    public long getLimit() {
        return limit;
    }
}
