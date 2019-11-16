package cn.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class NowTimeTest {/*

	try {
		// 删除原有文件
		File file_del = new File(filepath);
		if(file_del.isFile()){
			file_del.delete();
		}
		// 创建新文件
		File file_create = new File(filepath);
		file_create.createNewFile();
		FileOutputStream os = new FileOutputStream(file_create);
		WritableWorkbook book = Workbook.createWorkbook(os);
		//如果该文件存在
		if (file_create.exists()) {
			int columnSize = columnTitle.length;
			// 添加一个工作表
			WritableSheet sheet = book.createSheet("sheet0",0);	
			//设置数字格式
			NumberFormat nf = new NumberFormat("#0.0000");
			//设置表单格式
			
			// 合并单元格
			sheet.mergeCells(0, 0,columnSize-1, 0);
			
			//设置单元格样式
			for(int i = 0 ; i < columnSize; i++){
				if(i == 0){
					//第一个参数代表列，第二个参数代表宽度
					sheet.setColumnView(0, 10);
				}else{
					//将其他列的宽度设为15
					sheet.setColumnView(i, 15);
				}
			}
			// 设置字体
			WritableFont fontBT = new WritableFont(WritableFont.createFont("微软雅黑"),16, WritableFont.BOLD); // 标题字体
			WritableFont fontContent = new WritableFont(WritableFont.createFont("微软雅黑"), 10, WritableFont.NO_BOLD);// 内容字体
			WritableCellFormat formatZBT = new WritableCellFormat(fontBT);// 表格主标题
			WritableCellFormat formatBT1 = new WritableCellFormat(fontContent);// 表格标题1：单位名称
			WritableCellFormat formatBT2 = new WritableCellFormat(fontContent);// 表格标题2：日期
			WritableCellFormat formatBT3 = new WritableCellFormat(fontContent);// 表格标题2：日期
			WritableCellFormat formatContent = new WritableCellFormat(nf);// 内容

			// 画线条
			formatZBT.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // 线条
			formatBT1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // 线条
			formatBT2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // 线条
			formatBT3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // 线条
			formatContent.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // 线条

			// 定义排列格式：居中，左对齐，右对齐
			formatZBT.setAlignment(jxl.format.Alignment.CENTRE);// 主标题居中
			formatBT1.setAlignment(jxl.format.Alignment.RIGHT);// 表格标题1右对齐
			formatBT2.setAlignment(jxl.format.Alignment.LEFT);// 表格标题2左对齐
			formatBT3.setAlignment(jxl.format.Alignment.LEFT);// 表格标题2左对齐
			formatContent.setAlignment(jxl.format.Alignment.CENTRE);// 内容：居中对齐
			formatContent.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 内容：垂直对齐
			
			//创建第一行---在Label对象的构造子中指名单元格位置是第一列第一行(0,0) 以及单元格内容为:变电站基本信息
			Label label = new Label(0, 0, titleName, formatZBT);
			// 创建第二行---在Label对象的构造子中指名单元格位置是第一列第二行(0,1) 以及单元格内容为:时间
			// 合并單元格
			
			 * mergeCells(a,b,c,d) 单元格合并函数 a,b,c,d 要合并的坐标
			 *  a 单元格的列号
				b 单元格的行号
				c 从单元格[a,b]起，向下合并到的列数
				d 从单元格[a,b]起，向下合并到的行数
			 * 
			sheet.mergeCells(0, 1,columnSize-1, 1);
			Label label2 = new Label(0, 1," 日期： "+time, formatBT2);

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			sheet.addCell(label2);
			
			//添加列标题行
			for (int i = 0; i < columnTitle.length; i++) {
				Label excelTitle = new Label(i, 2, columnTitle[i],formatContent);
				sheet.addCell(excelTitle);
			}
			// 写入数据并关闭文件				
			for (int i = 0; i < listDatas.length; i++) {//行
				listDatas[i]=listDatas[i].substring(1,listDatas[i].length()-1);//去头和尾
				
				
				//导出excel设置数字格式
				HSSFWorkbook demoWorkBook = new HSSFWorkbook();   
			    HSSFSheet demoSheet = demoWorkBook.createSheet("The World's 500 Enterprises");   
			    HSSFCell cell = demoSheet.createRow(0).createCell(0);
			    cell.setCellValue(1.2);
		        HSSFCellStyle cellStyle = demoWorkBook.createCellStyle();
		        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		        cell.setCellStyle(cellStyle);
		        cell.setCellValue(Double.parseDouble(listDatas[i]));
		        
		        //=================================================
				String[] strCols=listDatas[i].split(",");
				double doubleColsContentValue = 0.0;
				for(int j = 0; j < strCols.length; j++){//列
					String[] strColsContent=strCols[j].split(":");
					String strColsContentValue=strColsContent[1].trim();
					if (strColsContentValue.indexOf('"')>=0)
						strColsContentValue=strColsContentValue.substring(1,strColsContentValue.length()-1);
					if(isNumeric(strColsContentValue)){
						 doubleColsContentValue = Double.parseDouble(strColsContentValue);
					}
					Label lb;
					
					if(j==0 && isNumeric(strColsContentValue)){							
						 lb = new Label(j, i + 3, String.valueOf(i+1),formatContent);
					}else{
						//如果第一列不是数字，则合并前三列
						if(j==0)sheet.mergeCells(j, i + 3, 2, i + 3);
						//格式化数值
						sheet = writeNumberToWs(sheet, formatContent, i,j,doubleColsContentValue );
						
						
						 //lb = new Label(j, i + 3, strColsContentValue,formatContent);
						 Label labelC = new Label(j, 1, "");
						 jxl.write.Number labelNF = new jxl.write.Number(j, 1,
									2); // 格式化数值
						}
					// sheet.addCell(sheet);							
				}
				
			}
			// 合并單元格，制作报表下脚标
			int intRows = sheet.getRows();
			sheet.mergeCells(0, intRows,(columnSize-1)/2, intRows);	
			Label sj = new Label(0, intRows,"打印时间："+getCurrentDate(),formatBT3);
			sheet.addCell(sj);
			sheet.mergeCells(columnSize/2,intRows,columnSize-1, intRows);	
			Label dw = new Label(columnSize/2, intRows,"单位：  ",formatBT1);
			sheet.addCell(dw);
			
			book.write();
			book.close();
			os.close();
		}
	} catch (RowsExceededException e) {
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (WriteException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return true;


*/}
