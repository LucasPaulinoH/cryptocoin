package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bo.ClienteBO;
import model.bo.ConversaoBO;
import model.dao.CoinConnection;
import model.vo.ClienteVO;
import model.vo.ConversaoVO;
import model.vo.Lista;
import model.vo.MoedaVO;
import view.Telas;

public class TelaHistoricoController extends Telas implements Initializable {
  @FXML
  private TableView<ConversaoVO> tabelaHistorico;
  @FXML
  private TableColumn<ConversaoVO, Double> valor1, valor2;
  @FXML
  private TableColumn<ConversaoVO, String> criptomoeda;

  @FXML
  private ChoiceBox<String> cripto;

  @FXML
  private Button sair, buscar;

  CoinConnection conn = new CoinConnection();
  Lista<MoedaVO> lista = new Lista();

  public void loadCB() {
    lista = conn.listaRS;
    for (int i = 1; i <= lista.size; i++) {
      cripto.getItems().add(lista.search(i).getTag());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadCB();

    String criptoSelecionada = cripto.getValue();
    // tabelaHistorico.setItems(lista2(criptoSelecionada));

    valor1.setCellValueFactory(new PropertyValueFactory<ConversaoVO, Double>("valor1"));
    valor2.setCellValueFactory(new PropertyValueFactory<ConversaoVO, Double>("valor2"));
    criptomoeda.setCellValueFactory(new PropertyValueFactory<ConversaoVO, String>("tag"));

    try {
      tabelaHistorico.setItems(lista());
    } catch (Exception e) {
      e.printStackTrace();
    }
     
  }

  private ObservableList<ConversaoVO> lista() throws Exception {
    ConversaoBO bo = new ConversaoBO();
    List<ConversaoVO> list = bo.buscarTudo();
    ObservableList<ConversaoVO> conversoesList = FXCollections.observableArrayList(list);
    return conversoesList;
  }

  private ObservableList<ConversaoVO> lista2() throws Exception {
    String criptoSelecionada = cripto.getValue();
    ConversaoVO vo = new ConversaoVO();
    vo.setTag(criptoSelecionada);
    
    ConversaoBO bo = new ConversaoBO();
    List<ConversaoVO> list2 = bo.buscarPorTag(vo);
    ObservableList<ConversaoVO> conversoesList = FXCollections.observableArrayList(list2);
    return conversoesList;
  }

  public void buscar(ActionEvent event) throws Exception{
    tabelaHistorico.setItems(lista2());
  }

  public void toMercado(ActionEvent event) throws Exception {
    telaMercado();
  }

  public void toConversoes(ActionEvent event) throws Exception {
    telaConversoes();
  }

  public void sair(ActionEvent event) throws Exception {
    telaLogin();
  }

}