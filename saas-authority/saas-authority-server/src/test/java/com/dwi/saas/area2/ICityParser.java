package com.dwi.saas.area2;

import java.util.List;

import com.dwi.saas.authority.domain.entity.common.Area;


public interface ICityParser {

    /**
     * 解析得到省市区数据
     *
     * @param url 请求url
     * @return 城市
     */
    List<Area> parseProvinces(String url);
}
