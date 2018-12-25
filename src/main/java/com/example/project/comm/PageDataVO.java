package com.example.project.comm;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页返回对象
 *
 * @author tangshengping
 * @data 2018-03-29
 */
@Data
@AllArgsConstructor
@Builder
public class PageDataVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页查询结果集
     */
    private T list;

    /**
     * 每页的数量
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Integer totalCount;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 分页数量
     */
    private Integer totalPage;

    /**
     * 初始化分页对象和结果集
     * @param pageSize
     * @param totalCount
     * @param currentPage
     * @param list
     */
    public PageDataVO(int pageSize, int totalCount, int currentPage, T list) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        this.currentPage = this.totalPage >= currentPage ? currentPage : 1;
    }

    /**
     * 分页初始化
     * @param currentPage
     * @param pageSize
     * @param totalCount
     * @param list
     * @param <T>
     * @return
     */
    public static <T> PageDataVO init(int currentPage,int pageSize, int totalCount, T list){
        return new PageDataVO(pageSize,totalCount,currentPage,list);
    }

}

