package com.EbookApi.apiEBook.menu;

public enum ColoursConsole {
    BLACK("\u001B[30m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    CYAN("\u001B[36m"),
    PURPLE("\u001B[35m"),
    GREEN("\u001B[32m"),
    WHITE("\u001B[37m");

    private String ansiValue;

    ColoursConsole(String ansiValue){
        this.ansiValue=ansiValue;
    }

    public static String paintFont(String fontColour,String text){
        for(ColoursConsole c:ColoursConsole.values()){
            if(c.name().equalsIgnoreCase(fontColour)){
                return c.getValue()+text+"\u001B[0m";
            }

        }
        return text;
    }

    public static String paintBackground(String colourBackground,String colourFont){
            return null;
    }
    private String getValue(){
        return this.ansiValue;
    }

}
