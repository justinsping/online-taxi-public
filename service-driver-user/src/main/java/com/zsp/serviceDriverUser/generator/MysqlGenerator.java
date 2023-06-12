package com.zsp.serviceDriverUser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 * */
public class MysqlGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimezone=GMT%2B8", "root", "mysql")
                .globalConfig(builder -> {
                    builder.author("张世平").fileOverride().outputDir("D:\\myWorkSpace\\back_end\\java\\mashibing\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
               .packageConfig(builder -> {
                    builder.parent("com.zsp.serviceDriverUser").pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                            "D:\\myWorkSpace\\back_end\\java\\mashibing\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\zsp\\serviceDriverUser\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("driver_car_bind_relationship");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
