/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.enums;

/**
 *
 * @author kieckegard
 */
public enum TipoDenunciante
{
    VITIMA(1), PRESENCIOU(2);
    
    int tipo;
    
    TipoDenunciante(int tipo){
        this.tipo = tipo;
    }
    
    public int getValue(){
        return this.tipo;
    }
    
    public static TipoDenunciante get(int tipo){
        for(TipoDenunciante t : TipoDenunciante.values()){
            if(t.getValue() == tipo)
                return t;
        }
        return null;
    }
}
