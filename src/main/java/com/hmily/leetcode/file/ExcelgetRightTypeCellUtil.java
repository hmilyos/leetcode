package com.hmily.leetcode.file;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelgetRightTypeCellUtil {

	/**
	 * 
	 * @param cell 一个单元格的对象 
	 * @return 返回该单元格相应的类型的值
	 */
	public static Object getRightTypeCell(Cell cell, int targetType) {

		if (0 <= targetType && targetType <= 5) {
			cell.setCellType(targetType);
		}

		Object object = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING: {
				object = cell.getStringCellValue();
				break;
			}
			case Cell.CELL_TYPE_NUMERIC: {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				object = cell.getNumericCellValue();
				break;
			}

			case Cell.CELL_TYPE_FORMULA: {
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				object = cell.getNumericCellValue();
				break;
			}

			case Cell.CELL_TYPE_BLANK: {
				cell.setCellType(Cell.CELL_TYPE_BLANK);
				object = cell.getStringCellValue();
				break;
			}
		}
		return object;
	}

}
