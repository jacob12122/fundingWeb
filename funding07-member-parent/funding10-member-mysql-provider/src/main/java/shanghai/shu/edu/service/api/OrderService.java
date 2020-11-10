package shanghai.shu.edu.service.api;

import shanghai.shu.edu.entity.vo.AddressVO;
import shanghai.shu.edu.entity.vo.OrderProjectVO;
import shanghai.shu.edu.entity.vo.OrderVO;

import java.util.List;

public interface OrderService {
    OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId);

    List<AddressVO> getAddressVOList(Integer memberId);

    void saveAddress(AddressVO addressVO);

    void saveOrder(OrderVO orderVO);
}
