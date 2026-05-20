package com.itheima;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class testEncoding {
    @Test
    public void testEnod() throws UnsupportedEncodingException {
        // 第一步：URL 解码（从 %E4%BD%A0%E5%A5%BD 解码为字节）
        // 使用 ISO-8859-1 解码 URL 编码
        String decodedIso88591 = URLDecoder.decode("%E4%BD%A0%E5%A5%BD", StandardCharsets.ISO_8859_1.name());

        // 第二步：将 ISO-8859-1 字符串转换为 UTF-8 字符串
        // 关键：将字符串按 ISO-8859-1 获取字节，然后用 UTF-8 重新构造
        System.out.println(new String(decodedIso88591.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
    }

}
