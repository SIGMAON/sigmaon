package br.udesc.ceavi.sigmaon.beans;

import br.udesc.ceavi.sigmaon.dao.core.DAOFactory;
import br.udesc.ceavi.sigmaon.dao.core.Factory;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polygon;

@ManagedBean
public class MapaBean implements Serializable {

    private MapModel model;
    
    @PostConstruct
    public void init() {
        model = new DefaultMapModel();
        
        Polygon polygon = getCoordenadas();

        polygon.setStrokeColor("#5CBDED");
        polygon.setFillColor("#5CBDED");
        polygon.setStrokeOpacity(0.6);
        polygon.setFillOpacity(0.6);

        model.addOverlay(polygon);
    }

    public MapModel getModel() {
        return model;
    }

    public Polygon getCoordenadas() {
        Polygon polygon = DAOFactory.getDAOFactory(Factory.JDBC).getNivelDAO().consultaNivel(0);
        return polygon;
    }
}
