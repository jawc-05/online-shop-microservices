/**
 * @author jawc
 */
package br.com.jawc.online.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author jawc
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.jawc.online.shop.repository")
public class MongoConfig {

}
