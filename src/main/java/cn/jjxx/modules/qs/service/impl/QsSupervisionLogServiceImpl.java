package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.qs.mapper.QsSupervisionLogMapper;
import cn.jjxx.modules.qs.entity.QsSupervisionLog;
import cn.jjxx.modules.qs.service.IQsSupervisionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 监理日志
 * @Description: 监理日志
 * @author jjxx
 * @date 2018-03-30 10:36:58
 * @version V1.0   
 *
 */
@Transactional
@Service("qsSupervisionLogService")
public class QsSupervisionLogServiceImpl  extends CommonServiceImpl<QsSupervisionLogMapper,QsSupervisionLog> implements  IQsSupervisionLogService {

}
