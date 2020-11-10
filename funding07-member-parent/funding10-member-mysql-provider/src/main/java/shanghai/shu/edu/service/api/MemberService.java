package shanghai.shu.edu.service.api;


import shanghai.shu.edu.entity.po.Member;

public interface MemberService {
    Member getMemberByLoginAcct(String loginacct);
    void saveMember(Member memberPO);
}
