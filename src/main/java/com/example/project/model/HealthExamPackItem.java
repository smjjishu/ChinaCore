package com.example.project.model;


import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HealthExamPackItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String image;
}
