package model.vo;

public class ConversaoVO {
  private Double valor1, valor2;
  private Long id;
  private String cliente, moeda, tag;

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getMoeda() {
    return moeda;
  }

  public void setMoeda(String moeda) {
    this.moeda = moeda;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getValor1() {
    return valor1;
  }

  public void setValor1(Double valor1) {
    this.valor1 = valor1;
  }

  public Double getValor2() {
    return valor2;
  }

  public void setValor2(Double valor2) {
    this.valor2 = valor2;
  }

}