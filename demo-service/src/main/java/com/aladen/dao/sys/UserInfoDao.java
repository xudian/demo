package com.aladen.dao.sys;

import com.aladen.dao.base.BaseDao;
import com.aladen.entity.sys.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoDao extends BaseDao<UserInfoDO> {

}
