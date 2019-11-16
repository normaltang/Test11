package cn.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class NowTimeTest {/*

	try {
		// ɾ��ԭ���ļ�
		File file_del = new File(filepath);
		if(file_del.isFile()){
			file_del.delete();
		}
		// �������ļ�
		File file_create = new File(filepath);
		file_create.createNewFile();
		FileOutputStream os = new FileOutputStream(file_create);
		WritableWorkbook book = Workbook.createWorkbook(os);
		//������ļ�����
		if (file_create.exists()) {
			int columnSize = columnTitle.length;
			// ���һ��������
			WritableSheet sheet = book.createSheet("sheet0",0);	
			//�������ָ�ʽ
			NumberFormat nf = new NumberFormat("#0.0000");
			//���ñ���ʽ
			
			// �ϲ���Ԫ��
			sheet.mergeCells(0, 0,columnSize-1, 0);
			
			//���õ�Ԫ����ʽ
			for(int i = 0 ; i < columnSize; i++){
				if(i == 0){
					//��һ�����������У��ڶ�������������
					sheet.setColumnView(0, 10);
				}else{
					//�������еĿ����Ϊ15
					sheet.setColumnView(i, 15);
				}
			}
			// ��������
			WritableFont fontBT = new WritableFont(WritableFont.createFont("΢���ź�"),16, WritableFont.BOLD); // ��������
			WritableFont fontContent = new WritableFont(WritableFont.createFont("΢���ź�"), 10, WritableFont.NO_BOLD);// ��������
			WritableCellFormat formatZBT = new WritableCellFormat(fontBT);// ���������
			WritableCellFormat formatBT1 = new WritableCellFormat(fontContent);// ������1����λ����
			WritableCellFormat formatBT2 = new WritableCellFormat(fontContent);// ������2������
			WritableCellFormat formatBT3 = new WritableCellFormat(fontContent);// ������2������
			WritableCellFormat formatContent = new WritableCellFormat(nf);// ����

			// ������
			formatZBT.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // ����
			formatBT1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // ����
			formatBT2.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // ����
			formatBT3.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.NONE); // ����
			formatContent.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN); // ����

			// �������и�ʽ�����У�����룬�Ҷ���
			formatZBT.setAlignment(jxl.format.Alignment.CENTRE);// ���������
			formatBT1.setAlignment(jxl.format.Alignment.RIGHT);// ������1�Ҷ���
			formatBT2.setAlignment(jxl.format.Alignment.LEFT);// ������2�����
			formatBT3.setAlignment(jxl.format.Alignment.LEFT);// ������2�����
			formatContent.setAlignment(jxl.format.Alignment.CENTRE);// ���ݣ����ж���
			formatContent.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // ���ݣ���ֱ����
			
			//������һ��---��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0) �Լ���Ԫ������Ϊ:���վ������Ϣ
			Label label = new Label(0, 0, titleName, formatZBT);
			// �����ڶ���---��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�еڶ���(0,1) �Լ���Ԫ������Ϊ:ʱ��
			// �ϲ���Ԫ��
			
			 * mergeCells(a,b,c,d) ��Ԫ��ϲ����� a,b,c,d Ҫ�ϲ�������
			 *  a ��Ԫ����к�
				b ��Ԫ����к�
				c �ӵ�Ԫ��[a,b]�����ºϲ���������
				d �ӵ�Ԫ��[a,b]�����ºϲ���������
			 * 
			sheet.mergeCells(0, 1,columnSize-1, 1);
			Label label2 = new Label(0, 1," ���ڣ� "+time, formatBT2);

			// ������õĵ�Ԫ����ӵ���������
			sheet.addCell(label);
			sheet.addCell(label2);
			
			//����б�����
			for (int i = 0; i < columnTitle.length; i++) {
				Label excelTitle = new Label(i, 2, columnTitle[i],formatContent);
				sheet.addCell(excelTitle);
			}
			// д�����ݲ��ر��ļ�				
			for (int i = 0; i < listDatas.length; i++) {//��
				listDatas[i]=listDatas[i].substring(1,listDatas[i].length()-1);//ȥͷ��β
				
				
				//����excel�������ָ�ʽ
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
				for(int j = 0; j < strCols.length; j++){//��
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
						//�����һ�в������֣���ϲ�ǰ����
						if(j==0)sheet.mergeCells(j, i + 3, 2, i + 3);
						//��ʽ����ֵ
						sheet = writeNumberToWs(sheet, formatContent, i,j,doubleColsContentValue );
						
						
						 //lb = new Label(j, i + 3, strColsContentValue,formatContent);
						 Label labelC = new Label(j, 1, "");
						 jxl.write.Number labelNF = new jxl.write.Number(j, 1,
									2); // ��ʽ����ֵ
						}
					// sheet.addCell(sheet);							
				}
				
			}
			// �ϲ���Ԫ�����������½ű�
			int intRows = sheet.getRows();
			sheet.mergeCells(0, intRows,(columnSize-1)/2, intRows);	
			Label sj = new Label(0, intRows,"��ӡʱ�䣺"+getCurrentDate(),formatBT3);
			sheet.addCell(sj);
			sheet.mergeCells(columnSize/2,intRows,columnSize-1, intRows);	
			Label dw = new Label(columnSize/2, intRows,"��λ��  ",formatBT1);
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
