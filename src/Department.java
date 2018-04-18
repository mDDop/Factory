public enum Department {
    PC(false), LAPTOP(true), NETBOOK(true), TABLET(true), SMARTPHONE(true);

    private boolean isPortable;

    Department(boolean isPortable) {
        this.isPortable = isPortable;
    }

    public boolean isPortable() {
        return isPortable;
    }
}
