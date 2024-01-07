package com.example.readysethome.view.Tenant.TenantFilterListings;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TenantFilterListingsPresenter {
    TenantFilterListingsView view;

    public TenantFilterListingsPresenter(TenantFilterListingsActivity view) {
        this.view = view;
    }

    /**
     * 'Ελεγχος αν το στοιχείο είναι κενό.
     * @param str Το στοιχείο
     * @return Ένα bool για το αν το στοιχείο είναι κενό ή όχι
     */
    private boolean isNull(String str) {
        return str.equals("");
    }

    /**
     * Set up ήδη υπάρχοντων φίλτρων
     * @param filterHolder Ένα αντικείμενο τύπου FilterHolder, όπου κρατάει πληροφορίες για τα δίαφορα
     * φίλτρα που ορίστηκαν
     */
    public void readFilters(FilterHolder filterHolder) {
        view.setAth(filterHolder.isAth());
        view.setThes(filterHolder.isThes());
        view.setPatra(filterHolder.isPatra());
        view.setHrakleio(filterHolder.isHrakleio());
        view.setIwan(filterHolder.isIwan());
        view.setVolos(filterHolder.isVolos());
        view.setSyros(filterHolder.isSyros());
        view.setNafplion(filterHolder.isNafplion());

        view.setWantedPrice(filterHolder.getPrice());
    }

    /**
     * Προσθήκη φίλτρων για την αναζήτηση διαμερίσματος
     * @return Ένα αντικείμενο τύπου FilterHolder, όπου κρατάει πληροφορίες για τα δίαφορα
     * φίλτρα που ορίστηκαν
     */
    public FilterHolder addFilters() {
        String price = view.getWantedPrice();
        String check_in = view.getWantedCheckIn();
        String check_out = view.getWantedCheckOut();

        // check if the dates are invalid
        if (!isNull(view.getWantedCheckIn()) && !isNull(view.getWantedCheckOut())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                dateFormat.parse(view.getWantedCheckIn());
                dateFormat.parse(view.getWantedCheckOut());
            } catch (ParseException e) {
                view.showMessage("Invalid dates");
                return null;
            }
        } else { // if check_in or check_out null, don't consider either
            check_in = "";
            check_out = "";
        }

        FilterHolder filterHolder = new FilterHolder(view.getAth(), view.getThes(), view.getPatra(), view.getHrakleio(), view.getIwan(), view.getVolos(), view.getSyros(), view.getNafpion(), price, check_in, check_out);
        view.showMessage("Saved filters");
        return filterHolder;
    }
}
