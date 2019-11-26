package tic1.client.ui;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tic1.client.ClientApplication;
import tic1.client.models.Funcion;
import tic1.client.models.Movie;
import tic1.client.services.FuncionRestTemplate;
import tic1.client.ui.adds.AddAdminController;
import tic1.client.ui.adds.AddFunctionController;
import tic1.client.ui.adds.AddManagerController;
import tic1.client.ui.adds.AddSalaController;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Controller
public class PrincipalManagerController implements Initializable {

    @Autowired
    private FuncionRestTemplate funcionRestTemplate;

    @FXML
    private TableView<Funcion> functionTable;

    @FXML
    private TableColumn<Funcion, Movie> colMovie;

    @FXML
    private TableColumn<Funcion, Long> colSalaID;

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

    private ClientApplication clientApplication;

    @FXML
    private JFXTextField filter;

    @Autowired
    public PrincipalManagerController(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }


    private ObservableList<Funcion> functionList = FXCollections.observableArrayList();

    private void loadFunction() {
        functionList.clear();
//        functionList.addAll(funcionRestTemplate.);

        functionTable.setItems(functionList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       functionTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        /*colMovie.setCellValueFactory(new PropertyValueFactory<>("movie"));
        colSalaID.setCellValueFactory(new PropertyValueFactory<>("salaId"));
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
        });*/
        loadFunction();

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

    public void refreshTable() {
        functionList.clear();
//        functionList.addAll(funcionRestTemplate.findAllPaged(0));

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
    }

    @FXML
    private void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
