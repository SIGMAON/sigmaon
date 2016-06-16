package br.udesc.ceavi.sigmaon.bean;

import br.udesc.ceavi.sigmaon.dao.core.DAOFactory;
import br.udesc.ceavi.sigmaon.model.wrapper.DefaultMapModel;
import br.udesc.ceavi.sigmaon.model.wrapper.MapModel;
import br.udesc.ceavi.sigmaon.model.wrapper.Nivel;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MapaBean implements Serializable {

    private MapModel model;
    private Nivel polygon;
    private int nivelRef;

    public MapModel getModel() {
        return model;
    }

    public void setModel(MapModel model) {
        this.model = model;
    }

    public int getNivelRef() {
        return nivelRef;
    }

    public void setNivelRef(int nivel) {
        this.nivelRef = nivel;
    }

    public Nivel getPolygon() {
        return polygon;
    }

    public void setPolygon(Nivel polygon) {
        this.polygon = polygon;
    }

    public void buscarNivel() {
        polygon = DAOFactory.getDAOFactory().getNivelDAO().consultaNivel(nivelRef);
        gerarPolygon();
    }

    private void gerarPolygon() {
        model = new DefaultMapModel();

        polygon.setStrokeColor("#5CBDED");
        polygon.setFillColor("#5CBDED");
        polygon.setStrokeOpacity(0.6);
        polygon.setFillOpacity(0.6);

        model.addOverlay(polygon);
    }
}
