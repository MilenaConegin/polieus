
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Ocorrencia;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class OcorrenciaDAO {

    private final DataSource dataSource;

    public OcorrenciaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Ocorrencia save(Ocorrencia ocorrencia) {
        String sql = "insert into ddd_ocorrencia (id_usuario, ds_categoria, ds_descricao, ds_endereco, num_departamento, num_cep) values (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_ocorrencia"})) {
            ps.setInt(1, ocorrencia.getIdUsuario());
            ps.setString(2, ocorrencia.getCategoria());
            ps.setString(3, ocorrencia.getDescricao());
            ps.setString(4, ocorrencia.getEndereco());
            ps.setString(5, ocorrencia.getDepartamento());
            ps.setString(6, ocorrencia.getCep());
            if (ps.executeUpdate() > 0) {

               try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()) {
                    ocorrencia.setIdOcorrencia(rs.getInt(1));
                }
            }
                return ocorrencia;

            } else {
                return null;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Ocorrência já cadastrada!");
            }
            if (e.getErrorCode() == 2291) {
                throw new RegraNegocioException("Usuário da ocorrência informada não existe!");
            }
            throw new RuntimeException("Erro ao salvar ocorrência: " + e.getMessage());
        }
    }

    public ArrayList<Ocorrencia> findAll() {
        ArrayList<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
        String sql = "select * from ddd_ocorrencia order by id_ocorrencia";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                  while (rs.next()) {
                     Ocorrencia ocorrencia = new Ocorrencia();
                     ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                     ocorrencia.setIdUsuario(rs.getInt("id_usuario"));
                     ocorrencia.setCategoria(rs.getString("ds_categoria"));
                     ocorrencia.setDescricao(rs.getString("ds_descricao"));
                     ocorrencia.setEndereco(rs.getString("ds_endereco"));
                     ocorrencia.setDepartamento(rs.getString("num_departamento"));
                     ocorrencia.setCep(rs.getString("num_cep"));
                     ocorrencias.add(ocorrencia);
                  }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrências: " + e.getMessage());
        }
        return ocorrencias;
    }

    public Ocorrencia findById(Integer idOcorrencia) {
        Ocorrencia ocorrencia = new Ocorrencia();
        String sql = "select * from ddd_ocorrencia where id_ocorrencia = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            try(ResultSet rs = ps.executeQuery()){

                if (rs.next()) {
                    ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    ocorrencia.setIdUsuario(rs.getInt("id_usuario"));
                    ocorrencia.setCategoria(rs.getString("ds_categoria"));
                    ocorrencia.setDescricao(rs.getString("ds_descricao"));
                    ocorrencia.setEndereco(rs.getString("ds_endereco"));
                    ocorrencia.setDepartamento(rs.getString("num_departamento"));
                    ocorrencia.setCep(rs.getString("num_cep"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrência: " + e.getMessage());
        }
        return ocorrencia;
    }

    public ArrayList<Ocorrencia> findByUsuario(Integer idUsuario) {
        ArrayList<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
        String sql = "select * from ddd_ocorrencia where id_usuario = ? order by id_ocorrencia";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()){
            if (rs != null) {
                 while (rs.next()) {

                    Ocorrencia ocorrencia = new Ocorrencia();
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
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar ocorrências do usuário: " + e.getMessage());
        }
        return ocorrencias;
    }

    public boolean delete(Integer idOcorrencia) {
        String sql = "delete from ddd_ocorrencia where id_ocorrencia = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ocorrência: " + e.getMessage());
        }
    }

    public Ocorrencia update(Ocorrencia ocorrencia) {
        String sql = "update ddd_ocorrencia set id_usuario = ?, ds_categoria = ?, ds_descricao = ?, " +
                "ds_endereco = ?, num_departamento = ?, num_cep = ? where id_ocorrencia = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
            if (e.getErrorCode() == 2291) {
                throw new RegraNegocioException("Usuário da ocorrência informada não existe!");
            }
            throw new RuntimeException("Erro ao atualizar ocorrência: " + e.getMessage());
        }
    }
}
