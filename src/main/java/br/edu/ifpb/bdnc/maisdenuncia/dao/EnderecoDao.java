/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Endereco;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Usuario;

/**
 *
 * @author kieckegard
 */
public interface EnderecoDao
{
    void add(Endereco e, Usuario u);
    void remove(Endereco e);
    Endereco get(Usuario u);
}
