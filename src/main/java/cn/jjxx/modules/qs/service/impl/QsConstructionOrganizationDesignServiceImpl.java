package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsConstructionOrganizationDesignMapper;
import cn.jjxx.modules.qs.entity.QsConstructionOrganizationDesign;
import cn.jjxx.modules.qs.service.IQsConstructionOrganizationDesignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**   
 * @Title: 施工组织设计
 * @Description: 施工组织设计
 * @author jjxx
 * @date 2018-03-07 11:03:57
 * @version V1.0   
 *
 */
@Transactional
@Service("qsConstructionOrganizationDesignService")
public class QsConstructionOrganizationDesignServiceImpl  extends CommonServiceImpl<QsConstructionOrganizationDesignMapper,QsConstructionOrganizationDesign> implements  IQsConstructionOrganizationDesignService {
	
	@Override
	public Page<QsConstructionOrganizationDesign> selectPage(Page<QsConstructionOrganizationDesign> page,
			Wrapper<QsConstructionOrganizationDesign> wrapper) {
		// TODO Auto-generated method stub
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectDataList(page, wrapper));
		return page;
	}
	

}
