package org.example.cinemabackend.reservation.core.domain;

public class QrCode {
    private byte[] qrCode;

    public QrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }

    public byte[] getQrCode() {
        return qrCode;
    }
}
