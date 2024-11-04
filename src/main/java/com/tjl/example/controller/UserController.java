package com.tjl.example.controller;

import co.elastic.clients.elasticsearch.core.DeleteRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.tjl.example.common.ErrorCode;
import com.tjl.example.common.R;
import com.tjl.example.common.ResultUtils;
import com.tjl.example.exception.BusinessException;
import com.tjl.example.exception.ThrowUtils;
import com.tjl.example.model.User;
import com.tjl.example.model.vo.UserReg;
import com.tjl.example.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public R<Boolean> register(@RequestBody UserReg regUser) {
        if (StringUtils.isAllBlank(regUser.getUserAccount(),
                regUser.getUserPassword(),regUser.getCheckPassword())){
            ThrowUtils.paramsError("帐号或密码不能为空");
        }
        if (!regUser.getUserPassword().equals(regUser.getCheckPassword())){
            ThrowUtils.paramsError("密码不一致");
        }
        User user = new User();
        BeanUtils.copyProperties(regUser, user);
        int register = userService.register(user);
        return ResultUtils.success(register > 0);
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody UserReg loginUser){
        if (StringUtils.isAllBlank(loginUser.getUserAccount(),
                loginUser.getUserPassword())){
            ThrowUtils.paramsError("帐号或密码不能为空");
        }
        User user = new User();
        BeanUtils.copyProperties(loginUser, user);
        return ResultUtils.success(userService.login(user));
    }

    @PostMapping("/ex")
    public R<String> ex() {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR,"业务异常");
    }
}
