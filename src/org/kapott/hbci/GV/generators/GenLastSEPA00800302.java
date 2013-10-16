package org.kapott.hbci.GV.generators;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;

import org.kapott.hbci.sepa.PainVersion;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.AccountIdentificationSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.ActiveOrHistoricCurrencyAndAmountSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.ActiveOrHistoricCurrencyCodeEUR;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.AmendmentInformationDetailsSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.BranchAndFinancialInstitutionIdentificationSEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.BranchAndFinancialInstitutionIdentificationSEPA3;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.CashAccountSEPA1;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.CashAccountSEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.ChargeBearerTypeSEPACode;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.CustomerDirectDebitInitiationV02;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.DirectDebitTransactionInformationSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.DirectDebitTransactionSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.Document;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.FinancialInstitutionIdentificationSEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.FinancialInstitutionIdentificationSEPA3;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.GroupHeaderSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.IdentificationSchemeNameSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.LocalInstrumentSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.MandateRelatedInformationSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.ObjectFactory;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.OthrIdentification;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.OthrIdentificationCode;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PartyIdentificationSEPA1;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PartyIdentificationSEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PartyIdentificationSEPA3;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PartyIdentificationSEPA5;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PartySEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PaymentIdentificationSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PaymentInstructionInformationSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PaymentMethod2Code;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PaymentTypeInformationSDD;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.PersonIdentificationSEPA2;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.RemittanceInformationSEPA1Choice;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.RestrictedFinancialIdentificationSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.RestrictedPersonIdentificationSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.RestrictedPersonIdentificationSchemeNameSEPA;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.RestrictedSMNDACode;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.SequenceType1Code;
import org.kapott.hbci.sepa.jaxb.pain_008_003_02.ServiceLevelSEPA;


/**
 * SEPA-Generator fuer pain.008.003.02.
 */
public class GenLastSEPA00800302 extends AbstractSEPAGenerator
{
    /**
     * @see org.kapott.hbci.GV.generators.AbstractSEPAGenerator#getPainVersion()
     */
    @Override
    public PainVersion getPainVersion()
    {
        return PainVersion.PAIN_008_003_02;
    }

