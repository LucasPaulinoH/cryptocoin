package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.bo.ConversaoBO;
import model.dao.CoinConnection;
import model.vo.ClienteVO;
import model.vo.ConversaoVO;
import model.vo.Lista;
import model.vo.MoedaVO;
import view.Telas;

public class TelaMercadoController extends Telas implements Initializable {

  @FXML
  private TableView<MoedaVO> tabelaMoedas;
  @FXML
  private TableColumn<MoedaVO, String> nome;
  @FXML
  private TableColumn<MoedaVO, String> tag;
  @FXML
  private TableColumn<MoedaVO, Double> preco;
  @FXML
  private TableColumn<MoedaVO, Double> var24;

  @FXML
  private Button converterMenu, historicoMenu, refresh, gerarRelatorio, sair;

  CoinConnection conn = new CoinConnection();
  Lista<MoedaVO> minhaLista = new Lista();

  ObservableList<MoedaVO> lista;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    minhaLista = conn.listaRS;
    lista = FXCollections.observableArrayList(conn.toArrayList(minhaLista));

    nome.setCellValueFactory(new PropertyValueFactory<MoedaVO, String>("nome"));
    tag.setCellValueFactory(new PropertyValueFactory<MoedaVO, String>("tag"));
    preco.setCellValueFactory(new PropertyValueFactory<MoedaVO, Double>("preco"));
    var24.setCellValueFactory(new PropertyValueFactory<MoedaVO, Double>("var24"));

    tabelaMoedas.setItems(lista);

  }

  public void atualizar(ActionEvent event) throws Exception {
    conn.conectar();
    telaMercado();
  }

  public void toConversoes(ActionEvent event) throws Exception {
    telaConversoes();
  }

  public void toHistorico(ActionEvent event) throws Exception {
    telaHistorico();
  }

  public void gerarRelatorio() throws ParseException {

    minhaLista = conn.listaRS;
    lista = FXCollections.observableArrayList(conn.toArrayList(minhaLista));

    Rectangle pageSize = new Rectangle(PageSize.A4);
    pageSize.setBackgroundColor(new BaseColor(26, 38, 54));
    Document document = new Document(pageSize);

    try {
      PdfWriter.getInstance(document,
          new FileOutputStream("RelatórioMercado.pdf"));
      document.open();

      try {

        Image img = Image.getInstance("LogoTopo.png");
        document.add(img);

        Font f1 = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, BaseColor.WHITE);
        Paragraph pConversao = (new Paragraph("RELATÓRIO DE MERCADO", f1));
        pConversao.setAlignment(Element.ALIGN_CENTER);
        document.add(pConversao);

        Paragraph vazio = (new Paragraph("       "));
        document.add(vazio);

        Font f2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        for (MoedaVO vo : lista) {
          Paragraph Mercado = new Paragraph(("Moeda: " + vo.getNome() + "  ||  Tag: " + vo.getTag() + "  ||  Valor: "
              + vo.getPreco() + "  ||  Variancia/24h(%): " + vo.getVar24()), f2);
          document.add(Mercado);
          document.add(vazio);

        }
      } catch (DocumentException de) {
        System.err.println(de.getMessage());
      } catch (IOException ioe) {
        System.err.println(ioe.getMessage());
      }
      document.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void sair(ActionEvent event) throws Exception {
    telaLogin();
  }

}