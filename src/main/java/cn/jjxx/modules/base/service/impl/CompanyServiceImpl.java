package cn.jjxx.modules.base.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.modules.base.mapper.CompanyMapper;
import cn.jjxx.modules.base.entity.Company;
import cn.jjxx.modules.base.service.ICompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Title: 公司资料
 * @Description: 公司资料
 * @author jjxx
 * @date 2018-02-07 17:14:40
 * @version V1.0   
 *
 */
@Transactional
@Service("companyService")
public class CompanyServiceImpl  extends CommonServiceImpl<CompanyMapper,Company> implements  ICompanyService {

}
