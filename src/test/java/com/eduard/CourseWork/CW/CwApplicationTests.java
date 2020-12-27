package com.eduard.CourseWork.CW;

import com.eduard.CourseWork.CW.Services.documentsServices.CreateDocumentService;

public class CwApplicationTests{
    public String string1 = "C:/Users/edik_/holderDocuments/ffff.docx"; //40
    public String string2 = "C:/Users/edik_/holderDocuments/1.xlsx";
    public String string3 = "C:/Users/edik_/holderDocuments/АВМиС_Сидоров_Э.С._ИКБО1718.docx";

    public static String str;



    public final String pathHolder = "C:/Users/edik_/holderDocuments/";


    public static void main(String[] args) {
        System.out.println(str);
        str = "fds";
        System.out.println(str);

    }

    /*public void doString(){
        char[] arrChar = string1.toCharArray();
        char[] arrCharNew = new char[arrChar.length - pathHolder.length()];
        char[] endArr = new char[arrCharNew.length];

        int j = 0;
        int k = 0;

        for (int i = arrChar.length - 1; arrChar[i] != '/'; i--){
            arrCharNew[j] = arrChar[i];
            j++;
        }

        for (int i = arrCharNew.length - 1; i > -1; i--){
            endArr[k] = arrCharNew[i];
            k++;
        }

        String str1 = new String(endArr);

        System.out.println("endArr= " + str1);
    }*/

    public String outputDocumentName(String pathDocument, String pathHolder){
        char[] arrChar = new char[pathDocument.toCharArray().length - pathHolder.length()];

        int j = 0;

        for (int i = pathDocument.toCharArray().length - 1; pathDocument.toCharArray()[i] != '/'; i--){
            arrChar[j] = pathDocument.toCharArray()[i];
            j++;
        }

        return new StringBuffer(String.valueOf(arrChar)).reverse().toString();
    }

}