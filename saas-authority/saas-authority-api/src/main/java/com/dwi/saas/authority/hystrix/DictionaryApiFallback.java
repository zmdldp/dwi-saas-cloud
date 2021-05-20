package com.dwi.saas.authority.hystrix;

//import com.dwi.saas.oauth.api.DictionaryApi;
import org.springframework.stereotype.Component;

import com.dwi.saas.authority.DictionaryApi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典项 查询
 *
 * @author dwi
 * @date 2020/07/26
 */
@Component
public class DictionaryApiFallback implements DictionaryApi {

    @Override
    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        return new HashMap<>(1);
    }
}
