package br.udesc.ceavi.sigmaon.dao.nivel;

import br.udesc.ceavi.sigmaon.dao.jdbc.JDBCInstance;
import br.udesc.ceavi.sigmaon.model.wrapper.LatLng;
import br.udesc.ceavi.sigmaon.model.wrapper.Nivel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.PGgeometry;
import org.postgis.Point;

/**
 *
 * @author Weverton Otoni
 */
public class JDBCNivelDAO implements NivelDAO {

    /**
     * Recupera no Postgis o poligono para o nivel selecionado. A implentacao
     * definitiva deste metodo depende de algumas definicoes para o banco de
     * dados.
     *
     * @param nivelRef Nivel para consulta
     * @return polygon Objeto Nivel contendo um poligono
     *
     * @see Nivel
     */
    @Override
    public Nivel consultaNivel(int nivelRef) {
        // nivelRef recebe um valor fixo para que seja possivel fazer os testes.
        nivelRef = 365;

        /* SQL Postgis.
        SRID - Spatial Reference System Identifier
        ST_SetSRID - define o SRID que o dado esta salvo.
        ST_Transform - converte para o SRID desejado.
         */
        String sql = "SELECT c.elevation "
                + "         ,ST_Transform(ST_SetSRID(c.geom, 29182), 4326) geom "
                + "     FROM curvas c "
                + "    WHERE c.elevation = ? ";

        try (PreparedStatement prstmt = JDBCInstance.getInstance().getConnection().prepareStatement(sql)) {
            prstmt.setInt(1, nivelRef);

            ResultSet rs = prstmt.executeQuery();

            Nivel polygon = new Nivel();

            /* Os dados de curva foram importados de um arquivo shapefile.
            Um nivel contem varios pedacos de linha. Por isso a consulta retorna uma ou mais linhas.
            O while abaixo adiciona todos os pontos de todas as linhas a um objeto de poligono,
            improvisando assim um poligono.
             */
            while (rs.next()) {
                PGgeometry geom = (PGgeometry) rs.getObject(2);
                MultiLineString mls = (MultiLineString) geom.getGeometry();
                LineString[] lss = mls.getLines();
                LineString ls = lss[0];
                Point[] ps = ls.getPoints();
                for (Point p : ps) {
                    LatLng latLng = new LatLng(p.getY(), p.getX());
                    polygon.getPaths().add(latLng);
                }
            }
            return polygon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
