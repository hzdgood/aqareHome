package com.aqara.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "qy")
public class QyProperties {
    private String SuiteID;
    private String CorpID;
    private String ProviderSecret;
    private String Secret;
    private String token;
    private String encodingAESKey;
    private String provider_token;
    private String suite_token;
    private String suite_access_token;
    private String session_info;
    private String permanent_code;
    private String corp_token;
    private String get_permissions;

    public String getSuiteID() {
        return SuiteID;
    }

    public void setSuiteID(String suiteID) {
        SuiteID = suiteID;
    }

    public String getCorpID() {
        return CorpID;
    }

    public void setCorpID(String corpID) {
        CorpID = corpID;
    }

    public String getProviderSecret() {
        return ProviderSecret;
    }

    public void setProviderSecret(String providerSecret) {
        ProviderSecret = providerSecret;
    }

    public String getSecret() {
        return Secret;
    }

    public void setSecret(String secret) {
        Secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getProvider_token() {
        return provider_token;
    }

    public void setProvider_token(String provider_token) {
        this.provider_token = provider_token;
    }

    public String getSuite_token() {
        return suite_token;
    }

    public void setSuite_token(String suite_token) {
        this.suite_token = suite_token;
    }

    public String getSuite_access_token() {
        return suite_access_token;
    }

    public void setSuite_access_token(String suite_access_token) {
        this.suite_access_token = suite_access_token;
    }

    public String getSession_info() {
        return session_info;
    }

    public void setSession_info(String session_info) {
        this.session_info = session_info;
    }

    public String getPermanent_code() {
        return permanent_code;
    }

    public void setPermanent_code(String permanent_code) {
        this.permanent_code = permanent_code;
    }

    public String getCorp_token() {
        return corp_token;
    }

    public void setCorp_token(String corp_token) {
        this.corp_token = corp_token;
    }

    public String getGet_permissions() {
        return get_permissions;
    }

    public void setGet_permissions(String get_permissions) {
        this.get_permissions = get_permissions;
    }
}
