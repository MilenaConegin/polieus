
package br.com.fiap.dao;

import br.com.fiap.to.RelatorioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelatorioDAO {

    public RelatorioTO save(RelatorioTO relatorio){
        String sql = "insert into ddd_relatorio(categoria, prioridade) values(?,?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, relatorio.getCategoria());
            ps.setString(2, relatorio.getPrioridade());
            if (ps.executeUpdate() > 0) {
                return relatorio;
            } else {
                return null;
            }
        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                throw new RuntimeException("Relatório já cadastrado.");
            }
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public ArrayList<RelatorioTO> findAll(){
        ArrayList<RelatorioTO> relatorios = new ArrayList<RelatorioTO>();
        String sql = "select * from ddd_relatorio order by id_relatorio";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()){
                    RelatorioTO relatorio = new RelatorioTO();
                    relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                    relatorio.setCategoria(rs.getString("categoria"));
                    relatorio.setPrioridade(rs.getString("prioridade"));
                    relatorios.add(relatorio);
                }
            }else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return relatorios;
    }

    public RelatorioTO findById(Integer idRelatorio){
        RelatorioTO relatorio = new RelatorioTO();
        String sql = "select * from ddd_relatorio where id_relatorio = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idRelatorio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                relatorio.setIdRelatorio(rs.getInt("id_relatorio"));
                relatorio.setCategoria(rs.getString("categoria"));
                relatorio.setPrioridade(rs.getString("prioridade"));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar relatório: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return relatorio;
    }

    public boolean delete(Integer idRelatorio){
        String sql = "delete from ddd_relatorio where id_relatorio = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setInt(1, idRelatorio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir relatório: " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public RelatorioTO update(RelatorioTO relatorio) {
        String sql = "update ddd_relatorio set categoria = ?, prioridade = ? where id_relatorio = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, relatorio.getCategoria());
            ps.setString(2, relatorio.getPrioridade());
            ps.setInt(3, relatorio.getIdRelatorio());
            if (ps.executeUpdate() > 0) {
                return relatorio;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar relatório: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
