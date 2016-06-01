package br.udesc.ceavi.sigmaon.dao.core;

public enum Factory {

    JDBC(1);

    private final int factory;

    Factory(int factory) {
        this.factory = factory;
    }

    public int getFactory() {
        return this.factory;
    }

}
