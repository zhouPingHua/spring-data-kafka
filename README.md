# spring-data-kafka
springboot+kafka（不提倡这种方法，请看spring-data-kafka2）


1.主要依赖
            <!--spring-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>${springboot.version}</version>
            </dependency>

            <!--test-->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>

            <!--kafka jar-->
            <dependency>
                <groupId>org.apache.kafka</groupId>
                <artifactId>kafka-clients</artifactId>
                <version>${kafka.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.kafka</groupId>
                <artifactId>kafka_2.11</artifactId>
                <version>${kafka.version}</version>
            </dependency>
2.托管kafka client给spring管理
  @Configuration
  public class Config {

    @Bean("KafkaService")
    @Scope("singleton")
    public KafkaService getStormLocalRpcHandle(){
        return  KafkaService.create();
    }
}


