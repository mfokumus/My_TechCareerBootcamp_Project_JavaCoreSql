package com.mfokumus.javase.files;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class FilePathData { // File yapısına veriler eklemek istiyorsak FilePathData ekleriz
    //Variables
    private String id;
    private String pathFileName;
    private String pathDirectoryName;
    private String url;
    private File file;
    private Date systemCreatedDate;

    //Parametresiz Constructor
    public FilePathData() {
        this.id= UUID.randomUUID().toString();
        this.systemCreatedDate = new Date(System.currentTimeMillis());
        pathFileName = "\\log.txt";
        // C:\io\techcareer\full_3
        pathDirectoryName = FilePathUrl.MY_FILE_PATH_URL;
        // "C:\\io\\techcareer\\full_3\\log.txt"
        url = pathDirectoryName.concat(pathFileName);
        file = new File(url);
        try{
            // Böyle bir dosya var mı? yoksa true yap if e gir, varsa false yap else gir
            if(file.createNewFile()){
                System.out.println(pathFileName+ "Boyle bir dosya yoktur. Olusturuldu.");
                System.out.println("Permission: Okunabilinir mi? "+file.canRead()+" yazılabilinir mi? "+file.canWrite()+" Calistirilabilinir mi? "+file.canExecute());
                //toString
                System.out.println("ID: "+this.id+" URL: "+this.url+" Hash Code: "+file.hashCode());

                secretFileWriter(); //Writer
                secretFileReader(); //Reader
                fileIsDelete();//Delete

            }else{
                String fileName = pathFileName+ "Boyle bir dosya var. Tekrar olusturulmadi.";
                System.out.println(fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }// end constructor

    //toString
    @Override
    public String toString() {
        return "FilePathData{" +
                "id='" + id + '\'' +
                ", pathFileName='" + pathFileName + '\'' +
                ", pathDirectoryName='" + pathDirectoryName + '\'' +
                ", url='" + url + '\'' +
                ", file=" + file +
                ", systemCreatedDate=" + systemCreatedDate +
                '}';
    }

    // file Date Locale
    private String localeDateTime(){
        Locale locale= new Locale("tr","TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy",locale);
        Date date = new Date();
        String changeDate = simpleDateFormat.format(date);
        return changeDate;
    }

    //File Writer
    private void secretFileWriter(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.url, true))){
            String data = "[ "+ localeDateTime()+" ]"+ " secret key: ssh-keygen -t rsa -b 4096 -C 'metefurkanokumus@gmail.com'" ;
            bufferedWriter.write(data);
            bufferedWriter.flush();// hoca güzel eklesin dedi o yüzen bi de flush yapsın
        }catch (Exception e){
            e.printStackTrace();
        }
    } // end Writer

    //File Reader
    private void secretFileReader(){
        String rows; // okunan satır olacak
        StringBuilder stringBuilder = new StringBuilder(); // stringleri toplam olarak gösteren yapı
        String buildToString;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(this.url))){
            while( (rows = bufferedReader.readLine()) != null){
                stringBuilder.append(rows); // satırları toplaya toplaya stringBuilder'a ekliyor burda
            }
            buildToString = stringBuilder.toString();
            System.out.println("OKUNDU: "+buildToString);
        }catch (Exception e){
            e.printStackTrace();
        }
    } // end reader

    // File Delete
    private void fileIsDelete(){
        Scanner klavye = new Scanner(System.in);
        char chooise;
        System.out.println(pathFileName+" Dosyayi silmek ister misiniz ? E / H");
        chooise = klavye.nextLine().charAt(0);
        if(chooise == 'E' || chooise == 'e'){
            file.exists();
        }else{
            System.out.println(pathFileName + " Silinmedi");
        }
    }// end delete

}// end class FilePathData
