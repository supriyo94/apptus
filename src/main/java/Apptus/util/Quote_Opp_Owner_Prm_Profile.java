package Apptus.util;

public class Quote_Opp_Owner_Prm_Profile {

    String OPPORTUNITYPARTNERACCOUNT_NAME;
    int OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE;

    public Quote_Opp_Owner_Prm_Profile(String OPPORTUNITYPARTNERACCOUNT_NAME, int OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE) {
        this.OPPORTUNITYPARTNERACCOUNT_NAME = OPPORTUNITYPARTNERACCOUNT_NAME;
        this.OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE = OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE;
    }

    public int getOPPORTUNITYPARTNERACCOUNT_PARTNER_CODE() {
        return OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE;
    }

    public void setOPPORTUNITYPARTNERACCOUNT_PARTNER_CODE(int OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE) {
        this.OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE = OPPORTUNITYPARTNERACCOUNT_PARTNER_CODE;
    }

    public String getOPPORTUNITYPARTNERACCOUNT_NAME() {
        return OPPORTUNITYPARTNERACCOUNT_NAME;
    }

    public void setOPPORTUNITYPARTNERACCOUNT_NAME(String OPPORTUNITYPARTNERACCOUNT_NAME) {
        this.OPPORTUNITYPARTNERACCOUNT_NAME = OPPORTUNITYPARTNERACCOUNT_NAME;
    }

}
