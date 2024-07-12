package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.hauhh.pojo.Category;
import com.hauhh.pojo.Product;
import com.hauhh.repository.ICategoryRepository;
import com.hauhh.repository.IProductRepository;
import com.hauhh.repository.impl.CategoryRepositoryImpl;
import com.hauhh.repository.impl.ProductRepositoryImpl;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.Callback;

public class AdminController implements Initializable {

	@FXML
	private Button btnAddProduct;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button updateBtn;

	@FXML
	private TableColumn<Product, String> categoryCol;

	@FXML
	private TableColumn<Product, LocalDate> dateCreatedCol;

	@FXML
	private TableColumn<Product, String> descriptionCol;

	@FXML
	private TableColumn<Product, Double> priceCol;

	@FXML
	private TableColumn<Product, Long> productIDCol;

	@FXML
	private TableColumn<Product, String> productNameCol;

	@FXML
	private TableView<Product> productView;

	private ObservableList<Product> productList;

	@FXML
	private TableColumn<Product, Integer> quantityCol;

	@FXML
	private TextField searchField;

	@FXML
	private ChoiceBox<String> txtCategory;

	@FXML
	private DatePicker txtDateCreated;

	@FXML
	private TextArea txtDescription;

	@FXML
	private TextField txtPrice;

	@FXML
	private TextField txtProductName;

	@FXML
	private TextField txtQuantity;

	private IProductRepository productRepository = null;

	private ICategoryRepository categoryRepository = null;

	private Product product = null;

	public AdminController() {
		product = new Product();
		categoryRepository = new CategoryRepositoryImpl("hibernate.cfg.xml");
		productRepository = new ProductRepositoryImpl("hibernate.cfg.xml");
		productList = FXCollections.observableArrayList(productRepository.listProduct());
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		productView();
		btnAddProduct.setDisable(false);
		updateBtn.setDisable(true);
		deleteBtn.setDisable(true);
		List<Category> categories = categoryRepository.listCategory();

		List<String> categoryNameList = categories.stream().map(Category::getCategoryName).collect(Collectors.toList());

		txtCategory.setItems(FXCollections.observableArrayList(categoryNameList));

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				filterData(newValue);
			}
		});

	}

	private void filterData(String value) {
		ObservableList<Product> filter = FXCollections.observableArrayList();
		List<Product> list = productRepository.listProduct();
		for (Product product : list) {
			if ((product.getProductName().toLowerCase().contains(value.toLowerCase()))
					|| (product.getDescription().toLowerCase().contains(value.toLowerCase()))
					|| (product.getCategoryID().getCategoryName().toLowerCase().contains(value.toLowerCase()))) {
				filter.add(product);
			}
		}
		productView.setItems(filter);
	}

	@FXML
	private void refreshTable() {
		productList.removeAll(productList);
		productList.addAll(productRepository.listProduct());
		productView.refresh();
	}

	@FXML
	private void productView() {
		productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		categoryCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Product, String> param) {
						return new SimpleStringProperty(param.getValue().getCategoryID().getCategoryName());
					}
				});
		dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
		productView.setItems(productList);
	}

	@FXML
	private void selectedProduct(MouseEvent event) {
		try {
			Product product = productView.getSelectionModel().getSelectedItem();
			product = new Product(product.getProductID(), product.getProductName(), product.getDescription(),
					product.getPrice(), product.getQuantity(), product.getCategoryID(), product.getDateCreated());
			this.product = product;

			txtProductName.setText(product.getProductName());
			txtDescription.setText(product.getDescription());
			txtPrice.setText(String.valueOf(product.getPrice()));
			txtQuantity.setText(String.valueOf(product.getQuantity()));
			txtDateCreated.setValue(product.getDateCreated());
			txtCategory.setValue(product.getCategoryID().getCategoryName());

			updateBtn.setDisable(false);
			deleteBtn.setDisable(false);
			btnAddProduct.setDisable(true);

		} catch (Exception e) {
			System.out.println("Error at selected Customer: " + e.getMessage());
		}
	}

	private void showAleart(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(content);
		alert.setHeaderText(header);
		alert.showAndWait();
	}

	private void clearField() {
		txtProductName.clear();
		txtDescription.clear();
		txtPrice.clear();
		txtQuantity.clear();
		txtDateCreated.setValue(null);
		txtCategory.setValue(null);

		btnAddProduct.setDisable(false);
		updateBtn.setDisable(true);
		deleteBtn.setDisable(true);
	}

	@FXML
	private void addProduct() {
		try {
			String productName = txtProductName.getText();
			String description = txtDescription.getText();
			double price = Double.parseDouble(txtPrice.getText());
			int quantity = Integer.parseInt(txtQuantity.getText());
			String categoryName = txtCategory.getValue();
			LocalDate dateCreated = txtDateCreated.getValue();

			Category category = categoryRepository.getCategoryByName(categoryName);

			Product product = new Product(productName, description, price, quantity, category, dateCreated);

			Product savedProduct = productRepository.saveProduct(product);
			if (savedProduct != null) {
				showAleart("Add Product Information", "Add product successfully");
			} else {
				showAleart("Add Product Information", "Add product faild");
			}
			clearField();
			refreshTable();
		} catch (Exception e) {
			System.out.println("Error at addProduct: " + e.getMessage());
		}
	}

	@FXML
	private void updateProduct() {
		try {
			if (this.product != null) {
				this.product.setDateCreated(txtDateCreated.getValue());
				this.product.setDescription(txtDescription.getText());
				this.product.setPrice(Double.valueOf(txtPrice.getText()));
				this.product.setProductName(txtProductName.getText());
				this.product.setQuantity(Integer.valueOf(txtQuantity.getText()));
				Category categoryName = categoryRepository.getCategoryByName(txtCategory.getValue());
				this.product.setCategoryID(categoryName);

				productRepository.updateProduct(this.product);

				System.out.println("Update product successfully");
				showAleart("Update product information",
						"Successfully updated product with ID: " + this.product.getProductID());
				refreshTable();
				clearField();
			} else {
				showAleart("Update product information", "No product were selected!!");
			}
		} catch (Exception e) {
			System.out.println("Error at updateProduct" + e.getMessage());
		}
	}

	@FXML
	private void deleteProduct() {
		try {
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setTitle("Confirm delete customer");
			dialog.setHeaderText("Are you sure you want to delete this product");
			dialog.initModality(Modality.APPLICATION_MODAL);

			ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
			ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
			Optional<ButtonType> result = dialog.showAndWait();
			if (this.product != null && result.isPresent() && result.get() == okButton) {
				productRepository.deleteProduct(this.product.getProductID());
				System.out.println("Delete customer successfully");
				showAleart("Delete product information", "Successfully deleted product");
				refreshTable();
				clearField();
			} else {
				showAleart("Delete product information", "No product selected");
			}
		} catch (Exception e) {
			System.out.println("Error at deleteProduct" + e.getMessage());
		}
	}

}
