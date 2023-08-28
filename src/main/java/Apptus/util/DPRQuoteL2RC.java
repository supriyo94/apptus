package Apptus.util;

import java.util.ArrayList;
import java.util.List;

public class DPRQuoteL2RC {

    public String getDPR_OFFER_LINE_ITEM_DPR_NAME() {
        return DPR_OFFER_LINE_ITEM_DPR_NAME;
    }

    public void setDPR_OFFER_LINE_ITEM_DPR_NAME(String DPR_OFFER_LINE_ITEM_DPR_NAME) {
        this.DPR_OFFER_LINE_ITEM_DPR_NAME = DPR_OFFER_LINE_ITEM_DPR_NAME;
    }

    public double getDPR_OFFER_LINE_ITEM_EXPR0() {
        return DPR_OFFER_LINE_ITEM_EXPR0;
    }

    public void setDPR_OFFER_LINE_ITEM_EXPR0(double DPR_OFFER_LINE_ITEM_EXPR0) {
        this.DPR_OFFER_LINE_ITEM_EXPR0 = DPR_OFFER_LINE_ITEM_EXPR0;
    }

    String DPR_OFFER_LINE_ITEM_DPR_NAME;
    double DPR_OFFER_LINE_ITEM_EXPR0;

    public List<DPRQuoteL2RC> getDPRQuoteL2RCL1List(){

        List<DPRQuoteL2RC> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteL2RC dPRQuoteL2RC = new DPRQuoteL2RC();
        dPRQuoteL2RC.setDPR_OFFER_LINE_ITEM_DPR_NAME("Adaptive Mobility");
        dPRQuoteL2RC.setDPR_OFFER_LINE_ITEM_EXPR0(29060.00);
       /* dPRQuoteL2RC.setBASKETSNAPSHOT__X_VolumeTier1(10000000);
        dPRQuoteL2RC.setBASKETSNAPSHOT__X_VolumeTier3(20000000);
        dPRQuoteL2RC.setBASKETSNAPSHOT__X_VolumeTier2(30000000);
        dPRQuoteL2RC.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(1000);

        DPRQuotePlanHH DPRQuotePlanHH2 = new DPRQuotePlanHH();
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 2");
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_QUANTITY(300);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier1(40000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier3(50000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_VolumeTier2(60000000);
        DPRQuotePlanHH2.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(2000);

        DPRQuotePlanHH DPRQuotePlanHH3 = new DPRQuotePlanHH();
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_PRODUCT_MODULE_NAME("module 3");
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_QUANTITY(400);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier1(70000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier3(80000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_VolumeTier2(90000000);
        DPRQuotePlanHH3.setBASKETSNAPSHOT__X_GSTEXCL_TOTAL_RECURRING_CHARGES(3000);*/

        DPRQuotePlanHHList.add(dPRQuoteL2RC);
        //DPRQuotePlanHHList.add(DPRQuotePlanHH2);
        //DPRQuotePlanHHList.add(DPRQuotePlanHH3);

        return DPRQuotePlanHHList;

    }


}
