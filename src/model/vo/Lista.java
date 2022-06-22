package model.vo;

public class Lista<T> {
  class Node {
    int id;
    T data;
    Node next, prev;

    public Node(T data) {
      this.id = nextId;
      nextId = nextId + 1;

      this.data = data;
      this.next = null;
      this.prev = null;
    }
  }

  public Node head;
  public Node tail;
  public int nextId;
  public int size;

  public Lista() {
    head = null;
    tail = null;
    nextId = 1;
    size = 0;
  }

  public void show() {
    Node p = head;

    if (p == null)
      System.out.println("Lista vazia.");
    else {
      while (p != null) {
        System.out.println("Dado: " + p.data);
        p = p.next;
      }
    }

    System.out.println("size = " + size + "\n");
  }

  public void addFirst(T dado) {
    Node novo = new Node(dado);

    if (head == null) {
      head = novo;
      tail = novo;
    } else {
      novo.next = head;
      head.prev = novo;
      head = novo;
    }

    size++;
  }

  public void addLast(T dado) {
    Node novo = new Node(dado);

    if (head == null) {
      head = novo;
      tail = novo;
    } else {
      novo.prev = tail;
      tail.next = novo;
      tail = novo;
    }
    size++;
  }

  public void addAfter(T dado, int idCrit) {
    Node p = searchNode(idCrit);

    if (p == null) {
      System.out.println("Critério inválido.");
    } else {
      Node novo = new Node(dado);

      if (p.next == null)
        tail = novo;

      novo.next = p.next;
      novo.prev = p;
      p.next = novo;

      Node frente = novo.next;

      if (frente != null)
        frente.prev = novo;

      size++;
    }
  }

  public T peekFirst() {
    if (head == null) {
      System.out.println("Lista vazia.");
      return null;
    } else
      return head.data;
  }

  public T peekLast() {
    if (tail == null) {
      System.out.println("Lista vazia.");
      return null;
    } else
      return tail.data;
  }

  private Node searchNode(int id) {
    Node p = head;

    while (p != null) {
      if (p.id == id) {
        return p;
      }
      p = p.next;
    }

    return null;
  }

  public T search(int id) {
    Node p = searchNode(id);

    if (p == null) {
      return null;
    } else
      return p.data;
  }

  public T removeFirst() {
    Node p = head;
    T dadoRetorno = null;

    if (head == null)
      System.out.println("Lista vazia.");
    else {
      dadoRetorno = p.data;

      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head = head.next;
        head.prev = null;
      }

      p.next = null;

      size--;
    }

    return dadoRetorno;
  }

  public T removeLast() {
    T dadoRetorno = null;

    if (tail == null) {
      System.out.println("Lista vazia.");
      return null;
    } else {
      dadoRetorno = tail.data;

      if (head == tail) {
        head = null;
        tail = null;
      } else {
        Node anterior = tail.prev;
        tail.prev = null;
        tail = anterior;
        tail.next = null;
      }
      size--;
    }
    return dadoRetorno;
  }

  public T remove(int id) {
    T dadoRetorno = null;

    if (head == null) {
      System.out.println("Lista vazia.");
      return null;
    }

    Node anterior = null;
    Node removido = searchNode(id);

    if (removido != null) {
      anterior = removido.prev;
    }

    if (anterior == null) {
      if (head.id != id) {
        System.out.println("ID inexistente.");
        return null;
      } else {
        removido = head;

        if (head == tail) {
          head = null;
          tail = null;
        } else {
          head = head.next;
          head.prev = null;
          removido.next = null;
        }
      }
    } else {
      removido = anterior.next;

      if (removido == tail) {
        tail.prev = null;
        tail = anterior;
        tail.next = null;
      } else {
        Node frente = removido.next;

        anterior.next = frente;
        frente.prev = anterior;
        removido.next = null;
        removido.prev = null;
      }
    }

    dadoRetorno = removido.data;
    size--;

    return dadoRetorno;
  }
}