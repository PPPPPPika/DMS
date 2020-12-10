package com.eduard.CourseWork.CW.Services.documentsServices;

import com.eduard.CourseWork.CW.Repositorys.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("downloadDocumentService")
public class DownloadDocumentService {
    private final String pathHolder = "C:/Users/edik_/holderDocuments/";

    public String outputDocumentName(String pathDocument){
        char[] arrChar = new char[pathDocument.toCharArray().length - pathHolder.length()];

        int j = 0;

        for (int i = pathDocument.toCharArray().length - 1; pathDocument.toCharArray()[i] != '/'; i--){
            arrChar[j] = pathDocument.toCharArray()[i];
            j++;
        }

        return new StringBuffer(String.valueOf(arrChar)).reverse().toString();
    }

}
