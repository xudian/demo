package com.aladen.service.sys.impl;

import com.aladen.entity.sys.SysFieldInfo;
import com.aladen.mapper.sys.SysFieldInfoMapper;
import com.aladen.service.sys.ISysFieldInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-28
 */
@Service
public class SysFieldInfoServiceImpl extends ServiceImpl<SysFieldInfoMapper, SysFieldInfo> implements ISysFieldInfoService {

    public static final String CACHE_KEY = "properties";


    @Override
    @Cacheable(cacheNames = CACHE_KEY,key = "#root.methodName")
    public Map<String, String> getPropertys() {
        Map<String,String> map = new HashMap<>();
        QueryWrapper<SysFieldInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("busi_id",CACHE_KEY);
        queryWrapper.orderByAsc("field_order");
        List<SysFieldInfo> list = getBaseMapper().selectList(queryWrapper);
        if (list != null) {
            for (SysFieldInfo fieldInfo : list) {
                map.put(fieldInfo.getFieldName(),fieldInfo.getFieldValue());
            }
        }
        return map;
    }

}
