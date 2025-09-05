//package com.ndz.tirana.utils;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.config.ConnectionConfig;
//import org.apache.http.config.SocketConfig;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.util.EntityUtils;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//import javax.net.ssl.X509TrustManager;
//import java.io.File;
//import java.io.FileInputStream;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.*;
//import java.security.cert.CertificateFactory;
//import java.util.Arrays;
//
//public class SSLClient {
//
//    /**
//     * 根据是否是https请求，获取HttpClient客户端
//     * @param isHttps 是否是HTTPS请求
//     * @return  HttpClient实例
//     */
//    public static CloseableHttpClient getHttpClient(boolean isHttps) {
//        CloseableHttpClient httpClient;
//        if (isHttps) {
//            SSLConnectionSocketFactory sslSocketFactory;
//            try {
//                /// 如果不作证书校验的话传入false
////                sslSocketFactory = getSocketFactory(false, null, null);
//                /// 如果需要证书检验的话
//                File file = new File("E:\\Desktop\\ds.crt");
//                InputStream ca = new FileInputStream(file);
//
//
//                // 证书
////                InputStream ca = SSLClient.class.getClassLoader().getResourceAsStream("ds.crt");
//
//                // 证书的别名，即:key。 注:cAalias只需要保证唯一即可，不过推荐使用生成keystore时使用的别名。
//                 String cAalias = System.currentTimeMillis() + "" + new SecureRandom().nextInt(1000);
//                sslSocketFactory = getSocketFactory(true, ca, cAalias);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            httpClient = HttpClientBuilder.create().setSSLSocketFactory(sslSocketFactory).build();
//            return httpClient;
//        }
//        httpClient = HttpClientBuilder.create().build();
//        return httpClient;
//    }
//
//    /**
//     * 为HTTPS请求 创建SSLSocketFactory实例、TrustManager实例
//     *
//     * @param needVerifyCa 是否需要检验CA证书(即:是否需要检验服务器的身份)
//     * @param caInputStream CA证书。(若不需要检验证书，那么此处传null即可)
//     * @param cAalias 别名。(若不需要检验证书，那么此处传null即可) 注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key。
//     * @return SSLConnectionSocketFactory实例
//     * @throws NoSuchAlgorithmException 异常信息
//     * @throws CertificateException 异常信息
//     * @throws KeyStoreException 异常信息
//     * @throws IOException 异常信息
//     * @throws KeyManagementException 异常信息
//     */
//    private static SSLConnectionSocketFactory getSocketFactory(boolean needVerifyCa, InputStream caInputStream, String cAalias)
//            throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
//            IOException, KeyManagementException {
//        X509TrustManager x509TrustManager;
//        // https请求，需要校验证书
////        if (needVerifyCa) {
//            KeyStore keyStore = getKeyStore(caInputStream, cAalias);
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//                throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
//            }
//            x509TrustManager = (X509TrustManager) trustManagers[0];
//            // 这里传TLS或SSL其实都可以的
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
//            return new SSLConnectionSocketFactory(sslContext);
////        }
//        // https请求，不作证书校验
////        x509TrustManager = new X509TrustManager() {
////            @Override
////            public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
////            }
////            @Override
////            public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
////                // 不验证
////            }
////            @Override
////            public X509Certificate[] getAcceptedIssuers() {
////                return new X509Certificate[0];
////            }
////        };
////        SSLContext sslContext = SSLContext.getInstance("TLS");
////        sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
////        return new SSLConnectionSocketFactory(sslContext);
//    }
//
//    /**
//     * 获取(密钥及证书)仓库
//     * @param caInputStream CA证书(此证书应由要访问的服务端提供)
//     * @param cAalias 别名 (注意:别名应该是唯一的， 别名不要和其他的别名一样，否者会覆盖之前的相同别名的证书信息。别名即key-value中的key。)
//     * @return 密钥、证书 仓库
//     * @throws KeyStoreException 异常信息
//     * @throws CertificateException 异常信息
//     * @throws IOException 异常信息
//     * @throws NoSuchAlgorithmException 异常信息
//     */
//    private static KeyStore getKeyStore(InputStream caInputStream, String cAalias)
//            throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
//        // 证书工厂
//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//        // 秘钥仓库
//        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//        keyStore.load(null);
//        keyStore.setCertificateEntry(cAalias, certificateFactory.generateCertificate(caInputStream));
//        return keyStore;
//    }
//
//
//    /**
//     * get请求
//     *
//     * @return
//     */
//    public  static String doGet(String url) {
//        try {
//            HttpClient client = getHttpClient(true);
//            //发送get请求
//            HttpGet request = new HttpGet(url);
//            HttpResponse response = client.execute(request);
//
//            /**请求发送成功，并得到响应**/
//            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                /**读取服务器返回过来的json字符串数据**/
//                String strResult = EntityUtils.toString(response.getEntity());
//                request.releaseConnection();
//                return strResult;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static void main(String[] args) {
//        String s = SSLClient.doGet("https://cn.bing.com/");
//        System.out.println(s);
//
//
//    }
//}
