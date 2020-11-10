package shanghai.shu.edu.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shanghai.shu.edu.entity.po.AddressPO;
import shanghai.shu.edu.entity.po.AddressPOExample;
import shanghai.shu.edu.entity.po.OrderPO;
import shanghai.shu.edu.entity.po.OrderProjectPO;
import shanghai.shu.edu.entity.vo.AddressVO;
import shanghai.shu.edu.entity.vo.OrderProjectVO;
import shanghai.shu.edu.entity.vo.OrderVO;
import shanghai.shu.edu.mapper.AddressPOMapper;
import shanghai.shu.edu.mapper.OrderPOMapper;
import shanghai.shu.edu.mapper.OrderProjectPOMapper;
import shanghai.shu.edu.service.api.OrderService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderProjectPOMapper orderProjectPOMapper;
	
	@Autowired
	private OrderPOMapper orderPOMapper;
	
	@Autowired
	private AddressPOMapper addressPOMapper;

	public OrderProjectVO getOrderProjectVO(Integer projectId, Integer returnId) {
		
		return orderProjectPOMapper.selectOrderProjectVO(returnId);
	}

	public List<AddressVO> getAddressVOList(Integer memberId) {
		
		AddressPOExample example = new AddressPOExample();
		
		example.createCriteria().andMemberIdEqualTo(memberId);
		
		List<AddressPO> addressPOList = addressPOMapper.selectByExample(example);
		
		List<AddressVO> addressVOList = new ArrayList<AddressVO>();
		
		for (AddressPO addressPO : addressPOList) {
			AddressVO addressVO = new AddressVO();
			BeanUtils.copyProperties(addressPO, addressVO);
			
			addressVOList.add(addressVO);
		}
		
		return addressVOList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void saveAddress(AddressVO addressVO) {
		
		AddressPO addressPO = new AddressPO();
		
		BeanUtils.copyProperties(addressVO, addressPO);
		
		addressPOMapper.insert(addressPO);
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void saveOrder(OrderVO orderVO) {

		OrderPO orderPO = new OrderPO();

		BeanUtils.copyProperties(orderVO, orderPO);

		OrderProjectPO orderProjectPO = new OrderProjectPO();

		BeanUtils.copyProperties(orderVO.getOrderProjectVO(), orderProjectPO);

		// 保存orderPO自动生成的主键是orderProjectPO需要用到的外键
		orderPOMapper.insert(orderPO);

		// 从orderPO中获取orderId
		Integer id = orderPO.getId();

		// 将orderId设置到orderProjectPO
		orderProjectPO.setOrderId(id);

		orderProjectPOMapper.insert(orderProjectPO);
	}

}
