package Apptus.util;


import java.util.ArrayList;
import java.util.List;

public class DPRQuotePlanMBB {

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

    public List<DPRQuotePlanMBB> getDPRQuotePlanMBBList(){

        List<DPRQuotePlanMBB> dPRQuotePlanMBBList = new ArrayList<>();

        /*DPRQuotePlanMBB dPRQuotePlanMBB1 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_QUANTITY(200);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier1(100000);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier3(10);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier2(300000);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuotePlanMBB dPRQuotePlanMBB2 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_QUANTITY(300);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier1(400000);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier3(20);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier2(600000);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuotePlanMBB dPRQuotePlanMBB3 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_QUANTITY(400);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier1(700000);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier3(30);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier2(900000);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(3000);*/

        DPRQuotePlanMBB dPRQuotePlanMBB1 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_QUANTITY(200);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier1(100000);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier3(10000);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_VolumeTier2(90000);
        dPRQuotePlanMBB1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(85714);

        DPRQuotePlanMBB dPRQuotePlanMBB2 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_QUANTITY(300);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier1(400000);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier3(80000);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_VolumeTier2(320000);
        dPRQuotePlanMBB2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(304761);

        DPRQuotePlanMBB dPRQuotePlanMBB3 = new DPRQuotePlanMBB();
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_QUANTITY(400);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier1(700000);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier3(210000);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_VolumeTier2(490000);
        dPRQuotePlanMBB3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(466666);

        dPRQuotePlanMBBList.add(dPRQuotePlanMBB1);
        dPRQuotePlanMBBList.add(dPRQuotePlanMBB2);
        dPRQuotePlanMBBList.add(dPRQuotePlanMBB3);

        return dPRQuotePlanMBBList;

    }

    public List<DPRQuotePlanMBB> getValue(){

        List<DPRQuotePlanMBB> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuotePlanMBB DPRQuotePlanHH1 = new DPRQuotePlanMBB();
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 1");
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_QUANTITY(200);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        DPRQuotePlanHH1.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuotePlanMBB DPRQuotePlanHH2 = new DPRQuotePlanMBB();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuotePlanMBB DPRQuotePlanHH3 = new DPRQuotePlanMBB();
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
