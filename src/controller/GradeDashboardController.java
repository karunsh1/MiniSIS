package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.TermInfoModel;

public class GradeDashboardController implements Initializable {
	
	private Desktop desktop = Desktop.getDesktop();
	@FXML
    private ComboBox cbSelectTerm;
	@FXML
    private ComboBox cbDeptName;
	@FXML
    private ComboBox cbCourseName;
	@FXML
    private ComboBox cbCareerName;
	@FXML
    private ComboBox cbSelectTermUpload;
	@FXML
    private ComboBox cbDeptNameUpload;
	@FXML
    private ComboBox cbCourseNameUpload;
	@FXML
    private ComboBox cbCareerNameUpload;	
	@FXML
    private Button btnDownloadTemplate;
	@FXML
    private Button btnUploadTemplate;
	@FXML
	private Button btnFileChooser;
	private Window stage;
	
	
	
	
	@FXML
	private void onSelectTerm(){
		/*String sql =  "SELECT cd.id, ti.term as term,CONCAT(ins.first_name,' ',ins.last_name) as instructor,GROUP_CONCAT(CONCAT(sch.day,' ',sch.start_time,' - ',sch.end_time) SEPARATOR ' & ') as time,"
				+ " CONCAT(bld.name,' ',rm.room_no,' ',bld.campus_short) as location, CONCAT(sbj.subject_code,' ',cs.course_code,' - ',cs.title) as course, "
				+ " if((cd.class_capacity -(select count(*) from registration rgs where cd.id=rgs.course_details_id))>0,'Open','Closed') as status, cs.units as unit"
				+ " FROM course_details cd, course cs, subject sbj, term_info ti, instructor ins, schedule sch, course_schedule csch, room rm, building bld, registration rc"
				+ " WHERE rc.course_details_id=cd.id AND" + " cd.term_id=ti.id AND" + " ti.id=? AND"
				+ " cd.instructor_id=ins.id AND" + " cd.room_id=rm.id AND" + " rm.building_id =bld.id AND"
				+ " cd.course_id=cs.id AND" + " cs.subject_id=sbj.id and" + " csch.schedule_id=sch.id AND"
				+ " csch.course_detail_id=cd.id" + " GROUP BY csch.course_detail_id";
		MySQLAccess obj = new MySQLAccess();
		Connection conn = obj.getConnection();
		ArrayList enrollCourseList = null;

		try {
			PreparedStatement courseList = conn.prepareStatement(sql);
			ResultSet result = courseList.executeQuery();
			ArrayList Rows = new ArrayList();
			while (result.next()) {
				// System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
				for (int i = 1; i <= 1; i++) {
					row.add(result.getString("id"));
					row.add(result.getString("course"));
					row.add(result.getString("instructor"));
					row.add(result.getString("term"));
					row.add(result.getString("time"));
					row.add(result.getString("location"));
					row.add(result.getString("unit"));
					row.add(result.getString("status"));
				}
				Rows.add(row);
				enrollCourseList = Rows;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	@FXML
	private void onSelectDept(){		
	}
	@FXML
	private void onSelectCourseName(){			
	}
	@FXML
	private void onSelectDeptName(){		
	}
	@FXML
	private void onSelectCareer(){		
	}
	@FXML
	private void downnloadCSV(){		
	}
	// For upload CSV 
	@FXML
	private void onSelectTermUpload(){		
	}
	@FXML
	private void onSelectDeptUpload(){		
	}
	@FXML
	private void onSelectCourseNameUpload(){			
	}
	@FXML
	private void onSelectDeptNameUpload(){		
	}
	@FXML
	private void onSelectCareerUpload(){		
	}
	@FXML
	private String clickOnFileChooser(){
		String fileName = "GradeTemplate.csv";
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Grade Template");
		File file = fileChooser.showOpenDialog(stage);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));				
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("","*.CSV"));
		
		
		 if (file != null) {
             openFile(file);
         }
		String selectedPath  = file.getPath();
		
		if(fileChooser.getSelectedExtensionFilter().getExtensions().equals(fileName)){
			
			System.out.println(file);
			
		}else{
			System.out.println("wrong file");
		}
				
		return selectedPath;
	}
	@FXML
	private void UploadCSV(){		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 
		TermInfoModel termDetail = new TermInfoModel();
		ArrayList temtermlist = termDetail.termList();
		//System.out.println("Only term"+temtermlist.);
		
		//for()
		
		
		ObservableList termlist = FXCollections.observableArrayList();
		termlist.addAll("Summer 2017","Fall 2017");
		cbSelectTerm.setItems(termlist);
		System.out.println(temtermlist);
		
		
		
		
		
		
		
		
	}
	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
            		clickOnFileChooser()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
	
	
	

}
