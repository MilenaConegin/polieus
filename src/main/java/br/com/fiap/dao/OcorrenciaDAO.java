
package br.com.fiap.dao;

import br.com.fiap.to.OcorrenciaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OcorrenciaDAO {

    public OcorrenciaTO save(OcorrenciaTO ocorrencia) {
        String sql = "insert into ddd_ocorrencia (id_usuario, ds_categoria, ds_descricao, ds_endereco, num_departamento, num_cep) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_ocorrencia"})) {
            ps.setInt(1, ocorrencia.getIdUsuario());
            ps.setString(2, ocorrencia.getCategoria());
            ps.setString(3, ocorrencia.getDescricao());
            ps.setString(4, ocorrencia.getEndereco());
            ps.setString(5, ocorrencia.getDepartamento());
            ps.setString(6, ocorrencia.getCep());
            if (ps.executeUpdate() > 0) {

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    ocorrencia.setIdOcorrencia(rs.getInt(1));
                }

                return ocorrencia;

            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Ocorrência já cadastrada!");
            }
            System.out.println("Erro ao salvar ocorrência: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<OcorrenciaTO> findAll() {
        ArrayList<OcorrenciaTO> ocorrencias = new ArrayList<OcorrenciaTO>();
        String sql = "select * from ddd_ocorrencia order by id_ocorrencia";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    OcorrenciaTO ocorrencia = new OcorrenciaTO();
                    ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    ocorrencia.setIdUsuario(rs.getInt("id_usuario"));
                    ocorrencia.setCategoria(rs.getString("ds_categoria"));
                    ocorrencia.setDescricao(rs.getString("ds_descricao"));
                    ocorrencia.setEndereco(rs.getString("ds_endereco"));
                    ocorrencia.setDepartamento(rs.getString("num_departamento"));
                    ocorrencia.setCep(rs.getString("num_cep"));
                    ocorrencias.add(ocorrencia);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrências: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return ocorrencias;
    }

    public OcorrenciaTO findById(Integer idOcorrencia) {
        OcorrenciaTO ocorrencia = new OcorrenciaTO();
        String sql = "select * from ddd_ocorrencia where id_ocorrencia = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                ocorrencia.setIdUsuario(rs.getInt("id_usuario"));
                ocorrencia.setCategoria(rs.getString("ds_categoria"));
                ocorrencia.setDescricao(rs.getString("ds_descricao"));
                ocorrencia.setEndereco(rs.getString("ds_endereco"));
                ocorrencia.setDepartamento(rs.getString("num_departamento"));
                ocorrencia.setCep(rs.getString("num_cep"));
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrência: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return ocorrencia;
    }

    public ArrayList<OcorrenciaTO> findByUsuario(Integer idUsuario) {
        ArrayList<OcorrenciaTO> ocorrencias = new ArrayList<OcorrenciaTO>();
        String sql = "select * from ddd_ocorrencia where id_usuario = ? order by id_ocorrencia";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    OcorrenciaTO ocorrencia = new OcorrenciaTO();
                    ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    ocorrencia.setIdUsuario(rs.getInt("id_usuario"));
                    ocorrencia.setCategoria(rs.getString("ds_categoria"));
                    ocorrencia.setDescricao(rs.getString("ds_descricao"));
                    ocorrencia.setEndereco(rs.getString("ds_endereco"));
                    ocorrencia.setDepartamento(rs.getString("num_departamento"));
                    ocorrencia.setCep(rs.getString("num_cep"));
                    ocorrencias.add(ocorrencia);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrências do usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return ocorrencias;
    }

    public boolean delete(Integer idOcorrencia) {
        String sql = "delete from ddd_ocorrencia where id_ocorrencia = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir ocorrência: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public OcorrenciaTO update(OcorrenciaTO ocorrencia) {
        String sql = "update ddd_ocorrencia set id_usuario = ?, ds_categoria = ?, ds_descricao = ?, " +
                "ds_endereco = ?, num_departamento = ?, num_cep = ? where id_ocorrencia = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, ocorrencia.getIdUsuario());
            ps.setString(2, ocorrencia.getCategoria());
            ps.setString(3, ocorrencia.getDescricao());
            ps.setString(4, ocorrencia.getEndereco());
            ps.setString(5, ocorrencia.getDepartamento());
            ps.setString(6, ocorrencia.getCep());
            ps.setInt(7, ocorrencia.getIdOcorrencia());
            if (ps.executeUpdate() > 0) {
                return ocorrencia;
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar ocorrência: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
