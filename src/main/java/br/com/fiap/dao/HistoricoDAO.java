
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Historico;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class HistoricoDAO {

    private final DataSource dataSource;

    public HistoricoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Historico save(Historico historico) {
        String sql = "insert into ddd_acompanhamento(num_protocolo, ds_categoria, dt_acompanhamento, ds_status, id_ocorrencia) values (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_acompanhamento"})) {
            ps.setString(1, historico.getProtocolo());
            ps.setString(2, historico.getCategoria());
            ps.setDate(3, Date.valueOf(historico.getData()));
            ps.setString(4, historico.getStatus());
            ps.setInt(5, historico.getIdOcorrencia());
            if (ps.executeUpdate() > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        historico.setIdHistorico(rs.getInt(1));
                    }
                }
                return historico;
            }
            return null;
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Histórico já cadastrado!");
            }

            if (e.getErrorCode() == 2291) {
                throw new RegraNegocioException("A ocorrência informada não existe!");
            }

            throw new RuntimeException("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public ArrayList<Historico> findAll() {
        ArrayList<Historico> historicos = new ArrayList<Historico>();
        String sql = "select * from ddd_acompanhamento order by id_acompanhamento";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Historico historico = new Historico();
                    historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                    historico.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    historico.setProtocolo(rs.getString("num_protocolo"));
                    historico.setCategoria(rs.getString("ds_categoria"));
                    historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                    historico.setStatus(rs.getString("ds_status"));
                    historicos.add(historico);
                }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar históricos: " + e.getMessage());
        }
        return historicos;
    }

    public Historico findById(Integer idHistorico) {
        Historico historico = new Historico();
        String sql = "select * from ddd_acompanhamento where id_acompanhamento = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHistorico);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                    historico.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    historico.setProtocolo(rs.getString("num_protocolo"));
                    historico.setCategoria(rs.getString("ds_categoria"));
                    historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                    historico.setStatus(rs.getString("ds_status"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar histórico: " + e.getMessage());
        }
        return historico;
    }

    public ArrayList<Historico> findByOcorrencia(Integer idOcorrencia) {
        ArrayList<Historico> historicos = new ArrayList<Historico>();
        String sql = "select * from ddd_acompanhamento where id_ocorrencia = ? order by id_acompanhamento";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs != null) {
                    while (rs.next()) {

                        Historico historico = new Historico();
                        historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                        historico.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                        historico.setProtocolo(rs.getString("num_protocolo"));
                        historico.setCategoria(rs.getString("ds_categoria"));
                        historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                        historico.setStatus(rs.getString("ds_status"));
                        historicos.add(historico);
                    }
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar histórico da ocorrência: " + e.getMessage());
        }
        return historicos;
    }

    public boolean delete(Integer idHistorico) {
        String sql = "delete from ddd_acompanhamento where id_acompanhamento = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHistorico);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

            if (e.getErrorCode() == 2292) {
                throw new RegraNegocioException("Não é possível excluir o histórico, pois existem registros relacionados!");
            }

            throw new RuntimeException("Erro ao excluir histórico: " + e.getMessage());
        }
    }

    public Historico update(Historico historico) {
        String sql = "update ddd_acompanhamento set num_protocolo = ?, ds_categoria = ?, dt_acompanhamento = ?, " +
                "ds_status = ?, id_ocorrencia = ? where id_acompanhamento = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, historico.getProtocolo());
            ps.setString(2, historico.getCategoria());
            ps.setDate(3, Date.valueOf(historico.getData()));
            ps.setString(4, historico.getStatus());
            ps.setInt(5, historico.getIdOcorrencia());
            ps.setInt(6, historico.getIdHistorico());
            if (ps.executeUpdate() > 0) {
                return historico;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Histórico já cadastrado!");
            }

            if (e.getErrorCode() == 2291) {
                throw new RegraNegocioException("A ocorrência informada não existe!");
            }

            throw new RuntimeException("Erro ao atualizar histórico: " + e.getMessage());
        }
    }
}
