package cn.database;

public class BeforeTest {/*
	*//**
	 * ����Excel�͵�������
	 * @param lists ҳ������
	 * @param titleName  ����
	 * @param columnTitle ����
	 * @param fileName �ļ���
	 * @param filepath �ļ�·��
	 * @return
	 *//*
	public boolean exportDataToExcel(String[] listDatas,String titleName,
			String[] columnTitle, String filename, String filepath,String time) {
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
				WritableCellFormat formatContent = new WritableCellFormat(fontContent);// ����

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
					String[] strCols=listDatas[i].split(",");
					for(int j = 0; j < strCols.length; j++){//��
						String[] strColsContent=strCols[j].split(":");
						String strColsContentValue=strColsContent[1];
												
						if (strColsContentValue.indexOf('"')>=0)
							strColsContentValue=strColsContentValue.substring(1,strColsContentValue.length()-1);
						Label lb;
						if(j==0 && isNumeric(strColsContentValue)){							
							 lb = new Label(j, i + 3, String.valueOf(i+1),formatContent);
						}else{
							//�����һ�в������֣���ϲ�ǰ����
							if(j==0)sheet.mergeCells(j, i + 3, 2, i + 3);
							 lb = new Label(j, i + 3, strColsContentValue,formatContent);
						}
						 sheet.addCell(lb);							
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
	}
	*//**
	 * ��datasת��Ϊstring list
	 * @param datas
	 * @return
	 *//*
	public String[] dealDatas(String datas){		
		String[] rows=datas.split ("##");	
		String[] rowsdeal=datas.split ("##");
		
		for(int i=0;i<rows.length;i++){//����
			rowsdeal[i]=rows[i].substring(1, rows[i].length()-1);		
		}	
		return rowsdeal;
	}
	*//**
	 * ��ȡ��ǰ����--yyyy-MM-dd
	 * @return
	 *//*
	public String getCurrentDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		Date date = new Date();
		return sdf.format(date);
	}
	
	*//**
	 * �ж��ַ������ݲ�������
	 * ������������򷵻�false
	 * @return
	 *//*	
	private boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++){		  
		   if (!Character.isDigit(str.charAt(i))){
			   return false;
		   }
		}
		return true;
	}



*/}
