package Apptus.util;


import java.util.ArrayList;
import java.util.List;

public class DPRQuoteDeviceOC {

    String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    int  BASKETSNAPSHOT__X_QUANTITY;
    int  BASKETSNAPSHOT__X_VolumeTier1;
    int  BASKETSNAPSHOT__X_VolumeTier3;
    int  BASKETSNAPSHOT__X_VolumeTier2;
    int BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES;

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

    public List<DPRQuoteDeviceOC> getDPRQuoteDeviceOCList(){

        List<DPRQuoteDeviceOC> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteDeviceOC DPRQuotePlanHH1 = new DPRQuoteDeviceOC();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(100000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(10000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(90000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(85714);

        DPRQuoteDeviceOC DPRQuotePlanHH2 = new DPRQuoteDeviceOC();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(400000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(80000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(320000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(304761);

        DPRQuoteDeviceOC DPRQuotePlanHH3 = new DPRQuoteDeviceOC();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_QUANTITY(400);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(700000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(210000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(490000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(466666);
        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
        DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        DPRQuotePlanHHList.add(DPRQuotePlanHH3);

        return DPRQuotePlanHHList;

    }

    public List<DPRQuoteDeviceOC> getValue(){

        List<DPRQuoteDeviceOC> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteDeviceOC DPRQuotePlanHH1 = new DPRQuoteDeviceOC();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuoteDeviceOC DPRQuotePlanHH2 = new DPRQuoteDeviceOC();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuoteDeviceOC DPRQuotePlanHH3 = new DPRQuoteDeviceOC();
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
