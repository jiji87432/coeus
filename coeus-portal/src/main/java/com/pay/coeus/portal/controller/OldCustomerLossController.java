package com.pay.coeus.portal.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pay.coeus.api.inner.bean.ReturnUtil;
import com.pay.coeus.api.inner.dubbo.NewOrLossCustomerFacade;
import com.pay.coeus.common.utils.StringUtils;
import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.coeus.portal.util.ExcelExportUtil;
import com.pay.coeus.portal.util.ExcelExportUtil.CellMap;
import com.pay.commons.utils.Page;

@Controller
@RequestMapping("/oldCustomer")
public class OldCustomerLossController {
private static final Logger logger = LoggerFactory.getLogger(OldCustomerLossController.class);
	
	
	@Autowired
	private NewOrLossCustomerFacade newOrLossCustomerFacade;
	
	
	@RequestMapping("/customeLossQuery.action")
	public ModelAndView testCon(){
		ModelAndView model = new ModelAndView();
		model.setViewName("OldCustomerLoss/queryOldCustomer");
		return model;
	}
	
	@RequestMapping("/findCustomerLoss.action")
	@ResponseBody
	public String findAllOldCustomer(HttpServletRequest request,@RequestParam Map<String,String> param){
		Page<List<OldCustomerLoss>> list;
		try {
			logger.info("params {}", param);
			Page<List<OldCustomerLoss>> page = (Page<List<OldCustomerLoss>>)request.getAttribute("page");
			System.out.println(request.getAttribute("page"));
			System.out.println(page);
			System.out.println(page.toString());
			list = newOrLossCustomerFacade.findAllOldCustomerList(page, param);
			return JSON.toJSONString(ReturnUtil.success(list));
		} catch (Exception e) {
			logger.error("findOldCustomer", e);
			return JSON.toJSONString(ReturnUtil.error(e));
		}
		
	}
	
	@RequestMapping("exportCustomerLoss.action")
	public void findOldCustomer(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String,String> param){
		try {
			logger.info(response.toString());
			export(response,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void export(HttpServletResponse response,Map<String,String> param) throws Exception{
		String excelName="流失数据结果";
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
	 	cellMapList.add(new CellMap("是否有流量", "isLoss"));
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
        Page<List<OldCustomerLoss>> page = new Page<>();
		page.setCurrentPage(1);
		page.setShowCount(10000);
		page = newOrLossCustomerFacade.findAllOldCustomerList(page, param);
		List<OldCustomerLoss> res = page.getObject();
		if(res != null && res.size()>0){
			for(int j=0;j<res.size();j++){
				try{
					String resultType = res.get(j).getResultType();
					if("LOSS".equals(resultType)){
						res.get(j).setResultType("流失");
					} else if ("SUSPECT_LOSS".equals(resultType)){
						res.get(j).setResultType("疑似流失");
					}
					String isloss = res.get(j).getIsLoss();
					if("Y".equals(isloss)){
						res.get(j).setIsLoss("有");
					}else{
						res.get(j).setIsLoss("无");
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
          		res = newOrLossCustomerFacade.findAllOldCustomerList(page, param).getObject();
          		if(res != null && res.size()>0){
          			for(int j=0;j<res.size();j++){
        				try{
        					String resultType = res.get(j).getResultType();
        					if("LOSS".equals(resultType)){
        						res.get(j).setResultType("流失");
        					} else if ("SUSPECT_LOSS".equals(resultType)){
        						res.get(j).setResultType("疑似流失");
        					}
        					String isloss = res.get(j).getIsLoss();
        					if("Y".equals(isloss)){
        						res.get(j).setIsLoss("有");
        					}else{
        						res.get(j).setIsLoss("无");
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
}
