package application;

import com.hauhh.pojo.Account;
import com.hauhh.repository.IAccountRepository;
import com.hauhh.repository.impl.AccountRepositoryImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button loginBtn;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUsername;

	private IAccountRepository accountRepository = null;

	public LoginController() {
		accountRepository = new AccountRepositoryImpl("hibernate.cfg.xml");
	}

	@FXML
	public void handleLogin(ActionEvent event) {
		String email = txtUsername.getText();
		String password = txtPassword.getText();

		Account loginAccount = accountRepository.login(email, password);
		if (loginAccount != null && "admin".equals(loginAccount.getRole())) {
			showAleart("Login Success", "Login successfully.");
			loadAdminView();
		} else {
			showAleart("Login Faild", "Invalid username or password.");
		}
	}

	private void showAleart(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(content);
		alert.setHeaderText(header);
		alert.showAndWait();
	}

	private void loadAdminView() {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Admin View");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
