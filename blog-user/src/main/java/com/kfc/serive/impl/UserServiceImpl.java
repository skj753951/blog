package com.kfc.serive.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kfc.mapper.UserMapper;
import com.kfc.model.entity.UserEntity;
import com.kfc.serive.IUserService;

public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
}
