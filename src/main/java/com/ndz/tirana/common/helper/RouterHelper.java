package com.ndz.tirana.common.helper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ndz.tirana.vo.sys.MetaVO;
import com.ndz.tirana.vo.sys.RouterVO;
import com.ndz.tirana.vo.sys.SysMenuVO;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *根据菜单数据构建路由的工具类
 */
public class RouterHelper {


    /**
     * 根据菜单构建路由
     * @param menus
     * @return
     */
    public static List<RouterVO> buildRouters(List<SysMenuVO> menus) {
        List<RouterVO> routers = new LinkedList<>();
        for (SysMenuVO menu : menus) {
            RouterVO router = new RouterVO();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVO(menu.getName(), menu.getIcon()));
            List<SysMenuVO> children = menu.getChildren();
            //如果当前是菜单，需将按钮对应的路由加载出来，如：“角色授权”按钮对应的路由在“系统管理”下面
            if(menu.getType().intValue() == 1) {
                List<SysMenuVO> hiddenMenuList = children.stream().filter(item -> !StrUtil.isEmpty(item.getComponent())).collect(Collectors.toList());
                for (SysMenuVO hiddenMenu : hiddenMenuList) {
                    RouterVO hiddenRouter = new RouterVO();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVO(hiddenMenu.getName(), hiddenMenu.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollUtil.isEmpty(children)) {
                    if(children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouters(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public static String getRouterPath(SysMenuVO menu) {
        String routerPath = "/" + menu.getPath();
        if(menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }
}
