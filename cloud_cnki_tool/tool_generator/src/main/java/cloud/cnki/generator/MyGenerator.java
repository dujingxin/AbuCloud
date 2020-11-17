package cloud.cnki.generator;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis代码生成工具
 *
 * @author JSON
 */
public class MyGenerator {
    static String diskDir = "E:\\mybatis-plus-gengerator";
    static String entityPackagePath = "net.cnki.management.modules.sms.entity";
    static String mapperPackagePath = "net.cnki.management.modules.sms.dao";
    static String mapperXmlPackagePath = "net.cnki.management.modules.sms.dao.xml";
    static String servicePackagePath = "net.cnki.management.modules.sms.service";
    static String serviceImplPackagePath = "net.cnki.management.modules.sms.service.impl";
    static String controllerPackagePath = "net.cnki.management.modules.sms.controller";

    public static void main(String[] args) {

        //执行生成代码
        excuteGenerate();
    }

    public static void excuteGenerate() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3360/sms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia" +
                "/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setEnableCache(false);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setOutputDir(diskDir);
        globalConfig.setAuthor("dujx");
        globalConfig.setOpen(true);
        // 是否覆盖已有文件
        globalConfig.setFileOverride(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setEntityName("%sEntity");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sServiceI");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        mpg.setGlobalConfig(globalConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 这个地址是生成文件的包路径
        pc.setParent(null);
        pc.setEntity(entityPackagePath);
        pc.setMapper(mapperPackagePath);
        pc.setXml(mapperXmlPackagePath);
        pc.setService(servicePackagePath);
        pc.setServiceImpl(serviceImplPackagePath);
        pc.setController(controllerPackagePath);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return diskDir + "\\" + mapperPackagePath.replaceAll("\\.", "\\\\") + "\\xml\\" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下
        // 可以自定义模板名称 只要放到目录下，名字不变 就会采用这个模版 下面这句有没有无所谓
        // 模版去github上看地址: https://github.com/baomidou/mybatis-plus/tree/3
        // .0/mybatis-plus-generator/src/main/resources/templates
        // 配置自定义输出模板，不需要时，直接设置为null就不会生成对应的模版了
        templateConfig.setEntity("templates/entity.java.vm");
        templateConfig.setMapper("templates/mapper.java.vm");
        templateConfig.setXml(null);
        templateConfig.setService("templates/service.java.vm");
        templateConfig.setServiceImpl("templates/serviceImpl.java.vm");
        templateConfig.setController("templates/controller.java.vm");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("BaseEntity路径");
        //strategy.setSuperMapperClass("IMapper路径");
        strategy.setEntityLombokModel(true);
        strategy.setInclude(scanner("表名"));
        // 设置继承的父类字段
        //strategy.setSuperEntityColumns("id");
        //strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}


