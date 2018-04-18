public enum HighOrLow {
    TOPFIVEHIGHPRICES(true), TOPFIVELOWPRICES(false);

    private boolean High;

    HighOrLow(boolean isHigh) {
        this.High = isHigh;
    }

    public boolean isHigh() {
        return High;
    }
}
