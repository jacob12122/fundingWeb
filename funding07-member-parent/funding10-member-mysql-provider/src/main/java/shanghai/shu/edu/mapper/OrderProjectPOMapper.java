package shanghai.shu.edu.mapper;
import org.apache.ibatis.annotations.Param;
import shanghai.shu.edu.entity.po.OrderProjectPO;
import shanghai.shu.edu.entity.po.OrderProjectPOExample;
import shanghai.shu.edu.entity.vo.OrderProjectVO;

import java.util.List;

public interface OrderProjectPOMapper {
    int countByExample(OrderProjectPOExample example);

    int deleteByExample(OrderProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderProjectPO record);

    int insertSelective(OrderProjectPO record);

    List<OrderProjectPO> selectByExample(OrderProjectPOExample example);

    OrderProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByExample(@Param("record") OrderProjectPO record, @Param("example") OrderProjectPOExample example);

    int updateByPrimaryKeySelective(OrderProjectPO record);

    int updateByPrimaryKey(OrderProjectPO record);
    
    OrderProjectVO selectOrderProjectVO(Integer returnId);
}