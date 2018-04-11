package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsMonthlyMeetingSummaryMapper;
import cn.jjxx.modules.qs.entity.QsMonthlyMeetingSummary;
import cn.jjxx.modules.qs.service.IQsMonthlyMeetingSummaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 质量月例会会议纪要
 * @Description: 质量月例会会议纪要
 * @author jjxx
 * @date 2018-03-20 11:46:49
 * @version V1.0   
 *
 */
@Transactional
@Service("qsMonthlyMeetingSummaryService")
public class QsMonthlyMeetingSummaryServiceImpl  extends CommonServiceImpl<QsMonthlyMeetingSummaryMapper,QsMonthlyMeetingSummary> implements  IQsMonthlyMeetingSummaryService {

}
