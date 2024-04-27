package com.tjl.openapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjl.openapi.common.ErrorCode;
import com.tjl.openapi.exception.BusinessException;
import com.tjl.openapi.model.User;
import com.tjl.openapi.model.vo.UserReg;
import com.tjl.openapi.service.UserService;
import com.tjl.openapi.mapper.UserMapper;
import com.tjl.openapi.utils.PwdUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
* @author 27701
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-04-27 14:23:44
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        var userQuery = new QueryWrapper<User>();
        userQuery.eq("userAccount", user.getUserAccount());
        if (userMapper.exists(userQuery)){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"帐号已存在");
        }
        user.setUserName(user.getUserAccount());
        user.setUserPassword(PwdUtil.toMD5_SALT(user.getUserPassword()));
        return userMapper.insert(user);
    }


    @Override
    public User login(User user) {
        var q = new QueryWrapper<User>();
        q.eq("userAccount", user.getUserAccount());
        q.eq("userPassword", PwdUtil.toMD5_SALT(user.getUserPassword()));
        User loginUser = userMapper.selectOne(q);
        if (loginUser == null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"帐号或密码错误");
        }
        return loginUser;
    }


}




