package com.example.parsing;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;
public class ValuteXMLParser {

        private ArrayList<Valute> ValCurs;

        public ValuteXMLParser(){
            ValCurs = new ArrayList<>();
        }

        public ArrayList<Valute> getValutes(){
            return  ValCurs;
        }

        public boolean parse(String xmlData){
            boolean status = true;
            Valute currentValute = null;
            boolean inEntry = false;
            String textValue = "";

            try{
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(xmlData));
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){

                    String tagName = xpp.getName();
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if("Valute".equalsIgnoreCase(tagName)){
                                inEntry = true;
                                currentValute = new Valute();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            textValue = xpp.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(inEntry){
                                if("Valute".equalsIgnoreCase(tagName)){
                                    ValCurs.add(currentValute);
                                    inEntry = false;
                                } else if("NumCode".equalsIgnoreCase(tagName)){
                                    currentValute.setNumCode(textValue);
                                } else if("CharCode".equalsIgnoreCase(tagName)) {
                                    currentValute.setCharCode(textValue);
                                }  else if("Nominal".equalsIgnoreCase(tagName)){
                                    currentValute.setNominal(textValue);
                                }  else if("Name".equalsIgnoreCase(tagName)){
                                    currentValute.setName(textValue);
                                }  else if("Value".equalsIgnoreCase(tagName)){
                                    currentValute.setValue(textValue);
                                }
                            }
                            break;
                        default:
                    }
                    eventType = xpp.next();
                }
            }
            catch (Exception e){
                status = false;
                e.printStackTrace();
            }
            return  status;
        }
    }

