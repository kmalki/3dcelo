
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Grid grid = new Grid("input.txt");
        System.out.println(Arrays.toString(grid.visibleTrees()));

    }

}