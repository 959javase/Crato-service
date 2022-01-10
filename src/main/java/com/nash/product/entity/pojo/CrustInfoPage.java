package com.nash.product.entity.pojo;

import com.nash.product.entity.device.DeviceManageModel;

import java.util.HashMap;
import java.util.List;

public class CrustInfoPage {
    /**
     * cru
     */
    private List<HashMap> list;
    /**
     * 总数
     */
    private int total;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 页数
     */
    private int pageSize;

    public List<HashMap> getList() {
        return list;
    }

    public void setList(List<HashMap> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
