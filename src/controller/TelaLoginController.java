package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import model.bo.ClienteBO;
import model.vo.ClienteVO;

import view.Telas;

public class TelaLoginController extends Telas {

  /* Assests da tela de login */
  @FXML
  private TextField login, senha;
  @FXML
  private Label erroAut;
  @FXML
  private Button botaoToCadastrar, entrar;


  ClienteBO cliBO = new ClienteBO();

  /* ===== Este método realiza a autenticação da tela de login ===== */
  public void autenticar(ActionEvent event) throws Exception {
    ClienteVO vo = new ClienteVO();
    vo.setLogin(login.getText());
    vo.setSenha(senha.getText());

    try {
      ClienteVO autenticado = cliBO.buscarPorLogin(vo);
      if (autenticado.getLogin().equals(vo.getLogin()) && autenticado.getSenha().equals(vo.getSenha())) {
          telaConversoes();
      } else
        erroAut.setVisible(true);

    } catch (Exception e) {
      erroAut.setVisible(true);
      e.printStackTrace();
    }
  }

  public void toCadastrar(ActionEvent event) throws Exception{
    telaCadastro();
  }
  
}
