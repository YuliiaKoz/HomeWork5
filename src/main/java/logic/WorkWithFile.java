package logic;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;


public class WorkWithFile {


    public void writeData(File fileForData, StringBuilder data) {
        try (OutputStream os = new FileOutputStream(fileForData)) {
            os.write(data.toString().getBytes());
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilaAPath(File file) {
        String getAPath = "This is Absolute File Path: " + file.getAbsolutePath();
        return getAPath;
    }


    public String getFileSize(File file) {
        String getFileSize = "The Size of the file is: " + file.length() / 1024 + " kb";
        return getFileSize;
    }

    public StringBuilder getFileAttribute(File file) throws IOException {

        StringBuilder attributes = new StringBuilder();
        BasicFileAttributeView view = Files.getFileAttributeView(file.toPath(), BasicFileAttributeView.class);

        attributes.append(System.getProperty("line.separator"));
        BasicFileAttributes attribute = view.readAttributes();
        attributes.append("This is all Attributes about the file: " + file.getName());
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-creation Time of the file: " + attribute.creationTime() + ";");
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-last Accessed Time of the file:  " + attribute.lastAccessTime() + ";");
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-last Modified Time for the file:  " + attribute.lastModifiedTime() + ";");
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-can you read this file:  " + file.canRead() + ";");
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-can you write this file:  " + file.canWrite() + ";");
        attributes.append(System.getProperty("line.separator"));
        attributes.append("-this file is hidden:  " + file.isHidden() + ";");

        return attributes;
    }


    public String getAmountOfWords(File file) throws IOException {

        FileReader fR = new FileReader(file);
        BufferedReader bR = new BufferedReader(fR);

        String line = bR.readLine();
        int count = 0;
        while (line != null) {
            String[] parts = line.split(" ");
            for (String w : parts) {
                count++;
            }
            line = bR.readLine();
        }

        fR.close();
        bR.close();
        String howManyeWords = "This text has " + count + " of words;";
        return howManyeWords;
    }

    public String getAmountOfSentences(File file) throws IOException {

        FileReader fR = new FileReader(file);
        BufferedReader bR = new BufferedReader(fR);
        String line = bR.readLine();
        int count = 0;
        while (line != null) {
            String[] parts = line.split(".");
            for (String w : parts) {
                count++;
            }
            line = bR.readLine();
        }
        fR.close();
        bR.close();
        String howManyeSentences = "This text has " + count + " of sentences;";
        return howManyeSentences;
    }

    public String getAmountOfGaps(String filePath){

        int counter = 0;
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath), "UTF-8");){
            int buff;
            while((buff =reader.read())>-1){
                if(Character.isSpaceChar((char)buff)){
                    counter ++;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       String howManyeCapitalLetters = "This text has "+counter+"  of gaps;";
       return howManyeCapitalLetters;
    }

    public String getAmountOfSeparators(String file) throws IOException {

        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");

        int buff;
        final String[] punctuation = new String[]{".", "?", "!", "...", ",", ";", ":", "-", "(", ")", "\"", "\""};
        int counter = 0;
        while ((buff = reader.read()) > -1) {
            for (String j :punctuation) {
              String symbols = Character.toString((char) buff);
              if (j.equals(symbols)) {
                  counter++;
              }
            }
        }

        reader.close();
        String howManyPunctuation = "This text has " + counter + " of different punctuation;";
        return howManyPunctuation;

    }

public String getVowels(String filePath, String identify) throws IOException {

    int counter = 0;

    FileReader fR = new FileReader(filePath);
    BufferedReader bR = new BufferedReader(fR);

    String line = bR.readLine();
    while (line != null) {
        String[] parts = line.split(" ");
        line = bR.readLine();

        for (String w : parts) {

                switch (identify) {

                    case "vowels":
                        final String[] vowels = new String[]{"a", "e", "i", "o", "y", "u","а", "э", "ы", "у", "о", "я", "е", "ё", "ю", "и", "A", "E", "I", "O", "Y", "U","А", "Э", "Ы", "У", "О", "Я", "Е", "Ё", "Ю", "И"};
                             for (String v : vowels) {
                                if (w.startsWith(v) && w.length() > 1) {
                                    counter++;
                                }
                            }
                            break;
                    case "consonant":
                        final String[] consonant = new String[]{"B", "C", "D", "F", "G", "J", "K", "L", "M", "N", "P", "Q", "S", "T", "V", "X", "Z","b", "c", "d", "f", "g", "j", "k", "l", "m", "n", "p", "q", "s", "t", "v", "x", "z","Б", "В", "Г", "Д", "Ж", "З", "Й", "К", "Л", "М", "Н", "П", "Р", "С", "Т", "Ф", "Х", "Ц", "Ч", "Ш", "Щ","б", "в", "г", "д", "ж", "з", "й", "к", "л", "м", "н", "п", "р", "с", "т", "ф", "х", "ц", "ч", "ш", "щ"};
                            for (String v : consonant) {
                                if (w.startsWith(v) && w.length() > 1) {
                                    counter++;
                                }
                            }
                        break;
                }


        }
    }
    fR.close();
    bR.close();

        String howManyW = "This text has " +counter+ " words beginning from "+identify+".";
        return howManyW;
    }


    public String ratioCirAndLatinS() throws IOException {
        BufferedReader bR = new BufferedReader(new FileReader("C:\\HomeWork5\\src\\main\\resources\\justCyrillicText.txt"));

        int countCir = 0;
        String data;
        while((data = bR.readLine()) != null) {
            countCir += data.replaceAll("\\s+", "").length();
        }

        bR.close();

        BufferedReader bR2 = new BufferedReader(new FileReader("C:\\HomeWork5\\src\\main\\resources\\justLatinText.txt"));

        int countLatin = 0;
        String dataL;
        while((dataL = bR2.readLine()) != null) {
            countLatin += dataL.replaceAll("\\s+", "").length();
        }

        bR2.close();


        Ratio theResult = new Ratio(countCir,countLatin);


        String theResultString = "The ratio the Latin to Cyrillic is " +theResult.getResultOfRatio()+"%;";
        return theResultString;
    }




    public String ratioCirAndLatinW() throws IOException {
        BufferedReader bRCir = new BufferedReader(new FileReader("C:\\HomeWork5\\src\\main\\resources\\justCyrillicText.txt"));

        String lineCir = bRCir.readLine();
        int countCir = 0;
        while (lineCir != null) {
            String[] parts = lineCir.split(" ");

            for (String w : parts) {
                if(w.length()>1) {
                    countCir++;
                }
            }
            lineCir = bRCir.readLine();
        }

        bRCir.close();


        BufferedReader bRLat = new BufferedReader(new FileReader("C:\\HomeWork5\\src\\main\\resources\\justLatinText.txt"));

        String lineLat = bRLat.readLine();
        int countLat = 0;
        while (lineLat != null) {
            String[] parts = lineLat.split(" ");

            for (String w : parts) {
                if(w.length()>1) {
                    countLat++;
                }
            }
            lineLat = bRLat.readLine();
        }

        bRLat.close();


        Ratio theRatioResult = new Ratio(countCir, countLat);

        String theResultString = "The ratio the Latin to Cyrillic words is "+theRatioResult.getResultOfRatio()+"%";
        return theResultString;
    }

    public String ratioSymAndPunct(File oFileName) {
        String theResultString = "";

        try(InputStream is = new FileInputStream(oFileName)){

            int buff;
            int counterPunct = 0;
            int counterSymbSpace = 0;
            while((buff = is.read())>-1){
                String currentS =Character.toString((char)buff);
                if(currentS.equals(",") || currentS.equals(".") || currentS.equals("?") || currentS.equals("!")){
                    counterPunct++;
                }

                String currentSymbSpace =Character.toString((char)buff);
                if(currentSymbSpace.equals("[") || currentSymbSpace.equals("–") || currentSymbSpace.equals(" ") || currentSymbSpace.equals("]")){
                    counterSymbSpace++;
                }
            }

            Ratio ratioPunctASymb = new Ratio (counterSymbSpace,counterPunct );
            ratioPunctASymb.getResultOfRatio();

            is.close();

            theResultString = "The ratio the Punctuation symbols to Another symbols is "+ratioPunctASymb.getResultOfRatio()+"%";
            return theResultString;

        } catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return theResultString;
    }


    public void writeLatinWords(String file) throws IOException {
        StringBuilder sB = new StringBuilder();
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");

        int buff;
        while ((buff = reader.read()) > -1) {

            if (String.valueOf((char) buff).matches("[A-Za-z]") || (char) buff == ' ' || (char) buff == '\n') {
                sB.append((char) buff);
            }
        }

        File justLatin = new File("C:\\HomeWork5\\src\\main\\resources\\justLatinText.txt");

        if (!justLatin.exists()) {
            justLatin.createNewFile();
        }

        WorkWithFile writeDataTo = new WorkWithFile();
        writeDataTo.writeData(justLatin, sB);

    }

    public void writeCyrillicWords(String file) throws IOException {
        StringBuilder sB = new StringBuilder();
        Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");

        int buff;
        while ((buff = reader.read()) > -1) {

            if (String.valueOf((char) buff).matches(".*\\p{InCyrillic}.*")|| (char) buff == ' ' || (char) buff == '\n') {
                sB.append((char) buff);
            }
        }

        File justCyrillic = new File("C:\\HomeWork5\\src\\main\\resources\\justCyrillicText.txt");

        if (!justCyrillic.exists()) {
            justCyrillic.createNewFile();
        }
        WorkWithFile writeDataTo = new WorkWithFile();
        writeDataTo.writeData(justCyrillic, sB);

        reader.close();


    }


    public void createNewFile(File file){
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {

        final String filePath = "C:\\HomeWork5\\src\\main\\resources\\Shantaram.txt";
        WorkWithFile wF = new WorkWithFile();

        File oFileName = new File(filePath);
        File fileForData = new File("C:\\HomeWork5\\src\\main\\resources\\fileData.txt");

        try {
            wF.writeLatinWords(filePath);
            wF.writeCyrillicWords(filePath);

            StringBuilder allDataAboutF = new StringBuilder();

            if(oFileName.isFile()){
                wF.createNewFile(fileForData);
                allDataAboutF.append(wF.getFilaAPath(oFileName));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getFileSize(oFileName));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getFileAttribute(oFileName));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getAmountOfWords(oFileName));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getAmountOfSentences(oFileName));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getAmountOfGaps(filePath));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getAmountOfSeparators(filePath));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getVowels(filePath, "vowels"));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.getVowels(filePath, "consonant"));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.ratioCirAndLatinW());
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.ratioCirAndLatinS());
                allDataAboutF.append(System.getProperty("line.separator"));
                allDataAboutF.append(wF.ratioSymAndPunct(oFileName));


                wF.writeData(fileForData, allDataAboutF);

            } else {
                System.out.println("Please check the path to the file.");
            }


        } catch (FileNotFoundException fnf){
            fnf.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }



}
