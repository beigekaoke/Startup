package com.flagship.startup.entity;

import java.io.Serializable;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class CsClaimData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -448705545893986472L;

	@CsvBindByName(column = "MemberName")
	private String memberName;

	@CsvBindByName(column = "ECLAIM_REF_NO")
	private String eclaimRefNo;
	
	private String clientCode;

	private String policyNo;

	private String certNo;

	private String memberId;

	private String providerId;

	private Date reciveDate;

	private String familyId;

	private String dependentCode;

	private String eobNotes;

	private String remarkCode;

	private String inspectResult;

	private Date inspectDate;

	private String inspector;

	private Double paidAmt;

	private Date payDate;

	private Date incurDate;

	private String prodCode;

	private String benCode;

	private String prodCodePxMed;

	private String benCodePxMed;

	private String claimType;

	private String submitChannel;

	@CsvBindByName(column = "RECEIPT_AMT")
	private Double receiptAmt;

	private Double claimAmt;

	private Double consultFee;

	private Double pxMedAmt;

	private String currCode;
	private String diagCode;
	private String settlementAdvice;
	private String clmSubBef;
	private String pxMed;
	private String auditCategory;
	private String auditResult;
	private String auditRemarkCode;
	private Date auditResultDate;
	private String auditor;
	private Double previousAmt;
	private String inspectWatchFlag;
	private String inspectWatchReason;
	private String auditWatchFlag;
	private String auditWatchReason;
	private String auditMonth;
	private Date auditSampleDate;
	private Date payPendingDate;
	private String claimAnt;
	private String syncFlag;
	private String compassDbrStatus;
	private String notifyCloseReason;
	private String procedureCode;
	private String isArchived;
	private Double declineAmt;
	private String remarkCode2;
	private String fsaChargeIndicator;
	private String splitIndicator;
	private String stpFlag;
	private String stpFaileReason;
}
