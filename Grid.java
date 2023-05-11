import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.lang.Math;

public class Grid {

    public enum Direction{
        UP, RIGHT, DOWN, LEFT
    }

    private final List<Integer> trees;
    private final int height;

    public Grid(String fileName){
        trees = loadGrid(fileName);
        height = getHeight(trees);
    }

    public List<Integer> loadGrid(String fileName){
        List<Integer> list = new ArrayList<>();
        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));

            while(scan.hasNextLine()){
                list.addAll(Arrays.stream(scan.next().split("")).map(
                        Integer::valueOf
                ).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }
        return list;
    }

    public boolean isOnBorder(int height, int index){
        return ((index+1) % height == 0) ||
                ((index+1) % height == 1) ||
                (index < height) || (index >= height * (height-1));
    }

    public int getHeight(List<Integer> trees){
        return (int) (Math.sqrt(trees.size()));
    }


    public int[] lookUp(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
        int[] returnValues = new int[2];
        int seeingTrees = 0;
        boolean tallerDoesntExist = true;
        int start = counter.get();
        int temp = index.get();
        while(temp >= height){
            if(temp == height){
                seeingTrees++;
                break;
            }
            temp -= height;
            if(grid.get(temp) >= tree){
                if(grid.get(temp).equals(tree)){
                    seeingTrees++;
                }
                tallerDoesntExist = false;
                break;
            }
            seeingTrees++;
        }
        if(tallerDoesntExist){
            counter.getAndIncrement();
        }
        System.out.printf("%d looking up : %d\n", grid.get(index.get()), counter.get()-start);
        returnValues[0] = counter.get();
        returnValues[1] = seeingTrees;
        return returnValues;
    }


    public int[] lookRight(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
        int[] returnValues = new int[2];
        int seeingTrees = 0;
        boolean tallerDoesntExist = true;
        int start = counter.get();
        int temp = index.get();
        while((temp+1) % height >= 0 ){
            if((temp+1) % height == 0){
                seeingTrees++;
                break;
            }
            temp++;
            if(grid.get(temp) >= tree){

                tallerDoesntExist = false;
                break;
            }
            seeingTrees++;
        }
        if(tallerDoesntExist){
            counter.getAndIncrement();
        }
        System.out.printf("%d looking right : %d\n", grid.get(index.get()), counter.get()-start);
        returnValues[0] = counter.get();
        returnValues[1] = seeingTrees;
        return returnValues;
    }


    public int[] lookDown(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
        int[] returnValues = new int[2];
        int seeingTrees = 0;
        boolean tallerDoesntExist = true;
        int start = counter.get();
        int temp = index.get();
        while(temp <= height*(height-1)){
            if(temp == height*(height-1)){
                seeingTrees++;
                break;
            }
            temp += height;
            if(grid.get(temp) >= tree){
                if(grid.get(temp).equals(tree)){
                    seeingTrees++;
                }
                tallerDoesntExist = false;
                break;
            }
            seeingTrees++;
        }
        if(tallerDoesntExist){
            counter.getAndIncrement();
        }
        System.out.printf("%d looking down : %d\n", grid.get(index.get()), counter.get()-start);
        returnValues[0] = counter.get();
        returnValues[1] = seeingTrees;
        return returnValues;
    }

    public int[] lookLeft(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
        int[] returnValues = new int[2];
        int seeingTrees = 0;
        boolean tallerDoesntExist = true;
        int start = counter.get();
        int temp = index.get();
        while(temp >= (temp-(temp % height))){
            if(temp == (temp-(temp % height))){
                seeingTrees++;
                break;
            }
            temp--;
            if(grid.get(temp) >= tree){
                if(grid.get(temp).equals(tree)){
                    seeingTrees++;
                }
                tallerDoesntExist = false;
                break;
            }
            seeingTrees++;
        }
        if(tallerDoesntExist){
            counter.getAndIncrement();
        }
        System.out.printf("%d looking left : %d\n", grid.get(index.get()), counter.get()-start);
        returnValues[0] = counter.get();
        returnValues[1] = seeingTrees;
        return returnValues;
    }

    public int[] visibleTrees(){
        AtomicInteger index = new AtomicInteger(0);
        AtomicInteger counter = new AtomicInteger(0);
        AtomicInteger scenic = new AtomicInteger(0);

        trees.forEach(
                tree -> {
                    if (isOnBorder(height, index.get())) {
                        counter.getAndIncrement();
                    } else {
                        int startCounting = counter.get();
                        int tempCounting = 0;
                        int tempScenic = 1;
                        int[] returnCountings;
                        for (Direction d :
                                Direction.values()) {

                            switch (d) {
                                case UP:
                                    System.out.println("LOOKING UP");
                                    returnCountings = lookUp(trees, index, counter, tree, height);
                                    tempCounting = returnCountings[0];
                                    tempScenic *= returnCountings[1];
                                    System.out.println("up" + returnCountings[1]);
                                    break;
                                case RIGHT:
                                    System.out.println("LOOKING RIGHT");
                                    returnCountings = lookRight(trees, index, counter, tree, height);
                                    tempCounting = returnCountings[0];
                                    tempScenic *= returnCountings[1];
                                    System.out.println("right" + returnCountings[1]);

                                    break;
                                case DOWN:
                                    System.out.println("LOOKING DOWN");
                                    returnCountings = lookDown(trees, index, counter, tree, height);
                                    tempCounting = returnCountings[0];
                                    tempScenic *= returnCountings[1];
                                    System.out.println("down" + returnCountings[1]);

                                    break;
                                case LEFT:
                                    System.out.println("LOOKING LEFT");
                                    returnCountings = lookLeft(trees, index, counter, tree, height);
                                    tempCounting = returnCountings[0];
                                    tempScenic *= returnCountings[1];
                                    System.out.println("left" + returnCountings[1]);

                                    break;
                            }

                            if (tempCounting > startCounting) {
                                break;
                            }
                        }
                        if(tempScenic > scenic.get()){
                            scenic.set(tempScenic);
                        }
                    }
                        index.getAndIncrement();
                    }

        );
            return new int[]{counter.get(), scenic.get()};
        }

}
