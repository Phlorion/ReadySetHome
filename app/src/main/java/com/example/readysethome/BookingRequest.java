class BookingRequest{
    private int booking_id;
    private Date submission_date;
    private Date check_in;
    private Date check_out;
    private STATUS booking_status;

    //Methodos gia thn ypovolh aithmatos
    public void submitReservationRequest() {}

    //Methodos gia akyrwsh
    public void cancelReservationRequest() {
    }

    // Methodos gia enhmerwsh tou status tou aithmatos krathshs
    public void updateStatus(STATUS newStatus) {
    }

    public void returnDepositToRenter() {
    }


    // Methodos gia thn enhmerwsh tou idiokthth se periptwsh aithmatos krathshs
    public void notifyOwner() {}

    // Setters kai getters
    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Date getSubmission_data() {
        return submission_data;
    }

    public void setSubmission_data(Date submission_data) {
        this.submission_data = submission_data;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public STATUS getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(STATUS booking_status) {
        this.booking_status = booking_status;
    }
}