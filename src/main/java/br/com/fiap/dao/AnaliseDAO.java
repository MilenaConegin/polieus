
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.to.AnaliseTO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class AnaliseDAO {

    private final DataSource dataSource;

    public AnaliseDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AnaliseTO save(AnaliseTO analise) {
        String sql = "insert into ddd_analise(protocolo, categoria, localizacao, prioridade, status, dt_analise) values(?,?,?,?,?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_analise"})) {
            ps.setString(1, analise.getProtocolo());
            ps.setString(2, analise.getCategoria());
            ps.setString(3, analise.getLocalizacao());
            ps.setString(4, analise.getPrioridade());
            ps.setString(5, analise.getStatus());
            ps.setDate(6, Date.valueOf(analise.getData()));
            if (ps.executeUpdate() > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        analise.setIdAnalise(rs.getInt(1));
                    } else {
                        throw new RuntimeException(
                                "Não foi possível recuperar o ID da análise cadastrada!");
                    }
                }
                return analise;
            } else {
                throw new RuntimeException(
                        "Não foi possível cadastrar a análise!"
                );
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Análise já cadastrada!");
            }
            throw new RuntimeException("Erro ao atualizar análise.");
        }
    }

    public ArrayList<AnaliseTO> findAll() {
        ArrayList<AnaliseTO> analises = new ArrayList<AnaliseTO>();
        String sql = "select * from ddd_analise order by id_analise";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
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
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar análises: " + e.getMessage());
        }
        return analises;
    }

    public AnaliseTO findById(Integer idAnalise) {
        AnaliseTO analise = new AnaliseTO();
        String sql = "select * from ddd_analise where id_analise = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAnalise);
           try  (ResultSet rs = ps.executeQuery()) {
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
           }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar análise: " + e.getMessage());
        }
        return analise;
    }

    public boolean delete(Integer idAnalise) {
        String sql = "delete from ddd_analise where id_analise = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAnalise);
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            if (e.getErrorCode() == 2292) {
                throw new RegraNegocioException(
                        "Não é possível excluir esta análise, porque existem registros relacionados!");
            }

            throw new RuntimeException("Erro ao excluir análise.");
        }
    }

    public AnaliseTO update(AnaliseTO analise) {
        String sql = "update ddd_analise set protocolo = ?, categoria = ?, localizacao = ?, prioridade = ?, status = ?, dt_analise = ? where id_analise = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
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
            throw new RuntimeException("Erro ao atualizar análise: " + e.getMessage());

        }
    }
}
