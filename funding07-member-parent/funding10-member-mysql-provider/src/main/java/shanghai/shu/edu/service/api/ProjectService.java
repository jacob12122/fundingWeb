package shanghai.shu.edu.service.api;

import shanghai.shu.edu.entity.vo.DetailProjectVO;
import shanghai.shu.edu.entity.vo.PortalTypeVO;
import shanghai.shu.edu.entity.vo.ProjectVO;

import java.util.List;

public interface ProjectService {

	void saveProject(ProjectVO projectVO, Integer memberId);
	List<PortalTypeVO> getPortalTypeVO();
	DetailProjectVO getDetailProjectVO(Integer projectId);

}
