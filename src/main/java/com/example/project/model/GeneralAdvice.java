package com.example.project.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GeneralAdvice
{
    public int AdviceCode;
    private String  AdviceName;
    private String  AdviceDescription;
    private String  IsPrivacy;
    private Integer ShowIndex;
    private String  GeneralSummarys;
}