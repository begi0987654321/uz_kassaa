package com.example.uz_kassa.entity;

public class EmailMessege {

    private String to;
    private String subject;
    private String massege;

    public EmailMessege(String to, String subject, String massege) {
        this.to = to;
        this.subject = subject;
        this.massege = massege;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMassege() {
        return massege;
    }

    public void setMassege(String massege) {
        this.massege = massege;
    }
}
