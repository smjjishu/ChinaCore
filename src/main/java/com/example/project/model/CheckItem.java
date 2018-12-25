package com.example.project.model;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckItem
{
    private String CheckItemName;
    private String CheckItemImageSrc ;
    private String CheckItemCode;
    private String DepartmentName;
    private String SalePrice ;
    private Integer CheckStateID ;
    private String CheckUserName;
    private String SummaryFormat ;
    private String IsAbnormal;
    private List<CheckResult> CheckResults ;
}
