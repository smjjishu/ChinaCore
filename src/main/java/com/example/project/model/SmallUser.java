package com.example.project.model;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SmallUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String namestr;
}
