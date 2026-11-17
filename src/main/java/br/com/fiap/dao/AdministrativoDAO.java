
package br.com.fiap.dao;

import br.com.fiap.to.AdministrativoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministrativoDAO {

    public AdministrativoTO save(AdministrativoTO administrativo) {
        String sql = "insert into ddd_cadastro_administrativo(nome ,email, instituicao, cargo, cd_convite, senha) values(?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, administrativo.getNomeAdmin());
            ps.setString(2, administrativo.getEmail());
            ps.setString(3, administrativo.getInstituicao());
            ps.setString(4, administrativo.getCargo());
            ps.setString(5, administrativo.getCdConvite());
            ps.setString(6, administrativo.getSenha());
            if (ps.executeUpdate() > 0) {
                return administrativo;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Administrador já cadastrado.");
            }

            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<AdministrativoTO> findAll() {
        ArrayList<AdministrativoTO> administrativos = new ArrayList<AdministrativoTO>();
        String sql = "select * from ddd_cadastro_administrativo order by id_admin";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    AdministrativoTO administrativo = new AdministrativoTO();
                    administrativo.setIdAdmin(rs.getInt("id_admin"));
                    administrativo.setNomeAdmin(rs.getString("nome"));
                    administrativo.setEmail(rs.getString("email"));
                    administrativo.setInstituicao(rs.getString("instituicao"));
                    administrativo.setCargo(rs.getString("cargo"));
                    administrativo.setCdConvite(rs.getString("cd_convite"));
                    administrativo.setSenha(rs.getString("senha"));
                    administrativos.add(administrativo);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return administrativos;
    }

    public AdministrativoTO findById(Integer idAdmin) {
        AdministrativoTO administrativo = new AdministrativoTO();
        String sql = "select * from ddd_cadastro_administrativo where id_admin = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
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
            throw new RuntimeException("Erro ao consultar administrador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return administrativo;
    }

    public boolean delete(Integer idAdmin) {
        String sql = "delete from ddd_cadastro_administrativo where id_admin = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAdmin);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir administrador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AdministrativoTO update(AdministrativoTO administrativo) {
        String sql = "update ddd_cadastro_administrativo set nome = ?, email = ?, instituicao = ?, cargo = ?, cd_convite = ?, senha = ? where id_admin = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
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
            System.out.println("Erro ao atualizar administrador: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
