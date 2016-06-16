package br.udesc.ceavi.sigmaon.dao.jdbc;

import br.udesc.ceavi.sigmaon.dao.core.DAOFactory;
import br.udesc.ceavi.sigmaon.dao.nivel.JDBCNivelDAO;
import br.udesc.ceavi.sigmaon.dao.nivel.NivelDAO;

public class JDBCDAOFactory extends DAOFactory {

    @Override
    public NivelDAO getNivelDAO() {
        return new JDBCNivelDAO();
    }
}
