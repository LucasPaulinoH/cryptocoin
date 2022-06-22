package model.bo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dao.ClienteDAO;
import model.vo.ClienteVO;

public class ClienteBO implements BaseInterBO<ClienteVO> {

	static private ClienteDAO<ClienteVO> cliDAO = new ClienteDAO<ClienteVO>();

	public void inserir(ClienteVO vo) throws Exception {
		try {
			ResultSet rs = cliDAO.buscarPorId(vo);
			if (rs.next()) {
				throw new Exception("Impossivel cadastrar, Cliente já existente.");
			} else {
				cliDAO.inserir(vo);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	

	public ClienteVO buscarPorId(ClienteVO vo) throws Exception {
		try {
			ClienteVO vo2 = new ClienteVO();
			ResultSet rs = cliDAO.buscarPorId(vo);
			if (rs.next() == false) {
				throw new Exception("Erro ao listar, Cliente não encontrado.");
			} else {
				while (rs.next()) {
					vo2.setClienteId(rs.getLong("cliente_id"));
					vo2.setLogin(rs.getString("usuario_login"));
					vo2.setSenha(rs.getString("usuario_senha"));
					vo2.setNome(rs.getString("pessoa_nome"));
					vo2.setCpf(rs.getString("pessoa_cpf"));

					System.out.println("Id: " + vo2.getClienteId());
					System.out.println("Nome: " + vo2.getNome());
					System.out.println("CPF: " + vo2.getCpf());
					System.out.println("Login: " + vo2.getLogin());
					System.out.println("Senha: " + vo2.getSenha());
				}
				return vo2;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public ClienteVO buscarPorLogin(ClienteVO vo) throws Exception{
		try {
      ClienteVO vo2 = new ClienteVO();
      ClienteDAO<ClienteVO> dao2 = new ClienteDAO<>();
      ResultSet rs = dao2.buscarPorLogin(vo);
      if (rs.next() == false) {
        throw new Exception("Erro ao listar, Usuario não encontrado.");
      } else {
        vo2.setLogin(rs.getString("usuario_login"));
        vo2.setSenha(rs.getString("usuario_senha"));
        vo2.setNome(rs.getString("pessoa_nome"));
        vo2.setCpf(rs.getString("pessoa_cpf"));
        vo2.setClienteId(rs.getLong("cliente_id"));

        while (rs.next()) {
          vo2.setLogin(rs.getString("usuario_login"));
          vo2.setSenha(rs.getString("usuario_senha"));
          vo2.setClienteId(rs.getLong("cliente_id"));
          vo2.setNome(rs.getString("pessoa_nome"));
          vo2.setCpf(rs.getString("pessoa_cpf"));

          System.out.println("Id: " + vo2.getClienteId());
          System.out.println("Login: " + vo2.getLogin());
          System.out.println("Senha: " + vo2.getSenha());
          System.out.println("Nome: " + vo2.getNome());
          System.out.println("CPF: " + vo2.getCpf());

        }
        return vo2;
      }
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
	

	public List<ClienteVO> buscarPorCpf(ClienteVO vo) throws Exception {
		try {
			List<ClienteVO> list = new ArrayList<>();
			ClienteDAO<ClienteVO> dao2 = new ClienteDAO<>();
			ResultSet rs = dao2.buscarPorCpf(vo);
			if (rs.next() == false) {
				throw new Exception("Erro ao listar, Cliente não encontrado.");
			} else {
				while (rs.next()) {
					ClienteVO vo2 = new ClienteVO();
					vo2.setClienteId(rs.getLong("cliente_id"));
					vo2.setNome(rs.getString("pessoa_nome"));
					vo2.setCpf(rs.getString("pessoa_cpf"));

					list.add(vo2);

					System.out.println("Id: " + vo2.getClienteId());
					System.out.println("Nome: " + vo2.getNome());
					System.out.println("CPF: " + vo2.getCpf());
				}
				return list;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<ClienteVO> buscar(ClienteVO vo) throws Exception {
		try {
			List<ClienteVO> list = new ArrayList<>();
			ClienteDAO<ClienteVO> dao2 = new ClienteDAO<>();
			ResultSet rs = dao2.buscarTudo();
			if (rs.next() == false) {
				throw new Exception("Erro ao listar, Cliente não encontrado.");
			} else {
				ClienteVO vo2 = new ClienteVO();
				vo2.setClienteId(rs.getLong("cliente_id"));
				vo2.setNome(rs.getString("pessoa_nome"));
				vo2.setCpf(rs.getString("pessoa_cpf"));

				String nome1, nome2, cpf1, cpf2;

				if (vo.getNome() == null || vo2.getNome() == null) {
					nome1 = "-";
					nome2 = "--";
				} else {
					nome1 = vo.getNome().substring(0, 3);
					nome2 = vo2.getNome().substring(0, 3);
				}

				if (vo.getCpf() == null || vo2.getCpf() == null) {
					cpf1 = "-";
					cpf2 = "--";
				} else {
					cpf1 = vo.getCpf().substring(0, 3);
					cpf2 = vo2.getCpf().substring(0, 3);
				}

				if (nome1.equals(nome2) || cpf1.equals(cpf2)) {
					list.add(vo2);
				}

				while (rs.next()) {
					ClienteVO vo3 = new ClienteVO();
					vo3.setClienteId(rs.getLong("cliente_id"));
					vo3.setNome(rs.getString("pessoa_nome"));
					vo3.setCpf(rs.getString("pessoa_cpf"));

					nome1 = vo.getNome().substring(0, 3);
					nome2 = vo3.getNome().substring(0, 3);

					if (vo.getNome() == null || vo2.getNome() == null) {
						nome1 = "-";
						nome2 = "--";
					} else {
						nome1 = vo.getNome().substring(0, 3);
						nome2 = vo3.getNome().substring(0, 3);
					}

					if (vo.getCpf() == null || vo3.getCpf() == null) {
						cpf1 = "-";
						cpf2 = "--";
					} else {
						cpf1 = vo.getCpf().substring(0, 3);
						cpf2 = vo3.getCpf().substring(0, 3);
					}

					if (nome1.equals(nome2) || cpf1.equals(cpf2)) {
						list.add(vo3);
					}
				}
				return list;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<ClienteVO> buscarTudo() throws Exception {
		try {
			List<ClienteVO> list = new ArrayList<>();
			ClienteDAO<ClienteVO> dao2 = new ClienteDAO<>();
			ResultSet rs = dao2.buscarTudo();

			if (rs.next() == false) {
				throw new Exception("Erro ao listar, Clientes não encontrados.");
			} else {
				ClienteVO vo2 = new ClienteVO();

				vo2.setNome(rs.getString("pessoa_nome"));
				vo2.setCpf(rs.getString("pessoa_cpf"));
				vo2.setClienteId(rs.getLong("cliente_id"));

				list.add(vo2);

				while (rs.next()) {

					ClienteVO vo3 = new ClienteVO();

					vo3.setNome(rs.getString("pessoa_nome"));
					vo3.setCpf(rs.getString("pessoa_cpf"));
					vo3.setClienteId(rs.getLong("cliente_id"));

					list.add(vo3);

				}
				return list;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void remover(ClienteVO vo) throws Exception {
		try {
			ResultSet rs = cliDAO.buscarPorId(vo);
			if (rs.next() == false) {
				throw new Exception("Impossivel remover, Cliente não encontrado.");
			} else {
				cliDAO.remover(vo);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void removerTudo() throws Exception {
		try {
			cliDAO.removerTudo();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void editar(ClienteVO vo) throws Exception {
		try {
			ResultSet rs = cliDAO.buscarPorId(vo);
			if (rs.next() == false) {
				throw new Exception("Impossivel editar, Cliente não encontrado.");
			} else {
				cliDAO.editar(vo);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void editarCpf(ClienteVO vo) throws Exception {
		ClienteDAO<ClienteVO> dao2 = new ClienteDAO<>();
		try {
			ResultSet rs = dao2.buscarPorId(vo);
			if (rs.next() == false) {
				throw new Exception("Impossivel editar, Cliente nao encontrado.");
			} else {
				dao2.editarCpf(vo);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}