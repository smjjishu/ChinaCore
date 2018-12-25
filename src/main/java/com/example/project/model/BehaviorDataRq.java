package com.example.project.model;

import com.example.project.comm.BaseParam;
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
public class BehaviorDataRq extends BaseParam implements Serializable {
    private BehaviorData data;
}