    /**
     * @see org.kapott.hbci.GV.generators.ISEPAGenerator#generate(java.util.Properties, java.io.OutputStream, boolean)
     */
    @Override
    public void generate(Properties sepaParams, OutputStream os, boolean validate) throws Exception
	{
		//Formatter um Dates ins gew�nschte ISODateTime Format zu bringen.
		Date now=new Date();
		SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DatatypeFactory df = DatatypeFactory.newInstance();
		
		
		//Document
		Document doc = new Document();
		
		
		//Customer Credit Transfer Initiation
		doc.setCstmrDrctDbtInitn(new CustomerDirectDebitInitiationV02());
		doc.getCstmrDrctDbtInitn().setGrpHdr(new GroupHeaderSDD());
		
		
		//Group Header
		doc.getCstmrDrctDbtInitn().getGrpHdr().setMsgId(sepaParams.getProperty("sepaid"));
		doc.getCstmrDrctDbtInitn().getGrpHdr().setCreDtTm(df.newXMLGregorianCalendar(sdtf.format(now)));
		doc.getCstmrDrctDbtInitn().getGrpHdr().setNbOfTxs("1");
		doc.getCstmrDrctDbtInitn().getGrpHdr().setInitgPty(new PartyIdentificationSEPA1());
		doc.getCstmrDrctDbtInitn().getGrpHdr().getInitgPty().setNm(sepaParams.getProperty("src.name"));
		
		
		//Payment Information 
		ArrayList<PaymentInstructionInformationSDD> pmtInfs = (ArrayList<PaymentInstructionInformationSDD>) doc.getCstmrDrctDbtInitn().getPmtInf();
		PaymentInstructionInformationSDD pmtInf = new PaymentInstructionInformationSDD();
		pmtInfs.add(pmtInf);
		
		pmtInf.setPmtInfId(sepaParams.getProperty("sepaid")); 
		pmtInf.setPmtMtd(PaymentMethod2Code.DD);
		
		pmtInf.setReqdColltnDt(df.newXMLGregorianCalendar(sepaParams.getProperty("targetdate")));
		pmtInf.setCdtr(new PartyIdentificationSEPA5());
		pmtInf.setCdtrAcct(new CashAccountSEPA1());
		pmtInf.setCdtrAgt(new BranchAndFinancialInstitutionIdentificationSEPA3());
				
		//Payment Information
		pmtInf.getCdtr().setNm(sepaParams.getProperty("src.name"));
				
		//Payment Information
		pmtInf.getCdtrAcct().setId(new AccountIdentificationSEPA());
		pmtInf.getCdtrAcct().getId().setIBAN(sepaParams.getProperty("src.iban"));
				
		//Payment Information
		pmtInf.getCdtrAgt().setFinInstnId(new FinancialInstitutionIdentificationSEPA3());
		pmtInf.getCdtrAgt().getFinInstnId().setBIC(sepaParams.getProperty("src.bic"));
		
		
		//Payment Information - ChargeBearer
		pmtInf.setChrgBr(ChargeBearerTypeSEPACode.SLEV);

		pmtInf.setPmtTpInf(new PaymentTypeInformationSDD());
		pmtInf.getPmtTpInf().setSvcLvl(new ServiceLevelSEPA());
		pmtInf.getPmtTpInf().getSvcLvl().setCd("SEPA");
		pmtInf.getPmtTpInf().setLclInstrm(new LocalInstrumentSEPA());
		pmtInf.getPmtTpInf().getLclInstrm().setCd(sepaParams.getProperty("type"));
		pmtInf.getPmtTpInf().setSeqTp(SequenceType1Code.fromValue(sepaParams.getProperty("sequencetype"))); 
		
		//Payment Information - Credit Transfer Transaction Information
		ArrayList<DirectDebitTransactionInformationSDD> drctDbtTxInfs = (ArrayList<DirectDebitTransactionInformationSDD>) pmtInf.getDrctDbtTxInf();
		DirectDebitTransactionInformationSDD drctDbtTxInf = new DirectDebitTransactionInformationSDD();
		drctDbtTxInfs.add(drctDbtTxInf);
		
		
		
		drctDbtTxInf.setDrctDbtTx(new DirectDebitTransactionSDD());
		drctDbtTxInf.getDrctDbtTx().setCdtrSchmeId(new PartyIdentificationSEPA3()); 
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().setId(new PartySEPA2());
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().getId().setPrvtId(new PersonIdentificationSEPA2());
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().getId().getPrvtId().setOthr(new RestrictedPersonIdentificationSEPA());
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().getId().getPrvtId().getOthr().setId(sepaParams.getProperty("creditorid"));
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().getId().getPrvtId().getOthr().setSchmeNm(new RestrictedPersonIdentificationSchemeNameSEPA());
		drctDbtTxInf.getDrctDbtTx().getCdtrSchmeId().getId().getPrvtId().getOthr().getSchmeNm().setPrtry(IdentificationSchemeNameSEPA.SEPA);
		
		drctDbtTxInf.getDrctDbtTx().setMndtRltdInf(new MandateRelatedInformationSDD());
		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().setMndtId(sepaParams.getProperty("mandateid"));
		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().setDtOfSgntr(df.newXMLGregorianCalendar(sepaParams.getProperty("manddateofsig")));
		
		
		boolean amend = Boolean.valueOf(sepaParams.getProperty("amendmandindic"));
		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().setAmdmntInd(amend);
		if(amend)
		{
    		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().setAmdmntInfDtls(new AmendmentInformationDetailsSDD());
    		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getAmdmntInfDtls().setOrgnlDbtrAgt(new BranchAndFinancialInstitutionIdentificationSEPA2());
    		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getAmdmntInfDtls().getOrgnlDbtrAgt().setFinInstnId(new FinancialInstitutionIdentificationSEPA2());
    		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getAmdmntInfDtls().getOrgnlDbtrAgt().getFinInstnId().setOthr(new RestrictedFinancialIdentificationSEPA());
    		drctDbtTxInf.getDrctDbtTx().getMndtRltdInf().getAmdmntInfDtls().getOrgnlDbtrAgt().getFinInstnId().getOthr().setId(RestrictedSMNDACode.SMNDA);
		}
		
		
		
		
		//Payment Information - Credit Transfer Transaction Information - Payment Identification
		drctDbtTxInf.setPmtId(new PaymentIdentificationSEPA());
		drctDbtTxInf.getPmtId().setEndToEndId(sepaParams.getProperty("endtoendid"));
		
		
		//Payment Information - Credit Transfer Transaction Information - Creditor
		drctDbtTxInf.setDbtr(new PartyIdentificationSEPA2());
		drctDbtTxInf.getDbtr().setNm(sepaParams.getProperty("dst.name"));
		
		
		
		//Payment Information - Credit Transfer Transaction Information - Creditor Account
		drctDbtTxInf.setDbtrAcct(new CashAccountSEPA2());
		drctDbtTxInf.getDbtrAcct().setId(new AccountIdentificationSEPA());
		drctDbtTxInf.getDbtrAcct().getId().setIBAN(sepaParams.getProperty("dst.iban"));
		
		//Payment Information - Credit Transfer Transaction Information - Creditor Agent
		drctDbtTxInf.setDbtrAgt(new BranchAndFinancialInstitutionIdentificationSEPA3());
		drctDbtTxInf.getDbtrAgt().setFinInstnId(new FinancialInstitutionIdentificationSEPA3());
		
		String bic = sepaParams.getProperty("dst.bic");
		if (bic != null && bic.length() > 0)
		{
            drctDbtTxInf.getDbtrAgt().getFinInstnId().setBIC(bic);
		}
		else
		{
		    drctDbtTxInf.getDbtrAgt().getFinInstnId().setOthr(new OthrIdentification());
		    drctDbtTxInf.getDbtrAgt().getFinInstnId().getOthr().setId(OthrIdentificationCode.NOTPROVIDED);
		}


		//Payment Information - Credit Transfer Transaction Information - Amount
		drctDbtTxInf.setInstdAmt(new ActiveOrHistoricCurrencyAndAmountSEPA());
		drctDbtTxInf.getInstdAmt().setValue(new BigDecimal(sepaParams.getProperty("btg.value")));
		
		drctDbtTxInf.getInstdAmt().setCcy(ActiveOrHistoricCurrencyCodeEUR.EUR); 
		

		//Payment Information - Credit Transfer Transaction Information - Usage
		drctDbtTxInf.setRmtInf(new RemittanceInformationSEPA1Choice());
		drctDbtTxInf.getRmtInf().setUstrd(sepaParams.getProperty("usage"));

        ObjectFactory of = new ObjectFactory();
        this.marshal(of.createDocument(doc), os, validate);
	}
}
