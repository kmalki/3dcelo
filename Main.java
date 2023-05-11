import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.lang.Math;

public class Main {

//    public enum Direction{
//        TOP, LEFT, DOWN, RIGHT
//    }
//
//    public static List<Integer> loadGrid(String fileName){
//        List<Integer> list = new ArrayList<>();
//        Scanner scan;
//        try {
//            scan = new Scanner(new File(fileName));
//
//            while(scan.hasNextLine()){
//                list.addAll(Arrays.stream(scan.next().split("")).map(
//                        Integer::valueOf
//                ).collect(Collectors.toList()));
//            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());;
//        }
//        return list;
//    }
//
//    public static int getHeight(List<Integer> grid){
//        return (int) (Math.sqrt(grid.size()));
//    }
//
//    public static boolean isOnBorder(int height, int index){
//        return ((index+1) % height == 0) ||
//                ((index+1) % height == 1) ||
//                (index < height) || (index >= height * (height-1));
//    }
//
//    public static int lookUp(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
//        boolean tallerDoesntExist = true;
//        int start = counter.get();
//        int temp = index.get();
//        while(temp > height){
//            temp -= height;
//            if(grid.get(temp) >= tree){
//                tallerDoesntExist = false;
//                break;
//            }
//        }
//        if(tallerDoesntExist){
//            counter.getAndIncrement();
//        }
//        System.out.printf("%d looking up : %d\n", grid.get(index.get()), counter.get()-start);
//        return counter.get();
//    }
//
//    public static int lookDown(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
//        boolean tallerDoesntExist = true;
//        int start = counter.get();
//        int temp = index.get();
//        while(temp < height*(height-1)){
//            temp += height;
//            if(grid.get(temp) >= tree){
//                tallerDoesntExist = false;
//                break;
//            }
//        }
//        if(tallerDoesntExist){
//            counter.getAndIncrement();
//        }
//        System.out.printf("%d looking down : %d\n", grid.get(index.get()), counter.get()-start);
//        return counter.get();
//    }
//
//    public static int lookLeft(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
//        boolean tallerDoesntExist = true;
//        int start = counter.get();
//        int temp = index.get();
//        while(temp > (temp-(temp % height))){
//            temp--;
//            if(grid.get(temp) >= tree){
//                tallerDoesntExist = false;
//                break;
//            }
//        }
//        if(tallerDoesntExist){
//            counter.getAndIncrement();
//        }
//        System.out.printf("%d looking left : %d\n", grid.get(index.get()), counter.get()-start);
//        return counter.get();
//    }
//
//    public static int lookRight(List<Integer> grid, AtomicInteger index, AtomicInteger counter, Integer tree, int height){
//        boolean tallerDoesntExist = true;
//        int start = counter.get();
//        int temp = index.get();
//        while((temp+1) % height > 0 ){
//            temp++;
//            if(grid.get(temp) >= tree){
//                tallerDoesntExist = false;
//                break;
//            }
//        }
//        if(tallerDoesntExist){
//            counter.getAndIncrement();
//        }
//        System.out.printf("%d looking right : %d\n", grid.get(index.get()), counter.get()-start);
//        return counter.get();
//    }
//
//    public static int visibleTrees(List<Integer> grid, int height){
//        AtomicInteger index = new AtomicInteger(0);
//        AtomicInteger counter = new AtomicInteger(0);
//
//        grid.forEach(
//                tree -> {
//                    if (isOnBorder(height, index.get())){
//                        counter.getAndIncrement();
//                    }else{
//                        int startCounting = counter.get();
//                        int tempCounting = 0;
//                        for (Direction d:
//                             Direction.values()) {
//
//                            switch(d) {
//                                case TOP:
//                                    System.out.println("LOOKING UP");
//                                    tempCounting = lookUp(grid, index, counter, tree, height);
//                                    break;
//                                case RIGHT:
//                                    System.out.println("LOOKING RIGHT");
//                                    tempCounting = lookRight(grid, index, counter, tree, height);
//                                    break;
//                                case DOWN:
//                                    System.out.println("LOOKING DOWN");
//                                    tempCounting = lookDown(grid, index, counter, tree, height);
//                                    break;
//                                case LEFT:
//                                    System.out.println("LOOKING LEFT");
//                                    tempCounting = lookLeft(grid, index, counter, tree, height);
//                                    break;
//
//
//                            }
//
//                            if(tempCounting > startCounting){
//                                break;
//                            }
//
//                        }
//
//
////                        if(tempCounting > startCounting){
////                            break;
////                        }
////                        System.out.println("LOOKING DOWN");
////                        lookDown(grid, index, counter, tree, height);
////                        System.out.println("LOOKING LEFT");
////                        lookLeft(grid, index, counter, tree, height);
////                        System.out.println("LOOKING RIGHT");
////                        lookRight(grid, index, counter, tree, height);
//                    }
//                    index.getAndIncrement();
//                }
//        );
//        return counter.get();
//    }

    public static void main(String[] args) {
        Grid grid = new Grid("sample.txt");
        System.out.println(Arrays.toString(grid.visibleTrees()));

    }

}