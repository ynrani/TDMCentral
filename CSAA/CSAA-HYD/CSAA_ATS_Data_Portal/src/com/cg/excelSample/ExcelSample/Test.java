package com.cg.excelSample.ExcelSample;

import java.util.ArrayList;
import java.util.List;

import com.tdm.model.DTO.FieldListDTO;

public class Test {

	static String str = "CREATE INDEX NO_OF_VIO_STG_INDEX1 ON NO_OF_VIO_STG (POLICYDETAIL_ID ASC) LOGGING "
			+ "TABLESPACE SYSTEM "
			+ "PCTFREE 10 "
			+ "INITRANS 2 "
			+ "STORAGE (   INITIAL 65536   NEXT 1048576   MINEXTENTS 1   MAXEXTENTS UNLIMITED   FREELISTS 1   FREELIST GROUPS 1   BUFFER_POOL DEFAULT ) "
			+ "NOPARALLEL;";

	static String st2 = "COMMIT";

	private static List<FieldListDTO> putFirstObjectAsAnyInList(
			List<FieldListDTO> list) {
		int count = 0;
		for (FieldListDTO fieldListDTO : list) {
			if (org.springframework.util.StringUtils.hasText(fieldListDTO
					.getListValue())
					&& fieldListDTO.getListValue().equalsIgnoreCase("Any")) {
				list.remove(count);

				list.add(0, fieldListDTO);
			}
			count++;
		}
		return list;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<FieldListDTO> list = new ArrayList<FieldListDTO>();

		FieldListDTO o1 = new FieldListDTO();
		o1.setListValue("BBC");
		FieldListDTO o2 = new FieldListDTO();
		o2.setListValue("AAA_A");

		FieldListDTO o3 = new FieldListDTO();
		o3.setListValue("Any");

		list.add(o1);
		list.add(o2);
		list.add(o3);
		System.out.println("berfore : " + list);
		list = putFirstObjectAsAnyInList(list);
		System.out.println("after : " + list);
	}
}
