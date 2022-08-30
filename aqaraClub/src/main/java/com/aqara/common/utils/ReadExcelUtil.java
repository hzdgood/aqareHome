package com.aqara.common.utils;

import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.aqara.common.entity.*;

public class ReadExcelUtil {
	public static List<Scheme> ReadExcel(MultipartFile files) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Scheme> list = new ArrayList<Scheme>();
			String CustomerName = "";
			String telephone = "";
			for (int i = 2; i <= lastRowNum; i++) {
				Scheme scheme = new Scheme();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						String value = cell.getStringCellValue();
						if (j == 0) {
							scheme.setOrderNumber(value);
						} else if (j == 3) {
							CustomerName = value;
							scheme.setCustomerName(value);
						} else if (j == 4) {
							telephone = value;
							scheme.setTelephone(value);
						} else if (j == 5) {
							scheme.setProjectName(CustomerName + telephone);
						} else if (j == 12) {
							scheme.setProductName(value);
						} else if (j == 14) {
							value = value.replaceAll("\t", ""); // 空格不判断
							scheme.setProductCode(value);
						} else if (j == 15) {
							scheme.setNumber(value);
						} else if (j == 16) {
							scheme.setMoney(value);
						} else if (j == 20) {
							if (value.equals("") || value.equals("0.00")) {
								scheme.setYesNoFee("0");
								scheme.setServiceFee("0.00");
							} else {
								scheme.setYesNoFee("1");
								scheme.setServiceFee("0.15");
							}
						}
					}
				}
				list.add(scheme);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Customer> customerExcel(MultipartFile files) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Customer> list = new ArrayList<Customer>();
			for (int i = 1; i <= lastRowNum; i++) {
				Customer customer = new Customer();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null || cell.equals(null)) {
					} else {
						if (j == 1) {
							customer.setName(cell.getStringCellValue());
						} else if (j == 2) {
							customer.setTelephone(cell.getStringCellValue());
						} else if (j == 4) {
							customer.setSales(cell.getStringCellValue());
						} else if (j == 6) {
							customer.setDepartment(cell.getStringCellValue());
						} else if (j == 9) {
							customer.setSex(cell.getStringCellValue());
						} else if (j == 10) {
							customer.setSource(cell.getStringCellValue());
						} else if (j == 11) {
							customer.setHouseType(cell.getStringCellValue());
						} else if (j == 12) {
							customer.setHouseState(cell.getStringCellValue());
						} else if (j == 13) {
							customer.setPersonnelAttr(cell.getStringCellValue());
						} else if (j == 14) {
							customer.setCustomerAttr(cell.getStringCellValue());
						} else if (j == 15) {
							customer.setCustomerStage(cell.getStringCellValue());
						} else if (j == 16) {
							customer.setSPcustomer(cell.getStringCellValue());
						} else if (j == 17) {
							customer.setHouseDemand(cell.getStringCellValue());
						} else if (j == 18) {
							customer.setLossStatus(cell.getStringCellValue());
						} else if (j == 19) {
							customer.setWeiXin(cell.getStringCellValue());
						} else if (j == 26) {
							customer.setUserID(cell.getStringCellValue());
						} else if (j == 27) {
							customer.setReleWeixin(cell.getStringCellValue());
						} else if (j == 29) {
							customer.setCreateName(cell.getStringCellValue());
						} else if (j == 30) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							customer.setCreateTime(data);
						} else if (j == 31) {
							customer.setUpdateName(cell.getStringCellValue());
						} else if (j == 32) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							customer.setUpdateTime(data);
						}
					}
				}
				list.add(customer);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Quotation> quotationExcel(MultipartFile files) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Quotation> list = new ArrayList<Quotation>();
			for (int i = 1; i <= lastRowNum; i++) {
				Quotation Quotation = new Quotation();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null || cell.equals(null)) {
					} else {
						if (cell == null || cell.equals(null)) {

						} else {
							if (j == 1) {
								Quotation.setQuotationType(cell.getStringCellValue());
							} else if (j == 2) {
								Quotation.setQuotationID(cell.getStringCellValue());
							} else if (j == 3) {
								Quotation.setCode(cell.getStringCellValue());
							} else if (j == 15) {
								Quotation.setSchemeAmount(cell.getNumericCellValue() + "");
							} else if (j == 16) {
								Quotation.setPreferentialAmount(cell.getNumericCellValue() + "");
							} else if (j == 17) {
								Quotation.setAmountReceivable(cell.getNumericCellValue() + "");
							} else if (j == 18) {
								Quotation.setAmountReceived(cell.getNumericCellValue() + "");
							} else if (j == 19) {
								Quotation.setUncollectedAmount(cell.getNumericCellValue() + "");
							} else if (j == 20) {
								Quotation.setRemarks(cell.getStringCellValue());
							} else if (j == 28) {
								Quotation.setCreateName(cell.getStringCellValue());
							} else if (j == 29) {
								Date data = simpleDateFormat.parse(cell.getStringCellValue());
								Quotation.setCreateTime(data);
							} else if (j == 30) {
								Quotation.setUpdateName(cell.getStringCellValue());
							} else if (j == 31) {
								Date data = simpleDateFormat.parse(cell.getStringCellValue());
								Quotation.setUpdateTime(data);
							}
						}
					}
				}
				list.add(Quotation);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Survey> SurveyExcel(MultipartFile file) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Survey> list = new ArrayList<Survey>();
			for (int i = 1; i <= lastRowNum; i++) {
				Survey Survey = new Survey();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null || cell.equals(null)) {
						
					} else {
						if (j == 1) {
							Survey.setCode(cell.getStringCellValue());
						} else if (j == 4) {
							Survey.setSales(cell.getStringCellValue());
						} else if (j == 6) {
							Survey.setDepartment(cell.getStringCellValue());
						} else if (j == 18) {
							Survey.setAppointmentTime(cell.getDateCellValue());
						} else if (j == 19) {
							Survey.setEstimatedDuration(cell.getNumericCellValue() + "");
						} else if (j == 20) {
							Survey.setParticipants(cell.getStringCellValue());
						} else if (j == 22) {
							Survey.setSurveyPictures(cell.getStringCellValue());
						} else if (j == 23) {
							Survey.setCreateName(cell.getStringCellValue());
						} else if (j == 24) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Survey.setCreateTime(data);
						} else if (j == 25) {
							Survey.setUpdateName(cell.getStringCellValue());
						} else if (j == 26) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Survey.setUpdateTime(data);
						}
					}
				}
				list.add(Survey);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Collent> collentExcel(MultipartFile file) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Collent> list = new ArrayList<Collent>();
			for (int i = 1; i <= lastRowNum; i++) {
				Collent Collent = new Collent();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null || cell.equals(null)) {
						
					} else {
						if (j == 3) {
							Collent.setCode(cell.getStringCellValue());
						} else if (j == 16) {
							Collent.setProjectStatus(cell.getStringCellValue());
						} else if (j == 17) {
							Collent.setTotalReceipts(cell.getStringCellValue());
						} else if (j == 18) {
							Collent.setFinalPrice(cell.getStringCellValue());
						} else if (j == 19) {
							Collent.setChangePrice(cell.getStringCellValue());
						} else if (j == 22) {
							Collent.setCollectionStatus(cell.getStringCellValue());
						} else if (j == 23) {
							Collent.setRefundType(cell.getStringCellValue());
						} else if (j == 24) {
							Collent.setPaymentMethod(cell.getStringCellValue());
						} else if (j == 26) {
							Collent.setCollection(cell.getStringCellValue());
						} else if (j == 28) {
							Collent.setQuotationID(cell.getStringCellValue());
						} else if (j == 33) {
							Collent.setDocumentDate(cell.getDateCellValue());
						} else if (j == 34) {
							Collent.setSettlementDate(cell.getDateCellValue());
						} else if (j == 36) {
							Collent.setUploadVoucher(cell.getStringCellValue());
						} else if (j == 40) {
							Collent.setPayee(cell.getStringCellValue());
						}  else if (j == 54) {
							Collent.setCrossCheck(cell.getStringCellValue());
						} else if (j == 60) {
							Collent.setCreateName(cell.getStringCellValue());
						} else if (j == 61) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Collent.setCreateTime(data);
						} else if (j == 62) {
							Collent.setUpdateName(cell.getStringCellValue());
						} else if (j == 63) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Collent.setUpdateTime(data);
						}
						
					}
				}
				list.add(Collent);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Project> projectExcel(MultipartFile file) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			// 两种方式读取工作表
			// Sheet sheet=workbook.getSheet("Sheet0");
			Sheet sheet = workbook.getSheetAt(0);
			// 获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			List<Project> list = new ArrayList<Project>();
			for (int i = 1; i <= lastRowNum; i++) {
				Project Project = new Project();
				Row row = sheet.getRow(i);
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null || cell.equals(null)) {
						
					} else {
						if (j == 0) {
							Project.setCode(cell.getStringCellValue());
						} else if (j == 1) {
							Project.setCurrrentTask(cell.getStringCellValue());
						} else if (j == 2) {
							Project.setDecorationProgress(cell.getStringCellValue());
						} else if (j == 3) {
							Project.setSaleConfirm(cell.getStringCellValue());
						} else if (j == 4) {
							Project.setEnd(cell.getStringCellValue());
						} else if (j == 5) {
							Project.setDeliveryDegree(cell.getStringCellValue());
						} else if (j == 6) {
							Project.setCompletDegree(cell.getStringCellValue());
						} else if (j == 7) {
							Project.setProjectName(cell.getStringCellValue());
						} else if (j == 8) {
							Project.setProjectTelephone(cell.getStringCellValue());
						} else if (j == 9) {
							Project.setProjectType(cell.getStringCellValue());
						} else if (j == 10) {
							Project.setMainProject(cell.getStringCellValue());
						} else if (j == 11) {
							Project.setGroupID(cell.getStringCellValue());
						} else if (j == 12) {
							Project.setFullPdf(cell.getStringCellValue());
						} else if (j == 13) {
							Project.setSchemePicture(cell.getStringCellValue());
						} else if (j == 14) {
							Project.setSystemSelect(cell.getStringCellValue());
						} else if (j == 15) {
							Project.setAddress(cell.getStringCellValue());
						} else if (j == 16) {
							Project.setArea(cell.getStringCellValue());
						} else if (j == 17) {
							Project.setVillage(cell.getStringCellValue());
						} else if (j == 18) {
							Project.setCreateName(cell.getStringCellValue());
						} else if (j == 19) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Project.setCreateTime(data);
						} else if (j == 21) {
							Project.setUpdateName(cell.getStringCellValue());
						} else if (j == 22) {
							Date data = simpleDateFormat.parse(cell.getStringCellValue());
							Project.setUpdateTime(data);
						}
					}
				}
				list.add(Project);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
