package Apptus.util;


import java.util.ArrayList;
import java.util.List;

public class IOTRateCardUtilities {

    String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    int BASKETSNAPSHOT__X_VolumeTier4;
    int  BASKETSNAPSHOT__X_VolumeTier1;
    int  BASKETSNAPSHOT__X_VolumeTier3;
    int  BASKETSNAPSHOT__X_VolumeTier2;
    int BASKETSNAPSHOT__X_VolumeTier5;

    public String getBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME() {
        return BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    }

    public void setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME(String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME) {
        this.BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME = BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    }

    public int getBASKETSNAPSHOT__X_VolumeTier4() {
        return BASKETSNAPSHOT__X_VolumeTier4;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier4(int BASKETSNAPSHOT__X_VolumeTier4) {
        this.BASKETSNAPSHOT__X_VolumeTier4 = BASKETSNAPSHOT__X_VolumeTier4;
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

    public int getBASKETSNAPSHOT__X_VolumeTier5() {
        return BASKETSNAPSHOT__X_VolumeTier5;
    }

    public void setBASKETSNAPSHOT__X_VolumeTier5(int BASKETSNAPSHOT__X_VolumeTier5) {
        this.BASKETSNAPSHOT__X_VolumeTier5 = BASKETSNAPSHOT__X_VolumeTier5;
    }

    public List<IOTRateCardUtilities> getIOTRateCardUtilitiesList(){

        List<IOTRateCardUtilities> DPRQuotePlanHHList = new ArrayList<>();

        /*IOTRateCardUtilities DPRQuotePlanHH1 = new IOTRateCardUtilities();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(1);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(75001);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(275001);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier4(500001);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier5(750001);

        IOTRateCardUtilities DPRQuotePlanHH2 = new IOTRateCardUtilities();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(2);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(75002);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(275002);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier4(500002);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier5(750002);

        IOTRateCardUtilities DPRQuotePlanHH3 = new IOTRateCardUtilities();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(3);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(75003);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(275003);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier4(500003);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier5(750003);

        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
        DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        DPRQuotePlanHHList.add(DPRQuotePlanHH3);*/

        return DPRQuotePlanHHList;

    }

    public List<IOTRateCardUtilities> getValue(){

        List<IOTRateCardUtilities> DPRQuotePlanHHList = new ArrayList<>();

        IOTRateCardUtilities DPRQuotePlanHH1 = new IOTRateCardUtilities();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier4(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier5(1000);

        IOTRateCardUtilities DPRQuotePlanHH2 = new IOTRateCardUtilities();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier4(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier5(2000);

        IOTRateCardUtilities DPRQuotePlanHH3 = new IOTRateCardUtilities();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier4(400);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(70000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(80000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(90000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier5(3000);

        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
        DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        DPRQuotePlanHHList.add(DPRQuotePlanHH3);

        return DPRQuotePlanHHList;

    }
    
}
