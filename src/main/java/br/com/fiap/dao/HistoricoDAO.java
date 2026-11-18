
package br.com.fiap.dao;

import br.com.fiap.to.HistoricoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class HistoricoDAO {

    public HistoricoTO save(HistoricoTO historico) {
        String sql = "insert into ddd_acompanhamento(num_protocolo, ds_categoria, dt_acompanhamento, ds_status, id_ocorrencia) values (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_acompanhamento"})) {
            ps.setString(1, historico.getProtocolo());
            ps.setString(2, historico.getCategoria());
            ps.setDate(3, Date.valueOf(historico.getData()));
            ps.setString(4, historico.getStatus());
            ps.setInt(5, historico.getIdOcorrencia());
            if (ps.executeUpdate() > 0) {

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    historico.setIdHistorico(rs.getInt(1));
                }

                return historico;

            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Histórico já cadastrado!");
            }

            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<HistoricoTO> findAll() {
        ArrayList<HistoricoTO> historicos = new ArrayList<HistoricoTO>();
        String sql = "select * from ddd_acompanhamento order by id_acompanhamento";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    HistoricoTO historico = new HistoricoTO();
                    historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                    historico.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    historico.setProtocolo(rs.getString("num_protocolo"));
                    historico.setCategoria(rs.getString("ds_categoria"));
                    historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                    historico.setStatus(rs.getString("ds_status"));
                    historicos.add(historico);
                }
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar históricos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return historicos;
    }

    public HistoricoTO findById(Integer idHistorico) {
        HistoricoTO historico = new HistoricoTO();
        String sql = "select * from ddd_acompanhamento where id_acompanhamento = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idHistorico);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return historico;
    }

    public ArrayList<HistoricoTO> findByOcorrencia(Integer idOcorrencia) {
        ArrayList<HistoricoTO> historicos = new ArrayList<HistoricoTO>();
        String sql = "select * from ddd_acompanhamento where id_ocorrencia = ? order by id_acompanhamento";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idOcorrencia);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    HistoricoTO historico = new HistoricoTO();
                    historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                    historico.setIdOcorrencia(rs.getInt("id_ocorrencia"));
                    historico.setProtocolo(rs.getString("num_protocolo"));
                    historico.setCategoria(rs.getString("ds_categoria"));
                    historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                    historico.setStatus(rs.getString("ds_status"));
                    historicos.add(historico);
                }
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar histórico da ocorrência: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return historicos;
    }

    public boolean delete(Integer idHistorico) {
        String sql = "delete from ddd_acompanhamento where id_acompanhamento = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idHistorico);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public HistoricoTO update(HistoricoTO historico) {
        String sql = "update ddd_acompanhamento set num_protocolo = ?, ds_categoria = ?, dt_acompanhamento = ?, " +
                "ds_status = ?, id_ocorrencia = ? where id_acompanhamento = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
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
            System.out.println("Erro ao atualizar histórico: " + e.getMessage());

        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
