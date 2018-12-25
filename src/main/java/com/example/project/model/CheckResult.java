package com.example.project.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckResult {
    private String  CheckIndexName;
    private String  CheckIndexCode;
    private String  ResultValue;
    private String  AppendInfo;
    public boolean IsCalc;
    private String  Unit;
    private String  TextRef;
    public boolean IsAbandon;
    public Integer ResultTypeID;
    public Integer ResultFlagID;
    private String  LowValueRef;
    private String  HighValueRef;
    public Integer ShowIndex;
    public Integer ConsultNum;
    public boolean IsAbnormalForamt;
    private String  ValueRefFormat;


}
