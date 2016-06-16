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

public class JDBCNivelDAO implements NivelDAO {

    @Override
    public Nivel consultaNivel(int nivel) {
        nivel = 365;

        String sql = "SELECT c.elevation "
                + "         ,ST_Transform(ST_SetSRID(c.geom, 29182), 4326) geom "
                + "     FROM curvas c "
                + "    WHERE c.elevation = ? ";

        try (PreparedStatement prstmt = JDBCInstance.getInstance().getConnection().prepareStatement(sql)) {
            prstmt.setInt(1, nivel);

            ResultSet rs = prstmt.executeQuery();

            Nivel polygon = new Nivel();

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
