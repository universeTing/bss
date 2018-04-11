package cn.jjxx.modules.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import org.framework.customutil.Convert;
import org.framework.customutil.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jjxx.core.utils.JdbcUtil;
import cn.jjxx.core.utils.MessageUtils;
import cn.jjxx.core.utils.ObjectUtils;
import cn.jjxx.core.utils.report.ReportFileType;
import cn.jjxx.core.utils.report.ReportUtils;

@Controller
@RequestMapping("/report")
public class ReportController {

	private static final String REPORT_PATH = "/report/";
	private static String driver = "jdbc.driver.jeecg";
	private static String url = "jdbc.url.jeecg";
	private static String username = "jdbc.username.jeecg";
	private static String password = "jdbc.password.jeecg";

	/**
	 * @功能: html版本的报表 .</br>
	 * @参数 @param request
	 * @参数 @param response
	 * @作者 郑成功 .</br>
	 * @日期 2016-12-5 .</br>
	 */
	@RequestMapping(params = "htmlReport")
	public void reports(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reportName = request.getParameter("reportName");
		String path = request.getRealPath("/");
		JdbcUtil db = new JdbcUtil(MessageUtils.getMessage(driver),MessageUtils.getMessage(url),MessageUtils.getMessage(username),MessageUtils.getMessage(password));
		Map<String,Object> params= Convert.getMapByReq(request);
		try {
			JasperCompileManager.compileReportToFile(path+REPORT_PATH+reportName+".jrxml", path+REPORT_PATH+reportName+".jasper");
			String filePath = JasperFillManager.fillReportToFile(path+REPORT_PATH+reportName+".jasper",params);
            ReportUtils.reportShow(response, params, filePath, db);
		} catch (JRException e) {
			e.printStackTrace();
		}
		db.close();
	}
	
	/**
	 * @功能: pdf版本的报表 .</br>
	 * @参数 @param request
	 * @参数 @param response
	 * @作者 郑成功 .</br>
	 * @日期 2016-12-5 .</br>
	 */
	@RequestMapping(params = "pdfReport")
	public void reportspef(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String reportName = request.getParameter("reportName");
		String path = request.getRealPath("/");
		JdbcUtil db = new JdbcUtil(MessageUtils.getMessage(driver),MessageUtils.getMessage(url),MessageUtils.getMessage(username),MessageUtils.getMessage(password));
		Map<String,Object> params= new HashMap<String, Object>();
		try {
			JasperCompileManager.compileReportToFile(path+REPORT_PATH+reportName+".jrxml", path+REPORT_PATH+reportName+".jasper");
			String filePath = JasperFillManager.fillReportToFile(path+REPORT_PATH+reportName+".jasper",params);
            ReportUtils.reportExport(response, params, filePath, reportName, ReportFileType.pdf, db);
		} catch (JRException e) {
			e.printStackTrace();
		}
		db.close();
	}
	
	/**
	 * @功能: excle版本的报表 .</br>
	 * @参数 @param request
	 * @参数 @param response
	 * @作者 郑成功 .</br>
	 * @日期 2016-12-5 .</br>
	 */
	@RequestMapping(params = "excleReport")
	public void reportsexcel(HttpServletRequest request,HttpServletResponse response) throws IOException{		
		String reportName = request.getParameter("reportName");
		String path = request.getRealPath("/");
		JdbcUtil db = new JdbcUtil(MessageUtils.getMessage(driver),MessageUtils.getMessage(url),MessageUtils.getMessage(username),MessageUtils.getMessage(password));
		Map<String,Object> params= new HashMap<String, Object>();
		try {
			JasperCompileManager.compileReportToFile(path+REPORT_PATH+reportName+".jrxml", path+REPORT_PATH+reportName+".jasper");
			String filePath = JasperFillManager.fillReportToFile(path+REPORT_PATH+reportName+".jasper",params);
            ReportUtils.reportExport(response, params, filePath, reportName, ReportFileType.excel, db);
		} catch (JRException e) {
			e.printStackTrace();
		}
		db.close();
	}
	
	/**
	 * @功能: 打印报表 .</br>
	 * @参数 @param request
	 * @参数 @param response
	 * @参数 @param list
	 * @参数 @param map
	 * @参数 @param viewname
	 * @参数 @throws IOException .</br>  
	 * @作者 郑成功 .</br>
	 * @日期 2017-4-1 .</br>
	 */
//	public static void reportsprint(HttpServletRequest request,HttpServletResponse response,
//			List<?> list,Map<String,Object> map,String viewname) throws IOException{	
//		String path = request.getRealPath("/");	
//		String reportName = request.getParameter("reportName");
//		try {
//			//JasperCompileManager.compileReportToFile(path+REPORT_PATH+viewname+".jrxml", path+REPORT_PATH+viewname+".jasper");
//			String sourfile=path+REPORT_PATH+viewname+".jasper";
//			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
//			JasperPrint print = JasperFillManager.fillReport(sourfile, map, beanColDataSource);									
//			
//			//直接调用服务器端的打印
//			JasperViewer view = new JasperViewer(print,true);
//			view.setVisible(true);				
//			JasperPrintManager.printReport(print, true);			
//			/**转成pdf文件输出*/
//			response.setContentType("application/pdf");	
//			String fileName = DateUtils.date2Str(DateUtils.getDate(), DateUtils.yyyymmddhhmmss);
//			response.setHeader("Content-Disposition", "inline; filename=\""+fileName + ".pdf"+ "\"");
//			ServletOutputStream ouputStream = response.getOutputStream();
//			JRAbstractExporter exporter = new JRPdfExporter();
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
//			exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
//			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
//			exporter.exportReport();			
//			final String CONTENTTYPE ="application/octet-stream"; 
//			response.setContentType(CONTENTTYPE);
//			//ServletOutputStream ouputStream = response.getOutputStream(); 
//			ObjectOutputStream oos = new ObjectOutputStream(ouputStream); 
//			oos.writeObject(print);
//			oos.flush();
//			oos.close();
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//		finally{	
//		}
//	}
	
	
}