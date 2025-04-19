package com.github.binarywang.wxpay.v3.auth;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class X509PublicCertificate extends X509Certificate {

    private final PublicKey publicKey;

    private final String publicId;

    public X509PublicCertificate(PublicKey publicKey, String publicId) {
        this.publicKey = publicKey;
        this.publicId = publicId;
    }

    @Override
    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override
    public BigInteger getSerialNumber() {
        return new BigInteger(publicId.replace("PUB_KEY_ID_", ""), 16);
    }

    @Override
    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
    }

    @Override
    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {

    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public Principal getIssuerDN() {
        return null;
    }

    @Override
    public Principal getSubjectDN() {
        return null;
    }

    @Override
    public Date getNotBefore() {
        return null;
    }

    @Override
    public Date getNotAfter() {
        return null;
    }

    @Override
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return new byte[0];
    }

    @Override
    public byte[] getSignature() {
        return new byte[0];
    }

    @Override
    public String getSigAlgName() {
        return "";
    }

    @Override
    public String getSigAlgOID() {
        return "";
    }

    @Override
    public byte[] getSigAlgParams() {
        return new byte[0];
    }

    @Override
    public boolean[] getIssuerUniqueID() {
        return new boolean[0];
    }

    @Override
    public boolean[] getSubjectUniqueID() {
        return new boolean[0];
    }

    @Override
    public boolean[] getKeyUsage() {
        return new boolean[0];
    }

    @Override
    public int getBasicConstraints() {
        return 0;
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return new byte[0];
    }

    @Override
    public void verify(PublicKey key) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {

    }

    @Override
    public void verify(PublicKey key, String sigProvider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {

    }

    @Override
    public String toString() {
        return "";
    }


    @Override
    public boolean hasUnsupportedCriticalExtension() {
        return false;
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        return Collections.emptySet();
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return new byte[0];
    }
}
