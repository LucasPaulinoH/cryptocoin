package model.dao;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import model.vo.Lista;
import model.vo.MoedaVO;

/* não alterar mais */
public class CoinConnection {
  public final String url = "https://crypto.com/price";
  public Lista<MoedaVO> listaRS = conectar();

  public Lista<MoedaVO> conectar() {
    MoedaVO moeda;
    Lista<MoedaVO> lista = new Lista<MoedaVO>();
    try {
      // atribui o valor da url a um documento
      final Document document = Jsoup.connect(url).get();

      // percorre a tabela do site
      for (org.jsoup.nodes.Element row : document
          .select("table.chakra-table.css-1bveei3 tr")) {
        if (row.select(".css-1kvo0ra").text().equals("")) {
          continue;
        } else {
          // pega o nome de cada criptomoeda
          final String nome = row.select(
              ".css-o2rp9n.chakra-text")
              .text();

          // pega a tag de cada moeda
          final String tag = row.select(".css-ft1qn5.chakra-text").text();

          // pega o preço atual de cada moeda
          String preco_usd = row.select(".css-b1ilzc").text();
          preco_usd = preco_usd.replace("$", ""); // remove os '$'
          preco_usd = preco_usd.replace(",", ""); // remove os ','

          // salva os preços como double
          final Double preco = Double.parseDouble(preco_usd);

          // pega a variação de cada moeda nas últimas 24h (com o símbolo de %)
          String var24_perc = row.select("td.css-1b7j986:nth-of-type(5)").text();
          var24_perc = var24_perc.replaceAll("%", ""); // remove os '%'

          // salva a variação como double
          final Double var24 = Double.parseDouble(var24_perc);

          moeda = new MoedaVO(nome, tag, preco, var24);
          lista.addLast(moeda);
        }
      }
      return lista;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void mostrarMoedas(Lista<MoedaVO> lista) {
    for (int i = 1; i <= lista.size; i++) {
      System.out.println(lista.search(i).getNome() + " | " +
          lista.search(i).getTag() + " | " +
          lista.search(i).getPreco() + " | " +
          lista.search(i).getVar24());
    }
  }

  public ArrayList<MoedaVO> toArrayList(Lista<MoedaVO> lista) {
    List<MoedaVO> array = new ArrayList<>();
    for (int i = 0; i < lista.size+1; i++) {
      array.add(i, lista.search(i));
    }

    array.remove(0); // remove a parte nula

    return (ArrayList<MoedaVO>) array;
  }
}
