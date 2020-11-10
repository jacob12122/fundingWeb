package shanghai.shu.edu.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shanghai.shu.edu.constant.CrowdConstant;
import shanghai.shu.edu.entity.po.Member;
import shanghai.shu.edu.service.api.MemberService;
import shanghai.shu.edu.util.ResultEntity;

@RestController
public class MemberProviderHandler {
    @Autowired
    private MemberService memberService;
    Logger logger=LoggerFactory.getLogger(MemberProviderHandler.class);
    @RequestMapping("/get/member/by/login/acct/remote")
    public ResultEntity<Member> getMemberByLoginAcctRemote(@RequestParam("loginacct")String loginacct){
        try {
            Member member = memberService.getMemberByLoginAcct(loginacct);
            return ResultEntity.successWithData(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(
            @RequestBody Member memberPO) {

        logger.info(memberPO.toString());

        try {

            memberService.saveMember(memberPO);

            return ResultEntity.successWithoutData();

        }catch(Exception e) {

            if(e instanceof DuplicateKeyException) {
                return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }

            return ResultEntity.failed(e.getMessage());
        }

    }
}
