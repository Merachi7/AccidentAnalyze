package org.merachi7.accidentanalyze.util;

import com.opencsv.CSVReader;

import org.merachi7.accidentanalyze.BuildConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public List<String[]> readAllCsvData(String file_path){
        File file = new File(file_path);

        try (FileReader fr = new FileReader(file);
             CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream (file), "EUC-KR"))) {
            return reader.readAll();
        } catch (IOException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }

            return new ArrayList<>();
        }
    }

    public List<String[]> readCsvData(String file_path) {
        File file = new File(file_path);
        List<String[]> dataArray = new ArrayList<>();

        try (FileReader fr = new FileReader(file);
             CSVReader reader = new CSVReader(fr)) {
            for (String[] data : reader) {
                dataArray.add(data);
            }

            return dataArray;
        } catch (IOException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }

            return new ArrayList<>();
        }
    }
}
