package com.example.project.comm;

public enum PushTypeEnum {

    PUSH_TYP_1("1", "ONE","说明文档"),
    PUSH_TYP_2("2", "TWO","说明文档"),
    PUSH_TYP_3("3", "THREE","说明文档"),
    PUSH_TYP_4("4", "FOUR","说明文档"),
    PUSH_TYP_5("5", "FIVE","说明文档"),
    PUSH_TYP_6("6", "SIX","说明文档");

    private String type;
    private String name;
    private String tip;

    PushTypeEnum(String type, String name, String tip) {
        this.type = type;
        this.name = name;
        this.tip = tip;
    }


    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public String getTip(){
        return tip;
    }
}

