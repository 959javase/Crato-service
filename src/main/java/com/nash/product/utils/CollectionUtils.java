package com.nash.product.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 2:16 下午
 */
@Slf4j
@Component
public class CollectionUtils {
    public static <T> Set<T> toSet(Collection<T> collection) {
        return new HashSet<>(collection);
    }

    public static List arrayToList(String[] ar) {
        return null;
    }
}
