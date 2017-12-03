package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import DTO.GradesInfo;
import DTO.Student;
import javafx.collections.ObservableList;
import model.DAO;
import model.ViewCGPAModel;
import java.util.Random;

public class PdfTranscript {

	FileOutputStream outfile = null;

	public void pdfgenrator(int studentID, File filePath) throws DocumentException, MalformedURLException, IOException {

		DAO dataAccess = new DAO();
		Student studentInfo = new Student();
		studentInfo = dataAccess.studentProfile(studentID);

		ViewCGPAModel getCGPAModel = new ViewCGPAModel();
		float floatCgpa = getCGPAModel.getCGPA(studentID);
		BigDecimal bdCGPA = new BigDecimal(Float.toString(floatCgpa));
		bdCGPA = bdCGPA.setScale(1, BigDecimal.ROUND_HALF_UP);
		String cGPA = bdCGPA.toString();
		
		String floatCgpaPreRqusite = getCGPAModel.getCGPAPreRequisite(studentID);
		System.out.println("floatCgpaPreRqusite   "+floatCgpaPreRqusite);
		
		int courseCompletedCount = dataAccess.studentDegreeStatus(studentID);
		String degreeStatus = "";
		if(courseCompletedCount==11){
			degreeStatus = "Completed";
			
		}else{
			degreeStatus ="Continue";
			
		}
		
		String CareerName = (studentInfo.getCareer_Name().equals("G") ? "Graduate" : "Under Graduate");

		Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		// String path = "results/tables/Transcript.pdf";
		FileOutputStream outfile = new FileOutputStream(filePath);
		PdfWriter.getInstance(document, outfile);
		document.open();
		document.add(new Paragraph("\n\n\n\n"));

		Image imgSoc = Image.getInstance("images/univ_logo.jpg");
		imgSoc.scaleToFit(110, 110);
		imgSoc.setAbsolutePosition(390, 720);
		document.add(new Image(imgSoc) {
		});
		// creating table and set the column width
		Paragraph title = new Paragraph("Trancript");
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph("\n\n"));
		PdfPTable table = new PdfPTable(2);
		float widths[] = { 10, 30 };
		table.setWidths(widths);

		// table.setHeaderRows(1);

		// add cell of table - header cell
		// Font font = new Font(new Font(FontFamily.COURIER, 12, Font.BOLD, new
		// BaseColor(0, 0, 0)));
		String stdID = String.valueOf(studentID);

		PdfPCell cell = new PdfPCell(new Phrase("Student ID"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(stdID));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Name"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(studentInfo.getFirst_name() + " " + studentInfo.getLast_name()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Programme"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(CareerName + " in " + studentInfo.getSubject_Name()));

		table.addCell(cell);

