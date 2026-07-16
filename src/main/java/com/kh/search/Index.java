package com.kh.Indexsearch;

import strategy.SearchStrategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index implements SearchStrategy {

    private Map<String, List<Path>> index = new HashMap<>();

    public Index() {
//building index
        buildIndex();
    }
//single responsibility principle

    public void buildIndex() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("Drive " + root);
            Path rootPath = root.toPath();
            try {
                Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        //key
                        String fileName = file.getFileName().toString().toLowerCase();
                        //functionalInterface + Lambda
                        index.computeIfAbsent(fileName, K -> new ArrayList<>()).add(file);

                        //  String[] tokens = fileName.split("[\\.\\-_\\s]+");

                       /*for (String token : tokens) {
                            index.computeIfAbsent(token, K -> new ArrayList<>()).add(file);

                        }*/
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
            }
        }
    }

    @Override
    public List<Path> search(String fileName) {
        List<Path> results = new ArrayList<>();

        for (String name : index.keySet()) {
            if (name.contains(fileName.toLowerCase())) {
                results.addAll(index.get(name));
            }
        }

        return results;

}}
