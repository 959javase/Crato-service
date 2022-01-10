package com.nash.product.entity.indicator;

import lombok.*;

import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 11:43
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChartDataModel {
    /**
     * 名称
     */
    private String name;
    /**
     * field
     */
    private String field;
    /**
     * data
     */
    private List<Object> data;
    public  ChartDataModel(){}
    public ChartDataModel(String name,String field,List<Object> data){
        this.name=name;
        this.field=field;
        this.data=data;
    }
}
