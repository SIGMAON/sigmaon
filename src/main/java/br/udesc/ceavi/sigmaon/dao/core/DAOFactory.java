package br.udesc.ceavi.sigmaon.dao.core;

import br.udesc.ceavi.sigmaon.dao.jdbc.JDBCDAOFactory;
import br.udesc.ceavi.sigmaon.dao.nivel.NivelDAO;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory() {
        return new JDBCDAOFactory();
    }

    public static DAOFactory getDAOFactory(Factory factory) {

        switch (factory) {
            case JDBC:
                return new JDBCDAOFactory();
            default:
                return null;
        }
    }

    public abstract NivelDAO getNivelDAO();
}
