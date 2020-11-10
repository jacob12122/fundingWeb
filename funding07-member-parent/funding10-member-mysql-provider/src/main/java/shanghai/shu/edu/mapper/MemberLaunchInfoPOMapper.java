package shanghai.shu.edu.mapper;
import org.apache.ibatis.annotations.Param;
import shanghai.shu.edu.entity.po.MemberLaunchInfoPO;
import shanghai.shu.edu.entity.po.MemberLaunchInfoPOExample;

import java.util.List;

public interface MemberLaunchInfoPOMapper {
    int countByExample(MemberLaunchInfoPOExample example);

    int deleteByExample(MemberLaunchInfoPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberLaunchInfoPO record);

    int insertSelective(MemberLaunchInfoPO record);

    List<MemberLaunchInfoPO> selectByExample(MemberLaunchInfoPOExample example);

    MemberLaunchInfoPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberLaunchInfoPO record, @Param("example") MemberLaunchInfoPOExample example);

    int updateByExample(@Param("record") MemberLaunchInfoPO record, @Param("example") MemberLaunchInfoPOExample example);

    int updateByPrimaryKeySelective(MemberLaunchInfoPO record);

    int updateByPrimaryKey(MemberLaunchInfoPO record);
}