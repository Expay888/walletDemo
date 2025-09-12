package com.star.wallet.utilis;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.star.wallet.domain.common.CommonReqDto;
import com.star.wallet.domain.common.CommonRespDto;
import com.star.wallet.domain.req.*;
import com.star.wallet.domain.resp.ChainOrgBalanceQueryResp;
import com.star.wallet.domain.resp.ChainWithdrawApplyResp;
import com.star.wallet.domain.resp.CheckAddresResp;
import com.star.wallet.domain.resp.CreateAddresResp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Demo {
    // 更換成對應環境地址
    private static String URL = "https://openapi.testexxx.com/api/wallet/";

//    private static String URL = "http://127.0.0.1:18003/api/wallet/";

    //租戶code
    private static String tenantCode = "CASINO1";
    //商戶code
    private static String orgCode = "CASINOORG1";

    // 商戶私鑰
    private static String orgPrivate = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL7dTGgONIfj/kpa/tS16pTT3hdWqtCdut3h7ULnLl4qExxObcku2skWt6no1MON1M2P9q3okMB1qcyoKGjKcdwupUXgL1IetJMoD//viHfhJkPYsUc14VHdM7wOeksBLUcziUFL5h0F4A5L/Jo2N7r3ynzkQmXGMNoSkUqRq5XRAgMBAAECgYAFA5vrMCPYxdBfcFFpYcPRT/lRukPzpfAjvfihYUDEZiUxQT10u4GRhrJxoRFtV32VnqSFrDJYzwKEYw7INXjNGxKh/rNNV1GPbSb7RgEUyoIIinesHHdE3pIhPXtxgktRMGUOZ0ByeLVhriZOlB0wwZU3Q8r/Opyk02LW2I4LgQJBAN0mAFzKpn8xjxMkL+VpOLEbvwR9+ttkgsZZAnSZ+7VwwVbxIy7fUvJUSwnUPms8bXUlzIjFehmNjDZCc/U5/VECQQDc8YV+cTgn1tdn0ipHWUazr9jZQuiPoBUjLWNok4o1E9qJq+7SJ6BRQZBO9M/4QV1vd163GUHJog8P2fg4x/CBAkEAwc1gufDaMlNjGBo5YylwBj3Ti4M/yO5vcJVmgnrDTIFw3dlaxOPvU/CtBgMbZGzDW8txUFcHJseWHaLk7f4Q0QJAPsUTMwABfzgHTISl5E1Vy9jql5btsVg+iRbtBhnciwXP8Yr6N5RwdIVW9UeSCiqv2+oi64vuZ9V1yAAaCC+tgQJBAMKXOjKSrgBdbH3emMU55+jbNq1kMJ6XKoIDEmndaJPBWLMA7WL6uL8uYHu5+nHgQaDetiUaKk0zMJ05Ko4vF0A=";
    //EXpay錢包公鑰
    private static String wPublic = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhUbLvQQGmkM9/XrCZn06CBy7zhjVdkXqhBmeRU2NRQtAJdP0EBMkEGV9ymF786Nd5XTM40ynu9SkSc/VCR6ErO7CF7IikHSZRwpNtyV+alxRE/ezqkTbDXkL3iNhbNG04bq6GAuW2ZOdpt7ZLVwuvYo+BSLtbaLSjkZT6mG/7bwIDAQAB";


    public static void main(String[] args) {
        // 1、创建 or 获取地址
//        createOrQueryAddress();
        // 2、提幣
//        withdraw();
        // 3、查询提币状态
//        withdrawQuery();
        // 4、檢查地址是否為平臺地址
//        checkAddress();
        // 5、查询商户钱包余额
        queryOrgBalance();

    }




    // 1、创建 or 获取地址
    public static void createOrQueryAddress(){
        // ============== 组装入参 ========================
        CreateAddresReq req = new CreateAddresReq();
        req.setBuzType("CREATEUSER");
        req.setBuzValue("t9_test_01");
        req.setChainType("EVM");

//        {"buzType":"CREATEUSER","buzValue":"t9_test_01","chainType":"TRON","orgCode":"DALAOJIA","tenantCode":"EXPAY01"}


        req.setTenantCode(tenantCode);
        req.setOrgCode(orgCode);
        req.setTimestamp(System.currentTimeMillis());

        //商户调用： 钱包端公钥加密
        String encryptData = SecureAesRsaUtil.publicEncryptData(JSON.toJSONString(req), wPublic);

        CommonReqDto reqDto = new CommonReqDto();
        reqDto.setTenantCode(tenantCode);
        reqDto.setOrgCode(orgCode);
        reqDto.setData(encryptData);


        // ================== 开始请求 =========================
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                .readTimeout(30, TimeUnit.SECONDS)    // 设置读取超时时间为30秒
                .writeTimeout(15, TimeUnit.SECONDS)   // 设置写入超时时间为15秒
                .build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(reqDto));

        Request request = new Request.Builder()
                .url(URL + "/createOrQueryAddress")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // 处理响应
            String encryptResp = response.body().string();
            // 开始解密 调用方私钥解密
            String resp = SecureAesRsaUtil.privatDecryptData(encryptResp, orgPrivate);

            CommonRespDto<CreateAddresResp> commonRespDto = JSON.parseObject(resp, CommonRespDto.class);

            System.out.println(commonRespDto);
        } catch (IOException e) {
            log.error("地址请求失败",e); // 打印异常信息
        }
    }

    public static void checkAddress(){
        // ============== 组装入参 ========================
        CheckAddresReq req = new CheckAddresReq();
        req.setAddress("0x466ee3b7c91ad66d1842a307e263b4c3cc0bcb8f");
        req.setChainType("EVM");


        req.setTenantCode(tenantCode);
        req.setOrgCode(orgCode);
        req.setTimestamp(System.currentTimeMillis());

        //商户调用： 钱包端公钥加密
        String encryptData = SecureAesRsaUtil.publicEncryptData(JSON.toJSONString(req), wPublic);

        CommonReqDto reqDto = new CommonReqDto();
        reqDto.setTenantCode(tenantCode);
        reqDto.setOrgCode(orgCode);
        reqDto.setData(encryptData);


        // ================== 开始请求 =========================
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                .readTimeout(30, TimeUnit.SECONDS)    // 设置读取超时时间为30秒
                .writeTimeout(15, TimeUnit.SECONDS)   // 设置写入超时时间为15秒
                .build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(reqDto));

        Request request = new Request.Builder()
                .url(URL + "/checkAddress")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // 处理响应
            String encryptResp = response.body().string();
            // 开始解密 调用方私钥解密
            String resp = SecureAesRsaUtil.privatDecryptData(encryptResp, orgPrivate);

            CommonRespDto<CheckAddresResp> commonRespDto = JSON.parseObject(resp, CommonRespDto.class);

            System.out.println(commonRespDto);
        } catch (IOException e) {
            log.error("地址请求失败",e); // 打印异常信息
        }
    }

    public static void withdraw(){
        // ============== 组装入参 ========================
        ChainWithdrawApplyReq req = new ChainWithdrawApplyReq();

        req.setTenantCode(tenantCode);
        req.setOrgCode(orgCode);
        req.setChainType("EVM");
        req.setProtocol("ERC20");
        req.setCoinName("EX");

        req.setOrgUserId("HB64311");  // 提幣用戶的商戶端用戶id
        req.setUserUid("edb3f81ef82246a08e1bdcc58bfbd2da");//提幣用戶通過地址創建接口 返回的對應的錢包uid
        req.setAmount(new BigDecimal("200"));


        //測試地址
        req.setToAddress("0xa90e79ac64cc38bbda91047992b5650dba34ed3c");//  提給誰，這是收款方的地址，由用戶自己複製過來


        String orderNo = RandomUtil.randomString(6);
        req.setTradeNo(orderNo);

        req.setTimestamp(System.currentTimeMillis());

        //商户调用： 钱包端公钥加密
        String encryptData = SecureAesRsaUtil.publicEncryptData(JSON.toJSONString(req), wPublic);

        CommonReqDto reqDto = new CommonReqDto();
        reqDto.setTenantCode(tenantCode);
        reqDto.setOrgCode(orgCode);
        reqDto.setData(encryptData);


        // ================== 开始请求 =========================
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                .readTimeout(30, TimeUnit.SECONDS)    // 设置读取超时时间为30秒
                .writeTimeout(15, TimeUnit.SECONDS)   // 设置写入超时时间为15秒
                .build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(reqDto));

        Request request = new Request.Builder()
                .url(URL + "/withdraw")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // 处理响应
            String encryptResp = response.body().string();
            // 开始解密 调用方私钥解密
            String resp = SecureAesRsaUtil.privatDecryptData(encryptResp, orgPrivate);

            CommonRespDto<ChainWithdrawApplyResp> commonRespDto = JSON.parseObject(resp, CommonRespDto.class);

            System.out.println(commonRespDto);
        } catch (IOException e) {
            log.error("提币申请请求失败",e); // 打印异常信息 // 打印异常信息
        }
    }

    public static void withdrawQuery(){
        // ============== 组装入参 ========================
        ChainWithdrawQueryReq req = new ChainWithdrawQueryReq();

        req.setTenantCode(tenantCode);
        req.setOrgCode(orgCode);

        req.setUserUid("e8837a4024f34678aff0c4881a3ef6e0");
        req.setOrderNo("2025071800275412203399");

        req.setTimestamp(System.currentTimeMillis());

        //商户调用： 钱包端公钥加密
        String encryptData = SecureAesRsaUtil.publicEncryptData(JSON.toJSONString(req), wPublic);

        CommonReqDto reqDto = new CommonReqDto();
        reqDto.setTenantCode(tenantCode);
        reqDto.setOrgCode(orgCode);
        reqDto.setData(encryptData);


        // ================== 开始请求 =========================
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                .readTimeout(30, TimeUnit.SECONDS)    // 设置读取超时时间为30秒
                .writeTimeout(15, TimeUnit.SECONDS)   // 设置写入超时时间为15秒
                .build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(reqDto));

        Request request = new Request.Builder()
                .url(URL + "/withdrawQuery")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // 处理响应
            String encryptResp = response.body().string();
            // 开始解密 调用方私钥解密
            String resp = SecureAesRsaUtil.privatDecryptData(encryptResp, orgPrivate);

            CommonRespDto<ChainWithdrawApplyResp> commonRespDto = JSON.parseObject(resp, CommonRespDto.class);

            System.out.println(commonRespDto);
        } catch (IOException e) {
            log.error("提币申请状态查询请求失败",e); // 打印异常信息 // 打印异常信息
        }
    }


    public static void queryOrgBalance(){
        // ============== 组装入参 ========================
        ChainOrgBalanceQueryReq req = new ChainOrgBalanceQueryReq();

        req.setTenantCode(tenantCode);
        req.setOrgCode(orgCode);

        // 不传则返回全部
        req.setChainType("EVM");
        req.setProtocol("ERC20");
        req.setCoinName("EX");

        req.setTimestamp(System.currentTimeMillis());

        //商户调用： 钱包端公钥加密
        String encryptData = SecureAesRsaUtil.publicEncryptData(JSON.toJSONString(req), wPublic);

        CommonReqDto reqDto = new CommonReqDto();
        reqDto.setTenantCode(tenantCode);
        reqDto.setOrgCode(orgCode);
        reqDto.setData(encryptData);

        // ================== 开始请求 =========================
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间为10秒
                .readTimeout(30, TimeUnit.SECONDS)    // 设置读取超时时间为30秒
                .writeTimeout(15, TimeUnit.SECONDS)   // 设置写入超时时间为15秒
                .build();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, JSON.toJSONString(reqDto));

        Request request = new Request.Builder()
                .url(URL + "/queryOrgBalance")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            // 处理响应
            String encryptResp = response.body().string();
            // 开始解密 调用方私钥解密
            String resp = SecureAesRsaUtil.privatDecryptData(encryptResp, orgPrivate);

            CommonRespDto<ChainOrgBalanceQueryResp> commonRespDto = JSON.parseObject(resp,new TypeReference<CommonRespDto<ChainOrgBalanceQueryResp>>() {});

            System.out.println(commonRespDto);
        } catch (IOException e) {
            log.error("查询商户可用余额请求失败",e); // 打印异常信息 // 打印异常信息
        }
    }


}

