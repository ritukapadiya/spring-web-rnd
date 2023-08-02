package com.service;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.CertificateBean;

import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class JasperReportCertificate {
	@Autowired
	CertificateMailService mail;
	
	List<CertificateBean> list = new ArrayList<CertificateBean>();
	public void getData(CertificateBean cb) {
//		System.out.println("NAme is "+cb.getName());
		list.add(new CertificateBean(cb.getName(), cb.getEmail(), cb.getCourse(), cb.getMentor(), cb.getMonthYear()));
		String path = generateReport();
		//mail.sendCertificate(cb.getEmail(),path);
	}
	public String generateReport() {
		try {

			CertificateBean cb =  list.get(0);
			String reportPath = "D:\\Java\\pdfExample";
			
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath + "\\certificate.jrxml");

			
			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(list);
//			JRBean

			// Add parameters
			Map<String, Object> parameters = new HashMap<String,Object>();

			parameters.put("jrBeanCollectionDataSource", jrBeanCollectionDataSource);

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			File f = new File(reportPath,cb.getEmail());
			f.mkdir();
			LocalDate l = LocalDate.now();
			File f1 = new File(f,l.toString());
			f1.mkdir();
			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, f1.getAbsolutePath()+"\\" +cb.getCourse()+ "_certificate.pdf");

			System.out.println("Done");
//			System.out.println("path => "+ f1.getAbsolutePath()+"\\"+cb.getCourse()+"_certificate.pdf");
			list.clear();
			return f1.getAbsolutePath()+"\\"+cb.getCourse()+"_certificate.pdf";

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
}
