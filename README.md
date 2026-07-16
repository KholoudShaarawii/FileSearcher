# File Searcher

File Searcher is a Java console application that searches for files across the available file-system roots using three different search strategies.

The project demonstrates how the same file-searching operation can be implemented through direct disk traversal, in-memory storage, and filename indexing.

## Features

- Searches across all available system drives.
- Performs case-insensitive partial filename matching.
- Displays matching file paths and the total number of matches.
- Measures and displays the execution time of the selected search operation.
- Applies the Strategy design pattern to switch between disk-based, in-memory, and indexed search methods.

## Search Strategies

### 1. Hard Disk Search

The hard disk strategy scans the available drives during each search operation.

It uses `Files.walkFileTree` to visit files and checks whether each filename contains the entered search text.

### 2. RAM Search

The RAM strategy scans the available drives during initialization and stores the discovered file paths in an in-memory list.

It then performs case-insensitive partial filename searches by filtering the stored paths using Java Streams.

### 3. Index Search

The Index strategy scans the available system drives during initialization and builds an in-memory `HashMap`.

Each lowercase filename is stored as a key, while a list of its corresponding file paths is stored as the value.

Partial filename searches are performed by checking which indexed filenames contain the entered search text.

## How It Works

1. The application displays the available search methods.
2. The user selects Hard Disk, RAM, or Index search.
3. The user enters a filename or part of a filename.
4. The selected strategy performs a case-insensitive search.
5. The application displays:
   - The number of matching files.
   - The matching file paths.
   - The search execution time in milliseconds.

## Class Responsibilities

### `Main`

Handles console input, selects the requested search strategy, executes the search, and displays the results.

### `SearchStrategy`

Defines the common search operation implemented by all search strategies.

```java
List<Path> search(String fileName);
```

### `HardDisk`

Traverses the file system and searches directly through the files during each search request.

### `Ram`

Loads file paths into an in-memory list and searches through the stored paths.

### `Index`

Builds an in-memory filename index using a `HashMap` and searches through the indexed filenames.

## Technologies

- Java 11
- Maven
- Java NIO
- Java Collections
- Java Streams
- Strategy Design Pattern


## The application displays:

```text
Choose search method:
1 - Hard Disk
2 - RAM
3 - Index
```

Enter the number of the required search method, followed by the filename or part of the filename.

## Example Workflow

```text
Choose search method:
1 - Hard Disk
2 - RAM
3 - Index
2

Enter file name:
report

Matches: 2
C:\Documents\annual-report.pdf
D:\Work\report-notes.txt
Time: 15 ms
```

The returned paths and execution time depend on the files and drives available on the current system.
