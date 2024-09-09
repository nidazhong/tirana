package com.ndz.tirana.common.helper;

import com.ndz.tirana.vo.sys.SysMenuVO;

import java.util.ArrayList;
import java.util.List;

public class MenuHelper {
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenuVO> buildTree(List<SysMenuVO> sysMenuList) {
        List<SysMenuVO> trees = new ArrayList<>();
        for (SysMenuVO sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static SysMenuVO findChildren(SysMenuVO sysMenu, List<SysMenuVO> treeNodes) {
        sysMenu.setChildren(new ArrayList<>());
        for (SysMenuVO it : treeNodes) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
