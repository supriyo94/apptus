package Apptus.service;

import Apptus.util.DPRQuotePlanHH;

import java.util.ArrayList;
import java.util.List;

public class DPRQuoteL1_New {

    double DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE;
    double DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST;
    double DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE;
    double DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST;

    public double getDELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE() {
        return DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE;
    }

    public void setDELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE(double DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE) {
        this.DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE = DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE;
    }

    public double getDELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST() {
        return DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST;
    }

    public void setDELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST(double DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST) {
        this.DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST = DELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE_INC_GST;
    }

    public double getDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE() {
        return DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE;
    }

    public void setDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE(double DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE) {
        this.DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE = DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE;
    }

    public double getDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST() {
        return DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST;
    }

    public void setDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST(double DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST) {
        this.DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST = DELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST;
    }

    public List<DPRQuoteL1_New> getDPRQuoteL1List(){

        List<DPRQuoteL1_New> DPRQuotePlanHHList = new ArrayList<>();

        DPRQuoteL1_New DPRQuotePlanHH1 = new DPRQuoteL1_New();
        DPRQuotePlanHH1.setDELEGATED_PRICING_REQUEST_TOTAL_RECURRING_PRICE(229060.00);
        DPRQuotePlanHH1.setDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST(0.00);
        DPRQuotePlanHH1.setDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE(0.00);
        DPRQuotePlanHH1.setDELEGATED_PRICING_REQUEST_TOTAL_ONCEOFF_PRICE_INC_GST(251966.00);


        DPRQuotePlanHHList.add(DPRQuotePlanHH1);
       // DPRQuotePlanHHList.add(DPRQuotePlanHH2);
       // DPRQuotePlanHHList.add(DPRQuotePlanHH3);

        return DPRQuotePlanHHList;

    }


}