package cn.jjxx.modules.base.service;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.base.entity.BaseProjectBasicInfo;

/**   
 * @Title: base_project_basic_info
 * @Description: base_project_basic_info
 * @author jjxx
 * @date 2018-03-23 13:50:40
 * @version V1.0   
 *
 */
public interface IBaseProjectBasicInfoService extends ICommonService<BaseProjectBasicInfo> {

    /**
     * 检查当前组织下是否存在数据，末级组织只允许有一条数据
     * @param orgId
     * @return
     */
    boolean checkOnlyByOrgId(String orgId);
}

