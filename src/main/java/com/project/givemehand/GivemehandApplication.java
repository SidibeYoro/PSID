package com.project.givemehand;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Arrays;

@SpringBootApplication
public class GivemehandApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GivemehandApplication.class, args);
		System.out.println("Print Arraylist using for loop");








	}

}
