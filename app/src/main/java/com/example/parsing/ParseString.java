package com.example.parsing;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ParseString {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void parse(ArrayList<ParsingValute> list, String str) {

        String s = new String();

        final DownloadData downloadData = new DownloadData();
        {
            try {
                //http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002
                s = downloadData.execute("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + str).get();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            ValuteXMLParser parser = new ValuteXMLParser();
            if (s != null && parser.parse(s)) {
                for (Valute prod : parser.getValutes()) {
                    list.add(new ParsingValute(prod.getCharCode(), prod.getNominal(), prod.getName(), prod.getValue()));
                }
            }
        }
    }
}

