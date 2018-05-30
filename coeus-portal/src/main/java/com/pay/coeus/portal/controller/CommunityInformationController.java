package com.pay.coeus.portal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pay.coeus.api.inner.bean.ReturnUtil;
import com.pay.coeus.api.inner.dubbo.CommunityInformationFacade;
import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.coeus.portal.util.ExcelExportUtil;
import com.pay.coeus.portal.util.ExcelExportUtil.CellMap;
import com.pay.commons.utils.Page;
import com.pay.customer.buss.service.api.rate.facade.CustomerFeeAPIFacade;
import com.pay.customer.buss.service.common.rate.enums.BusinessCode;
import com.pay.customer.buss.service.common.rate.param.CustomerFeeParam;
import com.pay.customer.buss.service.common.rate.param.CustomerFeeQueryParam;
import com.pay.wx.bean.ReturnContent;
import com.pay.wx.dubbo.WxCustomerOuterServiceFacade;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 社区信息查询
 * TODO
 * @author kun.lv
 * @date 2017年12月11日 下午2:27:55
 * @version V1.0
 */
@Controller
@RequestMapping("/community")
public class CommunityInformationController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityInformationController.class);
	
	@Autowired
	private CommunityInformationFacade communityInformationFacade;
	
	@Autowired
	private CustomerFeeAPIFacade customerFeeAPIFacade;
	
	@Autowired
	private WxCustomerOuterServiceFacade wxCustomerOuterServiceFacade;
	
	// 挑转到社区信息查询页面
		@RequestMapping("/communityQuery.action")
		public ModelAndView testCon(){
			ModelAndView model = new ModelAndView();
			model.setViewName("community/communityQuery");
			return model;
		}
	
	@RequestMapping(value="/communityInformationQuery.action",method = { RequestMethod.GET, RequestMethod.POST })
	public void communityInformationQuery(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "uploadCustomerNo") MultipartFile uploadCustomerNo)throws Exception{
		
		//解析文档
		List<String> listcustomerNo = getCustomerNo(uploadCustomerNo, "");
		logger.info("import excel size:"+listcustomerNo.size());
		String orderTime = request.getParameter("ordertime");//交易年月
		logger.info("查询参数："+orderTime);

		
		List<Map<String, String>> commList = new ArrayList<Map<String,String>>();
		//批量查询
		for (int i = 0; i < listcustomerNo.size(); i++) {
			Map<String, String> queryCust = queryCust(listcustomerNo.get(i), orderTime);
			if(queryCust != null){
		      commList.add(queryCust);
			}else {
				queryCust = new HashMap<String, String>();
				queryCust.put("CUSTOMERNO", listcustomerNo.get(i));
				commList.add(queryCust);
			}
		}
		exportExcel(response, commList);
	}
	
	@RequestMapping(value="/communityQueryByCustNo.action",method = { RequestMethod.GET, RequestMethod.POST })
	public String communityQueryByCustNo(HttpServletRequest request,Model mode){
		List<Map<String, String>> commList = new ArrayList<Map<String,String>>();
		String orderTime = request.getParameter("ordertime");//交易年月
		String customerNo = request.getParameter("customerNo");//单查商编
		logger.info("查询参数："+customerNo+"     "+orderTime);
		Map<String, String> queryCust = queryCust(customerNo, orderTime);
		if(queryCust != null){
			commList.add(queryCust);
			logger.info("*********************"+JSON.toJSONString(commList));
			mode.addAttribute("commList",commList);
			return "community/detail";
			}
		mode.addAttribute("commList",null);
		return "community/detail";
	}
	
	/**
	 * 调接口查信息 
	 * 商户编号	商户名称	借记卡费率	贷记卡费率	商户状态	真实性	机具类型	商户等级	服务商编号	服务商名称	卡友APP绑定	是否绑定公众号	月交易额
	 */
	public Map<String, String> queryCust(String customerNo,String orderTime){
		Map<String, String> communityInformationQuery = communityInformationFacade.CommunityInformationQuery(customerNo, orderTime);
		logger.info("自写接口查询结果："+communityInformationQuery);
		if(communityInformationQuery != null){
			try {
				//查询是否绑定公众号
				ReturnContent<Object> findWxCustRelationByCustomerNo = wxCustomerOuterServiceFacade.findWxCustRelationByCustomerNo(customerNo);
				logger.info("调公众号返回结果："+findWxCustRelationByCustomerNo.toString());
				if (findWxCustRelationByCustomerNo.getCode() != null) {
					if ("0000".equals(findWxCustRelationByCustomerNo.getCode()) && "BINDING".equals(findWxCustRelationByCustomerNo.getData())) {
						communityInformationQuery.put("wx", "已绑定");
					}else {
						communityInformationQuery.put("wx", "未绑定");
						logger.info("返回信息："+findWxCustRelationByCustomerNo.toString());
					}
				}else {
					logger.info("返回结果无结果：" + findWxCustRelationByCustomerNo.toString());
				}
			} catch (Exception e) {
				logger.error("接口调用异常", e);
				communityInformationQuery.put("wx", "查询错误");
			}
			
			
			try {
				//查询是否绑定过APP
				boolean isAppBinding = wxCustomerOuterServiceFacade.findAppCustRelationByCustomerNo(customerNo);
				logger.info("APP接口查询结果："+isAppBinding);
				if(isAppBinding){
					communityInformationQuery.put("app", "是");
				}else {
					communityInformationQuery.put("app", "否");
				}
			} catch (Exception e) {
				logger.error("接口调用异常", e);
				communityInformationQuery.put("app", "查询错误");
			}
			
			
			
			// 加载费率信息 #4
		      CustomerFeeQueryParam queryParam = new CustomerFeeQueryParam();
		      long currentTime = System.currentTimeMillis();
		      currentTime += 2 * 60 * 1000;
		      queryParam.setQueryDate(new Date(currentTime));
		      queryParam.setCustomerNo(customerNo);
		      queryParam.setBusinessCode(BusinessCode.DEFAULT.getCode());
		      List<CustomerFeeParam> queryCustFee = null;
			try {
				queryCustFee = customerFeeAPIFacade.queryCustFee(queryParam);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("接口调用异常", e);
				communityInformationQuery.put("debitcard","查询错误");
				communityInformationQuery.put("creditcard","查询错误");
			}
		      logger.info("查费率接口结果："+queryCustFee);
		      if(null != queryCustFee){
		    	  for (CustomerFeeParam customerFeeParam : queryCustFee) {
			    	  //借记卡费率
			    	  if("DEBIT_CARD".equals(customerFeeParam.getPayModeBean().getCardType().toString())){
			    		  communityInformationQuery.put("debitcard",customerFeeParam.getComputeRateBean().getRate().toString());
			    	  }
			    	  //贷记卡费率
			    	  if("CREDIT_CARD".equals(customerFeeParam.getPayModeBean().getCardType().toString())){
			    		  communityInformationQuery.put("creditcard",customerFeeParam.getComputeRateBean().getRate().toString());
			    	  }
				}
		      }
		      return communityInformationQuery;
		}
		return null;
	}
	
	/**
	 * 生成EXCEL并导出
	 */
	
	public void exportExcel(HttpServletResponse response,List<Map<String, String>> commList)throws Exception{
		logger.info("准备生成EXCEL"+commList);
		String excelName="社群信息查询";
	 	List<CellMap> cellMapList =new ArrayList<CellMap>();
	 	cellMapList.add(new CellMap("商户编号", "CUSTOMERNO"));
	 	cellMapList.add(new CellMap("商户名称", "CUSTOMERFULLNAME"));
	 	cellMapList.add(new CellMap("借记卡费率", "debitcard"));
	 	cellMapList.add(new CellMap("贷记卡费率", "creditcard"));
	 	cellMapList.add(new CellMap("商户状态", "STATUS"));
	 	cellMapList.add(new CellMap("真实性", "REALYTYPE"));
	 	cellMapList.add(new CellMap("是否大POS商户", "isPos"));
	 	cellMapList.add(new CellMap("商户等级", "CUSTOMERLEVEL"));
	 	cellMapList.add(new CellMap("服务商编号", "AGENTNO"));
	 	cellMapList.add(new CellMap("服务商名称", "AGENTFULLNAME"));
	 	cellMapList.add(new CellMap("卡友APP绑定", "app"));
	 	cellMapList.add(new CellMap("是否绑定公众号", "wx"));
	 	cellMapList.add(new CellMap("月交易额", "sumamount"));
	 	logger.info("导出报表开始-read write data into excel begin:"+new Date()); 
		
	 	//参数校验  
        if (response==null||cellMapList.isEmpty()){  
            logger.error("cellMapList is empty");  
            throw new Exception("cellMapList不能为空或小于等于0");  
        }  
        if (StringUtils.isBlank(excelName)){  
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
        
        
        Page<List<OldCustomerLoss>> page = new Page<>();
		page.setCurrentPage(1);
		page.setShowCount(10000);

		if(commList != null && commList.size()>0){
			  //详细数据样式  
        CellStyle dataCellStyle = workbook.createCellStyle();  
        dataCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        rowIndex = ExcelExportUtil.fillExcel_2007_SXSSF(dataCellStyle,cellSize,sheet, rowIndex, workbook, response, excelName, cellMapList, commList);  
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
	
	/**
	 * 解析文档
	 */
	public List<String> getCustomerNo(MultipartFile multipartFile, String filename) {
		Workbook workBook = null;
		try {
			workBook = createWorkBook(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = workBook.getSheetAt(0);// 获取上传的excel表格的第一个表格实体
		List<String> testList = new ArrayList<String>();// 一个sheet页
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {

			Row row = sheet.getRow(rowNum);
			if (row == null) {
				break;
			}
			if (row.getCell(0) == null) {
				break;
			}

			if (row.getCell(0) != null) {
				if (row.getCell(0) != null && StringUtils.isNotEmpty(row.getCell(0).toString())) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					String customerNo = row.getCell(0).getStringCellValue().trim();
					if (StringUtils.isNotEmpty(customerNo)) {
						testList.add(customerNo);
					}
				}
			}

		}
		return testList;
	}
	
	public Workbook createWorkBook(InputStream stream, String fileName) throws IOException {
		if (fileName.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(stream);
		}
		if (fileName.toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(stream);
		}
		return null;
	}

	
}
