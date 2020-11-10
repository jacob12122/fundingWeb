package shanghai.shu.edu.service.api;


import shanghai.shu.edu.entity.Menu;

import java.util.List;

public interface MenuService {
	
	List<Menu> getAll();

	void saveMenu(Menu menu);

	void updateMenu(Menu menu);

	void removeMenu(Integer id);

}
