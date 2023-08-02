package com.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bean.CertificateBean;
import com.bean.UserBean;
import com.bean.UserCountryBean;
import com.dao.UserDao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.JasperReportCertificate;
import com.service.JasperReportEmployee;

@Controller
public class AdminController {

	@Autowired
	UserDao userDao; 
	
	@Autowired
	JasperReportEmployee jre;
	
	@Autowired
	JasperReportCertificate jrc;
	
	
	@GetMapping("dashboard")
	public String dashboard(Model model) {
		List<UserCountryBean> users = userDao.getAllUsers(1);
		model.addAttribute("users",users); // key : value 
		return "Dashboard"; 
		
	}
	@GetMapping("dashboard_key")
	public String dashboardKey(@RequestParam("key") int key, Model model) {
		
		//get all users from db 
		
		System.out.println(key);
		List<UserCountryBean> users = userDao.getAllUsers(key);
		model.addAttribute("users",users); // key : value 
		return "Dashboard"; 
		
	}
	
	@GetMapping("dashboard_search")
	public String dashboardSearch(@RequestParam("searchValue") String searchValue, Model model) {
		System.out.println(searchValue);
		List<UserCountryBean> users = userDao.getUserBySearch(searchValue);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("users",users);
		return "Dashboard";
	}
	
	
	@GetMapping("delete")
	public String delete(@RequestParam("userId") String userId) {
		System.out.println(userId);
		userDao.deleteUser(userId);
		return "redirect:/dashboard";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("userId") String userId, Model model) {
		UserBean userBean = userDao.getUserByEmail(userId);
		model.addAttribute("userBean",userBean);
		return "Update";
	}
	
	@PostMapping("updateUser")
	public String updateUser(@ModelAttribute("userBean") UserBean userBean, String userId) {
		userId = userBean.getEmail();
		String masterPath = "D:\\Java\\Spring-Web\\src\\main\\webapp\\resources\\images\\"+userBean.getEmail();
		try {
			File f = new File(masterPath, userBean.getProfile().getOriginalFilename());
			FileUtils.writeByteArrayToFile(f, userBean.getProfile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		userBean.setProfilePath("resources/images/"+userBean.getEmail()+"/"+userBean.getProfile().getOriginalFilename());
		userDao.updateUser(userBean, userId);
		return "redirect:/dashboard";
	}
	
	@GetMapping("pdf")
	public String pdf() throws MalformedURLException, IOException {
		String dest = "D:\\Java\\pdfExample\\TestTableFile.pdf";   
		int totalUser = userDao.totalUser();
		List<UserCountryBean> user = userDao.getAllUsers();
	    try {
	    	Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(dest));
			document.open();
			//Create Table object, Here 4 specify the no. of columns
	        PdfPTable pdfPTable = new PdfPTable(5);
	 
	        
	        PdfPCell h1 = new PdfPCell(new Paragraph("Name"));
	        h1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	PdfPCell h2 = new PdfPCell(new Paragraph("Role"));
        	h2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        	h2.setHorizontalAlignment(Element.ALIGN_CENTER);
 	        PdfPCell h3 = new PdfPCell(new Paragraph("Email"));
 	        h3.setBackgroundColor(BaseColor.LIGHT_GRAY);
 	        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
 	        PdfPCell h4 = new PdfPCell(new Paragraph("Country"));
 	        h4.setBackgroundColor(BaseColor.LIGHT_GRAY);
 	        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
 	       
 	        PdfPCell h5 = new PdfPCell(new Paragraph("Profile"));
 	       	h5.setBackgroundColor(BaseColor.LIGHT_GRAY);
 	      	h5.setHorizontalAlignment(Element.ALIGN_CENTER);
 	       
 	       	pdfPTable.addCell(h1);
	        pdfPTable.addCell(h2);
	        pdfPTable.addCell(h3);
	        pdfPTable.addCell(h4);
	        pdfPTable.addCell(h5);
//	        h1.setHorizontalAlignment(1);
	        for(int i=0;i<user.size();i++) {
	        	//Create cells
	        	PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(user.get(i).getFirstName()));
	        	PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(user.get(i).getRole()));
	 	        PdfPCell pdfPCell3 = new PdfPCell(new Paragraph(user.get(i).getEmail()));
	 	        PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(user.get(i).getName()));
	 	        Image img = Image.getInstance("D:\\Java\\Spring-Web\\src\\main\\webapp\\"+user.get(i).getProfilePath());
	 	       img.scalePercent(10);
	 	        PdfPCell cell = new PdfPCell();
	 	        cell.addElement(new Chunk(img, 5, -5));
	 	        

	 	       
	 	    //Add cells to table
	 	       	pdfPTable.addCell(pdfPCell1);
		        pdfPTable.addCell(pdfPCell2);
		        pdfPTable.addCell(pdfPCell3);
		        pdfPTable.addCell(pdfPCell4);
		        pdfPTable.addCell(cell);
		        
		      //Add content to the document using Table objects.
		        
		       
	        }
	        document.add(pdfPTable);
	        //Close document
	        document.close();
	 
	        System.out.println("Pdf created successfully.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return "redirect:/dashboard";
		
	}
	
	
	
	@GetMapping("employeetable")
	public String employeeTable() {
		jre.generateReport();
		return "redirect:/dashboard";
	}
	
	@GetMapping("certificate")
	public String certificate(Model model) {
		model.addAttribute("certificateBean",new CertificateBean());
		return "Certificate";
	}
	
	@PostMapping("certificate_data")
	public String certificateData(CertificateBean certificateBean, Model model) {
		jrc.getData(certificateBean);
		System.out.println(certificateBean.getName());
		System.out.println(certificateBean.getCourse());
		return "Home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
