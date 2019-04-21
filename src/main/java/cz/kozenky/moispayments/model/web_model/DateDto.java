package cz.kozenky.moispayments.model.web_model;

import java.util.Date;

public class DateDto {
    private String fromS;
    private String toS;
    private Date fromD;
    private Date toD;

    public String getFromS() {
        return fromS;
    }

    public void setFromS(String fromS) {
        this.fromS = fromS;
    }

    public String getToS() {
        return toS;
    }

    public void setToS(String toS) {
        this.toS = toS;
    }

    public Date getFromD() {
        return fromD;
    }

    public void setFromD(Date fromD) {
        this.fromD = fromD;
    }

    public Date getToD() {
        return toD;
    }

    public void setToD(Date toD) {
        this.toD = toD;
    }
}
