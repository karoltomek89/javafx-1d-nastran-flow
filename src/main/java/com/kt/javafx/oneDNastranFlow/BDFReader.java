package com.kt.javafx.oneDNastranFlow;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class BDFReader {

    private String bdfName;
    private List<String> plotelList = new ArrayList<>();

    List<String> readBDF(String pathToDBF) {

        try {
            bdfName = pathToDBF;

            plotelList = Files.lines(Paths.get(bdfName), StandardCharsets.ISO_8859_1)
                    .filter(p -> p.startsWith("PLOTEL  "))
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return plotelList.parallelStream()
                .map(p -> p.trim()
                        .substring(9, 32))

                .map(s -> s.trim().replaceAll("[ ]{1,}", ","))
                .sorted()
                .collect(Collectors.toList());

    }

    void clear (){
        bdfName = null;
        plotelList = new ArrayList<>();
    }
}
