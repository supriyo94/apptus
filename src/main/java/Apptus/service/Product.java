package Apptus.service;


import java.util.ArrayList;
import java.util.List;

public class Product {
    String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1;
    String  BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2;

    String  BASKETSNAPSHOT__X_QUANTITY;

    /*public Product(String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1, String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2, String BASKETSNAPSHOT__X_QUANTITY) {
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1 = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1;
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2 = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2;
        this.BASKETSNAPSHOT__X_QUANTITY = BASKETSNAPSHOT__X_QUANTITY;
    }*/

    public String getBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1() {
        return BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1;
    }

    public void setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1(String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1) {
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1 = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1;
    }

    public String getBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2() {
        return BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2;
    }

    public void setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2(String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2) {
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2 = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2;
    }

    public String getBASKETSNAPSHOT__X_QUANTITY() {
        return BASKETSNAPSHOT__X_QUANTITY;
    }

    public void setBASKETSNAPSHOT__X_QUANTITY(String BASKETSNAPSHOT__X_QUANTITY) {
        this.BASKETSNAPSHOT__X_QUANTITY = BASKETSNAPSHOT__X_QUANTITY;
    }

    public List<Product> getNewProductList(){

        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-4");
        product1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-4");
        product1.setBASKETSNAPSHOT__X_QUANTITY("Quantity-4");

        Product product2 = new Product();
        product2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-5");
        product2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-5");
        product2.setBASKETSNAPSHOT__X_QUANTITY("Quantity-5");

        Product product3 = new Product();
        product3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME1("P-6");
        product3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME2("P-6");
        product3.setBASKETSNAPSHOT__X_QUANTITY("Quantity-6");

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;

    }
    public String getProductInfo(){

        return "method call check";

    }

    /*
    List<String> ProductModuleNames;
    List<String>  quantityList;
    List<String>  volumeTier1List1;
    List<String>  volumeTier1List2;
    List<String>  volumeTier1List3;


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
    }*/



}
