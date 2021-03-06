package tic1.client.ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Cinema;
import tic1.client.models.Funcion;
import tic1.client.models.Movie;
import tic1.client.services.CinemaRestTemplate;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.services.TransaccionRestTemplate;
import tic1.client.ui.adds.AddFunctionController;
import tic1.client.ui.adds.AddManagerController;
import tic1.client.ui.adds.AddSalaController;
import tic1.client.ui.client.EndUserController;
import tic1.client.ui.login.LoginController;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class PrincipalManagerController implements Initializable {
    @Autowired
    private FuncionRestTemplate funcionRestTemplate;
    @Autowired
    private TransaccionRestTemplate transaccionRestTemplate;
    @Autowired
    private CinemaRestTemplate cinemaRestTemplate;
    @FXML
    private JFXComboBox<Cinema> cinemas;
    @FXML
    private Label money;
    @FXML
    private TableView<Funcion> functionTable;

    @FXML
    private TableColumn<Funcion, Movie> colMovie;

    @FXML
    private TableColumn<Funcion, Long> colSalaId;

    @FXML
    private TableColumn<Funcion, LocalDateTime> colDate;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Tab functionTab;

    @FXML
    private MenuItem edit;

    @FXML
    private MenuItem delete;

    @FXML
    private MenuButton dropdownMenu;

    private ClientApplication clientApplication;

    @FXML
    private JFXTextField filter;

    private int actualPage;

    @FXML
    private TextField pages;

    @Autowired
    public PrincipalManagerController(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }


    private ObservableList<Funcion> functionList = FXCollections.observableArrayList();

    private void loadFunction(int page) {
        functionList.clear();
        functionList.addAll(funcionRestTemplate.findAllByProviderIdPaged(ClientApplication.userManager.getProvider(), page));

        functionTable.setItems(functionList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actualPage = 0;
        functionTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        colMovie.setCellValueFactory(new PropertyValueFactory<>("movie"));
        colSalaId.setCellValueFactory(new PropertyValueFactory<>("salaId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMovie.setCellFactory(col -> new TableCell<Funcion, Movie>() {
            @Override
            public void updateItem(Movie movie, boolean empty) {
                super.updateItem(movie, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(movie.getName());
                }
            }
        });

        loadFunction(0);

        FilteredList<Funcion> filteredData = new FilteredList<>(functionList, e -> true);

        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(function -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (function.getMovie().getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } /*else if () {

            }*/
                return false;
            });
        });
        SortedList<Funcion> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(functionTable.comparatorProperty());
        functionTable.setItems(sortedData);
    }

    public void refreshTable(int page) {
        functionList.clear();
        functionList.addAll(funcionRestTemplate.findAllByProviderIdPaged(ClientApplication.userManager.getProvider(), page));

        functionTable.setItems(functionList);

    }

    @FXML
    private void createSala(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddSalaController.class.getResourceAsStream("/movie_crud/ui/adds/AddSala.fxml"));

        AddSalaController salaController = fxmlLoader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void addManager(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddManagerController.class.getResourceAsStream("/movie_crud/ui/adds/AddManager.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void deleteFunction(ActionEvent event) {
       /* //Fetch the selected row
        Funcion selectedForDeletion = functionTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Eliminando Funcion...");
        alert.setContentText("Seguro quieres eliminar " + selectedForDeletion.getName() + " de manera permanente?");
        AlertMaker.styleAlert(alert);
        Optional<ButtonType> answer = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        if (answer.get() == ButtonType.OK) {
            funcionRestTemplate.deleteFuncion(selectedForDeletion.getId());
            // movieRestTemplate.deleteMovie(selectedForDeletion.getId()); todo id to movie
            alert1.setContentText("Pelicula " +  selectedForDeletion.getName() + " borrada con exito.");
            functionList.remove(selectedForDeletion);

        } else {
            alert1.setContentText("Borrado cancelado.");
        }*/
    }

    @FXML
    public void editFunction(ActionEvent event) {
        //Fetch the selected row
        Funcion selectedForEdit = functionTable.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

            Parent root = fxmlLoader.load(AddFunctionController.class.getResourceAsStream("/movie_crud/ui/adds/AddFunction.fxml"));

            AddFunctionController addFunctionController = fxmlLoader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            addFunctionController.loadFuncionData(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Editar Funcion");
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void createFunction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(AddFunctionController.class.getResourceAsStream("/movie_crud/ui/adds/AddFunction.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);
        stage.show();
        refreshTable(actualPage);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {

        ClientApplication.userManager = null;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(LoginController.class.getResourceAsStream("/movie_crud/ui/login/Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        Stage stage1 = (Stage) dropdownMenu.getScene().getWindow();
        stage1.close();

    }

    @FXML
    private void goToMain(ActionEvent event) throws IOException {
        ClientApplication.userManager = null;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);
        Parent root = fxmlLoader.load(EndUserController.class.getResourceAsStream("/movie_crud/ui/client/EndUser.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        Stage stage1 = (Stage) dropdownMenu.getScene().getWindow();
        stage1.close();
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void consultar(ActionEvent actionEvent) throws IOException {
        // money.setVisible(false);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(ClientApplication.getContext()::getBean);

        Parent root = fxmlLoader.load(PrincipalManagerController.class.getResourceAsStream("/movie_crud/ui/client/ConsultaSaldo.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/movie_crud/ui/styles/dark-theme.css");
        stage.setScene(scene);


        ObservableList<Cinema> cinemas =
                FXCollections.observableArrayList(cinemaRestTemplate.getByProvider(ClientApplication.userManager.getProvider()));

        this.cinemas.setItems(cinemas);
        Callback<ListView<Cinema>, ListCell<Cinema>> factory1 = lv1 -> new ListCell<Cinema>() {

            @Override
            protected void updateItem(Cinema item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }

        };

        this.cinemas.setCellFactory(factory1);
        this.cinemas.setButtonCell(factory1.call(null));

        stage.show();
    }


    public void cachin(ActionEvent actionEvent) {

        String a = transaccionRestTemplate.cachin(this.cinemas.getSelectionModel().getSelectedItem().getId());
        money.setText("" + transaccionRestTemplate.cachin(this.cinemas.getSelectionModel().getSelectedItem().getId()));
        money.setVisible(true);
    }

    @FXML
    private void nextPage(ActionEvent event) {
        actualPage++;
        pages.setText(String.valueOf(actualPage));
        refreshTable(actualPage);

    }

    @FXML
    private void previousPage(ActionEvent event) {
        if (actualPage > 0) actualPage--;
        pages.setText(String.valueOf(actualPage));
        refreshTable(actualPage);

    }
}
