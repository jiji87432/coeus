package com.pay.coeus.portal.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pay.coeus.api.inner.bean.ReturnUtil;
import com.pay.coeus.api.inner.dubbo.NewOrLossCustomerFacade;
import com.pay.coeus.common.utils.StringUtils;
import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.coeus.portal.util.ExcelExportUtil;
import com.pay.coeus.portal.util.ExcelExportUtil.CellMap;
import com.pay.commons.utils.Page;

@Controller
@RequestMapping("/newCustomer")
public class NewCustomerActivateController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewCustomerActivateController.class);
	
	
	@Resource
	private NewOrLossCustomerFacade newOrLossCustomerFacade;
	
	
	@RequestMapping("/toNewCustomerQuery.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("newCustomerActivate/queryNewCustomer");
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/findNewCustomer.action")
	@ResponseBody
	public String findAllNewCustomer(HttpServletRequest request,@RequestParam Map<String,String> param){
		try {
			logger.info("参数校验日志:{}",param.toString());
			Page<List<NewCustomerActivate>> page = null;
			page = (Page<List<NewCustomerActivate>> )request.getAttribute("page");
			logger.info("NewCustomerActivateController/findNewCustomer传入参数:{}",param==null?"空":param.toString());
			Page<List<NewCustomerActivate>> list = newOrLossCustomerFacade.findAllNewCustomerList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("findNewCustomer error", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
	}
	

	@RequestMapping("exportNewCustomer.action")
	public void findOldCustomer(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String,String> param){
		try {
			logger.info(response.toString());
			export(response,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void export(HttpServletResponse response,Map<String,String> param) throws Exception{
		String excelName="激活留存数据结果";
	 	List<CellMap> cellMapList =new ArrayList<CellMap>();
	 	cellMapList.add(new CellMap("商户编号", "customerNo"));
	 	cellMapList.add(new CellMap("商户名称", "customerName"));
	 	cellMapList.add(new CellMap("联系人", "linkMan"));
	 	cellMapList.add(new CellMap("商户等级", "customerLevel"));
	 	cellMapList.add(new CellMap("商户状态", "customerStatus"));
	 	cellMapList.add(new CellMap("是否真实", "customerCheckInfo"));
	 	cellMapList.add(new CellMap("绑定时间", "bindTime"));
	 	cellMapList.add(new CellMap("激活时间", "activateTime"));
	 	cellMapList.add(new CellMap("结果类型", "resultType"));
	 	cellMapList.add(new CellMap("创建时间", "createTime"));
	 	cellMapList.add(new CellMap("是否交易", "isTrans"));
	 	cellMapList.add(new CellMap("交易时间", "transTime"));
	 	cellMapList.add(new CellMap("POS数量", "posCount"));
	 	logger.info("导出报表开始-read write data into excel begin:"+new Date());  
        //参数校验  
        if (response==null||cellMapList.isEmpty())  
        {  
            logger.error("cellMapList is empty");  
            throw new Exception("cellMapList不能为空或小于等于0");  
        }  
        if (StringUtils.isBlank(excelName))  
        {  
            excelName = UUID.randomUUID().toString();  
        }  
          
        SXSSFWorkbook workbook = new SXSSFWorkbook(5000);  
        workbook.setCompressTempFiles(true);  
        Sheet sheet = workbook.createSheet();  
        Row row = null;  
        Cell cell = null;  
        int rowIndex = 0;  
        // header 标题  
        Font titleFont = workbook.createFont();  
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        CellStyle titleCellStyle = workbook.createCellStyle();  
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        titleCellStyle.setFont(titleFont);  
        row = sheet.createRow(rowIndex++);  
        // 数据列数  
        int cellSize = cellMapList.size();  
        for (int i = 0; i < cellSize; i++) {  
            CellMap cellMap = cellMapList.get(i);  
            String title = cellMap.getTitle();  
            cell = row.createCell(i);  
            cell.setCellStyle(titleCellStyle);  
            cell.setCellValue(title);  
            if (title != null) {  
                sheet.setColumnWidth(i, title.getBytes().length * 2 * 234);  
            }  
        }  
        
        //body 数据查询开始  
        //初始化第一页数据  
        Page<List<NewCustomerActivate>> page = new Page<>();
		page.setCurrentPage(1);
		page.setShowCount(10000);
		page = newOrLossCustomerFacade.findAllNewCustomerList(page, param);
		List<NewCustomerActivate> res = page.getObject();
		if(res != null && res.size()>0){
			for(int j=0;j<res.size();j++){
				try{
					String resultType = res.get(j).getResultType();
					if("NO_ACTIVATE".equals(resultType)){
						res.get(j).setResultType("未激活");
					} else if ("NO_REMAIN".equals(resultType)){
						res.get(j).setResultType("未留存");
					}
				}catch(Exception e){
					logger.error("createExcel error",e);
				}
			}
		}
        logger.info("导出报表-read write data into excel count:"+res.size()+"，page："+page.toString());  

        
        if(res != null && res.size()>0){
			  //详细数据样式  
          CellStyle dataCellStyle = workbook.createCellStyle();  
          dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          rowIndex = ExcelExportUtil.fillExcel_2007_SXSSF(dataCellStyle,cellSize,sheet, rowIndex, workbook, response, excelName, cellMapList, res);  
          if(page.getTotalPage()>1){
          	for(int i=2;i<=page.getTotalPage();i++){
          		page.setCurrentPage(i);
          		res.clear();
          		res = newOrLossCustomerFacade.findAllNewCustomerList(page, param).getObject();
          		if(res != null && res.size()>0){
          			for(int j=0;j<res.size();j++){
        				try{
        					String resultType = res.get(j).getResultType();
        					if("NO_ACTIVATE".equals(resultType)){
        						res.get(j).setResultType("未激活");
        					} else if ("NO_REMAIN".equals(resultType)){
        						res.get(j).setResultType("未留存");
        					}
        				}catch(Exception e){
        					logger.error("createExcel error",e);
        				}
        			}
      			}
  	            rowIndex = ExcelExportUtil.fillExcel_2007_SXSSF(dataCellStyle,cellSize,sheet, rowIndex, workbook, response, excelName, cellMapList, res);  
  		        logger.info("导出报表-read write data into excel count:"+i+"已处理完成，page："+page.toString());  
          	}
          }
		}
        response.setContentType("application/msexcel;charset=UTF-8");  
        response.setHeader("Content-Disposition", "attachment;filename="  
            + new String(excelName.getBytes("UTF-8"),  
                "ISO-8859-1") + ".xlsx");  
        OutputStream out = response.getOutputStream();  
        workbook.write(out);  
        out.close();  
        workbook.dispose();  
        logger.info("导出报表结束-read write data into excel end:"+new Date());  
	}
	
//	@RequestMapping("/flowDetail.action")
//	public ModelAndView flowDetail(HttpServletRequest request,@RequestParam String customerNo){
//		ModelAndView model = new ModelAndView();
//		Page<List<Map<String,String>>> page = new Page<List<Map<String,String>>>();;
//		page.setCurrentPage(request.getParameter("currentPage")==null?1:Integer.parseInt(request.getParameter("currentPage")));
//		page = newOrLossCustomerFacade.findAllCustomerNoFlowDetailByCustomerNo(page, customerNo);
//		model.setViewName("newCustomerActivate/flowDetail");
//		request.setAttribute("flows", page.getObject());
//		request.setAttribute("page", page);
//		return model;
//	}
//	
	
}
