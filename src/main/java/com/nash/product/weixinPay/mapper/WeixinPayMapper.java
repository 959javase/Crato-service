package com.nash.product.weixinPay.mapper;

import com.nash.product.weixinPay.domain.PayOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface WeixinPayMapper {
    public int insert(PayOrder order);
    public int updateStatus(String orderNo);
    public List<PayOrder> queryList(PayOrder order);
    public int updatePayOrder(PayOrder order);
    public String querystateByNo(String orderNo);

}
