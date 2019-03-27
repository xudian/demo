package com.aladen.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Title: IPUtil
 * @Description:
 * @Author xu
 * @Date 2019/3/26 10:13
 * @Version 1.0
 * @Copyright 2018 All Rights Reserved
 */
public class IPUtil {
    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);


    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) { //"***.***.***.***".length() = 15
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }

    /**
     * 获取本机IP，获取所有
     *
     * @return
     */
    public static List<String> getAllIPs() {
        List<String> list = new ArrayList<>();
        try {
            if (isWindowsOS()) {
                list.add(InetAddress.getLocalHost().getHostAddress());
            } else {
                for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                    NetworkInterface networkInterface = en.nextElement();
                    String name = networkInterface.getName();
                    if (!name.contains("docker") && !name.contains("lo")) {
                        for (Enumeration<InetAddress> enumIpAddr = networkInterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress()) {
                                String ipaddress = inetAddress.getHostAddress();
                                if (inetAddress instanceof Inet4Address && !ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                    list.add(ipaddress);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("获取本地ip失败");
        }
        logger.info("获取本地ip成功,{}", list);
        return list;
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }



}
