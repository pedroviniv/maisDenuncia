/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.model;

import br.edu.ifpb.bdnc.maisdenuncia.dao.UsuarioBdDao;
import br.edu.ifpb.bdnc.maisdenuncia.dao.UsuarioDao;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import javax.security.auth.login.LoginException;

/**
 *
 * @author kieckegard
 */
public class LoginBo
{
    private final UsuarioDao dao;
    
    public LoginBo(){
        dao = new UsuarioBdDao();
    }
    
    public Usuario logIn(String email, String senha) throws LoginException{
        for(Usuario usuario : dao.listar()){
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }
        throw new LoginException("Usuário e/ou Senha incorreto(as).");
    }
    
}
