package al.goxhaj.kafkaexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@Slf4j
public class KafkaExampleApplication {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    public static void main(String[] args) throws UnknownHostException {

        val application = new SpringApplication(KafkaExampleApplication.class);
        addDefaultProfile(application);
        val environment = application.run(args).getEnvironment();
        showApplicationEnvironmentDetails(environment);

    }

    /**
     * Set a default to use when no profile is configured.
     *
     * @param application the Spring application
     */
    public static void addDefaultProfile(@NonNull SpringApplication application) {
        application.setDefaultProperties(
                newHashMap(SPRING_PROFILE_DEFAULT, SpringProfiles.DEV));
    }

    public static void showApplicationEnvironmentDetails(@NonNull Environment environment) throws UnknownHostException {
        val message =
                MessageFormat.format(
                        "\n----------------------------------------------------------\n"
                                + "\tApplication: {0} is running! Access URLS:\n"
                                + "\tLocal:       http(s)://localhost:{1}\n"
                                + "\tExternal:    http(s)://{2}:{1}\n"
                                + "\tProfile(s):  {3}"
                                + "\n----------------------------------------------------------\n",
                        environment.getProperty("spring.application.name"),
                        environment.getProperty("server.port"),
                        InetAddress.getLocalHost().getHostAddress(),
                        getActiveProfiles(environment));
        log.info(message);
    }

    private static List<String> getActiveProfiles(Environment environment) {
        var profiles = environment.getActiveProfiles();
        if (profiles != null && profiles.length == 0) {
            profiles = environment.getDefaultProfiles();
        }
        return Arrays.asList(profiles);
    }

    private static <K, V> HashMap newHashMap(K key, V value) {
        var map = new HashMap<K, V>();
        map.put(key, value);
        return map;
    }

    final class SpringProfiles {
        public static final String TEST = "test";
        public static final String DEV = "dev";
        public static final String PROD = "prod";
    }
}
