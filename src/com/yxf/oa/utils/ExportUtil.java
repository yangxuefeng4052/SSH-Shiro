package com.yxf.oa.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.yxf.oa.bean.Emp;

/**
 *
 * @author yxf
 * @time 2018年9月4日上午10:56:54
 *
 */
public class ExportUtil {
	/**
	 * 导出全部emp
	 * 
	 * @param emps
	 */
	public static void export(List<Emp> emps) {
		/*
		 * 设置表头
		 */
		String[] tableHeader = { "工号", "姓名", "年龄", "性别", "婚姻状况", "联系方式", "邮箱", "所属部门", "职位" };

		short cellNumber = (short) tableHeader.length;// 表的列数
		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel
		HSSFCell cell = null; // Excel的列
		HSSFRow row = null; // Excel的行
		HSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		// style.setAlignment();
		HSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		// style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont(); // 设置字体
		HSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		HSSFHeader header = sheet.getHeader();// 设置sheet的头
		try { /**
				 * 根据是否取出数据，设置header信息
				 * 
				 */
			if (emps.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("学生表");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, 8000);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeight((short) 350); // 设置单元字体高度
					style1.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}
				/*
				 * // 给excel填充数据这里需要编写
				 * 
				 */
				for (int i = 0; i < emps.size(); i++) {
					Emp emp = (Emp) emps.get(i);// 获取student对象
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高

					if (emp.getEmpId() != null) {
						cell = row.createCell(0);// 创建第i+1行第0列
						cell.setCellValue(emp.getEmpId());// 设置第i+1行第0列的值
						cell.setCellStyle(style);// 设置风格
					}
					if (emp.getUsername() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列

						cell.setCellValue(emp.getUsername());// 设置第i+1行第1列的值

						cell.setCellStyle(style); // 设置风格
					}
					// 由于下面的和上面的基本相同，就不加注释了
					if (emp.getAge() != 0) {
						cell = row.createCell(2);
						cell.setCellValue(emp.getAge());
						cell.setCellStyle(style);
					}
					if (emp.getSex() != null) {
						cell = row.createCell(3);
						cell.setCellValue(emp.getSex());
						cell.setCellStyle(style);
					}
					if (emp.getIsmarry() != 0) {
						cell = row.createCell(4);
						if (emp.getIsmarry() == 1) {
							cell.setCellValue("已婚");
						} else {
							cell.setCellValue("未婚");
						}
						cell.setCellStyle(style);
					}
					if (emp.getPhone() != null) {
						cell = row.createCell(5);
						cell.setCellValue(emp.getPhone());
						cell.setCellStyle(style);
					}
					if (emp.getEmail() != null) {
						cell = row.createCell(6);
						cell.setCellValue(emp.getEmail());
						cell.setCellStyle(style);
					}
					if (emp.getDept().getId() != 0) {
						cell = row.createCell(7);
						String deptName = "";
						switch (emp.getDept().getId()) {
						case 1:
							deptName = "综合管理部";
							break;
						case 2:
							deptName = "财务部";
							break;
						case 3:
							deptName = "研发部";
							break;
						case 4:
							deptName = "市场部";
							break;
						case 5:
							deptName = "营销部";
							break;
						case 6:
							deptName = "人事部";
							break;
						case 7:
							deptName = "行政部";
							break;
						}
						cell.setCellValue(deptName);
						cell.setCellStyle(style);
					}
					if (emp.getRole().getId() != 0) {
						cell = row.createCell(8);
						String roleName = "";
						switch (emp.getRole().getId()) {
						case 1:
							roleName = "管理员";
							break;
						case 2:
							roleName = "总经理";
							break;
						case 3:
							roleName = "主管";
							break;
						case 4:
							roleName = "总监";
							break;
						case 5:
							roleName = "项目经理";
							break;
						case 6:
							roleName = "员工";
							break;
						}
						cell.setCellValue(roleName);
						cell.setCellStyle(style);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename=" + "emps.xls");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			workbook.write(out);
			out.flush();
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
