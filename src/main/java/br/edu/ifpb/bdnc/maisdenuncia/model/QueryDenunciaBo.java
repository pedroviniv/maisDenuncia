/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.model;

import br.edu.ifpb.bdnc.maisdenuncia.dao.DenunciaBdDao;
import br.edu.ifpb.bdnc.maisdenuncia.dao.DenunciaDao;
import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenuncia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class QueryDenunciaBo
{
    private final DenunciaDao denunciaDao;
    
    public QueryDenunciaBo(){
        denunciaDao = new DenunciaBdDao();
    }
    
    /**
     * Retorna a denúncia que tem o id passado por parâmetro
     * @param id identificação da denúncia
     * @return Objeto que representa Denúncia
     */
    public Denuncia getDenunciaById(int id){
        return denunciaDao.get(id);
    }
    
    /**
     * Retorna todas as denúncias registradas entre duas datas
     * @param start data inicial
     * @param end data final
     * @return List of Denuncia
     */
    public List<Denuncia> getDenunciasBetweenDates(LocalDate start, LocalDate end){
        List<Denuncia> denuncias = new ArrayList<>();
        
        for(Denuncia denuncia : denunciaDao.listar()){
            if(denuncia.getData().isEqual(start) || denuncia.getData().isEqual(end))
                denuncias.add(denuncia);
            else if(denuncia.getData().isAfter(start) && denuncia.getData().isBefore(end))
                denuncias.add(denuncia);
        }
        
        return denuncias;
    }
    
    /**
     * Retorna todas as denúncias que tiverem o mesmo tipo do parâmetro
     * @param tipo Objeto TipoDenuncia representando o tipo da denúncia desejado
     * @return List of Denuncia
     */
    public List<Denuncia> getDenunciasByType(TipoDenuncia tipo){
        List<Denuncia> denuncias = new ArrayList<>();
        
        for(Denuncia denuncia : denunciaDao.listar()){
            if(denuncia.getTipoDenuncia() == tipo)
                denuncias.add(denuncia);
        }
        
        return denuncias;
    }
    
    /**
     * Retorna todas as denúncias
     * @return List of Denuncia
     */
    public List<Denuncia> getDenuncias(){
        return denunciaDao.listar();
    }
    
    /**
     * Retorna uma lista contendo todas as denúncias registradas nesse mês
     * @return List of Denuncia
     */
    public List<Denuncia> getDenunciasFromLastMonth(){
        List<Denuncia> denuncias = new ArrayList<>();
        LocalDate dataAtual = LocalDate.now();
        
        for(Denuncia denuncia : denunciaDao.listar()){
            if(dataAtual.getYear() == denuncia.getData().getYear() && dataAtual.getMonth() == denuncia.getData().getMonth())
                denuncias.add(denuncia);
        }
        return denuncias;
    }
    
    /**
     * Retorna as ultimas N Denúncias registradas, onde N é o valor passado por parâmetro.
     * @param number - Quantidade de denúncias desejada
     * @return List of Denuncia
     */
    public List<Denuncia> lastOf(int number){
        List<Denuncia> lastOf = new ArrayList<>();
        List<Denuncia> all = denunciaDao.listar();
        Collections.reverse(all);
        if(all.size() < number){
            return all;
        }else{
            for(int i=0;i<number;i++)
                lastOf.add(all.get(i));
            return lastOf;
        }
    }
}
