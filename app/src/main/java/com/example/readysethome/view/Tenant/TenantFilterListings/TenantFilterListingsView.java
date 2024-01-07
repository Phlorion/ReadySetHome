package com.example.readysethome.view.Tenant.TenantFilterListings;

public interface TenantFilterListingsView {

    boolean getAth();
    boolean getThes();
    boolean getPatra();
    boolean getHrakleio();
    boolean getIwan();
    boolean getVolos();
    boolean getSyros();
    boolean getNafpion();
    void setAth(boolean isAth);
    void setThes(boolean isThes);
    void setPatra(boolean isPatra);
    void setHrakleio(boolean isHrakleio);
    void setIwan(boolean isIwan);
    void setVolos(boolean isVolos);
    void setSyros(boolean isSyros);
    void setNafplion(boolean isNafplion);

    String getWantedPrice();

    String getWantedCheckIn();
    String getWantedCheckOut();
    void setWantedPrice(String wantedPrice);

    void showMessage(String message);
}
