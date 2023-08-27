import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {



    public static void main(String[] args) {

        ParameterBag bag = new ParameterBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();
        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculator);
        System.out.println(root);

        //System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");

    }


}
