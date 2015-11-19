package com.connection.test;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Wong Kam Shing on 2015/11/19.
 */
public class Test {
    public static void main(String[] args){
        File dir = new File("sample_data\\sample_data");

        File[] fileList = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        System.out.println("size of fileList is = " + fileList.length);
        for(File file : fileList){
            try {
                BufferedReader inFile = new BufferedReader(new FileReader(file));
                String line = inFile.readLine();
                while(line != null){
                    System.out.println(line);
                    line = inFile.readLine();
                }

            } catch (FileNotFoundException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        for(File file : fileList){
            System.out.println(file.toString());
        }
        //System.out.println(fileList[0].toString().equals("sample_data\\category.txt"));

        String folderName = "sample_data\\sample_data";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(folderName+"\\category.txt")));
            String l = br.readLine();

            while(l != null){
                System.out.println(l);
                l = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
