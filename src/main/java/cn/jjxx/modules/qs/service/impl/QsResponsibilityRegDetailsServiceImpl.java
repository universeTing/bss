package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsResponsibilityRegDetailsMapper;
import cn.jjxx.modules.qs.entity.QsResponsibilityRegDetails;
import cn.jjxx.modules.qs.service.IQsResponsibilityRegDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 质量责任登记表详情
 * @Description: 质量责任登记表详情
 * @author jjxx
 * @date 2018-03-21 10:35:46
 * @version V1.0   
 *
 */
@Transactional
@Service("qsResponsibilityRegDetailsService")
public class QsResponsibilityRegDetailsServiceImpl  extends CommonServiceImpl<QsResponsibilityRegDetailsMapper,QsResponsibilityRegDetails> implements  IQsResponsibilityRegDetailsService {

}
