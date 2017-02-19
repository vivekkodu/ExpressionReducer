package com.cleartax.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by VIVEK VERMA on 2/17/2017.
 */
public class FileReader implements IReader {

    public String read(String fileName) throws FileNotFoundException {
        return new Scanner(new File(fileName)).useDelimiter("\\Z").next();
    }
}
