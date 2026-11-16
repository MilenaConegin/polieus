
package br.com.fiap.dao;

import br.com.fiap.to.AnaliseTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnaliseDAO {

    public AnaliseTO save(AnaliseTO analise) {
        String sql = "insert into ddd_analise(protocolo, categoria, localizacao, prioridade, status, dt_analise) values(?,?,?,?,?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, analise.getProtocolo());
            ps.setString(2, analise.getCategoria());
            ps.setString(3, analise.getLocalizacao());
            ps.setString(4, analise.getPrioridade());
            ps.setString(5, analise.getStatus());
            ps.setDate(6, Date.valueOf(analise.getData()));
            if (ps.executeUpdate() > 0) {
                return analise;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Análise já cadastrada!");
            }

            System.out.println("Erro ao salvar análise: " + e.getMessage());

        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<AnaliseTO> findAll() {
        ArrayList<AnaliseTO> analises = new ArrayList<AnaliseTO>();
        String sql = "select * from ddd_analise order by id_analise";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs != null) {
                while (rs.next()) {
                    AnaliseTO analise = new AnaliseTO();
                    analise.setIdAnalise(rs.getInt("id_analise"));
                    analise.setProtocolo(rs.getString("protocolo"));
                    analise.setCategoria(rs.getString("categoria"));
                    analise.setLocalizacao(rs.getString("localizacao"));
                    analise.setPrioridade(rs.getString("prioridade"));
                    analise.setStatus(rs.getString("status"));
                    analise.setData(rs.getDate("dt_analise").toLocalDate());
                    analises.add(analise);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " +e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return analises;
    }

    public AnaliseTO findById(Integer idAnalise) {
        AnaliseTO analise = new AnaliseTO();
        String sql = "select * from ddd_analise where id_analise = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAnalise);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                analise.setIdAnalise(rs.getInt("id_analise"));
                analise.setProtocolo(rs.getString("protocolo"));
                analise.setCategoria(rs.getString("categoria"));
                analise.setLocalizacao(rs.getString("localizacao"));
                analise.setPrioridade(rs.getString("prioridade"));
                analise.setStatus(rs.getString("status"));
                analise.setData(rs.getDate("dt_analise").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar análise: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return analise;
    }

    public boolean delete(Integer idAnalise) {
        String sql = "delete from ddd_analise where id_analise = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, idAnalise);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir análise: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AnaliseTO update(AnaliseTO analise) {
        String sql = "update ddd_analise set protocolo = ?, categoria = ?, localizacao = ?, prioridade = ?, status = ?, dt_analise = ? where id_analise = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, analise.getProtocolo());
            ps.setString(2, analise.getCategoria());
            ps.setString(3, analise.getLocalizacao());
            ps.setString(4, analise.getPrioridade());
            ps.setString(5, analise.getStatus());
            ps.setDate(6, Date.valueOf(analise.getData()));
            ps.setInt(7, analise.getIdAnalise());
            if (ps.executeUpdate() > 0) {
                return analise;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar análise: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
