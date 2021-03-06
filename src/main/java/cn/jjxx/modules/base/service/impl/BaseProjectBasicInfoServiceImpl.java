package cn.jjxx.modules.base.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.modules.base.mapper.BaseProjectBasicInfoMapper;
import cn.jjxx.modules.base.entity.BaseProjectBasicInfo;
import cn.jjxx.modules.base.service.IBaseProjectBasicInfoService;
import cn.jjxx.modules.sys.entity.Organization;
import cn.jjxx.modules.sys.service.IOrganizationService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Title: base_project_basic_info
 * @Description: base_project_basic_info
 * @author jjxx
 * @date 2018-03-23 13:50:40
 * @version V1.0   
 *
 */
@Transactional
@Service("baseProjectBasicInfoService")
public class BaseProjectBasicInfoServiceImpl  extends CommonServiceImpl<BaseProjectBasicInfoMapper,BaseProjectBasicInfo> implements  IBaseProjectBasicInfoService {

    @Autowired
    protected IOrganizationService iOrganizationService;

    @Override
    public boolean checkOnlyByOrgId(String orgId) {
        Organization top = iOrganizationService.selectById(orgId);
        if (top != null) {
            if (org.apache.commons.lang3.StringUtils.isBlank(top.getParentId())) {// 1. 本身就是最大级组织，直接通过
                return true;
            } else {
                // 若本身不是最末级
                Map<String, Object> columnMap = new HashMap<String,Object>();
                columnMap.put("parent_id", top.getId());
                List<Organization> middleOrgs = iOrganizationService.selectByMap(columnMap);// 查询是否有子组织
                if (ObjectUtils.isNullOrEmpty(middleOrgs)) {// 2.1 没有子组织，即为末级组织
                    int count = super.selectCount(new EntityWrapper<BaseProjectBasicInfo>(BaseProjectBasicInfo.class).eq("orgId", orgId));
                    if (count > 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {// 2.2 有子组织，即为非末级节点，通过
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * 重写查询列表方法
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public Page<BaseProjectBasicInfo> selectPage(Page<BaseProjectBasicInfo> page, Wrapper<BaseProjectBasicInfo> wrapper) {
        wrapper.eq("1", "1");
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return page;
    }
}
