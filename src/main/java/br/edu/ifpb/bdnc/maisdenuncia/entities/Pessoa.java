/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.entities;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public abstract class Pessoa implements Serializable
{
    private String     nome;
    private LocalDate  dataNascimento;
    private char       sexo;
    private Endereco   endereco;

    public Pessoa(String nome, LocalDate dataNascimento, char sexo)
    {
        this.nome             = nome;
        this.dataNascimento   = dataNascimento;
        this.sexo             = sexo;
    }
    
    public Pessoa(String nome, LocalDate dataNascimento, char sexo, Endereco endereco)
    {
        this.nome             = nome;
        this.dataNascimento   = dataNascimento;
        this.sexo             = sexo;
        this.endereco         = endereco;
    }

    public String getNome()
    {
        return nome;
    }

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public char getSexo()
    {
        return sexo;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }
    
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    @Override
    public String toString()
    {
        return "Pessoa{" + "nome=" + nome + ", dataNascimento=" + dataNascimento + ", sexo=" + sexo + ", endereco=" + endereco + '}';
    }
    
    
}
