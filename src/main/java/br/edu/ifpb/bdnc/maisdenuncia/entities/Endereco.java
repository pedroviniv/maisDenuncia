/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.entities;

import java.io.Serializable;

/**
 *
 * @author kieckegard
 */
public class Endereco implements Serializable
{
    private int      id;
    private String   pais;
    private String   estado;
    private String   cidade;
    private String   bairro;
    private String   rua;
    private int      numero;

    public Endereco(String pais, String estado, String cidade, String bairro, String rua, int numero)
    {
        this.pais    = pais;
        this.estado  = estado;
        this.cidade  = cidade;
        this.bairro  = bairro;
        this.rua     = rua;
        this.numero  = numero;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    @Override
    public String toString()
    {
        return "Endereco{" + "pais=" + pais + ", estado=" + estado + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + '}';
    }
    
    
    
}
