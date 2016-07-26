/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenuncia;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenunciante;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
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
public class DenunciaBdDao implements DenunciaDao
{
    private PreparedStatement pstm;

    @Override
    public void add(Denuncia denuncia)
    {
        String sql = "INSERT INTO denuncia(descricao, id_tipodenuncia, data, id_tipodenunciante, email_usuario, ponto, ehanonimo)"
                + " VALUES(?,?,?,?,?,?,?) RETURNING id";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i=1;
            pstm.setString(i++, denuncia.getDescricao());
            pstm.setInt(i++, denuncia.getTipoDenuncia().getValue());
            pstm.setDate(i++, java.sql.Date.valueOf(denuncia.getData()));
            pstm.setInt(i++, denuncia.getTipoDenunciante().getValue());
            pstm.setString(i++, denuncia.getDenunciante().getEmail());
            pstm.setString(i++, new WKTWriter().write(denuncia.getPonto()));
            pstm.setBoolean(i++, denuncia.getEhAnonimo());
            
            ResultSet rs = pstm.executeQuery();
          
            if(rs.next())
                denuncia.setId(rs.getInt("id"));
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(DenunciaBdDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void remove(Denuncia denuncia)
    {
        String sql = "DELETE FROM denuncia WHERE id = ?";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setInt(1, denuncia.getId());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Denuncia> listar()
    {
        List<Denuncia> denuncias = new ArrayList<>();
        String sql = "SELECT * FROM denuncia ORDER BY id";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                denuncias.add(formaDenuncia(rs));
            }
            
            return denuncias;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        return denuncias;
    }
    
    private Denuncia formaDenuncia(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String descricao = rs.getString("descricao");
        TipoDenuncia tipoDenuncia = TipoDenuncia.get(rs.getInt("id_tipodenuncia"));
        TipoDenunciante tipoDenunciante = TipoDenunciante.get(rs.getInt("id_tipodenunciante"));
        LocalDate data = rs.getDate("data").toLocalDate();
        boolean ehAnonimo = rs.getBoolean("ehAnonimo");
        try
        {
            Point point = (Point) new WKTReader().read(rs.getString("ponto"));
            
            Usuario denunciante = new UsuarioBdDao().get(rs.getString("email_usuario"));
        
            Denuncia denuncia = new Denuncia(id,descricao, tipoDenuncia, denunciante, tipoDenunciante, point, data, ehAnonimo);

            return denuncia;
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Denuncia get(int id)
    {
        String sql = "SELECT * FROM denuncia WHERE id = ?";
        
        try
        {
            pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            Denuncia denuncia = null;
            if(rs.next())
                denuncia = formaDenuncia(rs);
            
            return denuncia;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
}
