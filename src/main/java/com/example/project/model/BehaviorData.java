package com.example.project.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 行为数据模型
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BehaviorData implements Serializable {

    private String userId;

    private String type;

    private String content;

    private String date;
}
