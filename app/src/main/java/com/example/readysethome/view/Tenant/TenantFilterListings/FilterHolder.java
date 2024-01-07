package com.example.readysethome.view.Tenant.TenantFilterListings;

import java.io.Serializable;

public class FilterHolder implements Serializable {
    boolean isAth, isThes, isPatra, isHrakleio, isIwan, isVolos, isSyros, isNafplion;
    String price, check_in, check_out;

    public FilterHolder(boolean isAth, boolean isThes, boolean isPatra, boolean isHrakleio, boolean isIwan, boolean isVolos, boolean isSyros, boolean isNafplion, String price, String check_in, String check_out) {
        this.isAth = isAth;
        this.isThes = isThes;
        this.isPatra = isPatra;
        this.isHrakleio = isHrakleio;
        this.isIwan = isIwan;
        this.isVolos = isVolos;
        this.isSyros = isSyros;
        this.isNafplion = isNafplion;
        this.price = price;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public boolean isAth() {
        return isAth;
    }

    public boolean isThes() {
        return isThes;
    }

    public boolean isPatra() {
        return isPatra;
    }

    public boolean isHrakleio() {
        return isHrakleio;
    }

    public boolean isIwan() {
        return isIwan;
    }

    public boolean isVolos() {
        return isVolos;
    }

    public boolean isSyros() {
        return isSyros;
    }

    public boolean isNafplion() {
        return isNafplion;
    }

    public String getPrice() {
        return price;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }
}
