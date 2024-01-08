package com.example.readysethome.view.Tenant.TenantFilterListingsTest;

import com.example.readysethome.view.Tenant.TenantFilterListings.TenantFilterListingsView;

public class TenantFilterListingsViewStub implements TenantFilterListingsView {

    private boolean athChecked;
    private boolean thesChecked;
    private boolean patraChecked;
    private boolean hrakleioChecked;
    private boolean iwanChecked;
    private boolean volosChecked;
    private boolean syrosChecked;
    private boolean nafpionChecked;
    private String wantedPrice;
    private String wantedCheckIn;
    private String wantedCheckOut;
    private boolean showMessageCalled;
    private String showMessage;


    public boolean isAthChecked() {
        return athChecked;
    }


    public boolean isThesChecked() {
        return thesChecked;
    }


    public boolean isPatraChecked() {
        return patraChecked;
    }


    public boolean isHrakleioChecked() {
        return hrakleioChecked;
    }


    public boolean isIwanChecked() {
        return iwanChecked;
    }


    public boolean isVolosChecked() {
        return volosChecked;
    }


    public boolean isSyrosChecked() {
        return syrosChecked;
    }

    public boolean isNafpionChecked() {
        return nafpionChecked;
    }

    @Override
    public boolean getAth() {
        return false;
    }

    @Override
    public boolean getThes() {
        return false;
    }

    @Override
    public boolean getPatra() {
        return false;
    }

    @Override
    public boolean getHrakleio() {
        return false;
    }

    @Override
    public boolean getIwan() {
        return false;
    }

    @Override
    public boolean getVolos() {
        return false;
    }

    @Override
    public boolean getSyros() {
        return false;
    }

    @Override
    public boolean getNafpion() {
        return false;
    }

    @Override
    public void setAth(boolean isAth) {
        this.athChecked=isAth;
    }

    @Override
    public void setThes(boolean isThes) {
        this.thesChecked=isThes;
    }

    @Override
    public void setPatra(boolean isPatra) {
        this.patraChecked=isPatra;
    }

    @Override
    public void setHrakleio(boolean isHrakleio) {
        this.hrakleioChecked=isHrakleio;
    }

    @Override
    public void setIwan(boolean isIwan) {
        this.iwanChecked=isIwan;
    }

    @Override
    public void setVolos(boolean isVolos) {
        this.volosChecked=isVolos;
    }

    @Override
    public void setSyros(boolean isSyros) {
        this.syrosChecked=isSyros;
    }

    @Override
    public void setNafplion(boolean isNafplion) {
        this.hrakleioChecked=isNafplion;
    }

    @Override
    public String getWantedPrice() {
        return wantedPrice;
    }

    @Override
    public String getWantedCheckIn() {
        return wantedCheckIn;
    }

    @Override
    public String getWantedCheckOut() {
        return wantedCheckOut;
    }

    @Override
    public void showMessage(String message) {
        showMessageCalled = true;
        showMessage = message;
    }

    public boolean showMessageCalled() {
        return showMessageCalled;
    }

    public String getShowMessage() {
        return showMessage;
    }

    // Other setters for testing

    public void setAthChecked(boolean athChecked) {
        this.athChecked = athChecked;
    }

    public void setThesChecked(boolean thesChecked) {
        this.thesChecked = thesChecked;
    }

    public void setPatraChecked(boolean patraChecked) {
        this.patraChecked = patraChecked;
    }

    public void setHrakleioChecked(boolean hrakleioChecked) {
        this.hrakleioChecked = hrakleioChecked;
    }

    public void setIwanChecked(boolean iwanChecked) {
        this.iwanChecked = iwanChecked;
    }

    public void setVolosChecked(boolean volosChecked) {
        this.volosChecked = volosChecked;
    }

    public void setSyrosChecked(boolean syrosChecked) {
        this.syrosChecked = syrosChecked;
    }

    public void setNafpionChecked(boolean nafpionChecked) {
        this.nafpionChecked = nafpionChecked;
    }

    public void setWantedPrice(String wantedPrice) {
        this.wantedPrice = wantedPrice;
    }

    public void setWantedCheckIn(String wantedCheckIn) {
        this.wantedCheckIn = wantedCheckIn;
    }

    public void setWantedCheckOut(String wantedCheckOut) {
        this.wantedCheckOut = wantedCheckOut;
    }


}
