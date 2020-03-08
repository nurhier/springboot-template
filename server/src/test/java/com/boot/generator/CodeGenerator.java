package com.boot.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author nurhier
 * @date 2020/3/7
 */
public class CodeGenerator {

    @Test
    public void generateCode() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig gc = getGlobalConfig(projectPath);
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(getDataSourceConfig());
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);
        mpg.setCfg(getInjectionConfig(projectPath, pc, gc));
        mpg.setTemplate(getTemplateConfig());
        mpg.setStrategy(getStrategyConfig(pc));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private StrategyConfig getStrategyConfig(PackageConfig pc) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.boot.template.common.base.model.BaseModel");
        strategy.setSuperMapperClass("com.boot.template.common.base.mapper.BaseMapper");
        strategy.setSuperServiceClass("com.boot.template.common.base.service.BaseService");
        strategy.setSuperServiceImplClass("com.boot.template.common.base.service.impl.BaseServiceImpl");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix("t_");
        strategy.setInclude("t_user");
        strategy.setControllerMappingHyphenStyle(true);
        return strategy;
    }

    private TemplateConfig getTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/consume/freemarker/entity.java");
        templateConfig.setMapper("templates/consume/freemarker/mapper.java");
        templateConfig.setXml(null);
        return templateConfig;
    }

    private InjectionConfig getInjectionConfig(String projectPath, PackageConfig pc, GlobalConfig gc) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo
                        .getMapperName() + ".xml";
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private PackageConfig getPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("user");
        pc.setParent("com.boot.template.module");
        pc.setEntity("model.po");
        return pc;
    }

    private GlobalConfig getGlobalConfig(String projectPath) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("code generator");
        gc.setOpen(false);
        gc.setEntityName("%sPo");
        gc.setServiceName("%sService");
        gc.setFileOverride(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }


    private DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        DBConfig dbConfig = DBConfig.getInstance();
        dsc.setUrl(dbConfig.getUrl());
        dsc.setDriverName(dbConfig.getDriverName());
        dsc.setUsername(dbConfig.getUserName());
        dsc.setPassword(dbConfig.getPassword());
        return dsc;
    }
}
