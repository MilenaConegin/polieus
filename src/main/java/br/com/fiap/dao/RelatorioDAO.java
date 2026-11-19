
package br.com.fiap.dao;

import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Relatorio;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class RelatorioDAO {

    private final DataSource dataSource;

    public RelatorioDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Relatorio save(Relatorio relatorio){
        String sql = "insert into ddd_relatorio(categoria, prioridade) values(?,?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id_relatorio"})) {

            ps.setString(1, relatorio.getCategoria());
            ps.setString(2, relatorio.getPrioridade());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        relatorio.setIdRelatorio(rs.getInt(1));
                    }
                }
                return relatorio;
            }
            return null;
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Relatório já cadastrado!");
            }
            throw new RuntimeException("Erro ao salvar relatório!");
        }
    }

    public ArrayList<Relatorio> findAll(){
        ArrayList<Relatorio> relatorios = new ArrayList<Relatorio>();
        String sql = "select * from ddd_relatorio order by id_relatorio";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                    relatorio.setCategoria(rs.getString("categoria"));
                    relatorio.setPrioridade(rs.getString("prioridade"));
                    relatorios.add(relatorio);
                }
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar relatórios!");
        }
        return relatorios;
    }

    public Relatorio findById(Integer idRelatorio){
        Relatorio relatorio = new Relatorio();
        String sql = "select * from ddd_relatorio where id_relatorio = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRelatorio);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                    relatorio.setCategoria(rs.getString("categoria"));
                    relatorio.setPrioridade(rs.getString("prioridade"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar relatório: " + e.getMessage());
        }
        return relatorio;
    }

    public boolean delete(Integer idRelatorio){
        String sql = "delete from ddd_relatorio where id_relatorio = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRelatorio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 2292) {
                throw new RegraNegocioException(
                        "Não é possível excluir o relatório, pois existem registros relacionados!");
            }
            throw new RuntimeException("Erro ao excluir relatório: " + e.getMessage());
        }
    }

    public Relatorio update(Relatorio relatorio) {
        String sql = "update ddd_relatorio set categoria = ?, prioridade = ? where id_relatorio = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, relatorio.getCategoria());
            ps.setString(2, relatorio.getPrioridade());
            ps.setInt(3, relatorio.getIdRelatorio());
            if (ps.executeUpdate() > 0) {
                return relatorio;
            } else {
                return null;
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) {
                throw new RegraNegocioException("Relatório já cadastrado!");
            }
            throw new RuntimeException("Erro ao atualizar relatório!");
        }
    }
}
