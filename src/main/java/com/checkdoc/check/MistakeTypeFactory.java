package com.checkdoc.check;

import com.checkdoc.domain.MistakeType;
import com.checkdoc.service.MistakeTypeService;
import com.checkdoc.service.impl.MistakeTypeServiceImpl;

/**
 * Factory of MistakeType which retrieves them from DB
 *
 * @author Oleg Dashkevych
 * @version 1.0 4.11.2015
 */
public class MistakeTypeFactory {

	private MistakeTypeService mistakeTypeService;


	public MistakeTypeFactory() {
		mistakeTypeService = new MistakeTypeServiceImpl();
	}

	public MistakeType getMistakeType(MistakeTypeEnum typeEnum) {
		if (typeEnum == null) {
			return null;
		}
		if (typeEnum.equals(MistakeTypeEnum.FONT_MISTAKE)) {
			String name = MistakeTypeEnum.FONT_MISTAKE.name();
			return mistakeTypeService.findMistakeTypeByName(name);

		} else if (typeEnum.equals(MistakeTypeEnum.FONT_SIZE_MISTAKE)) {
			String name = MistakeTypeEnum.FONT_SIZE_MISTAKE.name();
			return mistakeTypeService.findMistakeTypeByName(name);

		} else if (typeEnum.equals(MistakeTypeEnum.INTEND_SIZE_MISTAKE)) {
			String name = MistakeTypeEnum.INTEND_SIZE_MISTAKE.name();
			return mistakeTypeService.findMistakeTypeByName(name);
		}
		return null;
	}
}
