package com.EbookApi.apiEBook.model;

public enum Gender {
    FICITION("Fiction"),
    CHILDREN("Children's stories"),
    Lovestories("Love stories"),
    FICTIONFANTASY("Fantasy fiction"),
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

    public static String getValue(Gender gender){
        return gender.value;
    }

}
