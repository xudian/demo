package com.aladen.service.sys;

import com.aladen.entity.sys.SysFieldInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-28
 */
public interface ISysFieldInfoService extends IService<SysFieldInfo> {


    Map<String,String> getPropertys();

}
