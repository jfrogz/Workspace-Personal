package com.jfrogz.identities;

/**
 * Created by Praxis on 18/04/2017.
 */
public enum PaisCambio {
    KRW("KRW"),
    XAG("XAG"),
    VND("VND"),
    BOB("BOB"),
    BDT("BDT"),
    MDL("MDL"),
    VEF("VEF"),
    ISK("ISK"),
    BYR("BYR"),
    THB("THB"),
    MXV("MXV"),
    TND("TND"),
    JMD("JMD"),
    DKK("DKK"),
    SRD("SRD"),
    BWP("BWP"),
    NOK("NOK"),
    MUR("MUR"),
    AZN("AZN"),
    INR("INR"),
    MGA("MGA"),
    CAD("CAD"),
    XAF("XAF"),
    LBP("LBP"),
    XDR("XDR"),
    IDR("IDR"),
    IEP("IEP"),
    AUD("AUD"),
    MMK("MMK"),
    LYD("LYD"),
    ZAR("ZAR"),
    IQD("IQD"),
    XPF("XPF"),
    TJS("TJS"),
    CUP("CUP"),
    UGX("UGX"),
    NGN("NGN"),
    PGK("PGK"),
    TOP("TOP"),
    TMT("TMT"),
    KES("KES"),
    CRC("CRC"),
    MZN("MZN"),
    BYN("BYN"),
    SYP("SYP"),
    ANG("ANG"),
    ZMW("ZMW"),
    BRL("BRL"),
    BSD("BSD"),
    NIO("NIO"),
    GNF("GNF"),
    BMD("BMD"),
    SLL("SLL"),
    MKD("MKD"),
    BIF("BIF"),
    LAK("LAK"),
    BHD("BHD"),
    SHP("SHP"),
    BGN("BGN"),
    SGD("SGD"),
    EUR("EUR"),
    SCR("SCR"),
    BBD("BBD"),
    SBD("SBD"),
    MAD("MAD"),
    GTQ("GTQ"),
    MWK("MWK"),
    PKR("PKR"),
    ITL("ITL"),
    PEN("PEN"),
    AED("AED"),
    LVL("LVL"),
    XPD("XPD"),
    UAH("UAH"),
    FRF("FRF"),
    LRD("LRD"),
    LSL("LSL"),
    SEK("SEK"),
    RON("RON"),
    XOF("XOF"),
    COP("COP"),
    CDF("CDF"),
    USD("USD"),
    TZS("TZS"),
    GHS("GHS"),
    NPR("NPR"),
    SOS("SOS"),
    DZD("DZD"),
    LKR("LKR"),
    JPY("JPY"),
    KYD("KYD"),
    CLP("CLP"),
    IRR("IRR"),
    AFN("AFN"),
    DJF("DJF"),
    SVC("SVC"),
    PLN("PLN"),
    PYG("PYG"),
    ERN("ERN"),
    ETB("ETB"),
    ILS("ILS"),
    TWD("TWD"),
    KPW("KPW"),
    GIP("GIP"),
    BND("BND"),
    HNL("HNL"),
    CZK("CZK"),
    HUF("HUF"),
    BZD("BZD"),
    DEM("DEM"),
    JOD("JOD"),
    RWF("RWF"),
    LTL("LTL"),
    RUB("RUB"),
    RSD("RSD"),
    WST("WST"),
    XPT("XPT"),
    NAD("NAD"),
    PAB("PAB"),
    DOP("DOP"),
    ALL("ALL"),
    AMD("AMD"),
    MRO("MRO"),
    HRK("HRK"),
    HTG("HTG"),
    KHR("KHR"),
    PHP("PHP"),
    CYP("CYP"),
    KWD("KWD"),
    XCD("XCD"),
    XCP("XCP"),
    CNH("CNH"),
    SDG("SDG"),
    CLF("CLF"),
    KZT("KZT"),
    TRY("TRY"),
    FJD("FJD"),
    NZD("NZD"),
    BAM("BAM"),
    BTN("BTN"),
    STD("STD"),
    VUV("VUV"),
    MVR("MVR"),
    EGP("EGP"),
    QAR("QAR"),
    OMR("OMR"),
    CVE("CVE"),
    KGS("KGS"),
    MXN("MXN"),
    MYR("MYR"),
    GYD("GYD"),
    SZL("SZL"),
    YER("YER"),
    SAR("SAR"),
    UYU("UYU"),
    GBP("GBP"),
    UZS("UZS"),
    GMD("GMD"),
    AWG("AWG"),
    MNT("MNT"),
    XAU("XAU"),
    HKD("HKD"),
    ARS("ARS"),
    HUX("HUX"),
    FKP("FKP"),
    CHF("CHF"),
    GEL("GEL"),
    MOP("MOP"),
    SIT("SIT"),
    KMF("KMF"),
    ZWL("ZWL"),
    AOA("AOA"),
    CNY("CNY"),
    TTD("TTD"),
    BRX("BRX"),
    ECS("ECS");

    private String simbolo;

    private PaisCambio (String s)
    {
        simbolo = s;
    }

    public String getSimbolo(){
        return  simbolo;
    }
}