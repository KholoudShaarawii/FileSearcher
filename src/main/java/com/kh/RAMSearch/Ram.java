package com.kh.RAMSearch;

import strategy.SearchStrategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ram implements SearchStrategy {

    private List<Path> allFiles = new ArrayList<>();

    public Ram() {
        loadAllFiles();
    }

    private void loadAllFiles() {
        File[] roots = File.listRoots();

        for (File root : roots) {
            try {
                Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                        allFiles.add(file);
                        return FileVisitResult.CONTINUE;
                    }
                });

            } catch (IOException e) {}}}
    @Override
    public List<Path> search(String fileName) {

        String lower = fileName.toLowerCase();
        return allFiles.stream()
                .filter(path -> path.getFileName()
                 .toString()
                  .toLowerCase()
                 .contains(lower))
                .collect(Collectors.toList());
    }
}