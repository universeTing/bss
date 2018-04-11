package cn.jjxx.modules.qs.service.impl;

import cn.jjxx.core.common.service.impl.CommonServiceImpl;
import cn.jjxx.core.query.wrapper.EntityWrapper;
import cn.jjxx.modules.qs.mapper.QsResponsibilityRegMapper;
import cn.jjxx.modules.qs.entity.QsResponsibilityReg;
import cn.jjxx.modules.qs.service.IQsResponsibilityRegService;
import cn.jjxx.modules.qs.entity.QsResponsibilityRegDetails;
import cn.jjxx.modules.qs.service.IQsResponsibilityRegDetailsService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.jjxx.core.utils.ServletUtils;
import cn.jjxx.core.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;

/**   
 * @Title: 质量责任登记表
 * @Description: 质量责任登记表
 * @author jjxx
 * @date 2018-03-21 10:35:46
 * @version V1.0   
 *
 */
@Transactional
@Service("qsResponsibilityRegService")
public class QsResponsibilityRegServiceImpl  extends CommonServiceImpl<QsResponsibilityRegMapper,QsResponsibilityReg> implements  IQsResponsibilityRegService {
	@Autowired
	private IQsResponsibilityRegDetailsService qsResponsibilityRegDetailsService;
	
	@Override
	public boolean insert(QsResponsibilityReg qsResponsibilityReg) {
		// 保存主表
		super.insert(qsResponsibilityReg);
		// 保存质量责任登记表详情
		String qsResponsibilityRegDetailsListJson = StringEscapeUtils
				.unescapeHtml4(ServletUtils.getRequest().getParameter("qsResponsibilityRegDetailsListJson"));
		List<QsResponsibilityRegDetails> qsResponsibilityRegDetailsList = JSONObject.parseArray(qsResponsibilityRegDetailsListJson, QsResponsibilityRegDetails.class);
		for (QsResponsibilityRegDetails qsResponsibilityRegDetails : qsResponsibilityRegDetailsList) {
			// 保存字段列表
			qsResponsibilityRegDetails.setQsResponsibilityReg(qsResponsibilityReg);
			qsResponsibilityRegDetailsService.insert(qsResponsibilityRegDetails);
		}
		return true;
	}
	
	@Override
	public boolean insertOrUpdate(QsResponsibilityReg qsResponsibilityReg) {
		try {
			// 获得以前的数据
			List<QsResponsibilityRegDetails> oldQsResponsibilityRegDetailsList = qsResponsibilityRegDetailsService.selectList(new EntityWrapper<QsResponsibilityRegDetails>(QsResponsibilityRegDetails.class).eq("qsResponsibilityReg.id",qsResponsibilityReg.getId()));
			// 字段
			String qsResponsibilityRegDetailsListJson = StringEscapeUtils
					.unescapeHtml4(ServletUtils.getRequest().getParameter("qsResponsibilityRegDetailsListJson"));
			List<QsResponsibilityRegDetails> qsResponsibilityRegDetailsList = JSONObject.parseArray(qsResponsibilityRegDetailsListJson,
					QsResponsibilityRegDetails.class);
			// 更新主表
			super.insertOrUpdate(qsResponsibilityReg);
			qsResponsibilityRegDetailsList = JSONObject.parseArray(qsResponsibilityRegDetailsListJson, QsResponsibilityRegDetails.class);
			List<String> newsQsResponsibilityRegDetailsIdList = new ArrayList<String>();
			// 保存或更新数据
			for (QsResponsibilityRegDetails qsResponsibilityRegDetails : qsResponsibilityRegDetailsList) {
				// 设置不变更的字段
				if (StringUtils.isEmpty(qsResponsibilityRegDetails.getId())) {
					// 保存字段列表
					qsResponsibilityRegDetails.setQsResponsibilityReg(qsResponsibilityReg);
					qsResponsibilityRegDetailsService.insert(qsResponsibilityRegDetails);
				} else {
					qsResponsibilityRegDetailsService.insertOrUpdate(qsResponsibilityRegDetails);
				}
				newsQsResponsibilityRegDetailsIdList.add(qsResponsibilityRegDetails.getId());
			}

			// 删除老数据
			for (QsResponsibilityRegDetails qsResponsibilityRegDetails : oldQsResponsibilityRegDetailsList) {
				String qsResponsibilityRegDetailsId = qsResponsibilityRegDetails.getId();
				if (!newsQsResponsibilityRegDetailsIdList.contains(qsResponsibilityRegDetailsId)) {
					qsResponsibilityRegDetailsService.deleteById(qsResponsibilityRegDetailsId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}

	@Override
	public Page<QsResponsibilityReg> selectPage(Page<QsResponsibilityReg> page, Wrapper<QsResponsibilityReg> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectPage(page, wrapper));
		return page;
	}
}
