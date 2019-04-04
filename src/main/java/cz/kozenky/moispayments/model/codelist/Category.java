package cz.kozenky.moispayments.model.codelist;

import java.math.BigDecimal;

public enum Category {
    ELECTRONICS(new BigDecimal(1.5), "Elektronika"),
    UNKNOWN(new BigDecimal(0), "Nezarazeno");

    private String name;
    private BigDecimal id;

    Category(BigDecimal id, String name) {
        this.id = id;
        this.name = name;
    }


    public static Category getById(BigDecimal categoryId) {
        if (categoryId != null) {

            for (Category category : values()) {
                if (category.id.compareTo(categoryId) == 0) {
                    return category;
                }
            }
        }
        return UNKNOWN;
    }

    public static Category getByName(String name) {
        if (name != null) {
            for (Category category : values()) {
                if (category.name.equals(name)) {
                    return category;
                }
            }
        }
        return UNKNOWN;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getId() {
        return id;
    }


    //*************************************
    //tohle se mi nelibi, kdo prijde s lepsim resenim napiste
    // (enum ma problemy s BigDecimalem jako klicem, to same switch)

    //abysme si kategorie pridavali samy byla by potreba DB, protoze APIna bere pouze IDcko
    //tak jsme se s lukasem dohodli ze to tam budeme mit "NATVRDO"
    //*************************************
    private String getNameOfCategory(BigDecimal categoryId){

        if (categoryId.compareTo(new BigDecimal(1.5)) == 0 ){
            return "Elektronika";
        }
        if (categoryId.compareTo(new BigDecimal(0)) == 0 ){
            return "Nezarazeno";
        }
        return "Nezarazeno(null)";
    };

    private BigDecimal getIdOfCategory(String name){

        switch (name){
            case "Elektronika":
                return new BigDecimal(1.5);
            case "Nezarazeno":
                return new BigDecimal(0);
        }
        return null;
    };
}
