package com.EbookApi.apiEBook.model;

public enum Gender {
    FICITION("Ficcion"),
    CHILDREN("children"),
    Lovestories("Love stories"),
    UNKNOWN("unknown");

    private String value;
    Gender(String value){
        this.value=value;
    }

    public static Gender getGender(String gender){
        for(Gender genero: Gender.values()){
            if(genero.value.equalsIgnoreCase(gender)){
                return genero;
            }
        }
        return UNKNOWN;
    }
}
