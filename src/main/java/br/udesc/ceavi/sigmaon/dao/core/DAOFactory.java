package br.udesc.ceavi.sigmaon.dao.core;

import br.udesc.ceavi.sigmaon.dao.jdbc.JDBCDAOFactory;
import br.udesc.ceavi.sigmaon.dao.nivel.NivelDAO;

/**
 *
 * @author Weverton Otoni
 */
public abstract class DAOFactory {

    /**
     * Metodo preferencial para a criacao de DAOFactory. Cria uma conexao padrao
     * JDBC transparente para outras classes.
     *
     * @return DAOFactory Retorna a conexao padrao JDBC
     *
     * @see #getDAOFactory(br.udesc.ceavi.sigmaon.dao.core.Factory)
     */
    public static DAOFactory getDAOFactory() {
        return getDAOFactory(Factory.JDBC);
    }

    /**
     * Metodo para criacao de DAOFactory. Cria uma conexao do tipo passado por
     * parametro. Pode ser usado se for necessario criar uma conexao diferente
     * da padrao.
     *
     * @param factory Emum de conexoes
     *
     * @return DAOFactory Retorna uma conexao disponivel em {@code Factory}
     *
     * @see br.udesc.ceavi.sigmaon.dao.core.Factory
     */
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
