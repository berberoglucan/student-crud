package io.can.studentcrud.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("serviceUtil")
public class ServiceUtil {

	@Autowired
	private ModelMapper modelMapper;
	
	public Long getValidLongId(String id) {
		String trimmedId = id.trim();
		if(StringUtils.isEmpty(trimmedId)) {
			throw new IllegalArgumentException("id parameter is not empty");
		}
		if(!StringUtils.isNumeric(trimmedId)) {
			throw new IllegalArgumentException("id parameter is not numeric. Given id is: " + id);
		}
		
		return Long.valueOf(trimmedId);
	}
	
	public <D> D map(Object source, Class<D> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public <D, T> List<D> mapAll(Collection<T> sourceCollection, Class<D> destinationType) {
		return sourceCollection.stream().map(entity -> map(entity, destinationType)).collect(Collectors.toList());
	}
	
	
	
}
