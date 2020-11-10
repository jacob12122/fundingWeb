package shanghai.shu.edu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shanghai.shu.edu.entity.Role;
import shanghai.shu.edu.entity.RoleExample;
import shanghai.shu.edu.mapper.RoleMapper;
import shanghai.shu.edu.service.api.RoleService;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 分页展示角色
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
        //开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //执行查询
        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        //封装为PageInfo对象返回
        return new PageInfo<>(roleList);
    }

    /**
     * 保存角色
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleMapper.insert(role);
    }

    /**
     * 更新角色
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 移除角色
     * @param roleIdList
     */
    @Override
    public void removeRole(List<Integer> roleIdList) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(roleIdList);
        roleMapper.deleteByExample(example);
    }

    /**
     * 选出符合条件的Role
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return roleMapper.selectAssignedRole(adminId);
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        return roleMapper.selectUnAssignedRole(adminId);
    }
}
