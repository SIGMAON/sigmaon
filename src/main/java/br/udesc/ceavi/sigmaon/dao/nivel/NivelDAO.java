package br.udesc.ceavi.sigmaon.dao.nivel;

import org.primefaces.model.map.Polygon;

public interface NivelDAO {
    
    public Polygon consultaNivel(double nivel);
    
}
