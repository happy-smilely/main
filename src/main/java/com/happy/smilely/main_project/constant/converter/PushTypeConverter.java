package com.happy.smilely.main_project.constant.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import com.happy.smilely.main_project.constant.PushType;

@Converter
public class PushTypeConverter implements AttributeConverter<PushType, String> {

	@Override
	public String convertToDatabaseColumn(PushType type) {
		return type == null ? null : type.getIdx();
	}

	@Override
	public PushType convertToEntityAttribute(String str) {
		return PushType.from(str);
	}

}
