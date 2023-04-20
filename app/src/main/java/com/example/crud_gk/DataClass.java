package com.example.crud_gk;

public class DataClass {
    private String dataID;
    private String dataName;
    private String dataBrand;
    private String dataPrice;
    private String dataDesc;
    private String dataImage;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getDataID() {
        return dataID;
    }

    public String getDataName() {
        return dataName;
    }

    public String getDataBrand() {
        return dataBrand;
    }

    public String getDataPrice() {
        return dataPrice;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataID, String dataName, String dataBrand, String dataPrice, String dataDesc, String dataImage) {
        this.dataID = dataID;
        this.dataName = dataName;
        this.dataBrand = dataBrand;
        this.dataPrice = dataPrice;
        this.dataDesc = dataDesc;
        this.dataImage = dataImage;
    }
    public DataClass(){

    }
}
