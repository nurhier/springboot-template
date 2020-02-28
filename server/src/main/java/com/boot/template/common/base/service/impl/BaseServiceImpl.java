package com.boot.template.common.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.template.common.base.mapper.BaseMapper;
import com.boot.template.common.base.service.BaseService;

/**
 * @author nurhier
 * @date 2020/2/15
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {
}
