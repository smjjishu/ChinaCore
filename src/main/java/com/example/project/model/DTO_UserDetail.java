package com.example.project.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 */
@Data
public class DTO_UserDetail implements Serializable {
    private String id;
    private String title;
    private String author;
    private String name;
    private String age;
    private String email;
    private Date date;
}