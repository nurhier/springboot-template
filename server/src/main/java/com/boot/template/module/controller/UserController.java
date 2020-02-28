package com.boot.template.module.controller;

import com.boot.template.common.resource.RedisConfig;
import com.boot.template.common.utils.ResultUtils;
import com.boot.template.module.model.po.User;
import com.boot.template.module.model.vo.UserVo;
import com.boot.template.module.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nurhier
 * @date 2020/2/15
 */
@RestController
@RequestMapping(value = "/web")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RedisConfig redisConfig;

    @GetMapping(value = "/user/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        System.out.println(redisConfig);
        User user = userService.getById(id);
        return ResultUtils.success(user);
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<?> saveUser(@RequestBody UserVo userVo) {
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        userService.save(user);
        return ResultUtils.success();
    }
}
