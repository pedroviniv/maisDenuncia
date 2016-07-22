/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface UsuarioDao
{
    void add(Usuario usuario);
    void remove(Usuario usuario);
    List<Usuario> listar();
    Usuario get(String email);
}
