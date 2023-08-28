package Apptus.service;


import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class ComplexData {

    List<String> ProductModuleNames;
    List<String>  quantityList;
    List<String>  volumeTier1List1;
    List<String>  volumeTier1List2;
    List<String>  volumeTier1List3;

    ZonedDateTime createdDate;


    public List<String> getProductModuleNames() {
        return ProductModuleNames;
    }

    public void setProductModuleNames(List<String> productModuleNames) {
        ProductModuleNames = productModuleNames;
    }

    public List<String> getQuantityList() {
        return quantityList;
    }

    public void setQuantityList(List<String> quantityList) {
        this.quantityList = quantityList;
    }

    public List<String> getVolumeTier1List1() {
        return volumeTier1List1;
    }

    public void setVolumeTier1List1(List<String> volumeTier1List1) {
        this.volumeTier1List1 = volumeTier1List1;
    }

    public List<String> getVolumeTier1List2() {
        return volumeTier1List2;
    }

    public void setVolumeTier1List2(List<String> volumeTier1List2) {
        this.volumeTier1List2 = volumeTier1List2;
    }

    public List<String> getVolumeTier1List3() {
        return volumeTier1List3;
    }

    public void setVolumeTier1List3(List<String> volumeTier1List3) {
        this.volumeTier1List3 = volumeTier1List3;
    }



}
