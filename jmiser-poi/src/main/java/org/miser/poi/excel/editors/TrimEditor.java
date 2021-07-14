package org.miser.poi.excel.editors;

import org.apache.poi.ss.usermodel.Cell;

import org.miser.core.util.StringUtil;
import org.miser.poi.excel.cell.CellEditor;

/**
 * 去除String类型的单元格值两边的空格
 * @author Oliver
 *
 */
public class TrimEditor implements CellEditor{

	@Override
	public Object edit(Cell cell, Object value) {
		if(value instanceof String) {
			return StringUtil.trim((String)value);
		}
		return value;
	}

}
