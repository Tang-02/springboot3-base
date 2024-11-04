package com.tjl.example.service;

import com.tjl.example.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tjl.example.model.vo.UserReg;

/**
* @author 27701
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-27 14:23:44
*/
public interface UserService extends IService<User> {

    int register(User user);

    User login(User user);
}
