package com.example.project.test;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chinese  extends  Person  implements Human {
    private  String eyeColor;

    private  String nation;

    private  String height;

    private  String weight;

    public void  run()
    {
        System.out.println("Run!");
    }

    public void  eat()
    {
        System.out.println("Eat!");
    }

//    public void  eat(Integer num)
//    {
//        System.out.println("Eat!");
//    }
//
//    public void  eat(String name)
//    {
//        System.out.println("Eat!");
//    }
//
//    public void  eat(Object obj)
//    {
//        System.out.println("Eat!");
//    }

}
