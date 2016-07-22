/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.model;

import br.edu.ifpb.bdnc.maisdenuncia.dao.UsuarioBdDao;
import br.edu.ifpb.bdnc.maisdenuncia.dao.UsuarioDao;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import br.edu.ifpb.bdnc.maisdenuncia.exceptions.EmailAlreadyExistsException;

/**
 *
 * @author kieckegard
 */
public class UsuarioBo
{
    private UsuarioDao dao;
    
    public UsuarioBo(){
        dao = new UsuarioBdDao();
    }
    
    public void cadastrarUsuario(Usuario usuario) throws EmailAlreadyExistsException{
        validaUsuario(usuario);
        dao.add(usuario);
    }
    
    public void removerUsuario(Usuario usuario){
        dao.remove(usuario);
    }
    
    public void validaUsuario(Usuario usuario) throws EmailAlreadyExistsException{
        verificaEmail(usuario.getEmail());
    }
    
    public void verificaEmail(String email) throws EmailAlreadyExistsException{
        for(Usuario usuario : dao.listar()){
            if(usuario.getEmail().equals(email))
                throw new EmailAlreadyExistsException("O e-mail já está em uso!");
        }
    }
}
