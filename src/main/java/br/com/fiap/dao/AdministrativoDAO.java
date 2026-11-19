
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Administrativo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class AdministrativoDAO {

    private final DataSource dataSource;

    public AdministrativoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Administrativo save(Administrativo administrativo) {
        String sql = "insert into ddd_cadastro_administrativo(nome ,email, instituicao, cargo, cd_convite, senha) values(?,?,?,?,?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_admin"})) {
            ps.setString(1, administrativo.getNomeAdmin());
            ps.setString(2, administrativo.getEmail());
            ps.setString(3, administrativo.getInstituicao());
            ps.setString(4, administrativo.getCargo());
            ps.setString(5, administrativo.getCdConvite());
            ps.setString(6, administrativo.getSenha());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        administrativo.setIdAdmin(rs.getInt(1));
                    }
                }

                return administrativo;
            }

            return null;
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Administrador já cadastrado!");
            }

            throw new RuntimeException("Erro ao salvar administrador: " + e.getMessage(), e);
        }
    }

    public ArrayList<Administrativo> findAll() {
        ArrayList<Administrativo> administrativos = new ArrayList<Administrativo>();
        String sql = "select * from ddd_cadastro_administrativo order by id_admin";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Administrativo administrativo = new Administrativo();
                    administrativo.setIdAdmin(rs.getInt("id_admin"));
                    administrativo.setNomeAdmin(rs.getString("nome"));
                    administrativo.setEmail(rs.getString("email"));
                    administrativo.setInstituicao(rs.getString("instituicao"));
                    administrativo.setCargo(rs.getString("cargo"));
                    administrativo.setCdConvite(rs.getString("cd_convite"));
                    administrativo.setSenha(rs.getString("senha"));
                    administrativos.add(administrativo);
                }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao consultar administradores: " + e.getMessage(), e
            );
        }
        return administrativos;
    }

    public Administrativo findById(Integer idAdmin) {
        Administrativo administrativo = new Administrativo();
        String sql = "select * from ddd_cadastro_administrativo where id_admin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAdmin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                administrativo.setIdAdmin(rs.getInt("id_admin"));
                administrativo.setNomeAdmin(rs.getString("nome"));
                administrativo.setEmail(rs.getString("email"));
                administrativo.setInstituicao(rs.getString("instituicao"));
                administrativo.setCargo(rs.getString("cargo"));
                administrativo.setCdConvite(rs.getString("cd_convite"));
                administrativo.setSenha(rs.getString("senha"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao consultar administrador: " + e.getMessage(), e);
        }
        return administrativo;
    }

    public boolean delete(Integer idAdmin) {
        String sql = "delete from ddd_cadastro_administrativo where id_admin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAdmin);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            if (e.getErrorCode() == 2292) {
                throw new RegraNegocioException(
                        "Não é possível excluir o administrador, pois existem registros relacionados!");
            }

            throw new RuntimeException(
                    "Erro ao excluir administrador: " + e.getMessage(), e);
        }
    }

    public Administrativo update(Administrativo administrativo) {
        String sql = "update ddd_cadastro_administrativo set nome = ?, email = ?, instituicao = ?, cargo = ?, cd_convite = ?, senha = ? where id_admin = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, administrativo.getNomeAdmin());
            ps.setString(2, administrativo.getEmail());
            ps.setString(3, administrativo.getInstituicao());
            ps.setString(4, administrativo.getCargo());
            ps.setString(5, administrativo.getCdConvite());
            ps.setString(6, administrativo.getSenha());
            ps.setInt(7, administrativo.getIdAdmin());
            if (ps.executeUpdate() > 0) {
                return administrativo;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException(
                        "Já existe um administrador cadastrado com esses dados!");
            }
            throw new RuntimeException(
                    "Erro ao atualizar administrador: " + e.getMessage(), e);
        }
    }

    public Administrativo login(String email, String senha) {
        Administrativo administrador = new Administrativo();
        String sql = "select id_admin, nome, email, instituicao, cargo, cd_convite from ddd_cadastro_administrativo where email = ? and senha = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    administrador.setIdAdmin(rs.getInt("id_admin"));
                    administrador.setNomeAdmin(rs.getString("nome"));
                    administrador.setEmail(rs.getString("email"));
                    administrador.setInstituicao(rs.getString("instituicao"));
                    administrador.setCargo(rs.getString("cargo"));
                    administrador.setCdConvite(rs.getString("cd_convite"));

                    return administrador;

                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao realizar login: " + e.getMessage(), e);
        }

    }
}
