package shanghai.shu.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shanghai.shu.edu.entity.Menu;
import shanghai.shu.edu.entity.MenuExample;
import shanghai.shu.edu.mapper.MenuMapper;
import shanghai.shu.edu.service.api.MenuService;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询得到所有menu
     * @return
     */
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    /**
     * 插入新的menu
     * @param menu
     */
    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
     * 更新menu
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 删除menu
     * @param id
     */
    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
