package cn.jjxx.modules.sys.service.impl;

import java.util.List;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.StringUtils;
import cn.jjxx.modules.sys.mapper.AppVersionMapper;
import cn.jjxx.modules.sys.entity.AppVersion;
import cn.jjxx.modules.sys.service.IAppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: App版本管理
 * @Description: App版本管理
 * @author jjxx
 * @date 2018-01-12 23:40:56
 * @version V1.0   
 *
 */
@Transactional
@Service("appVersionService")
public class AppVersionServiceImpl  extends CommonServiceImpl<AppVersionMapper,AppVersion> implements  IAppVersionService {

	@Override
	@Transactional
	public boolean insert(AppVersion entity) {
		updateOrther(entity);
		return super.insert(entity);
	}
	
	@Override
	@Transactional
	public boolean insertOrUpdate(AppVersion entity) {
		updateOrther(entity);
		return super.insertOrUpdate(entity);
	}
	
	private void updateOrther(AppVersion entity){
		if(StringUtils.equals(entity.getVersionStatus(),String.valueOf(1))){
			//更新原来所有的为否
			EntityWrapper<AppVersion> wrapper = new EntityWrapper<AppVersion>();
			wrapper.eq("type", entity.getType());
			List<AppVersion> list = super.selectList(wrapper);
			for(AppVersion app:list){
				app.setVersionStatus(String.valueOf(0));
			}
			super.updateBatchById(list);
		}
	}
}
