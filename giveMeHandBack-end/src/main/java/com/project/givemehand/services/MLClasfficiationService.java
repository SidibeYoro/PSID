package com.project.givemehand.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


@Service
public class MLClasfficiationService {

    public static ArrayList<String> retrieveData() throws IOException {

        ArrayList<String> listBadWords = new ArrayList<>();
        String excelFilePath = "C:\\Users\\kamis\\IdeaProjects\\PSID\\giveMeHandBack-end\\src\\main\\java\\com\\project\\givemehand\\utils\\data.xlsx";


        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case STRING:
                        //	System.out.print(cell.getStringCellValue());
                        listBadWords.add(cell.getStringCellValue());
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                //System.out.print(" - ");
            }

            //System.out.println();
        }

        workbook.close();
        inputStream.close();
        return listBadWords;


    }

    public boolean detectNegativeClassForText(String text) throws IOException {

        // retrieve bad words in file

        ArrayList<String> lists = retrieveData();


        /*
         * Create a new classifier instance. The context features are
         * Strings and the context will be classified with a String according
         * to the featureset of the context.
         */
        final de.daslaboratorium.machinelearning.classifier.Classifier<String, String> bayes =
                new de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier<String, String>();
        /*
         * The classifier can learn from classifications that are handed over
         * to the learn methods. Imagin a tokenized text as follows. The tokens
         * are the text's features. The category of the text will either be
         * negative, we dont nedd the positive class.
         */


        bayes.learn("negative", lists);

        final String[] positiveText = "".split("\\s");
        bayes.learn("positive", Arrays.asList(positiveText));
        /*
         * Now that the classifier has "learned" two classifications, it will
         * be able to classify similar sentences. The classify method returns
         * a Classification Object, that contains the given featureset,
         * classification probability and resulting category.
         */

        final String[] unknownText2 = text.split("\\s");
        for(int i=0;i< unknownText2.length;i++){
            System.out.println("Text to classify" + unknownText2[i] );

        }


        //	System.out.println( // will output "negative"
        System.out.println( //
                bayes.classify(Arrays.asList(unknownText2)).getCategory());


        /*
         * The BayesClassifier extends the abstract Classifier and provides
         * detailed classification results that can be retrieved by calling
         * the classifyDetailed Method.
         *
         * The classification with the highest probability is the resulting
         * classification. The returned List will look like this.
         * [
         *   Classification [
         *     category=negative,
         *     probability=0.0078125,
         *     featureset=[today, is, a, sunny, day]
         *   ],
         *   Classification [
         *     category=positive,
         *     probability=0.0234375,
         *     featureset=[today, is, a, sunny, day]
         *   ]
         * ]
         */


        System.out.println(((de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownText2)));



        /*
         * Please note, that this particular classifier implementation will
         * "forget" learned classifications after a few learning sessions. The
         * number of learning sessions it will record can be set as follows:
         */
        bayes.setMemoryCapacity(500); // remember the last 500 learned classifications


        // For retaining positive class

        if ( bayes.classify(Arrays.asList(unknownText2)).getCategory() !="negative"){
            bayes.learn("positive", Arrays.asList(unknownText2));

            return false;

        }
        else {
            bayes.learn("negative", Arrays.asList(unknownText2));

            return true;
        }






    }

}
