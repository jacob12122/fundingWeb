package shanghai.shu.edu.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import shanghai.shu.edu.constant.CrowdConstant;
import shanghai.shu.edu.entity.Admin;
import shanghai.shu.edu.entity.AdminExample;
import shanghai.shu.edu.exception.LoginAcctAlreadyInUseException;
import shanghai.shu.edu.exception.LoginAcctAlreadyInUseForUpdateException;
import shanghai.shu.edu.exception.LoginFailedException;
import shanghai.shu.edu.mapper.AdminMapper;
import shanghai.shu.edu.service.api.AdminService;
import shanghai.shu.edu.util.CrowdUtil;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    private Logger logger=LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void saveAdmin(Admin admin) {

        // 1.密码加密
        String userPswd = admin.getUserPswd();
         userPswd = CrowdUtil.md5(userPswd);
//		userPswd = passwordEncoder.encode(userPswd);
        admin.setUserPswd(userPswd);

        // 2.生成创建时间
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);

        // 3.执行保存
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常全类名="+e.getClass().getName());

            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }

    }

    @Override
    public List<Admin> getAll() {
        return null;
    }

    /**
     * 通过帐号密码得到Admin
     * @param loginAcct
     * @param userPswd
     * @return
     */
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        //根据登录帐号查询Admin对象
        AdminExample adminExample = new AdminExample();
        //创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        //在Criteria对象中封装查询条件
        System.out.println(loginAcct);
        System.out.println(userPswd);
        criteria.andLoginAcctEqualTo(loginAcct);
        //调用AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        //判断Admin对象是否为null
        if(list==null||list.size()==0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if(list.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);

        // 3.如果Admin对象为null则抛出异常
        if(admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 如果Admin对象不为null则将数据库密码从Admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 将表单提交的明文密码进行加密
        String userPswdForm = CrowdUtil.md5(userPswd);

        // 对密码进行比较
        if(!Objects.equals(userPswdDB, userPswd)) {
            // 7.如果比较结果是不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 8.如果一致则返回Admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用PageHelper的静态方法开启分页功能
        // 这里充分体现了PageHelper的“非侵入式”设计：原本要做的查询不必有任何修改
        PageHelper.startPage(pageNum, pageSize);

        // 2.执行查询
        List<Admin> list = adminMapper.selectAdminByKeyword(keyword);

        // 3.封装到PageInfo对象中
        return new PageInfo<>(list);
    }

    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void update(Admin admin) {

        // “Selective”表示有选择的更新，对于null值的字段不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("异常全类名="+e.getClass().getName());

            if(e instanceof DuplicateKeyException) {
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {
        adminMapper.deleteOLdRelationship(adminId);
        if(roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String username) {

        AdminExample example = new AdminExample();

        AdminExample.Criteria criteria = example.createCriteria();

        criteria.andLoginAcctEqualTo(username);

        List<Admin> list = adminMapper.selectByExample(example);

        Admin admin = list.get(0);

        return admin;
    }
}
