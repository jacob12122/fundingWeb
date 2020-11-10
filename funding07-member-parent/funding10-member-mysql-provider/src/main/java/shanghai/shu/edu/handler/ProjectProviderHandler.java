package shanghai.shu.edu.handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shanghai.shu.edu.entity.vo.DetailProjectVO;
import shanghai.shu.edu.entity.vo.PortalTypeVO;
import shanghai.shu.edu.entity.vo.ProjectVO;
import shanghai.shu.edu.service.api.ProjectService;
import shanghai.shu.edu.util.ResultEntity;

import java.util.List;

@RestController
public class ProjectProviderHandler {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/save/project/vo/remote")
	public ResultEntity<String> saveProjectVORemote(
			@RequestBody ProjectVO projectVO,
			@RequestParam("memberId") Integer memberId) {
		
		try {
			projectService.saveProject(projectVO, memberId);
			
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			e.printStackTrace();
			
			return ResultEntity.failed(e.getMessage());
		}
	}
	@RequestMapping("/get/portal/type/project/data/remote")
	public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote() {

		try {
			List<PortalTypeVO> portalTypeVOList = projectService.getPortalTypeVO();

			return ResultEntity.successWithData(portalTypeVOList);
		} catch (Exception e) {
			e.printStackTrace();

			return ResultEntity.failed(e.getMessage());
		}

	}
	@RequestMapping("/get/project/detail/remote/{projectId}")
	public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId) {

		try {
			DetailProjectVO detailProjectVO = projectService.getDetailProjectVO(projectId);

			return ResultEntity.successWithData(detailProjectVO);

		} catch (Exception e) {
			e.printStackTrace();

			return ResultEntity.failed(e.getMessage());
		}

	}
}
