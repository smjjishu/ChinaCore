package com.example.project.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GeneralSummary
{
    private String  SummaryName;
    private String  SummaryCode;
    private String  SummaryDescription;
    private String  ReviewAdvice;
    private Boolean IsPrivacy;
    private String  SummaryMedicalExplanation;
    private String  SummaryReasonResult;
    private String  SummaryAdvice;
}