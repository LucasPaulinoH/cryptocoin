package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import model.dao.CoinConnection;
import model.dao.ConversaoDAO;
import model.vo.ConversaoVO;
import model.vo.Lista;
import model.vo.MoedaVO;

import view.Telas;

public class TelaConversoesController extends Telas implements Initializable {

  @FXML
  private TextField valor, nome;
  @FXML
  private Label resultMessage, label1, label2;
  @FXML
  private Button converter, mercadoMenu, historicoMenu, sair1, confirmar;
  @FXML
  private ChoiceBox<String> moedas;
  @FXML
  private Pane telaHistorico, telaConversoes;

  private Stage stage;
  private Scene cena;

  CoinConnection conn = new CoinConnection();
  Lista<MoedaVO> lista = new Lista();
  List<ConversaoVO> listaConv = new ArrayList<ConversaoVO>();

  public void loadCB() {
    lista = conn.listaRS;
    for (int i = 1; i <= lista.size; i++) {
      moedas.getItems().add(lista.search(i).getTag());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    loadCB();
  }

  public ConversaoVO converter(ActionEvent event) {

    String quantiaS = valor.getText();
    Double quantia = Double.parseDouble(quantiaS); // quantia a ser convertida
    Double resultado;

    MoedaVO moeda = new MoedaVO();

    String criptoSelecionada = moedas.getValue();

    try {
      int i;
      // pega o preço da moeda selecionado no ChoiceBox verificando sua tag
      for (i = 1; i <= lista.size; i++) {
        if (lista.search(i).getTag().equals(criptoSelecionada)) {
          moeda.setNome(lista.search(i).getNome());
          moeda.setTag(lista.search(i).getTag());
          moeda.setPreco(lista.search(i).getPreco());
          break;
        }
      }

      resultado = moeda.precoMoeda(quantia);

      ConversaoVO conversao = new ConversaoVO();

      conversao.setValor1(quantia);
      conversao.setValor2(resultado);
      conversao.setTag(moeda.getTag());
      conversao.setCliente(nome.getText());
      conversao.setMoeda(moeda.getNome());

      ConversaoDAO dao = new ConversaoDAO();
      dao.inserir(conversao);

      resultMessage.setText(quantia + " $USD" + "\n = \n"
          + resultado + " " + criptoSelecionada);
      resultMessage.setVisible(true);

      return conversao;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void confirmar(ActionEvent event) throws Exception {
    nome.setVisible(false);
    confirmar.setVisible(false);
    label2.setVisible(false);

    valor.setVisible(true);
    converter.setVisible(true);
    
    label1.setVisible(true);
    moedas.setVisible(true);
  }

  public void toMercado(ActionEvent event) throws Exception {
    telaMercado();
  }

  public void toHistorico(ActionEvent event) throws Exception {
    telaHistorico();
  }

  public void sair(ActionEvent event) throws Exception {
    telaLogin();
  }

}
