package br.udesc.ceavi.sigmaon.bean;

import br.udesc.ceavi.sigmaon.dao.core.DAOFactory;
import br.udesc.ceavi.sigmaon.model.wrapper.DefaultMapModel;
import br.udesc.ceavi.sigmaon.model.wrapper.MapModel;
import br.udesc.ceavi.sigmaon.model.wrapper.Nivel;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Weverton Otoni
 */
@ManagedBean
public class MapaBean implements Serializable {

    /**
     * Representa o mapa. Pode conter varios poligonos e outros objetos
     */
    private MapModel model;
    /**
     * Contem o poligono do nivel
     */
    private Nivel polygon;
    /**
     * Nivel selecionado pelo usuario
     */
    private int nivelRef;

    public MapaBean() {
        this.model = new DefaultMapModel();
    }

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

    /**
     * Busca o poligono do nivel. Faz a consulta no banco de dados do poligono
     * para o {@code nivelRef}. Atribui o resultado a {@code polygon}. Configura
     * o {@code polygon} e adiciona ao {@code model}.
     *
     * @see #polygon
     * @see #model
     */
    public void buscarNivel() {
        polygon = DAOFactory.getDAOFactory().getNivelDAO().consultaNivel(nivelRef);
        polygon.setStrokeColor("#5CBDED");
        polygon.setFillColor("#5CBDED");
        polygon.setStrokeOpacity(0.6);
        polygon.setFillOpacity(0.6);

        model.addOverlay(polygon);
    }

}
