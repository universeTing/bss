package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsQualityProjectSummaryMapper;
import cn.jjxx.modules.qs.entity.QsQualityProjectSummary;
import cn.jjxx.modules.qs.service.IQsQualityProjectSummaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: qs_quality_project_summary
 * @Description: qs_quality_project_summary
 * @author jjxx
 * @date 2018-04-03 11:20:01
 * @version V1.0   
 *
 */
@Transactional
@Service("qsQualityProjectSummaryService")
public class QsQualityProjectSummaryServiceImpl  extends CommonServiceImpl<QsQualityProjectSummaryMapper,QsQualityProjectSummary> implements  IQsQualityProjectSummaryService {

}
