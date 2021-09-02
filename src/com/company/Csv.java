package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Csv implements IConst {

    private static final String SPLIT_STR = ";";
    private static final int STRINGS_PER_ITEM_QUESTION = 5;

    private Csv() {
    }


    public static List<Question> readQuestions(String theme) {
        List<Question> questions = new ArrayList<>();
        String path = new java.io.File(".").getAbsolutePath();
        String filename = path + LOCAL_PATCH + FILENAME;
        String line = "";

        try
        {
            FileReader fr= new FileReader(filename);
            Scanner scan = new Scanner(fr);

            while (scan.hasNext()) {
                line = scan.nextLine();
                String[] arr = line.split(SPLIT_STR);
                if(arr.length == STRINGS_PER_ITEM_QUESTION) {
                    questions.add(new Question(arr[0], arr[1], arr[2], arr[3], arr[4]));
                }
            }
            fr.close();
        }
        catch(IOException ex){
            throw new CsvException("csv file nod found: " + filename);
        }

        return questions;
    }

}
