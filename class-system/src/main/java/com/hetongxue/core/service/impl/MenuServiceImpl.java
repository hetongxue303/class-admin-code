package com.hetongxue.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.core.domain.Menu;
import com.hetongxue.core.mapper.MenuMapper;
import com.hetongxue.core.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 菜单业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}