package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsQualityTrainingEducationMapper;
import cn.jjxx.modules.qs.entity.QsQualityTrainingEducation;
import cn.jjxx.modules.qs.service.IQsQualityTrainingEducationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 质量培训教育
 * @Description: 质量培训教育
 * @author jjxx
 * @date 2018-03-21 11:27:31
 * @version V1.0   
 *
 */
@Transactional
@Service("qsQualityTrainingEducationService")
public class QsQualityTrainingEducationServiceImpl  extends CommonServiceImpl<QsQualityTrainingEducationMapper,QsQualityTrainingEducation> implements  IQsQualityTrainingEducationService {

}
