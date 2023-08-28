package Apptus.util;


import java.util.ArrayList;
import java.util.List;

public class DPRQuoteDeviceRC {

    String BASKETSNAPSHOT__X_PRODUCT_MODULE_NAME;
    int  BASKETSNAPSHOT__X_QUANTITY;
    int  BASKETSNAPSHOT__X_VolumeTier1;
    int  BASKETSNAPSHOT__X_VolumeTier3;
    int  BASKETSNAPSHOT__X_VolumeTier2;
    int BASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES;
    int BASKETSNAPSHOT__X_DISCOUNT_TEXT;

    public int getBASKETSNAPSHOT__X_DISCOUNT_TEXT() {
        return BASKETSNAPSHOT__X_DISCOUNT_TEXT;
    }

    public void setBASKETSNAPSHOT__X_DISCOUNT_TEXT(int BASKETSNAPSHOT__X_DISCOUNT_TEXT) {
        this.BASKETSNAPSHOT__X_DISCOUNT_TEXT = BASKETSNAPSHOT__X_DISCOUNT_TEXT;
    }

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

    public List<DPRQuoteDeviceRC> getDPRQuoteDeviceRCList(){

        List<DPRQuoteDeviceRC> DPRQuotePlanHHList = new ArrayList<>();

        /*DPRQuoteDeviceRC dPRQuoteDeviceRC1 = new DPRQuoteDeviceRC();
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_QUANTITY(200);
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_VolumeTier3(10000);
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_VolumeTier2(90000);
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(85714);
        dPRQuoteDeviceRC1.setBASKETSNAPSHOT__X_DISCOUNT_TEXT(10);

        DPRQuoteDeviceRC dPRQuoteDeviceRC2 = new DPRQuoteDeviceRC();
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_QUANTITY(300);
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_VolumeTier3(80000);
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_VolumeTier2(320000);
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(304761);
        dPRQuoteDeviceRC2.setBASKETSNAPSHOT__X_DISCOUNT_TEXT(20);

        DPRQuoteDeviceRC dPRQuoteDeviceRC3 = new DPRQuoteDeviceRC();
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_QUANTITY(400);
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_VolumeTier1(70000000);
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_VolumeTier3(210000);
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_VolumeTier2(490000);
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(466666);
        dPRQuoteDeviceRC3.setBASKETSNAPSHOT__X_DISCOUNT_TEXT(30);

        DPRQuotePlanHHList.add(dPRQuoteDeviceRC1);
        DPRQuotePlanHHList.add(dPRQuoteDeviceRC2);
        DPRQuotePlanHHList.add(dPRQuoteDeviceRC3);*/

        return DPRQuotePlanHHList;

    }

   /* public List<DPRQuoteDeviceRC> getDPRQuoteDeviceRC(){

        List<DPRQuoteDeviceRC> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteDeviceRC DPRQuotePlanHH1 = new DPRQuoteDeviceRC();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuoteDeviceRC DPRQuotePlanHH2 = new DPRQuoteDeviceRC();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuoteDeviceRC DPRQuotePlanHH3 = new DPRQuoteDeviceRC();
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

    }*/
    
}
