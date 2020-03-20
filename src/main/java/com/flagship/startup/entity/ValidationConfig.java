package com.flagship.startup.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4842451716667150964L;

	private String srcFileName;
	
	private String srcField;
	
	private String destFileName;
	
	private String destField;
	
	private String destFiledType;
	
	private String srcType;
	
	private String destType;
}
