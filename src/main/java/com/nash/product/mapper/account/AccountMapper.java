package com.nash.product.mapper.account;

import com.nash.product.entity.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Description：账号数据库映射类
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/27 7:30 下午
 */
@Component
@Mapper
public interface AccountMapper {
    /**
     * 通过ID查询账号信息
     * @param id
     * @return
     */
    Account selectById(@Param("id") long id);

    /**
     * 通过名称查询账号信息
     * @param name
     * @return
     */
    Account selectByName(@Param("name") String name);

    /**
     * 通过ID和名称查询账号信息
     * @param id
     * @param name
     * @return
     */
    Account selectByIdAndName(@Param("id") long id, @Param("name") String name);

    /**
     * 查询账号列表
     * @return
     */
    List<Account> list(Map<String,Object> map);

    /**
     * 查询账号数量
     * @return
     */
    int getCount();

    /**
     * 通过手机号查询账号信息
     * @param mobile
     * @return
     */
    Account selectByMobile(@Param("mobile") String mobile);

    /**
     * 通过名称查询key
     * @param name
     * @return
     */
    List<Account>  selectKeyByName(@Param("name") String name);

    /**
     * 添加账号
     * @param account
     * @return
     */
    int insertAccount(Account account);

    /**
     * 更新密码
     * @param password
     * @param mobile
     * @return
     */
    int updatePassByMobile(String password, String mobile);

    /**
     * 更新密码
     * @param password
     * @param name
     * @return
     */
    int updatePassByName(String password, String name);


    int updateBalance(BigDecimal cost, String name);
}
