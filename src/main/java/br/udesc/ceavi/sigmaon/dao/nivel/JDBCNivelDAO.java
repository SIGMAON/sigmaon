package br.udesc.ceavi.sigmaon.dao.nivel;

import br.udesc.ceavi.sigmaon.dao.jdbc.JDBCInstance;
import java.sql.ResultSet;
import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.PGgeometry;
import org.postgis.Point;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.Polygon;

public class JDBCNivelDAO implements NivelDAO {

    @Override
    public Polygon consultaNivel(double nivel) {
        try {
            String sql = "SELECT c.elevation "
                    + ",ST_Transform(ST_SetSRID(c.geom, 29182), 4326) geom "
                    + "FROM curvas c "
                    + "WHERE c.elevation = 365.000000000 "
                    + " ";

            ResultSet rs
                    = JDBCInstance.getInstance().getStatement().executeQuery(sql);

            Polygon polygon = new Polygon();

            while (rs.next()) {
                PGgeometry geom = (PGgeometry) rs.getObject(2);
                MultiLineString mls = (MultiLineString) geom.getGeometry();
                LineString[] lss = mls.getLines();
                LineString ls = lss[0];
                Point[] ps = ls.getPoints();
                for (Point p : ps) {
                    LatLng coord = new LatLng(p.getY(), p.getX());
                    polygon.getPaths().add(coord);
                }
            }
            JDBCInstance.getInstance().getStatement().close();
            return polygon;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
