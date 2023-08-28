package Apptus.util;


import java.util.ArrayList;
import java.util.List;

public class DPRQuoteTC {

    String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    String BASKETSNAPSHOT__X_DISCOUNT_TEXT;
    int BASKETSNAPSHOT__X_QUANTITY;
    int  BASKETSNAPSHOT__X_VolumeTier1;
    int  BASKETSNAPSHOT__X_VolumeTier3;
    int  BASKETSNAPSHOT__X_VolumeTier2;
    int BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES;

    public String getBASKETSNAPSHOT__X_DISCOUNT_TEXT() {
        return BASKETSNAPSHOT__X_DISCOUNT_TEXT;
    }

    public void setBASKETSNAPSHOT__X_DISCOUNT_TEXT(String BASKETSNAPSHOT__X_DISCOUNT_TEXT) {
        this.BASKETSNAPSHOT__X_DISCOUNT_TEXT = BASKETSNAPSHOT__X_DISCOUNT_TEXT;
    }
    public int getBASKETSNAPSHOT__X_VolumeTier6() {
        return BASKETSNAPSHOT__X_VolumeTier6;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier6(int BASKETSNAPSHOT__X_VolumeTier6) {
        this.BASKETSNAPSHOT__X_VolumeTier6 = BASKETSNAPSHOT__X_VolumeTier6;
    }

    int BASKETSNAPSHOT__X_VolumeTier6;

    public String getBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME() {
        return BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    }

    public void setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME(String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME) {
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    }

    public int getBASKETSNAPSHOT__X_QUANTITY() {
        return BASKETSNAPSHOT__X_QUANTITY;
    }

    public void setBASKETSNAPSHOT__X_QUANTITY(int BASKETSNAPSHOT__X_QUANTITY) {
        this.BASKETSNAPSHOT__X_QUANTITY = BASKETSNAPSHOT__X_QUANTITY;
    }

    public int getBASKETSNAPSHOT__X_VolumeTier1() {
        return BASKETSNAPSHOT__X_VolumeTier1;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier1(int BASKETSNAPSHOT__X_VolumeTier1) {
        this.BASKETSNAPSHOT__X_VolumeTier1 = BASKETSNAPSHOT__X_VolumeTier1;
    }

    public int getBASKETSNAPSHOT__X_VolumeTier3() {
        return BASKETSNAPSHOT__X_VolumeTier3;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier3(int BASKETSNAPSHOT__X_VolumeTier3) {
        this.BASKETSNAPSHOT__X_VolumeTier3 = BASKETSNAPSHOT__X_VolumeTier3;
    }

    public int getBASKETSNAPSHOT__X_VolumeTier2() {
        return BASKETSNAPSHOT__X_VolumeTier2;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier2(int BASKETSNAPSHOT__X_VolumeTier2) {
        this.BASKETSNAPSHOT__X_VolumeTier2 = BASKETSNAPSHOT__X_VolumeTier2;
    }

    public int getBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES() {
        return BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES;
    }

    public void setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(int BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES) {
        this.BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES = BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES;
    }

    public List<DPRQuoteTC> getDPRQuoteTCList(){

        List<DPRQuoteTC> DPRQuotePlanHHList = new ArrayList<>();

       /* DPRQuoteTC DPRQuotePlanHH1 = new DPRQuoteTC();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_DISCOUNT_TEXT("10");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(100000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(10);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(300000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuoteTC DPRQuotePlanHH2 = new DPRQuoteTC();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_DISCOUNT_TEXT("15");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(400000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(10);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(600000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuoteTC DPRQuotePlanHH3 = new DPRQuoteTC();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_DISCOUNT_TEXT("20");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_QUANTITY(400);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(700000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(10);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(900000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(3000);

        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
        DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        DPRQuotePlanHHList.add(DPRQuotePlanHH3);*/

        return DPRQuotePlanHHList;

    }

    public List<DPRQuoteTC> getValue(){

        List<DPRQuoteTC> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteTC DPRQuotePlanHH1 = new DPRQuoteTC();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuoteTC DPRQuotePlanHH2 = new DPRQuoteTC();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuoteTC DPRQuotePlanHH3 = new DPRQuoteTC();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_QUANTITY(400);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(70000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(80000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(90000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(3000);

        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
        DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        DPRQuotePlanHHList.add(DPRQuotePlanHH3);

        return DPRQuotePlanHHList;

    }
    
}
