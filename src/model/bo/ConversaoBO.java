package model.bo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dao.ConversaoDAO;
import model.dao.BaseInterDAO;
import model.vo.ConversaoVO;

public class ConversaoBO implements BaseInterBO<ConversaoVO> {
    BaseInterDAO<ConversaoVO> dao = new ConversaoDAO<>();

    public void inserir(ConversaoVO vo) throws Exception {
        try {
            ResultSet rs = dao.buscarPorId(vo);
            if (rs.next()) {
                throw new Exception("Impossível registrar conversão");
            } else {
                dao.inserir(vo);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ConversaoVO> buscarPorTag(ConversaoVO vo) throws Exception {
        try {
            List<ConversaoVO> list = new ArrayList<>();
            ConversaoDAO<ConversaoVO> dao2 = new ConversaoDAO<>();
            ResultSet rs = dao2.buscarPorTag(vo);
            if (rs.next() == false) {
                throw new Exception("Erro ao listar, Tag não encontrada.");
            } else {
                while (rs.next()) {
                    ConversaoVO vo2 = new ConversaoVO();
                    vo2.setId(rs.getLong("id"));
                    vo2.setValor1(rs.getDouble("valor1"));
                    vo2.setValor2(rs.getDouble("valor2"));
                    vo2.setCliente(rs.getString("cliente"));
                    vo2.setMoeda(rs.getString("moeda"));
                    vo2.setTag(rs.getString("tag"));

                    list.add(vo2);

                    System.out.println("Id: " + vo2.getId());
                    System.out.println("Valor1: " + vo2.getValor1());
                    System.out.println("Valor2: " + vo2.getValor2());
                    System.out.println("Cliente: " + vo2.getCliente());
                    System.out.println("Moeda: " + vo2.getMoeda());
                    System.out.println("Tag: " + vo2.getTag());
                }
                return list;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<ConversaoVO> buscarTudo() throws Exception {
        try {
            List<ConversaoVO> list = new ArrayList<ConversaoVO>();
            ResultSet rs = dao.buscarTudo();
            if (rs.next() == false) {
                throw new Exception("Erro ao listar, ID não encontrado");
            } else {
                while (rs.next()) {
                    ConversaoVO vo2 = new ConversaoVO();
                    vo2.setId(rs.getLong("id"));
                    vo2.setValor1(rs.getDouble("valor1"));
                    vo2.setValor2(rs.getDouble("valor2"));
                    vo2.setTag(rs.getString("tag"));
                    vo2.setCliente(rs.getString("cliente"));

                    list.add(vo2);

                    System.out.println("Id: " + vo2.getId());
                    System.out.println("Valor1: " + vo2.getValor1());
                    System.out.println("Valor2: " + vo2.getValor2());
                    System.out.println("Tag: " + vo2.getTag());
                    System.out.println("Cliente: " + vo2.getCliente());
                }
                return list;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<ConversaoVO> buscarPorCliente(ConversaoVO vo) throws Exception {
        try {
            List<ConversaoVO> list = new ArrayList<>();
            ConversaoDAO<ConversaoVO> dao2 = new ConversaoDAO<>();
            ResultSet rs = dao2.buscarPorCliente(vo);
            if (rs.next() == false) {
                throw new Exception("Erro ao listar, conversao não encontrada.");
            } else {
                while (rs.next()) {
                    ConversaoVO vo2 = new ConversaoVO();
                    vo2.setId(rs.getLong("id"));
                    vo2.setValor1(rs.getDouble("valor1"));
                    vo2.setValor2(rs.getDouble("valor2"));
                    vo2.setCliente(rs.getString("cliente"));
                    vo2.setMoeda(rs.getString("moeda"));
                    vo2.setTag(rs.getString("tag"));

                    list.add(vo2);

                    System.out.println("Id: " + vo2.getId());
                    System.out.println("Valor1: " + vo2.getValor1());
                    System.out.println("Valor2: " + vo2.getValor2());
                    System.out.println("Cliente: " + vo2.getCliente());
                    System.out.println("Moeda: " + vo2.getMoeda());
                    System.out.println("Tag: " + vo2.getTag());
                }
                return list;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void remover(ConversaoVO vo) throws Exception {
        try {
            ResultSet rs = dao.buscarPorId(vo);
            if (rs.next() == false) {
                throw new Exception("ID não encontrado, impossível remover");
            } else {
                dao.remover(vo);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removerTudo() throws Exception {
        try {
            ((ConversaoBO) dao).removerTudo();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void editar(ConversaoVO vo) throws Exception {
        try {
            ResultSet rs = dao.buscarPorId(vo);
            if (rs.next() == false) {
                throw new Exception("ID não encontrado, impossível editar");
            } else {
                dao.editar(vo);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ConversaoVO buscarPorId(ConversaoVO entity) throws Exception {
        return null;
    }
}