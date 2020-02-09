package com.tdm.model.DTO;

import java.util.Comparator;

public class ATSFieldCompator implements Comparator<FieldListDTO> {
	public int compare(FieldListDTO fieldListDTO1, FieldListDTO fieldListDTO2) {
		return fieldListDTO1.getListValue().compareToIgnoreCase(
				fieldListDTO2.getListValue());
	}

}
