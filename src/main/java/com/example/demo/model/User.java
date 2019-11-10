package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private LocalDate systemDate;
    private String firmCD;
    private String firstName;
    private String fromAddLine1;
    private String fromAddLine2;
    private String fromAddLine3;
    private String fromCity;
    private String fromSTCD;
    private String fromZip;
    private String fromCountry;
    private String fromPhone;
    private String fromPhoneExt;
    private String fromFax;
    private String fromLt;
    private String fulFlmCnfIntPrty1;
    private String fulFlmCnfIntPrty2;
    private String fulFlmCnfmAdd1;
    private String fulFlmCnfmAdd2;
    private String fulFlmCnfmAdd3;
    private String fulFlmCnfmCity;
    private String fulFlmCnfmStCd;
    private String fulFlmCnfmZip;
    private String fulFlmCnfmCtry;
    private String fulFlmCnfmElectDlvPrfrInd;
    private String fulFlmCnfmMgrDtccNo;
    private String fulFlmCnfmElecDlvMthdCd;
    private String fulFlmPrcIntPrtyName1;
    private String fulFlmPrcIntPrtyName2;
    private String fulFlmPrxAddr1;
    private String fulFlmPrxAddr2;
    private String fulFlmPrxAddr3;
    private String fulFlmPrxCityNm;
    private String fulFlmPrxStCd;
    private String fulFlmPrxZip;
    private String fulFlmPrxCtryNm;
    private String fulFlmPrxElecDlvPrfrInd;
    private String fulFlmPrxInvMgrElecAgtId;
    private String fulFlmAdvntEnabledMgrInd;
    private String fulFlmCntctSeqNbr;
    private String fulFlmCntctRole;
    private String fulFlmCntctEmailId;
    private String fulFlmCntctFirstNm;
    private String fulCntctMidNm;
    private String frmCntctLastNm;
    private String frmCntctPhnNbr;
    private String frmCntctPhnExt;
    private String frmCntctFaxNbr;
    private String frmCntctDsclDlvInd;
}
