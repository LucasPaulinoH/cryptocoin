package model.vo;

public class MoedaVO {
  private String nome, tag;
  private Double preco, var24;

  public MoedaVO(String nome, String tag, Double preco, Double var24) {
    this.nome = nome;
    this.tag = tag;
    this.preco = preco;
    this.var24 = var24;
  }

  public MoedaVO() {
  }

  public MoedaVO(String nome2, String tag2, Double preco2) {
  }

  /*
   * converte o dinheiro da carteira ($) na quantia de cryptocoins,
   * Ex.: o usuário tem $50, o quantia em crypto seria 50/(preço da
   * moeda naquele momento)
   */
  public Double precoMoeda(Double i) {
    if (this.preco >= 1)
      return i / this.preco;
    else
      return i * this.preco;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public Double getVar24() {
    return var24;
  }

  public void setVar24(Double var24) {
    this.var24 = var24;
  }
}
