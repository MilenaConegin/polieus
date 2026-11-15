package br.com.fiap.dao;

import br.com.fiap.to.HistoricoTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoricoDAO {

    public HistoricoTO save(HistoricoTO historico){
        String sql = "insert into ddd_acompanhamento(protocolo, categoria, dt_acompanhamento, status) values(?,?,?,?)";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, historico.getProtocolo());
            ps.setString(2, historico.getCategoria());
            ps.setDate(3,  Date.valueOf(historico.getData()));
            ps.setString(4, historico.getStatus());
            if (ps.executeUpdate() > 0) {
                return historico;
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<HistoricoTO> findAll() {
        ArrayList<HistoricoTO> historicos = new ArrayList<HistoricoTO>();
        String sql = "select * from ddd_acompanhamento order by id_acompanhamento";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            if (rs != null){
                while (rs.next()){
                    HistoricoTO historico = new HistoricoTO();
                    historico.setIdHistorico(rs.getInt("id_acompanhamento"));
                    historico.setProtocolo(rs.getString("protocolo"));
                    historico.setCategoria(rs.getString("categoria"));
                    historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                    historico.setStatus(rs.getString("status"));
                    historicos.add(historico);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return historicos;
    }

    public HistoricoTO findById(Integer idHistorico){
        HistoricoTO historico = new HistoricoTO();
        String sql = "select * from ddd_acompanhamento where id_acompanhamento = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idHistorico);
            ResultSet rs = ps.executeQuery();
              if (rs.next()){
                  historico.setIdHistorico(rs.getInt("id_historico"));
                  historico.setProtocolo(rs.getString("protocolo"));
                  historico.setCategoria(rs.getString("categoria"));
                  historico.setData(rs.getDate("dt_acompanhamento").toLocalDate());
                  historico.setStatus(rs.getString("status"));
              }else {
                  return null;
              }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return historico;
    }

    public boolean delete(Integer idHistorico){
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

    public HistoricoTO update(HistoricoTO historico){
        String sql = "update ddd_acompanhamneto set protocolo = ?, categoria = ?, dt_acompanhamento = ?, status = ? where id_acompanhamento = ?  ";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, historico.getProtocolo());
            ps.setString(2, historico.getCategoria());
            ps.setDate(3, Date.valueOf(historico.getData()));
            ps.setString(4, historico.getStatus());
            ps.setInt(5, historico.getIdHistorico());
            if (ps.executeUpdate() > 0) {
                return historico;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar histórico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
























