import com.kh.IndexSearch.Index;
import com.kh.RAMSearch.Ram;
import com.kh.hardDiskSearch.HardDisk;

import strategy.SearchStrategy;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose search method:");
        System.out.println("1 - Hard Disk");
        System.out.println("2 - RAM");
        System.out.println("3 - Index");


        int choice = scanner.nextInt();
        scanner.nextLine();

        SearchStrategy strategy;

        switch (choice) {
            case 1:
                strategy = new HardDisk();
                break;
            case 2:
                strategy = new Ram();
                break;
            case 3:
                strategy = new Index();
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        System.out.println("Enter file name:");
        String input = scanner.nextLine();

        long start = System.currentTimeMillis();
        List<Path> results = strategy.search(input);
        long end = System.currentTimeMillis();

        System.out.println("Matches: " + results.size());

        for (Path path : results) {
            System.out.println(path);
        }

        System.out.println("Time: " + (end - start) + " ms");
    }
}