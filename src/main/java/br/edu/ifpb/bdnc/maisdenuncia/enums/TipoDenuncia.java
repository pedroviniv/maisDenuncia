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
public enum TipoDenuncia
{
    ASSEDIO(1), AGRESSAO(2), ESTUPRO(3);
    
    int tipo;
    
    TipoDenuncia(int tipo){
        this.tipo = tipo;
    }
    
    public int getValue(){
        return this.tipo;
    }
    
    public static TipoDenuncia get(int tipo){
        for(TipoDenuncia t : TipoDenuncia.values()){
            if(t.getValue() == tipo)
                return t;
        }
        return null;
    }
}
