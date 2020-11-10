package shanghai.shu.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shanghai.shu.edu.entity.po.Member;
import shanghai.shu.edu.entity.po.MemberExample;
import shanghai.shu.edu.mapper.MemberMapper;
import shanghai.shu.edu.service.api.MemberService;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    public Member getMemberByLoginAcct(String loginacct) {
        MemberExample example = new MemberExample();
        MemberExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        List<Member> list = memberMapper.selectByExample(example);
        return list.get(0);
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class,
            readOnly = false)
    public void saveMember(Member memberPO) {

        memberMapper.insertSelective(memberPO);

    }
}
