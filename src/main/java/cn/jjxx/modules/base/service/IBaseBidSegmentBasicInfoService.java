package cn.jjxx.modules.base.service;

import cn.jjxx.core.common.service.ICommonService;
import cn.jjxx.modules.base.entity.BaseBidSegmentBasicInfo;

/**   
 * @Title: base_bid_segment_basic_info
 * @Description: base_bid_segment_basic_info
 * @author jjxx
 * @date 2018-03-27 13:43:43
 * @version V1.0   
 *
 */
public interface IBaseBidSegmentBasicInfoService extends ICommonService<BaseBidSegmentBasicInfo> {
    boolean checkOnlyByOrgId(String orgId);
}

