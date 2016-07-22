/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Endereco;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class EnderecoBdDao implements EnderecoDao
{
    private PreparedStatement pstm;

    @Override
    public void add(Endereco e, Usuario usuario)
    {
        String sql = "INSERT INTO endereco(pais,cidade,estado,bairro,rua,numero,email_usuario) VALUES(?,?,?,?,?,?,?)";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i=1;
            pstm.setString(i++, e.getPais());
            pstm.setString(i++, e.getEstado());
            pstm.setString(i++, e.getCidade());
            pstm.setString(i++, e.getBairro());
            pstm.setString(i++, e.getRua());
            pstm.setInt(i++, e.getNumero());
            pstm.setString(i++, usuario.getEmail());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Endereco e)
    {
        String sql = "DELETE FROM endereco WHERE id = ?";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setInt(1, e.getId());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EnderecoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Endereco get(Usuario u)
    {
        String sql = "SELECT * FROM Endereco WHERE email_usuario = ?";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setString(1, u.getEmail());
            
            ResultSet rs = pstm.executeQuery();
            
            Endereco endereco = null;
            
            if(rs.next()){
                endereco = formaEndereco(rs);
            }
            
            return endereco;
            
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EnderecoBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private Endereco formaEndereco(ResultSet rs) throws SQLException{
        int id        = rs.getInt("id");
        String pais   = rs.getString("pais");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String bairro = rs.getString("bairro");
        String rua    = rs.getString("rua");
        int numero    = rs.getInt("numero");
        
        Endereco endereco = new Endereco(pais,estado,cidade,bairro,rua,numero);
        endereco.setId(id);
        
        return endereco;
    }
    
}
