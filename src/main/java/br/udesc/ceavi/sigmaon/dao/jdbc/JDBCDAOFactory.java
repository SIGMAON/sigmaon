package br.udesc.ceavi.sigmaon.dao.jdbc;

import br.udesc.ceavi.sigmaon.dao.core.DAOFactory;
import br.udesc.ceavi.sigmaon.dao.nivel.NivelDAO;
import br.udesc.ceavi.sigmaon.dao.nivel.JDBCNivelDAO;

public class JDBCDAOFactory extends DAOFactory {

    @Override
    public NivelDAO getNivelDAO() {
        return new JDBCNivelDAO();
    }
}
