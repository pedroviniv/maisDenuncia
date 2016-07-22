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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class UsuarioBdDao implements UsuarioDao
{
    
    private PreparedStatement pstm;
    private final EnderecoDao enderecoDao;
    
    public UsuarioBdDao(){
        enderecoDao = new EnderecoBdDao();
    }

    @Override
    public void add(Usuario usuario)
    {
        String sql = "INSERT INTO usuario VALUES(?,?,?,?,?)";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i=1;
            
            pstm.setString(i++, usuario.getEmail());
            pstm.setString(i++, usuario.getSenha());
            pstm.setString(i++, usuario.getNome());
            pstm.setDate(i++, java.sql.Date.valueOf(usuario.getDataNascimento()));
            pstm.setString(i++, String.valueOf(usuario.getSexo()));
            
            if(pstm.executeUpdate() > 0)
                enderecoDao.add(usuario.getEndereco(),usuario);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void remove(Usuario usuario)
    {
        String sql = "DELETE FROM usuario WHERE email = ?";
        
        try
        {
            enderecoDao.remove(usuario.getEndereco());
            
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setString(1,usuario.getEmail());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Usuario> listar()
    {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                usuarios.add(formaUsuario(rs));
            }
            
            return usuarios;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        return usuarios;
    }
    
    
    private Usuario formaUsuario(ResultSet rs) throws SQLException{
        String email              = rs.getString("email");
        String senha              = rs.getString("senha");
        String nome               = rs.getString("nome");
        LocalDate dataNascimento  = rs.getDate("datanascimento").toLocalDate();
        char sexo                 = rs.getString("sexo").charAt(0);
        
        Usuario usuario = new Usuario(nome,dataNascimento,sexo,email,senha);
        
        Endereco endereco = enderecoDao.get(usuario);
        
        usuario.setEndereco(endereco);
        
        return usuario;
    }

    @Override
    public Usuario get(String email)
    {
        String sql = "SELECT * FROM usuario WHERE email = ?";        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setString(1, email);
            
            ResultSet rs = pstm.executeQuery();
            
            Usuario usuario = null;
            
            if(rs.next())
                usuario = formaUsuario(rs);
            
            return usuario;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
}
