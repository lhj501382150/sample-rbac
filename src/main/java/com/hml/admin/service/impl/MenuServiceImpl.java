package com.hml.admin.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hml.admin.entity.Menu;
import com.hml.admin.mapper.MenuMapper;
import com.hml.admin.service.IMenuService;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author hml
 * @since 2020-06-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
