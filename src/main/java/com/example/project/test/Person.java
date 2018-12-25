package com.example.project.test;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Person {
    private  String name;

    private  String uid;

    private  String sex;

    private  String age;

    private  String address;

}
