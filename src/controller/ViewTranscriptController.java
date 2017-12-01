package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import model.DAO;
import util.PdfTranscript;
import util.Singleton;

public class ViewTranscriptController extends StackPane implements Initializable {

	@FXML
	private TextField txtSearchStudentID;
	@FXML
	private Button btnViewTranscript;
	@FXML
	private HBox hBoxSearchStudent, hBoxDownload, hBoxView;
	@FXML
	private Label lblErrorTranscript;
	@FXML
	private Hyperlink hLinkDownloadTranscript, hLinkViewTranscript;
	private Window stage;

	public String studentID = Singleton.getInstance().getUserAcessID().getText();
	public String userType = Singleton.getInstance().getUserType().getText();
	public DAO dataAccess = new DAO();
	public int studentIDSearch = 0;

	@FXML
	private void ClickOnBtnSearchStudent() {

		lblErrorTranscript.setText(null);
		studentIDSearch = Integer.parseInt(txtSearchStudentID.getText());

		if (dataAccess.validateStudentForCGPA(studentIDSearch)) {
			hBoxDownload.visibleProperty().set(true);
			hBoxView.visibleProperty().set(true);
			lblErrorTranscript.setText(null);

		} else {
			lblErrorTranscript.setText("Sorry!,No record is avaiable!");
			hBoxDownload.visibleProperty().set(false);
			hBoxView.visibleProperty().set(false);
		}

	}

	@FXML
	private void clickOnDownLoadTranscript() {

		FileChooser fileSave = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF File(*.pdf)", "*.pdf");
		fileSave.getExtensionFilters().add(extFilter);
		File file = fileSave.showSaveDialog(stage);

		if (userType.equals("1")) {
			if (file != null) {
				PdfTranscript pdfTranscript = new PdfTranscript();
				try {
					pdfTranscript.pdfgenrator(Integer.parseInt(studentID), file);
				} catch (NumberFormatException | DocumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else {
			if (file != null) {
				PdfTranscript pdfTranscript = new PdfTranscript();
				try {
					pdfTranscript.pdfgenrator(studentIDSearch, file);
				} catch (NumberFormatException | DocumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	@FXML
	private void clickOnViewTranscript() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/pdfViewer.fxml"));
			Parent pdfview = (Parent) loader.load();
			ViewPdfController pdfReader = loader.getController();
			pdfReader.searchStudentID(studentIDSearch);
			Scene scene = new Scene(pdfview, 600, 800);
			Stage popupLayout = new Stage();
			popupLayout.initModality(Modality.APPLICATION_MODAL);
			// popupLayout.initStyle(StageStyle.UNDECORATED);
			popupLayout.setScene(scene);
			popupLayout.show();
			popupLayout.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					PdfTranscript pdfGenrator = new PdfTranscript();
					pdfGenrator.closeFileOutPutStreaam();
					
				}
			});

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * BorderPane root1; try { root1 =
		 * FXMLLoader.load(getClass().getResource("/view/pdfViewer.fxml"));
		 * Scene scene = new Scene(root1,600, 800);
		 * //scene.getStylesheets().add(getClass().getResource("application.css"
		 * ).toExternalForm());
		 * 
		 * Stage popupLayout = new Stage();
		 * popupLayout.initModality(Modality.APPLICATION_MODAL);
		 * //popupLayout.initStyle(StageStyle.UNDECORATED);
		 * popupLayout.setScene(scene); popupLayout.show(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	public void initialize(URL location, ResourceBundle resources) {

		if (userType.equals("1")) {
			if (dataAccess.validateStudentForCGPA(Integer.parseInt(studentID))) {
				hBoxDownload.visibleProperty().set(true);
				hBoxView.visibleProperty().set(true);
				hBoxSearchStudent.visibleProperty().set(false);

			} else {
				lblErrorTranscript.setText("Sorry!,No record is avaiable!");
			}

		} else {
			txtSearchStudentID.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*") || newValue.equals(null)) {
						txtSearchStudentID.setText(newValue.replaceAll("[^\\d]", ""));
					}

				}

			});
		}

	}

}
