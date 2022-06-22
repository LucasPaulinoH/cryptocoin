package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.dao.ClienteDAO;
import model.vo.ClienteVO;
import view.Telas;

public class TelaCadastroClienteController extends Telas {
  @FXML
  private TextField nomeCadastro, cpfCadastro, nomeLoginCadastro, senhaCadastro;
  @FXML
  private Button cadastrar, voltar;
  @FXML private Label usuCadastrado;

  public void cadastrar(ActionEvent event) {
    ClienteVO vo = new ClienteVO();
    vo.setNome(nomeCadastro.getText());
    vo.setCpf(cpfCadastro.getText());
    vo.setLogin(nomeLoginCadastro.getText());
    vo.setSenha(senhaCadastro.getText());

    ClienteDAO<ClienteVO> dao = new ClienteDAO<>();
    dao.inserir(vo);
    usuCadastrado.setVisible(true);

  }

  public void voltar() {
    try {
      telaLogin();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
