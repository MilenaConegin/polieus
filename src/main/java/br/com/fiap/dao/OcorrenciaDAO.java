package br.com.fiap.dao;

import br.com.fiap.to.OcorrenciaTO;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OcorrenciaDAO {

    public OcorrenciaTO save(OcorrenciaTO ocorrencia){
        String sql = "insert into ddd_ocorrencia(categoria, descricao, endereco, departamento, cep) values(?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, ocorrencia.getCategoria());
            ps.setString(2, ocorrencia.getDescricao());
            ps.setString(3, ocorrencia.getEndereco());
            ps.setString(4, ocorrencia.getDepartamento());
            ps.setString(5, ocorrencia.getCep());
            if (ps.executeUpdate() > 0) {
                return ocorrencia;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Ocorrência já cadastrada.");
            }
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<OcorrenciaTO> findAll(){
        ArrayList<OcorrenciaTO> ocorrencias = new ArrayList<OcorrenciaTO>();
        String sql = "select * from ddd_ocorrencia order by id_ocorrencia";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    OcorrenciaTO ocorrencia = new OcorrenciaTO();
                    ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    ocorrencia.setCategoria(rs.getString("categoria"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setEndereco(rs.getString("endereco"));
                    ocorrencia.setDepartamento(rs.getString("departamento"));
                    ocorrencia.setCep(rs.getString("cep"));
                    ocorrencias.add(ocorrencia);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " +e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return ocorrencias;
    }

    public OcorrenciaTO findById(Integer idOcorrencia){
        OcorrenciaTO ocorrencia = new OcorrenciaTO();
        String sql = "select * from ddd_ocorrencia where id_ocorrencia = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idOcorrencia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                    ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    ocorrencia.setCategoria(rs.getString("categoria"));
                    ocorrencia.setDescricao(rs.getString("descricao"));
                    ocorrencia.setEndereco(rs.getString("endereco"));
                    ocorrencia.setDepartamento(rs.getString("departamento"));
                    ocorrencia.setCep(rs.getString("cep"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrência: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return ocorrencia;
    }

    public boolean delete(Integer idOcorrencia){
        String sql = "delete from ddd_ocorrencia where id_ocorrencia = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idOcorrencia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir ocorrência: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public OcorrenciaTO update(OcorrenciaTO ocorrencia) {
        String sql = "update ddd_ocorrencia set categoria = ?, descricao = ?, endereco = ?, departamento = ?, cep = ? where id_ocorrencia = ? ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, ocorrencia.getCategoria());
            ps.setString(2, ocorrencia.getDescricao());
            ps.setString(3, ocorrencia.getEndereco());
            ps.setString(4, ocorrencia.getDepartamento());
            ps.setString(5, ocorrencia.getCep());
            ps.setInt(6, ocorrencia.getIdOcorrencia());
            if (ps.executeUpdate() > 0) {
                return ocorrencia;
            } else {
                return null;
            }
        } catch (SQLException e) {

        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
