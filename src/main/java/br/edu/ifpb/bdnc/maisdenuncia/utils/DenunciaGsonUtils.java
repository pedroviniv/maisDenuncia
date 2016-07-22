/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.bdnc.maisdenuncia.utils;

import br.edu.ifpb.bdnc.maisdenuncia.entities.Denuncia;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

/**
 *
 * @author kieckegard
 */
public class DenunciaGsonUtils
{
    public static String toGson(List<Denuncia> denuncias){
        GsonBuilder gb = new GsonBuilder();
        gb.serializeSpecialFloatingPointValues().setExclusionStrategies(new ZPointExclusionStrategy());
        Gson gson = gb.create();
        
        
        return gson.toJson(denuncias);
    }
}
