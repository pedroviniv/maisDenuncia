/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.dao;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public interface DenunciaDao
{
    void add(Denuncia denuncia);
    void remove(Denuncia denuncia);
    Denuncia get(int id);
    List<Denuncia> listar();
}
