/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.model;

import br.edu.ifpb.bdnc.maisdenuncia.dao.DenunciaBdDao;
import br.edu.ifpb.bdnc.maisdenuncia.dao.DenunciaDao;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class DenunciaBo
{
    private DenunciaDao dao;
    
    public DenunciaBo(){
        dao = new DenunciaBdDao();
    }
    
    public void registraDenuncia(Denuncia denuncia){
        dao.add(denuncia);
    }
}
