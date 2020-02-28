package com.boot.template.module.service.impl;

import com.boot.template.common.base.service.impl.BaseServiceImpl;
import com.boot.template.module.mapper.UserMapper;
import com.boot.template.module.model.po.User;
import com.boot.template.module.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author nurhier
 * @date 2020/2/15
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
