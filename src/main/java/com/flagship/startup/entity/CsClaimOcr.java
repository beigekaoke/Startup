package com.flagship.startup.entity;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class CsClaimOcr implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2281555136053407850L;

	private String index;

	@CsvBindByName(column = "REFNO")
	private String refNo;

	private String memberName;

	@CsvBindByName(column = "MEMBER")
	private String member;

	@CsvBindByName(column = "RECEIPT_AMT")
	private Double receiptAmt;

	private Double amount;

	@CsvBindByName(column = "DOCTOR")
	private String doctor;
}
