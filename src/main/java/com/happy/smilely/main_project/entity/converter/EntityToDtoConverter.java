package com.happy.smilely.main_project.entity.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityToDtoConverter {

	private final ModelMapper modelMapper;
	
	
}
