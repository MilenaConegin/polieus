
package br.com.fiap.dao;

import br.com.fiap.to.AutenticacaoUsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutenticacaoUsuarioDAO {

    public AutenticacaoUsuarioTO save(AutenticacaoUsuarioTO autenticacao) {
        String sql = "insert into ddd_autenticacao_usuario(email, senha) values(?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, autenticacao.getEmail());
            ps.setString(2, autenticacao.getSenha());
            if (ps.executeUpdate() > 0) {
                return autenticacao;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("E-mail já cadastrado.");
            }

            System.out.println("Erro ao salvar autenticação: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<AutenticacaoUsuarioTO> findAll() {
        ArrayList<AutenticacaoUsuarioTO> autenticacoes = new ArrayList<>();
        String sql = "select * from ddd_autenticacao_usuario order by id_autenticacao";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    AutenticacaoUsuarioTO autenticacao = new AutenticacaoUsuarioTO();
                    autenticacao.setIdAutenticacao(rs.getInt("id_autenticacao"));
                    autenticacao.setEmail(rs.getString("email"));
                    autenticacao.setSenha(rs.getString("senha"));
                    autenticacoes.add(autenticacao);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de autenticações: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return autenticacoes;
    }

    public AutenticacaoUsuarioTO findById(Integer idAutenticacao) {
        String sql = "select * from ddd_autenticacao_usuario where id_autenticacao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAutenticacao);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AutenticacaoUsuarioTO autenticacao = new AutenticacaoUsuarioTO();
                autenticacao.setIdAutenticacao(rs.getInt("id_autenticacao"));
                autenticacao.setEmail(rs.getString("email"));
                autenticacao.setSenha(rs.getString("senha"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar autenticação: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Integer idAutenticacao) {
        String sql = "delete from ddd_autenticacao_usuario where id_autenticacao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAutenticacao);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir autenticação: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AutenticacaoUsuarioTO update(AutenticacaoUsuarioTO autenticacao) {
        String sql = "update ddd_autenticacao_usuario set email = ?, senha = ? where id_autenticacao = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, autenticacao.getEmail());
            ps.setString(2, autenticacao.getSenha());
            ps.setInt(3, autenticacao.getIdAutenticacao());
            if (ps.executeUpdate() > 0) {
                return autenticacao;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar autenticação: " + e.getMessage());

        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
