/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.entities;

import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenuncia;
import br.edu.ifpb.bdnc.maisdenuncia.enums.TipoDenunciante;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.WKTWriter;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author kieckegard
 */
public class Denuncia implements Serializable
{
    private int              id;
    private String           descricao;
    private TipoDenuncia     tipoDenuncia;
    private LocalDate        data;
    private Point            ponto;
    private TipoDenunciante  tipoDenunciante;
    private Usuario          denunciante;
    private boolean          ehAnonimo;
    
    public Denuncia(String descricao, TipoDenuncia tipoDenuncia, Usuario denunciante, TipoDenunciante tipoDenunciante, Point ponto, LocalDate data, boolean ehAnonimo){
        this.descricao        = descricao;
        this.tipoDenuncia     = tipoDenuncia;
        this.tipoDenunciante  = tipoDenunciante;
        this.ponto            = ponto;
        this.denunciante      = denunciante;
        this.data             = data;
        this.ehAnonimo        = ehAnonimo;
    }
    
    public Denuncia(int id, String descricao, TipoDenuncia tipoDenuncia, Usuario denunciante, TipoDenunciante tipoDenunciante, Point ponto, LocalDate data, boolean ehAnonimo){
        this.descricao        = descricao;
        this.tipoDenuncia     = tipoDenuncia;
        this.tipoDenunciante  = tipoDenunciante;
        this.ponto            = ponto;
        this.denunciante      = denunciante;
        this.id               = id;
        this.data             = data;
        this.ehAnonimo        = ehAnonimo;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return this.descricao;
    }
    
    public boolean getEhAnonimo(){
        return this.ehAnonimo;
    }

    public TipoDenuncia getTipoDenuncia()
    {
        return this.tipoDenuncia;
    }
    
    public Usuario getDenunciante(){
        return this.denunciante;
    }

    public TipoDenunciante getTipoDenunciante(){
        return this.tipoDenunciante;
    }
    
    public Point getPonto(){
        return this.ponto;
    }
    
    public LocalDate getData(){
        return this.data;
    }
    
    public String getWKTGeometry(){
        WKTWriter writer = new WKTWriter();
        return writer.write(this.ponto);
    }

    @Override
    public String toString()
    {
        return "Denuncia{" + "descricao=" + descricao + ", tipoDenuncia=" + tipoDenuncia + ", ponto=" + getWKTGeometry() + ", tipoDenunciante=" + tipoDenunciante + ", denunciante=" + denunciante + '}';
    }

    

    
    
}
