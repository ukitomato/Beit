package com.google.developer.beit.beit;

import android.support.v4.util.SparseArrayCompat;


public class Order {

    public static final int METHOD_WALK_OR_BYCICLE = 0;
    public static final int METHOD_BIKE = 1;
    public static final int METHOD_CAR = 2;

    public static final int FRAGILE = 0;//壊れ物
    public static final int THIS_WAY_UP = 1;//天地無用
    public static final int HANDLE_WITH_CARE = 2;//取り扱い注意
    public static final int KEEP_DRY = 3;//水濡れ防止
    public static final int DONT_USE_CUTTER = 4;//カッター注意
    public static final int PROTECT_FROM_HEAT = 5;//直射日光

    private String sender_account;
    private String destination_account;

    private String item_name;
    private String item_detail;

    private int method;

    private SparseArrayCompat<Boolean> caremark_settings;

    private boolean option_insurance;

    public Order(String sender_account, String destination_account, String item_name) {
        this.sender_account = sender_account;
        this.destination_account = destination_account;
        this.item_name = item_name;
        caremark_settings = new SparseArrayCompat<>();
    }

    public String getSender_account() {
        return sender_account;
    }

    public String getDestination_account() {
        return destination_account;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_detail() {
        return item_detail;
    }

    public void setItem_detail(String item_detail) {
        this.item_detail = item_detail;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }


    public void setCareMarkSetting(int key, Boolean bool) {
        caremark_settings.append(key, bool);
    }

    public void setAllCareMarkSettings(Boolean[] settings) {
        for (int i = 0; i < 6; i++) {
            caremark_settings.append(i, settings[i]);
        }
    }

    public boolean isOption_insurance() {
        return option_insurance;
    }

    public SparseArrayCompat<Boolean> getCaremark_settings() {
        return caremark_settings;
    }
}
