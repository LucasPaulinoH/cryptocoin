package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas extends Application {
  private static Stage primaryStage;

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  public static void setPrimaryStage(Stage primaryStage) throws Exception {
    Telas.primaryStage = primaryStage;
  }
  @Override
  public void start(Stage primaryStage) throws Exception {
    setPrimaryStage(primaryStage);
    primaryStage.setTitle("cryptocoin");
    primaryStage.show();
    telaLogin();
  }

  public static void main(String[] args) {
    launch(args);
  }

  public static void telaLogin() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaLogin.fxml"));
    Scene cena = new Scene(root);
    primaryStage.setScene(cena);
  }

  public static void telaConversoes() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaConversoes.fxml"));
    Scene cena = new Scene(root);
    primaryStage.setScene(cena);
  }

  public static void telaHistorico() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaHistorico.fxml"));
    Scene cena = new Scene(root);
    primaryStage.setScene(cena);
  }

  public static void telaMercado() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaMercado.fxml"));
    Scene cena = new Scene(root);
    primaryStage.setScene(cena);
  }

  public static void telaCadastro() throws Exception {
    Parent root = FXMLLoader.load(Telas.class.getResource("VE/telaCadastro.fxml"));
    Scene cena = new Scene(root);
    primaryStage.setScene(cena);
  }
}
