package com.boot.template.module.mapper;

import com.boot.template.common.base.mapper.BaseMapper;
import com.boot.template.module.model.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * user mapper interface
 *
 * @author nurhier
 * @date 2020/2/15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
