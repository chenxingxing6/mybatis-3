package com.demo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: lanxinghua
 * Date: 2019/10/18 09:24
 * Desc:
 */
public interface BunkTypeMapper {
    List<BunkType> listBunkType(@Param(value = "mallEntityId") String mallEntityId);

}
