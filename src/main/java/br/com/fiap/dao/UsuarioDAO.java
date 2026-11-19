
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.to.UsuarioTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class UsuarioDAO {

    private final DataSource dataSource;

    public UsuarioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "insert into ddd_cadastro_usuario(nome, cpf, telefone, email, senha) values(?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_usuario"})) {

            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        usuario.setIdUsuario(rs.getInt(1));
                    }
                }
                return usuario;
            }
            return null;
        }  catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("CPF ou e-mail já cadastrado!");
            }
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
        String sql = "select * from ddd_cadastro_usuario order by id_usuario";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }
        }  catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao consultar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public UsuarioTO findById(Integer idUsuario) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select * from ddd_cadastro_usuario where id_usuario = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                } else {
                    return null;
                }
            }
        }  catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao consultar usuário: " + e.getMessage());
        }
        return usuario;
    }

    public boolean delete(Integer idUsuario) {
        String sql = "delete from ddd_cadastro_usuario where id_usuario = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            return ps.executeUpdate() > 0;
        }  catch (SQLException e) {

            if (e.getErrorCode() == 2292) {
                throw new RegraNegocioException("Não é possível excluir o usuário, pois existem registros relacionados!");
            }

            throw new RuntimeException(
                    "Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "update ddd_cadastro_usuario set nome = ?, cpf = ?, telefone = ?, email = ?, senha = ? where id_usuario = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getCpf());
            ps.setString(3, usuario.getTelefone());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getSenha());
            ps.setInt(6, usuario.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        }catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("CPF ou e-mail já cadastrado!");
            }

            throw new RuntimeException(
                    "Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public UsuarioTO login(String email, String senha) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "select id_usuario, nome, cpf, telefone, email from ddd_cadastro_usuario where email = ? and senha = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt("id_usuario"));
                    usuario.setNomeUsuario(rs.getString("nome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setEmail(rs.getString("email"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao realizar login: " + e.getMessage()
            );
        }
        return usuario;
    }
}