		cell = new PdfPCell(new Phrase("CGPA"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(cGPA));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("CGPA for Pre-Rquisite courses"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(floatCgpaPreRqusite));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Degree Status"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(degreeStatus));
		table.addCell(cell);
		
		
		document.add(table);

		//document.add(new Paragraph("\n\n\n"));
		
		
		

		// Grade Table
		document.add(new Paragraph("\n\n\n\n\n"));

		ArrayList<String> termLsit = dataAccess.getTermGrade(studentID);

		for (String term : termLsit) {

			document.add(new Paragraph("Session: " + term));
			document.add(new Paragraph("\n"));
			PdfPTable tblGrade = new PdfPTable(5);
			float widthsGradeTable[] = { 10, 15, 8, 4, 4 };
			tblGrade.setWidths(widthsGradeTable);
			tblGrade.setHeaderRows(1);

			PdfPCell cellGrade = new PdfPCell(new Phrase("Course Name"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Course Title"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Attempted"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Grade"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("GPA"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			float totaltermGrade = 0.0f;
			int count = 0;
			ObservableList<GradesInfo> grade = dataAccess.getGradeViewDetail(studentID, term);
			for (GradesInfo gd : grade) {

				String courseName = gd.getCourse();
				String CourseTitle = gd.getCourse_Title();
				String CourseGpa = gd.getGpa();
				String CourseGrade = gd.getGrade();
				float CourseUnits = gd.getAttempted();
				String CourseUnitsValue = String.valueOf(CourseUnits);
				if (!CourseGpa.equals("-")) {
					count = count + 1;
					totaltermGrade = totaltermGrade + Float.parseFloat(CourseGpa);

				}

				cellGrade = new PdfPCell(new Phrase(courseName));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseTitle));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseUnitsValue));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseGrade));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseGpa));
				tblGrade.addCell(cellGrade);

			}
			document.add(tblGrade);
			float termGpa = totaltermGrade / count;
			String gpa = null;
			DecimalFormat df = new DecimalFormat("###.#");
			gpa = String.valueOf(df.format(termGpa));
			System.out.println(termGpa + "   " + totaltermGrade + " " + count);

			document.add(new Paragraph("Term GPA :" + gpa));
			document.add(new Paragraph("\n"));

		}

		// write the all into a file and save it.

		document.close();
		System.out.println("Successfull.");

	}

	public File pdfgenratoViewr(int studentID) throws DocumentException, MalformedURLException, IOException {

		DAO dataAccess = new DAO();
		Student studentInfo = new Student();
		studentInfo = dataAccess.studentProfile(studentID);

		ViewCGPAModel getCGPAModel = new ViewCGPAModel();
		float floatCgpa = getCGPAModel.getCGPA(studentID);
		BigDecimal bdCGPA = new BigDecimal(Float.toString(floatCgpa));
		bdCGPA = bdCGPA.setScale(1, BigDecimal.ROUND_HALF_UP);
		String cGPA = bdCGPA.toString();
		
		String floatCgpaPreRqusite = getCGPAModel.getCGPAPreRequisite(studentID);
		
		if(floatCgpaPreRqusite.isEmpty())
		{
			System.out.println("floatCgpaPreRqusite   "+floatCgpaPreRqusite.length());
		}
		
		
		
		String CareerName = (studentInfo.getCareer_Name().equals("G") ? "Graduate" : "Under Graduate");

		Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		Random rand = new Random();
		int  n = rand.nextInt(50) + 1;

		File path = new File("results/Transcript"+n+".pdf");
		FileOutputStream outfile = new FileOutputStream(path);
		PdfWriter.getInstance(document, outfile);
		document.open();
		document.add(new Paragraph("\n\n\n\n"));

		Image imgSoc = Image.getInstance("images/univ_logo.jpg");
		imgSoc.scaleToFit(110, 110);
		imgSoc.setAbsolutePosition(390, 720);
		document.add(new Image(imgSoc) {
		});
		// creating table and set the column width
		Paragraph title = new Paragraph("Transcript");
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		document.add(new Paragraph("\n\n"));
		PdfPTable table = new PdfPTable(2);
		float widths[] = { 10, 30 };
		table.setWidths(widths);

		// table.setHeaderRows(1);

		// add cell of table - header cell
		// Font font = new Font(new Font(FontFamily.COURIER, 12, Font.BOLD, new
		// BaseColor(0, 0, 0)));
		String stdID = String.valueOf(studentID);

		PdfPCell cell = new PdfPCell(new Phrase("Student ID"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(stdID));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Name"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(studentInfo.getFirst_name() + " " + studentInfo.getLast_name()));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Programme"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(CareerName + " in " + studentInfo.getSubject_Name()));

		table.addCell(cell);

		cell = new PdfPCell(new Phrase("CGPA"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(cGPA));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("CGPA for Pre-Rquisite courses"));
		cell.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(floatCgpaPreRqusite));
		table.addCell(cell);
		document.add(table);

		document.add(new Paragraph("\n\n\n\n\n"));

		// Grade Table

		ArrayList<String> termLsit = dataAccess.getTermGrade(studentID);

		for (String term : termLsit) {

			document.add(new Paragraph("Session: " + term));
			document.add(new Paragraph("\n"));
			PdfPTable tblGrade = new PdfPTable(5);
			float widthsGradeTable[] = { 10, 15, 8, 4, 4 };
			tblGrade.setWidths(widthsGradeTable);
			tblGrade.setHeaderRows(1);

			PdfPCell cellGrade = new PdfPCell(new Phrase("Course Name"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Course Title"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Attempted"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("Grade"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			cellGrade = new PdfPCell(new Phrase("GPA"));
			cellGrade.setBackgroundColor(new BaseColor(20).LIGHT_GRAY);
			tblGrade.addCell(cellGrade);
			float totaltermGrade = 0.0f;
			int count = 0;
			ObservableList<GradesInfo> grade = dataAccess.getGradeViewDetail(studentID, term);
			for (GradesInfo gd : grade) {

				String courseName = gd.getCourse();
				String CourseTitle = gd.getCourse_Title();
				String CourseGpa = gd.getGpa();
				String CourseGrade = gd.getGrade();
				float CourseUnits = gd.getAttempted();
				String CourseUnitsValue = String.valueOf(CourseUnits);
				if (!CourseGpa.equals("-")) {
					count = count + 1;
					totaltermGrade = totaltermGrade + Float.parseFloat(CourseGpa);

				}

				cellGrade = new PdfPCell(new Phrase(courseName));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseTitle));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseUnitsValue));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseGrade));
				tblGrade.addCell(cellGrade);
				cellGrade = new PdfPCell(new Phrase(CourseGpa));
				tblGrade.addCell(cellGrade);

			}
			document.add(tblGrade);
			float termGpa = totaltermGrade / count;
			String gpa = null;
			DecimalFormat df = new DecimalFormat("###.#");
			gpa = String.valueOf(df.format(termGpa));
			System.out.println(termGpa + "   " + totaltermGrade + " " + count);

			document.add(new Paragraph("Term GPA :" + gpa));
			document.add(new Paragraph("\n"));

		}

		// write the all into a file and save it.

		document.close();

		System.out.println("Successfull.");
		return path;

	}

	
	public void closeFileOutPutStreaam() {
		try {
			if (outfile != null) {
				outfile.flush();
				outfile.close();
				outfile = null;
				System.gc();
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
