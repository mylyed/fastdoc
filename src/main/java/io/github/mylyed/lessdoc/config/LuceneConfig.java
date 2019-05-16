package io.github.mylyed.lessdoc.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * lucene配置
 *
 * @author lilei
 * created at 2019/5/16
 */
@Configuration
public class LuceneConfig {


    /**
     * 分词器
     *
     * @return
     */
    @Bean
    public Analyzer analyzer() {
        Analyzer analyzer = new SmartChineseAnalyzer();
        return analyzer;
    }

    @Bean
    public Directory directory() throws IOException {
        Directory directory = FSDirectory.open(Paths.get("lucene_index"));
        return directory;
    }

}
